package de.karthaus.heatingControl3.service;

import de.karthaus.heatingControl3.model.HeatingControlContext;
import de.karthaus.heatingControl3.model.PumpState;
import de.karthaus.heatingControl3.model.dto.Hc3StateDto;
import io.micronaut.context.annotation.Context;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class MainService {

    @Inject
    HeatingControlContext heatingControlContext;
    @Inject
    PumpState pumpState;

    public Hc3StateDto getHc3StateDto() {
        return new Hc3StateDto(
                heatingControlContext.getInletGreyWaterTemp(),
                heatingControlContext.getGreyWaterTemp(),
                heatingControlContext.getOutletSolarTemp(),
                heatingControlContext.getInletHeatingTemp(),
                heatingControlContext.getOutdoorTemp(),
                pumpState.isPumpHeating(),
                pumpState.isPumpProcessWater(),
                pumpState.isPumpSolar()
        );
    }



}
