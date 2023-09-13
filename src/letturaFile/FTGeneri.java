package letturaFile;

import resto.Costante;
import ristorante.GeneriExtra;
import ristorante.Prodotto;

import java.io.*;
import java.util.HashMap;

public class FTGeneri implements FileTestoRepository{
    public HashMap<Prodotto, Double> leggi(){
        HashMap<Prodotto, Double> generiMap = new HashMap<>();

        String filename = Costante.FILE_GENERI;

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

    public void aggiungi(Prodotto prod, String filename){

        try (FileWriter fw = new FileWriter(filename, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.print("\n" + prod.getNome().toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
