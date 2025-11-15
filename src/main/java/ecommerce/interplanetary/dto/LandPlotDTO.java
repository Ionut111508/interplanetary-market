package ecommerce.interplanetary.dto;

import ecommerce.interplanetary.entity.LandPlot;
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
    private Double temperature;

    public LandPlotDTO(LandPlot plot, Double temperature) {
        this.id = plot.getId();
        this.terrainType = plot.getTerrainType();
        this.price = plot.getPrice();
        this.areaSize = plot.getAreaSize();
        this.planetName = plot.getPlanet() != null ? plot.getPlanet().getName() : "Unknown";
        this.temperature = temperature;
    }

    public LandPlotDTO(Long id, String terrainType, Double areaSize, BigDecimal price, Long aLong, String s) {
    }
}