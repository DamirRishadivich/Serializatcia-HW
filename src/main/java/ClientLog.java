import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

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
