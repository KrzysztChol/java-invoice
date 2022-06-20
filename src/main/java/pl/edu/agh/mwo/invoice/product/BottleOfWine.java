package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWine extends Product {
    private static final BigDecimal excise = BigDecimal.valueOf(5.56);

    public BottleOfWine(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.23"));
    }

    @Override
    public BigDecimal getPriceWithTax() {
        return price.multiply(taxPercent).add(price).add(excise);
    }
}
