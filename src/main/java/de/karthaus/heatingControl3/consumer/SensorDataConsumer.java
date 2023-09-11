package de.karthaus.heatingControl3.consumer;

import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import de.karthaus.heatingControl3.model.HeatingControlContext;
import io.micronaut.context.annotation.Value;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RabbitListener
@Slf4j
@RequiredArgsConstructor
@Singleton
public class SensorDataConsumer {

    List<String> messageLengths = Collections.synchronizedList(new ArrayList<>());


    private final HeatingControlContext heatingControlContext;

    @Value("${hc3.sensor.inlet-grey-water-sensor-id}")
    protected String inletGreyWaterSensorID;

    @Value("${hc3.sensor.grey-water-Sensor-id}")
    protected String greyWaterSensorID;

    @Value("${hc3.sensor.outlet-solar-sensor-id}")
    protected String outletSolarSensorID;

    @Value("${hc3.sensor.inlet-heating-sensor-id}")
    protected String inletHeatingSensorID;

    @Value("${hc3.sensor.outdoor-sensor-id}")
    protected String outdoorSensorID;

    @Value("${hc3.temperature-queue.name}")
    protected String temperatureQueue;

    @Value("${rabbitmq.host}")
    protected String rabbitMqHost;


    @PostConstruct
    public void init() {
        log.info("initializing amqp listener on Queue:{}", temperatureQueue);
        log.debug("inletGreyWaterSensorID:{}", inletGreyWaterSensorID);
        log.debug("greyWaterSensorID:{}", greyWaterSensorID);
        log.debug("outletSolarSensorID:{}", outletSolarSensorID);
        log.debug("inletHeatingSensorID:{}", inletHeatingSensorID);
        log.debug("outdoorSensorID:{}", outdoorSensorID);
        log.info("Listening on RabbitMQ - Host:{}", rabbitMqHost);
    }


    @Queue("hc-temperature")
    public void receive(byte[] data, Envelope envelope, BasicProperties basicProperties, Channel channel) {
        log.debug("Receive Data from Rabbit MQ in Queue:{}", temperatureQueue);
        boolean map = false;

        if (envelope.getRoutingKey().equalsIgnoreCase(inletGreyWaterSensorID)) {
            heatingControlContext.setInletGreyWaterTemp(Double.valueOf(new String(data)));
            map = true;
            log.debug("Receive inletGreyWaterTemp:{}", heatingControlContext.getInletGreyWaterTemp());
        }

        if (envelope.getRoutingKey().equalsIgnoreCase(greyWaterSensorID)) {
            heatingControlContext.setGreyWaterTemp(Double.valueOf(new String(data)));
            map = true;
            log.debug("Receive greyWaterTemp:{}", heatingControlContext.getGreyWaterTemp());
        }

        if (envelope.getRoutingKey().equalsIgnoreCase(outletSolarSensorID)) {
            heatingControlContext.setOutletSolarTemp(Double.valueOf(new String(data)));
            map = true;
            log.debug("Receive outletSolarTemp:{}", heatingControlContext.getOutletSolarTemp());
        }

        if (envelope.getRoutingKey().equalsIgnoreCase(inletHeatingSensorID)) {
            heatingControlContext.setInletHeatingTemp(Double.valueOf(new String(data)));
            map = true;
            log.debug("Receive inletHeatingTemp {}", heatingControlContext.getInletHeatingTemp());
        }

        if (envelope.getRoutingKey().equalsIgnoreCase(outdoorSensorID)) {
            heatingControlContext.setOutdoorTemp(Double.valueOf(new String(data)));
            map = true;
            log.debug("Receive outDoorTemp:{}", heatingControlContext.getOutdoorTemp());
        }

        // Data can not be mapped

        if (!map) {
            log.warn("Sensor {} not Mapped to configured sensors...", envelope.getRoutingKey());
        }
    }

}
