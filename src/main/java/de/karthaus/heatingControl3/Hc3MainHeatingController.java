package de.karthaus.heatingControl3;

import io.micronaut.http.annotation.*;

@Controller("/hc3MainHeating")
public class Hc3MainHeatingController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}