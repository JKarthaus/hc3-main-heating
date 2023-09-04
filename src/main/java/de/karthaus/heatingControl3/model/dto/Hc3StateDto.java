package de.karthaus.heatingControl3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hc3StateDto {
    private Double inletGreyWaterTemp;
    private Double greyWaterTemp;
    private Double outletSolarTemp;
    private Double inletHeatingTemp;
    private Double outdoorTemp;
    boolean pumpHeating;
    boolean pumpProcessWater;
    boolean pumpSolar;

}
