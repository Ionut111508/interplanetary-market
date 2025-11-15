package ecommerce.interplanetary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartViewDto {
    private Long itemId;
    private Long landPlotId;
    private String landPlotName;
    private double price;
    private int quantity;
    private double total;

    public CartViewDto(Long itemId, Long landPlotId, String landPlotName, BigDecimal price, int quantity, BigDecimal total) {
        this.itemId = itemId;
        this.landPlotId = landPlotId;
        this.landPlotName = landPlotName;
        this.price = price.doubleValue();
        this.quantity = quantity;
        this.total = total.doubleValue();
    }
}

