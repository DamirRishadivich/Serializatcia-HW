import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Basket basket = new Basket(new String[]{"Хлеб","Молоко","Масло"}, new int[]{30,60,90});
//        ClientLog clientLog = new ClientLog();
//        while(true) {
//            System.out.println("Введите товар и количество или введите \"end\"");
//            Scanner scanner = new Scanner(System.in);
//            String input = scanner.nextLine();
//            if (input.equals("end")) {
//                basket.printCart();
//                break;
//            } else {
//                String[] parts = input.split(" ");
//                basket.addToCart(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
//                clientLog.log(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
//            }
//        }

//        clientLog.exportAsCSV(new File("log.csv"));
//        basket.saveJSON(new File("basket.json"));

        Basket basket = Basket.loadFromJSON(new File("basket.json"));
        basket.printCart();


    }
}