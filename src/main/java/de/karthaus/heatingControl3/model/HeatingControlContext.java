package de.karthaus.heatingControl3.model;

import jakarta.inject.Singleton;
import lombok.Data;

@Data
@Singleton
public class HeatingControlContext {

    // Vorlauf Brauchwasser
    private Double inletGreyWaterTemp;
    // Warmwasserspeicher
    private Double greyWaterTemp;
    // Rücklauf Solar
    private Double outletSolarTemp;
    // Vorlauf brauchwasser
    private Double inletHeatingTemp;
    // Aussentemperatur
    private Double outdoorTemp;


}
