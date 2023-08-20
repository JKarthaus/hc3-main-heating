package de.karthaus.heatingControl3.model;

import jakarta.inject.Singleton;
import lombok.Data;

@Data
@Singleton
public class HeatingControlContext {

    private Double greyWaterTemp;
    private Double outdoorTemp;
    private Double inletTemp;
    private Double outletTemp;


}
