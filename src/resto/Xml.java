package resto;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ristorante.Ingrediente;
import ristorante.Ricetta;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;

public class Xml {

    public static ArrayList<Ricetta> leggiRicettario(){

        ArrayList<Ricetta> ricette = new ArrayList<>();

        try {

            // Leggi il file XML
            File inputFile = new File(Costante.XML_RICETTARIO);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Trova tutte le ricette nel documento
            NodeList ricetteList = doc.getElementsByTagName("ricetta");

            // Scandisci ogni ricetta e stampa alcune informazioni
            for (int i = 0; i < ricetteList.getLength(); i++) {
                Element ricetta = (Element) ricetteList.item(i);
                String nomeRicetta = ricetta.getElementsByTagName("nomeRicetta").item(0).getTextContent();
                int porzioni = Integer.parseInt(ricetta.getElementsByTagName("porzione").item(0).getTextContent());
                int tempo = Integer.parseInt(ricetta.getElementsByTagName("tempo").item(0).getTextContent());
                Stagioni stagione = Stagioni.getStagione(ricetta.getElementsByTagName("stagione").item(0).getTextContent());
                System.out.println("Ricetta: " + nomeRicetta);
                System.out.println("Porzioni: " + porzioni);
                System.out.println("Tempo di preparazione: " + tempo + " minuti");
                System.out.println("Ingredienti:");
                NodeList ingredientiList = ricetta.getElementsByTagName("ingrediente");
                ArrayList<Ingrediente> ingredienti = new ArrayList<>();
                for (int j = 0; j < ingredientiList.getLength(); j++) {
                    Element ingrediente = (Element) ingredientiList.item(j);
                    String nome = ingrediente.getElementsByTagName("nome").item(0).getTextContent();
                    int dosaggio = Integer.parseInt(ingrediente.getElementsByTagName("dosaggio").item(0).getTextContent());
                    String unita = ingrediente.getElementsByTagName("unità").item(0).getTextContent();
                    System.out.println("- " + nome + ": " + dosaggio + " " + unita);
                    ingredienti.add(new Ingrediente(nome, dosaggio));
                }
                ricette.add(new Ricetta(nomeRicetta, stagione, porzioni, tempo, ingredienti));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ricette;
    }

    public static void leggiMenuTematico(){
        try {
            File inputFile = new File(Costante.XML_MENU);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList menuTematici = doc.getElementsByTagName("menuTematico");

            for (int i = 0; i < menuTematici.getLength(); i++) {

                Node menuTematicoNode = menuTematici.item(i);

                if (menuTematicoNode.getNodeType() == Node.ELEMENT_NODE) {

                    Node nomeNode = ((org.w3c.dom.Element) menuTematicoNode).getElementsByTagName("nome").item(0);
                    System.out.println("Nome del menu tematico: " + nomeNode.getTextContent());

                    NodeList piattiList = ((org.w3c.dom.Element) menuTematicoNode).getElementsByTagName("Piatti");

                    for (int j = 0; j < piattiList.getLength(); j++) {
                        Node piattiNode = piattiList.item(j);
                        if (piattiNode.getNodeType() == Node.ELEMENT_NODE) {
                            NodeList piattoList = ((org.w3c.dom.Element) piattiNode).getElementsByTagName("Piatto");
                            System.out.println("Piatti: ");
                            for (int k = 0; k < piattoList.getLength(); k++) {
                                Node piattoNode = piattoList.item(k);
                                if (piattoNode.getNodeType() == Node.ELEMENT_NODE) {
                                    System.out.println(piattoNode.getTextContent());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*
    public static void scriviPersone(String nome_file, ArrayList<Persona> persone, ArrayList<codiceFiscale> codici_invalidi, ArrayList<codiceFiscale> codici_spaiati) {

        XMLOutputFactory xmlof;
        XMLStreamWriter xmlw;

        try { // blocco try per raccogliere eccezioni
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(nome_file), Costante.ENCODING);
            xmlw.writeStartDocument(Costante.ENCODING, Costante.VERSION);
            xmlw.writeStartElement(Costante.OUTPUT); // scrittura del tag radice output

            xmlw.writeStartElement(Costante.PERSONE);
            xmlw.writeAttribute(Costante.NUMERO, Integer.toString(persone.size()));

            for (int i = 0; i < persone.size(); i++) {
                xmlw.writeStartElement(Costante.PERSONA); // apertura del tag <persona>
                xmlw.writeAttribute(Costante.ID, Integer.toString(i)); // attributo id
                scriviTag(xmlw, Costante.NOME, persone.get(i).getNome()); // scrittura del tag <nome>
                scriviTag(xmlw, Costante.COGNOME, persone.get(i).getCognome()); // scrittura del tag <cognome>
                scriviTag(xmlw, Costante.SESSO, persone.get(i).getSesso()); // scrittura del tag <sesso>
                scriviTag(xmlw, Costante.COMUNE_NASCITA, persone.get(i).getComune_nascita()); // scrittura del tag <comune_nascita>
                scriviTag(xmlw, Costante.DATA_NASCITA, persone.get(i).getData_nascita()); // scrittura del tag <data_nascita>
                scriviTag(xmlw, Costante.CODICE_FISCALE, persone.get(i).getCodice_fiscale()); // scrittura del tag <codice_fiscale>
                xmlw.writeEndElement(); // chiusura di </persona>
            }
            xmlw.writeEndElement(); // chiusura di </persone>

            xmlw.writeStartElement(Costante.CODICI); // scrittura del tag <codici>
            stampaCodici(xmlw, Costante.INVALIDI, codici_invalidi);
            stampaCodici(xmlw, Costante.SPAIATI, codici_spaiati);
            xmlw.writeEndElement(); // chiusura di </codici>

            xmlw.writeEndElement(); // chiusura di </output>
            xmlw.writeEndDocument(); // scrittura della fine del documento

            xmlw.flush(); // svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate
        } catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println(Costante.ERRORE_SCRITTURA);
            System.out.println(e.getMessage());
        }
    }

    //stampa CF
    private static void stampaCodici(XMLStreamWriter xmlw, String tag, ArrayList<codiceFiscale> codici) throws XMLStreamException {

        xmlw.writeStartElement(tag); // scrittura del tag <...>
        xmlw.writeAttribute(Costante.NUMERO, Integer.toString(codici.size())); // attributo numero

        for (int i = 0; i < codici.size(); i++)   // scrittura tutti CF
            scriviTag(xmlw, Costante.CODICE, codici.get(i).toString());

        xmlw.writeEndElement(); // chiusura di </...>
    }

    //scrive un tag completo
    private static void scriviTag(XMLStreamWriter xmlw, String tag, String valore) throws XMLStreamException {
        xmlw.writeStartElement(tag);
        xmlw.writeCharacters(valore);
        xmlw.writeEndElement();
    }

    //prende il comune di nascita della persona e restituisce il relativo codice se trovato nel file xml
    public static String leggiComune(String nome_file, String comune) {

        XMLInputFactory xmlif;
        XMLStreamReader xmlr;

        String codice = "";

        boolean trovato = false;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nome_file, new FileInputStream(nome_file));

            while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
                if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) { // interessa solo il nome dei comuni
                    if (xmlr.getText().trim().length() > 0) { // controlla se il testo non contiene solo spazi
                        if (trovato) {
                            codice = xmlr.getText();
                            return codice;
                        }
                        if (xmlr.getText().equals(comune)) trovato = true;
                    }
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.out.println(Costante.ERRORE_LETTURA);
            System.out.println(e.getMessage());
        }
        return codice;
    }

    //legge xml e riempie un ArrayList di codici fiscali se questi risultano corretti
    public static void leggiCodiceFiscale(String nome_file, ArrayList<codiceFiscale> codici_corretti, ArrayList<codiceFiscale> codici_sbagliati) {

        XMLInputFactory xmlif;
        XMLStreamReader xmlr;

        String cod_fis;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nome_file, new FileInputStream(nome_file));

            while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
                if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
                    if (xmlr.getText().trim().length() > 0) { // controlla se il testo non contiene solo spazi
                        cod_fis = xmlr.getText();
                        if (new codiceFiscale(cod_fis).validitaCodice()) // crea codice fiscale e verifica se e' corretto
                            codici_corretti.add(new codiceFiscale(cod_fis)); // se corretto lo aggiunge all'ArrayList CF corretti
                        else
                            codici_sbagliati.add(new codiceFiscale(cod_fis)); // se sbagliato lo aggiunge all'ArrayList CF sbagliati
                    }
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.out.println(Costante.ERRORE_LETTURA);
            System.out.println(e.getMessage());
        }
    }

    public static void formatXMLFile(String file) throws Exception { //prende un xml non formattato e lo formatta

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new InputStreamReader(new FileInputStream(file))));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, Costante.METODO_FORMATTAZIONE); //tipo di file generato
        transformer.setOutputProperty(OutputKeys.INDENT, Costante.INDENT_FORMATTAZIONE); //indentazione
        transformer.setOutputProperty(Costante.HTTPS_FORMATTAZIONE, Costante.LIVELLO_INDENTAZIONE); //formattazione
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, Costante.DICHIARAZIONE_FORMATTAZIONE);
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(file));
        transformer.transform(source, result);
    }*/
}
