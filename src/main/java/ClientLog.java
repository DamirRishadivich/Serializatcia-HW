import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    protected List<String> list = new ArrayList<>();

    public void log(int productNum, int amount) {
        list.add(productNum + " " + amount);
    }

    public void exportAsCSV(File txtFile) {
        try(CSVWriter writer = new CSVWriter(new FileWriter(txtFile))) {
            for (int i = 0; i < list.size(); i++) {
                String[] x = list.get(i).split(" ");
                writer.writeNext(x);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
