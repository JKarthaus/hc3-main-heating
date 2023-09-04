package de.karthaus.heatingControl3.model;

import jakarta.inject.Singleton;
import lombok.Data;


@Singleton
@Data
public class PumpState {

    // Heizungspumpenstatus
    boolean pumpHeating = false;
    // Brauchwasserpumpenstatus
    boolean pumpProcessWater = false;
    // Solarpumpenstatus
    boolean pumpSolar = false;

}
