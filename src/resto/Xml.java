package resto;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ristorante.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class Xml {

    public static Ricettario leggiRicettario(){

        ArrayList<Ricetta> ricette = new ArrayList<>();

        try {

            // Leggi il file XML
            File inputFile = new File(Costante.XML_RICETTARIO);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Trova tutte le ricette nel documento
            NodeList ricetteList = doc.getElementsByTagName(Costante.RICETTA);

            // Scandisci ogni ricetta e stampa alcune informazioni
            for (int i = 0; i < ricetteList.getLength(); i++) {
                Element ricetta = (Element) ricetteList.item(i);
                String nomeRicetta = ricetta.getElementsByTagName(Costante.NOME_RICETTA).item(0).getTextContent();
                int porzioni = Integer.parseInt(ricetta.getElementsByTagName(Costante.PORZIONE).item(0).getTextContent());
                int tempo = Integer.parseInt(ricetta.getElementsByTagName(Costante.TEMPO).item(0).getTextContent());
                Stagioni stagione = Stagioni.getStagione(ricetta.getElementsByTagName(Costante.STAGIONE).item(0).getTextContent());
                NodeList ingredientiList = ricetta.getElementsByTagName(Costante.INGREDIENTE);
                ArrayList<Ingrediente> ingredienti = new ArrayList<>();
                for (int j = 0; j < ingredientiList.getLength(); j++) {
                    Element ingrediente = (Element) ingredientiList.item(j);
                    String nome = ingrediente.getElementsByTagName(Costante.NOME).item(0).getTextContent();
                    int dosaggio = (int)Double.parseDouble(ingrediente.getElementsByTagName(Costante.DOSAGGIO).item(0).getTextContent());
                    ingredienti.add(new Ingrediente(nome, dosaggio));
                }
                ricette.add(new Ricetta(nomeRicetta, stagione, porzioni, tempo, ingredienti));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Ricettario(ricette);
    }

    public static void leggiMenuTematico(){

        ArrayList<Piatto> piatti = new ArrayList<>();

        try {
            File inputFile = new File(Costante.XML_MENU);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList menu_tematici = doc.getElementsByTagName(Costante.MENU_TEMATICO);

            for (int i = 0; i < menu_tematici.getLength(); i++) {

                Node menu_tematico_node = menu_tematici.item(i);

                if (menu_tematico_node.getNodeType() == Node.ELEMENT_NODE) {

                    Node nome_node = ((org.w3c.dom.Element) menu_tematico_node).getElementsByTagName(Costante.NOME).item(0);
                    System.out.println("Nome del menu tematico: " + nome_node.getTextContent());

                    Node stagione_node = ((org.w3c.dom.Element) menu_tematico_node).getElementsByTagName(Costante.STAGIONE).item(0);
                    System.out.println("NStagione del menu tematico: " + stagione_node.getTextContent());

                    NodeList piatti_list = ((org.w3c.dom.Element) menu_tematico_node).getElementsByTagName(Costante.PIATTI);

                    for (int j = 0; j < piatti_list.getLength(); j++) {
                        Node piatti_node = piatti_list.item(j);
                        if (piatti_node.getNodeType() == Node.ELEMENT_NODE) {
                            NodeList piatto_list = ((org.w3c.dom.Element) piatti_node).getElementsByTagName(Costante.PIATTO);
                            System.out.println("Piatti: ");
                            for (int k = 0; k < piatto_list.getLength(); k++) {
                                Node piatto_node = piatto_list.item(k);
                                if (piatto_node.getNodeType() == Node.ELEMENT_NODE) {
                                    System.out.println(piatto_node.getTextContent());
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

    public static void aggiungiRicetta(Ricetta ricetta){
        try {
            // Carica il documento XML
            File inputFile = new File(Costante.XML_RICETTARIO);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Crea la nuova ricetta
            Element nuovaRicetta = doc.createElement(Costante.RICETTA);

            Element nomeRicetta = doc.createElement(Costante.NOME_RICETTA);
            nomeRicetta.appendChild(doc.createTextNode(ricetta.getNome()));
            nuovaRicetta.appendChild(nomeRicetta);

            Element caricoLavoroPerPorzione = doc.createElement(Costante.CARICO_LAVORO_PER_PORZIONE);
            caricoLavoroPerPorzione.appendChild(doc.createTextNode(String.valueOf(ricetta.getCarico_lavoro_porzione())));
            nuovaRicetta.appendChild(caricoLavoroPerPorzione);

            Element tempo = doc.createElement(Costante.TEMPO);
            tempo.appendChild(doc.createTextNode(String.valueOf(ricetta.getTempo_preparazione())));
            nuovaRicetta.appendChild(tempo);

            Element porzione = doc.createElement(Costante.PORZIONE);
            porzione.appendChild(doc.createTextNode(String.valueOf(ricetta.getPorzioni())));
            nuovaRicetta.appendChild(porzione);

            Element stagione = doc.createElement(Costante.STAGIONE);
            stagione.appendChild(doc.createTextNode(ricetta.getStagione().toString()));
            nuovaRicetta.appendChild(stagione);

            Element ingredienti = doc.createElement(Costante.INGREDIENTI);

            for (int i = 0; i < ricetta.getIngredienti().size(); i++){

                Ingrediente ingrediente = ricetta.getIngredienti().get(i);

                Element ingrediente_ele = doc.createElement(Costante.INGREDIENTE);
                Element nome_ingrediente = doc.createElement(Costante.NOME);
                nome_ingrediente.appendChild(doc.createTextNode(ingrediente.getNome()));
                ingrediente_ele.appendChild(nome_ingrediente);
                Element dosaggio = doc.createElement(Costante.DOSAGGIO);
                dosaggio.appendChild(doc.createTextNode(String.valueOf(ingrediente.getQuantita())));
                ingrediente_ele.appendChild(dosaggio);
                Element unita = doc.createElement(Costante.UNITA);
                unita.appendChild(doc.createTextNode(Misura.GRAMMI.toString()));

                ingredienti.appendChild(ingrediente_ele);
            }

            nuovaRicetta.appendChild(ingredienti);

            // Aggiunge la nuova ricetta alla radice del documento
            Node radice = doc.getDocumentElement();
            radice.appendChild(nuovaRicetta);

            // Scrive il documento XML modificato su un file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Costante.XML_RICETTARIO));
            transformer.transform(source, result);

            System.out.println("Nuova ricetta aggiunta con successo!");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void aggiungiMenuTematico(){
        try {
            String filepath = Costante.XML_MENU;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Prendi il nodo radice
            Element root = doc.getDocumentElement();

            // Crea un nuovo menuTematico
            Element menuTematico = doc.createElement(Costante.MENU_TEMATICO);
            root.appendChild(menuTematico);

            // Aggiungi il nome del menuTematico
            Element nome = doc.createElement(Costante.NOME);
            nome.appendChild(doc.createTextNode("Vegetariano"));
            menuTematico.appendChild(nome);

            // Aggiungi la stagione del menuTematico
            Element stagione = doc.createElement(Costante.STAGIONE);
            stagione.appendChild(doc.createTextNode("Autunno"));
            menuTematico.appendChild(stagione);

            // Aggiungi i piatti del menuTematico
            Element piatti = doc.createElement(Costante.PIATTI);
            menuTematico.appendChild(piatti);

            Element piatto1 = doc.createElement("Piatto");
            piatto1.appendChild(doc.createTextNode("Lasagne"));
            piatti.appendChild(piatto1);

            Element piatto2 = doc.createElement("Piatto");
            piatto2.appendChild(doc.createTextNode("Parmigiana"));
            piatti.appendChild(piatto2);

            // Scrivi le modifiche sul file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.out.println("Elemento aggiunto con successo!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
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
