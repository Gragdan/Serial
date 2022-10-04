import java.io.*;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1;
    private static String[] products;
    private double[] prices;
    private static int[] selected;

    public Basket(String[] products, double[] prices, int[] selected) throws IOException {
        this.products = products;
        this.prices = prices;
        this.selected = selected;

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


    public static Basket loadFromBinFile(File binFile) throws IOException {
        if (binFile.exists()) {
            try (FileInputStream fis = new FileInputStream(binFile);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                selected = (int[]) ois.readObject();
                System.out.println("Your cart:");
                int sum = 0;
                for (int i = 0; i < selected.length; i++) {
                    sum = sum + selected[i];
                    if (selected[i] > 0) {
                        System.out.println(products[i] + " " + selected[i]);
                    }
                }
                if (sum <= 0) {
                    System.out.println("  is empty :(");
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }else {
            binFile.createNewFile();
        }
        return null;
    }

    public void saveBin(File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(selected);
        objectOutputStream.close();
    }
}


