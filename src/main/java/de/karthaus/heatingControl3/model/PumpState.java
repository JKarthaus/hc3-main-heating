package de.karthaus.heatingControl3.model;

import jakarta.inject.Singleton;
import lombok.Data;


@Singleton
@Data
public class PumpState {

    boolean pumpHeating;

    boolean pumpProcessWater;

    boolean pumpSolar;

}
