package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

public class FuelCanister extends Product {
    private static final BigDecimal excise = BigDecimal.valueOf(5.56);

    private static final int DayOfCarpenterDay = 19;
    private static final Month DayOfCarpenterMonth = Month.MARCH;

    public FuelCanister(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.23"));
    }

    @Override
    public BigDecimal getPriceWithTax() {
        LocalDateTime now = LocalDateTime.now();
        BigDecimal priceWithTax = price;
        // day of the carpenter discount
        if (now.getDayOfMonth() == DayOfCarpenterDay
            && now.getMonth() == DayOfCarpenterMonth) {
            // nothing to do here, no tax
        } else {
            priceWithTax = price.multiply(taxPercent).add(price).add(excise);
        }
        return priceWithTax;
    }
}
