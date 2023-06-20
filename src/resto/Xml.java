package resto;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import prenotazione.Prenotazione;
import ristorante.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Xml {

    public static Ricettario leggiRicettario() {

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

                ArrayList<Stagioni> stagioni = new ArrayList<>();
                NodeList listaStagioni = ricetta.getElementsByTagName(Costante.DISPONIBLITA).item(0).getChildNodes();
                for (int j = 0; j < listaStagioni.getLength(); j++) {
                    if (listaStagioni.item(j).getNodeType() == Element.ELEMENT_NODE) {
                        String stagione = listaStagioni.item(j).getTextContent();
                        stagioni.add(Stagioni.getStagione(stagione));
                    }
                }

                NodeList ingredientiList = ricetta.getElementsByTagName(Costante.INGREDIENTE);
                ArrayList<Ingrediente> ingredienti = new ArrayList<>();
                for (int j = 0; j < ingredientiList.getLength(); j++) {
                    Element ingrediente = (Element) ingredientiList.item(j);
                    String nome = ingrediente.getElementsByTagName(Costante.NOME).item(0).getTextContent();
                    int dosaggio = (int) Double.parseDouble(ingrediente.getElementsByTagName(Costante.DOSAGGIO).item(0).getTextContent());
                    ingredienti.add(new Ingrediente(nome, dosaggio));
                }
                ricette.add(new Ricetta(nomeRicetta, stagioni, porzioni, tempo, ingredienti));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Ricettario(ricette);
    }

    public static ArrayList<MenuTematico> leggiMenuTematico() {

        ArrayList<Piatto> piatti = null;
        ArrayList<Stagioni> stagioni = null;
        ArrayList<MenuTematico> menu_tematici = new ArrayList<>();
        String nome = null;

        try {
            File inputFile = new File(Costante.XML_MENU);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList menu_tematici_ele = doc.getElementsByTagName(Costante.MENU_TEMATICO);

            for (int i = 0; i < menu_tematici_ele.getLength(); i++) {

                Node menu_tematico_node = menu_tematici_ele.item(i);

                if (menu_tematico_node.getNodeType() == Node.ELEMENT_NODE) {

                    Node nome_node = ((org.w3c.dom.Element) menu_tematico_node).getElementsByTagName(Costante.NOME).item(0);
                    nome = nome_node.getTextContent();

                    NodeList disponibilita = ((org.w3c.dom.Element) menu_tematico_node).getElementsByTagName(Costante.DISPONIBLITA);
                    stagioni = new ArrayList<>();
                    for (int j = 0; j < disponibilita.getLength(); j++) {
                        Node stagioni_node = disponibilita.item(j);
                        if (stagioni_node.getNodeType() == Node.ELEMENT_NODE) {
                            NodeList stagione_list = ((org.w3c.dom.Element) stagioni_node).getElementsByTagName(Costante.STAGIONE);
                            for (int k = 0; k < stagione_list.getLength(); k++) {
                                Node stagione_node = stagione_list.item(k);
                                if (stagione_node.getNodeType() == Node.ELEMENT_NODE) {
                                    stagioni.add(Stagioni.getStagione(stagione_node.getTextContent()));
                                }
                            }
                        }
                    }

                    NodeList piatti_list = ((org.w3c.dom.Element) menu_tematico_node).getElementsByTagName(Costante.PIATTI);
                    piatti = new ArrayList<>();
                    for (int j = 0; j < piatti_list.getLength(); j++) {
                        Node piatti_node = piatti_list.item(j);
                        if (piatti_node.getNodeType() == Node.ELEMENT_NODE) {
                            NodeList piatto_list = ((org.w3c.dom.Element) piatti_node).getElementsByTagName(Costante.PIATTO);
                            for (int k = 0; k < piatto_list.getLength(); k++) {
                                Node piatto_node = piatto_list.item(k);
                                if (piatto_node.getNodeType() == Node.ELEMENT_NODE) {
                                    piatti.add(new Piatto(Ricettario.getRicettaByNome(piatto_node.getTextContent())));
                                }
                            }
                        }
                    }
                }
                menu_tematici.add(new MenuTematico(nome, stagioni, piatti));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return menu_tematici;
    }

    public static ArrayList<Prenotazione> leggiPrenotazioni() {

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

        try {
            // Crea un'istanza di DocumentBuilderFactory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Crea un'istanza di DocumentBuilder
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parsa il file XML e lo converte in un oggetto Document
            Document doc = dBuilder.parse("Prenotazioni.xml");

            // Normalizza il documento per gestire eventuali spazi bianchi
            doc.getDocumentElement().normalize();

            // Legge tutti gli elementi <prenotazione>
            NodeList nodeList = doc.getElementsByTagName("prenotazione");

            int anno, mese, giorno, numero_coperti;

            // Scorre tutti gli elementi <prenotazione>
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Legge la data
                    anno = Integer.parseInt(element.getElementsByTagName("anno").item(0).getTextContent());
                    mese = Integer.parseInt(element.getElementsByTagName("mese").item(0).getTextContent());
                    giorno = Integer.parseInt(element.getElementsByTagName("giorno").item(0).getTextContent());

                    LocalDate data = LocalDate.of(anno, mese, giorno);

                    //String data = giorno + "/" + mese + "/" + anno;

                    // Legge il numero di coperti
                    numero_coperti = Integer.parseInt(element.getElementsByTagName("numeroCoperti").item(0).getTextContent());

                    // Legge tutti i piatti ordinati
                    NodeList piatti_ordinati = element.getElementsByTagName("piatto");

                    HashMap<Prenotabile, Integer> lista_piatti = new HashMap<>();

                    for (int j = 0; j < piatti_ordinati.getLength(); j++) {
                        Node piatto = piatti_ordinati.item(j);
                        int numero_piatto = Integer.parseInt(((Element) piatto).getAttribute("numero"));
                        String tipo = ((Element) piatto).getAttribute("tipo");
                        String nome = piatto.getTextContent();

                        if (tipo.equalsIgnoreCase("ricetta")) {
                            Ricettario ricettario = Xml.leggiRicettario();
                            lista_piatti.put(new Piatto(Ricettario.getRicettaByNome(nome)), numero_piatto);
                        } else {
                            ArrayList<MenuTematico> menu_tematici = Xml.leggiMenuTematico();
                            for (MenuTematico m : menu_tematici) {
                                if (m.getNome().equalsIgnoreCase(nome)) {
                                    lista_piatti.put(m, numero_piatto);
                                    break;
                                }
                            }
                        }
                    }
                    Prenotazione p = new Prenotazione(numero_coperti, data, lista_piatti);
                    prenotazioni.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prenotazioni;
    }

    public static HashMap<String, Integer> leggiMerci(){
        HashMap<String, Integer> merci = new HashMap<>();
        try {
            // Carica il file XML
            File file = new File(Costante.XML_MAGAZZINO);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            // Ottieni l'elemento radice
            Element radice = document.getDocumentElement();

            // Itera sui figli dell'elemento radice
            NodeList merceList = radice.getElementsByTagName("merce");
            for (int i = 0; i < merceList.getLength(); i++) {
                Element merceElement = (Element) merceList.item(i);
                String quantita = merceElement.getAttribute("quantita");
                String unita = merceElement.getAttribute("unita");
                String nome_merce = merceElement.getTextContent();

                merci.put(nome_merce, Integer.valueOf(quantita));
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return merci;
    }

    public static Utente leggiUtente() {

        String username = "";
        String password = "";
        String ruolo = "";

        try {
            File inputFile = new File(Costante.XML_UTENTI);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList utentiList = doc.getElementsByTagName("utente");

            for (int i = 0; i < utentiList.getLength(); i++) {
                Node utenteNode = utentiList.item(i);

                if (utenteNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList infoUtenteList = utenteNode.getChildNodes();


                    for (int j = 0; j < infoUtenteList.getLength(); j++) {
                        Node infoUtenteNode = infoUtenteList.item(j);

                        if (infoUtenteNode.getNodeType() == Node.ELEMENT_NODE) {
                            String tagName = infoUtenteNode.getNodeName();

                            if (tagName.equals("username")) {
                                username = infoUtenteNode.getTextContent();
                            } else if (tagName.equals("password")) {
                                password = infoUtenteNode.getTextContent();
                            } else if (tagName.equals("ruolo")) {
                                ruolo = infoUtenteNode.getTextContent();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Utente(username, password, RuoloUtente.getRuolo(ruolo));
    }

    public static void aggiungiUtente(Utente utente) {
        try {
            File inputFile = new File(Costante.XML_UTENTI);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Creazione del nuovo utente
            Element newUtente = doc.createElement("utente");
            Element newUsername = doc.createElement("username");
            newUsername.setTextContent(utente.getUsername());
            Element newPassword = doc.createElement("password");
            newPassword.setTextContent(utente.getPassword());
            Element newRuolo = doc.createElement("ruolo");
            newRuolo.setTextContent(utente.getRuolo().toString());

            newUtente.appendChild(newUsername);
            newUtente.appendChild(newPassword);
            newUtente.appendChild(newRuolo);

            // Aggiunta del nuovo utente alla lista di utenti
            Element root = doc.getDocumentElement();
            root.appendChild(newUtente);

            // Salva le modifiche nel file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Costante.XML_UTENTI));
            transformer.transform(source, result);

            System.out.println("Utente aggiunto correttamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void aggiungiRicetta(Ricetta ricetta) {
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

            Element disponibilita = doc.createElement(Costante.DISPONIBLITA);

            for (int i = 0; i < ricetta.getStagione().size(); i++) {
                Stagioni stagione = ricetta.getStagione().get(i);

                Element stagione_tag = doc.createElement(Costante.STAGIONE);
                stagione_tag.appendChild(doc.createTextNode(stagione.name()));

                disponibilita.appendChild(stagione_tag);
            }

            nuovaRicetta.appendChild(disponibilita);

            Element ingredienti = doc.createElement(Costante.INGREDIENTI);
            for (int i = 0; i < ricetta.getIngredienti().size(); i++) {

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

    public static void aggiungiMenuTematico(MenuTematico m) {
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
            nome.appendChild(doc.createTextNode(m.getNome()));
            menuTematico.appendChild(nome);

            // Aggiungi la stagione del menuTematico
            Element disponibilita = doc.createElement(Costante.DISPONIBLITA);

            for (int i = 0; i < m.getStagione().size(); i++) {
                Stagioni stagione = m.getStagione().get(i);

                Element stagione_tag = doc.createElement(Costante.STAGIONE);
                stagione_tag.appendChild(doc.createTextNode(stagione.name()));

                disponibilita.appendChild(stagione_tag);
            }
            menuTematico.appendChild(disponibilita);

            // Aggiungi i piatti del menuTematico
            Element piatti = doc.createElement(Costante.PIATTI);
            menuTematico.appendChild(piatti);

            for (int i = 0; i < m.getPiatti().size(); i++) {
                Element piatto = doc.createElement(Costante.PIATTO);
                piatto.appendChild(doc.createTextNode(m.getPiatti().get(i).getNome()));
                piatti.appendChild(piatto);
            }

            // Scrivi le modifiche sul file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.out.println("Elemento aggiunto con successo!");

        } catch (Exception pce) {
            pce.printStackTrace();
        }
    }

    public static void aggiungiPrenotazione(Prenotazione prenotazione) {

        String filePath = Costante.XML_PRENOTAZIONI;
        String nuovaPrenotazioneAnno = String.valueOf(prenotazione.getData().getYear());
        String nuovaPrenotazioneMese = String.valueOf(prenotazione.getData().getMonthValue());
        String nuovaPrenotazioneGiorno = String.valueOf(prenotazione.getData().getDayOfMonth());
        String nuovaPrenotazioneNumeroCoperti = String.valueOf(prenotazione.getNum_coperti());
        List<String> nuovaPrenotazionePiatti = new ArrayList<>();
        for (Prenotabile prenotabile : prenotazione.getLista_prenotazioni_piatti().keySet()) {
            nuovaPrenotazionePiatti.add(prenotabile.toString());
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));

            Element nuovaPrenotazione = doc.createElement("prenotazione");

            // Creazione elemento data
            Element data = doc.createElement("data");
            Element anno = doc.createElement("anno");
            anno.setTextContent(nuovaPrenotazioneAnno);
            Element mese = doc.createElement("mese");
            mese.setTextContent(nuovaPrenotazioneMese);
            Element giorno = doc.createElement("giorno");
            giorno.setTextContent(nuovaPrenotazioneGiorno);
            data.appendChild(anno);
            data.appendChild(mese);
            data.appendChild(giorno);
            nuovaPrenotazione.appendChild(data);

            // Creazione elemento numeroCoperti
            Element numeroCoperti = doc.createElement("numeroCoperti");
            numeroCoperti.setTextContent(nuovaPrenotazioneNumeroCoperti);
            nuovaPrenotazione.appendChild(numeroCoperti);

            // Creazione elemento piattiOrdinati
            Element piattiOrdinati = doc.createElement("piattiOrdinati");
            for (int i = 0; i < nuovaPrenotazionePiatti.size(); i++) {
                Element piatto = doc.createElement("piatto");
                piatto.setTextContent(nuovaPrenotazionePiatti.get(i));
                piatto.setAttribute("numero", String.valueOf(i + 1));
                piattiOrdinati.appendChild(piatto);
            }
            nuovaPrenotazione.appendChild(piattiOrdinati);

            // Aggiunta della nuova prenotazione al documento
            doc.getDocumentElement().appendChild(nuovaPrenotazione);

            // Scrittura del documento su file

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Costante.XML_PRENOTAZIONI));
            transformer.transform(source, result);

            System.out.println("Nuova prenotazione aggiunta con successo!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void eliminaPrenotazione(Prenotazione da_eliminare) {
        String xmlFilePath = Costante.XML_PRENOTAZIONI;
        String annoDaEliminare = String.valueOf(da_eliminare.getData().getYear());
        String meseDaEliminare = String.valueOf(da_eliminare.getData().getMonthValue());
        String giornoDaEliminare = String.valueOf(da_eliminare.getData().getDayOfMonth());

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));

            // Trova la prenotazione da eliminare
            NodeList prenotazioni = document.getElementsByTagName("prenotazione");
            for (int i = 0; i < prenotazioni.getLength(); i++) {
                Element prenotazione = (Element) prenotazioni.item(i);
                Element data = (Element) prenotazione.getElementsByTagName("data").item(0);
                String anno = data.getElementsByTagName("anno").item(0).getTextContent();
                String mese = data.getElementsByTagName("mese").item(0).getTextContent();
                String giorno = data.getElementsByTagName("giorno").item(0).getTextContent();
                if (anno.equals(annoDaEliminare) && mese.equals(meseDaEliminare) && giorno.equals(giornoDaEliminare)) {
                    Node parent = prenotazione.getParentNode();
                    parent.removeChild(prenotazione);
                    break;
                }
            }

            // Salva il documento XML modificato
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(Costante.XML_PRENOTAZIONI));
            transformer.transform(source, result);

            System.out.println("Prenotazione eliminata con successo.");
        } catch (ParserConfigurationException | TransformerException | org.xml.sax.SAXException |
                 java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
