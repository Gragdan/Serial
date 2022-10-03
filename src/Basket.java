import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Basket {
    private static String[] products;
    private double[] prices;
    private static int[] selected;
    //private static int[] fromLoad;

    public Basket(String[] products, double[] prices, int[] selected) throws IOException {
        this.products = products;
        this.prices = prices;
        this.selected = selected;
        loadFromTxtFile();
    }

    public static Basket loadFromTxtFile() throws IOException {
        File basket = new File("basket.txt");
        if (basket.exists()) {
            try (FileReader rider = new FileReader(basket)) {
                BufferedReader stringReader = new BufferedReader(rider);
                String line = stringReader.readLine();
                if (line != null) {
                    System.out.println("Your cart:");
                    String digLine = line.replaceAll("[^0-9]", " ");
                    String[] parts = digLine.split(" ");

                    List<String> mylist = new ArrayList<>(Arrays.asList(parts));
                    mylist.removeAll(Arrays.asList("", null));
                    int sum = 0;
                    for (int i = 0; i < mylist.size(); i = i + 1) {

                        try {
                            selected[i] = Integer.parseInt(mylist.get(i));
                            if (selected[i] > 0) {
                                sum = sum + selected[i];
                                System.out.println(products[i] + " " + selected[i]);
                            }

                        } catch (NumberFormatException | NullPointerException n) {
                            continue;
                        }
                    }
                    if(sum == 0){
                        System.out.println(" empty :(");
                    }
                } else {
                    System.out.println("Your cart is empty");
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }
        } else {
            System.out.println("xui");
            basket.createNewFile();
        }

        return null;
    }

    public static String removeNumeric(String str) {
        return str.chars()
                .filter(ch -> Character.isDefined(ch))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();

    }


    public int[] addToCart(int productNum, int amount) {
        selected[productNum - 1] = selected[productNum - 1] + amount;
        return selected;
    }

    public void printCart() {
        System.out.println("Goods in yuor cart: ");
        for (int i = 0; i < products.length; i++) {
            if (selected[i] != 0) {
                System.out.println(products[i] + ", prise: " + prices[i] + "/pie, quantity: " + selected[i] + " sum - " + prices[i] * selected[i]);
            }
        }

    }

    public void saveTxt() throws IOException {
        try (FileWriter writer = new FileWriter("basket.txt", false)) {
            writer.write(Arrays.toString(selected));
         //   System.out.println(Arrays.toString(selected));
           // System.out.println("writed!!!");
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}

//
//
