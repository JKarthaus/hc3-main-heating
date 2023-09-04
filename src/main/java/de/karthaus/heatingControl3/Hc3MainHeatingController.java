package de.karthaus.heatingControl3;

import de.karthaus.heatingControl3.model.dto.Hc3StateDto;
import de.karthaus.heatingControl3.service.MainService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@Controller("/hc3MainHeating")
@RequiredArgsConstructor
public class Hc3MainHeatingController {

    @Inject
    MainService mainService;

    @Get(uri = "/state", produces = "text/plain")
    public Hc3StateDto getState() {
        return mainService.getHc3StateDto();
    }
}
