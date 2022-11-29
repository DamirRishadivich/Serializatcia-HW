import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BasketTest {
    String[] product = {"Хлеб", "Молоко", "Масло"};
    int[] price = {10,20,30};
    Basket basket1 = new Basket(product, price);

    @Test
    public void constructorTest() { //Создается ли объект
        Basket basket = new Basket(product, price);
        Assertions.assertNotNull(basket);
    }

    @Test
    public void addToCardTest() { //Проверка метода addToCart
        basket1.addToCart(1,10);
        Assertions.assertEquals(10 ,basket1.basketCount[0]);
    }

    @Test
    public void basketArrayTest() { // Создается ли указанный в конструкторе массив
        Basket basket2 = new Basket(new String[] {"Молоко", "Пиво", "Доширак"}, new int[]{20,40,60} );
        Assertions.assertArrayEquals(new int[]{20,40,60},basket2.getPrice());
        Assertions.assertArrayEquals(new String[] {"Молоко", "Пиво", "Доширак"}, basket2.getProduct());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,5,10})
    public void basketCountTest(int arg) { //Параметризированный тест метода addToCart
        basket1.addToCart(1, arg);
        Assertions.assertEquals(arg, basket1.basketCount[0]);
    }

    @Test
    public void saveTxtTest() { // Проверка равны ли элементы сохраненного объекта, загруженному
        String file = "basket.txt";
        basket1.saveTxt(new File(file));
        Basket basket5 = Basket.loadFromTxtFile(new File(file));
        Assertions.assertArrayEquals(basket1.getPrice(), basket5.getPrice());
    }



}
