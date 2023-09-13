package letturaFile;

import resto.Costante;
import ristorante.Bevanda;
import ristorante.Prodotto;

import java.io.*;
import java.util.HashMap;

public class FTBevande implements FileTestoRepository{

    public HashMap<Prodotto, Double> leggi(){
        HashMap<Prodotto, Double> bevandeMap = new HashMap<>();

        String filename = Costante.FILE_BEVANDE;

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

    public void aggiungi(Prodotto prod, String filename){

        try (FileWriter fw = new FileWriter(filename, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.print("\n" + prod.getNome().toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
