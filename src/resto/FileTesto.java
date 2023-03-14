package resto;

import ristorante.Bevanda;
import ristorante.GeneriExtra;
import ristorante.Gestore;
import ristorante.Prodotto;

import java.io.*;
import java.util.HashMap;

public class FileTesto {

    public static HashMap<Bevanda, Double> leggiBevande(String filename){
        HashMap<Bevanda, Double> bevandeMap = new HashMap<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                bevandeMap.put(new Bevanda(line), 0.0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bevandeMap;
    }

    public static HashMap<GeneriExtra, Double> leggiGeneri(String filename){
        HashMap<GeneriExtra, Double> generiMap = new HashMap<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                generiMap.put(new GeneriExtra(line), 0.0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generiMap;
    }

    public static void aggiungiBevande(Bevanda bevanda){
        String filePath = "Bevande.txt";

        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.print("\n" + bevanda.getNome().toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void aggiungiGenere(GeneriExtra genere){
        String filePath = "Generi.txt";

        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.print("\n" + genere.getNome().toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
