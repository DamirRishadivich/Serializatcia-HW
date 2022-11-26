import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Basket basket;
        String[] product = {"Хлеб","Молоко","Масло"};
        int[] price = {20,40,60};
        File file;
        ClientLog clientLog = new ClientLog();
        // Проверка первого блока load. Если enabled, то загружаем сохраненную корзину, если нет, то создаем пустую.
        ShopXML load = new ShopXML("load");
        if (load.getEnable()) {
            file = new File(load.getFileName());
            basket = (load.getFileFormat().equals("json")) ? Basket.loadFromJSON(file) : Basket.loadFromTxtFile(file);
        } else {
            basket = new Basket(product,price);
        }
        //Второй блок, пользовательский ввод, сохранение и логи

        while (true) {
            System.out.println("Введите товар и количество или введите \"end\" ");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                basket.printCart();
                break;
            } else {
                String[] parts = input.split(" ");
                basket.addToCart(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                clientLog.log(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            }
        }

        ShopXML save = new ShopXML("save");
        if (save.getEnable()) {
            file = new File(save.getFileName());
            if (save.getFileFormat().equals("json")) {
                basket.saveJSON(file);
            } else {
                basket.saveTxt(file);
            }
        }

        ShopXML logXML = new ShopXML("log");
        if (logXML.getEnable()) {
            File logFile = new File(logXML.getFileName());
            if (!logFile.exists()) {
                String[] head = {"productNum", "amount"};
                try (CSVWriter writer = new CSVWriter(new FileWriter(logFile))) {
                    writer.writeNext(head);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            clientLog.exportAsCSV(new File(logXML.getFileName()));
        }

    }
}