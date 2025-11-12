package ecommerce.interplanetary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LandPlotDTO {
    private Long id;
    private String terrainType;
    private Double areaSize;
    private BigDecimal price;
    private Long planetId;
    private String planetName;
}
