package resto;

import ristorante.Bevanda;
import ristorante.GeneriExtra;
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

    public static void aggiungiProdotto(Prodotto  prod, String filename){

        try (FileWriter fw = new FileWriter(filename, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.print("\n" + prod.getNome().toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
