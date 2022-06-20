package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private int number;
    static int nextNumber = 1;
    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public Invoice() {
        this.number = nextNumber++;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return this.number;
    }

    public String getItems() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getNumber());
        stringBuilder.append('\n');
        for (Product product : products.keySet()) {
            stringBuilder.append(product.getName() + " "
                    + products.get(product) + " " + product.getPrice() + "\n");
        }

        stringBuilder.append("Liczba pozycji: " + String.valueOf(products.size()));
        return stringBuilder.toString();
    }

    public Map<Product, Integer> getProducts() {
        return this.products;
    }
}
