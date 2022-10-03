import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String products[] = {"chees", "bread"};
        double prices[] = {3.5, 15};
        int selected[] = new int[products.length];
        Basket myBasket = new Basket(products, prices, selected);
        myBasket.loadFromTxtFile();
        System.out.println("Products for sale:");
        for (int i = 0; i < products.length; i++) {
            System.out.println(i + 1 + " " + products[i] + "  " + prices[i] + " $/pie");
        }

        while (true) {
            System.out.println("Choose number and quant of goods or inter `end`");

            String input = scanner.nextLine();
            if ("end".equals(input)) {
                myBasket.printCart();
                break;
            }
            int currentProduct;
            int currentQuan;
            String parts[] = input.split(" ");
            if (parts.length == 2) {
                try {
                    currentProduct = Integer.parseInt(parts[0]);
                    currentQuan = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Одно из введенных значений, либо оба - не являются числом!");
                    continue;
                }

                if (currentProduct < 1 || currentProduct > products.length) {
                    System.out.println("Товара под номером " + currentProduct + " не существует!");
                    continue;
                }

                if (Integer.parseInt(parts[1]) < 0) {
                    System.out.println("Нельзя выбрать такое количество товара! ");
                    continue;
                }
                myBasket.addToCart(currentProduct, currentQuan);
                myBasket.saveTxt();
            } else {
                System.out.println("Введено неверное количество чисел! ");
                continue;
            }

        }

    }
}
