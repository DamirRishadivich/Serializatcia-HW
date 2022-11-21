import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Arrays;

public class Basket {
    protected String[] product;
    protected int[] price;
    protected int[] basketCount;


    public String[] getProduct() {
        return product;
    }

    public int[] getPrice() {
        return price;
    }

    public int[] getBasketCount() {
        return basketCount;
    }

    public Basket(String[] product, int[] price) {
        this.product = product;
        this.price = price;
        basketCount = new int[product.length];
    }

    public void addToCart(int productNum, int amount) {
        basketCount[productNum - 1] += amount;
    }

    public void printCart() {
        int sum = 0;
        for (int i = 0; i < product.length; i++) {
            sum += price[i] * basketCount[i];
        }
        System.out.println("Ваша корзина: ");
        for (int i = 0; i < product.length; i++) {
            System.out.println(product[i] + " " + price[i] + " руб/шт. " + "Количество: " + basketCount[i] + " шт. Стоимость: " + basketCount[i] * price[i]);
        }
        System.out.println("Общая стоимость корзины: " + sum);
    }

    public void saveTxt(File textFile) {
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (String product : product) {
                out.print(product + " ");
            }
            out.println();
            for (int price : price) {
                out.print(price + " ");
            }
            out.println();
            for (int count : basketCount) {
                out.print(count + " ");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static Basket loadFromTxtFile(File textFile) {
        try (BufferedReader br = new BufferedReader(new FileReader("Save.txt"))) {
            String[] product = br.readLine().split(" ");
            String[] stringPrice = br.readLine().split(" ");
            String[] stringBasketCount = br.readLine().split(" ");

            int[] price = new int[stringPrice.length];
            for (int i = 0; i < price.length; i++) {
                price[i] = Integer.parseInt(stringPrice[i]);
            }

            int[] basketCount = new int[stringBasketCount.length];
            for (int i = 0; i < basketCount.length; i++) {
                basketCount[i] = Integer.parseInt(stringBasketCount[i]);
            }

            Basket basket = new Basket(product,price);
            basket.basketCount = basketCount;
            return basket;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void saveJSON(File textFile) {
        Basket basket = new Basket(this.product, this.price);
        basket.basketCount = basketCount;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try (FileWriter writer = new FileWriter(textFile)) {
            writer.write(gson.toJson(basket));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static Basket loadFromJSON(File textFile) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try (FileReader reader = new FileReader(textFile)) {
            Basket basket = gson.fromJson(reader, Basket.class);
            return basket;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}