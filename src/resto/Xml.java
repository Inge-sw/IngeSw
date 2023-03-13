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
                    int dosaggio = (int)Double.parseDouble(ingrediente.getElementsByTagName(Costante.DOSAGGIO).item(0).getTextContent());
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

    public static ArrayList<MenuTematico> leggiMenuTematico(){

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
                    for(int j = 0; j < disponibilita.getLength(); j++){
                        Node stagioni_node = disponibilita.item(j);
                        if(stagioni_node.getNodeType() == Node.ELEMENT_NODE){
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
                menu_tematici.add(new MenuTematico(nome, stagioni, piatti));}
            } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu_tematici;
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

            Element disponibilita = doc.createElement(Costante.DISPONIBLITA);

            for(int i = 0; i < ricetta.getStagione().size(); i++){
                Stagioni stagione = ricetta.getStagione().get(i);

                Element stagione_tag = doc.createElement(Costante.STAGIONE);
                stagione_tag.appendChild(doc.createTextNode(stagione.name()));

                disponibilita.appendChild(stagione_tag);
            }

            nuovaRicetta.appendChild(disponibilita);

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

    public static void aggiungiMenuTematico(MenuTematico m){
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

            for(int i = 0; i < m.getStagione().size(); i++){
                Stagioni stagione = m.getStagione().get(i);

                Element stagione_tag = doc.createElement(Costante.STAGIONE);
                stagione_tag.appendChild(doc.createTextNode(stagione.name()));

                disponibilita.appendChild(stagione_tag);
            }
            menuTematico.appendChild(disponibilita);

            // Aggiungi i piatti del menuTematico
            Element piatti = doc.createElement(Costante.PIATTI);
            menuTematico.appendChild(piatti);

            for (int i = 0; i < m.getPiatti().size(); i++){
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
}
