package de.karthaus.heatingControl3.consumer;

import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import de.karthaus.heatingControl3.model.HeatingControlContext;
import io.micronaut.context.annotation.Value;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RabbitListener
public class SensorDataConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    List<String> messageLengths = Collections.synchronizedList(new ArrayList<>());

    private HeatingControlContext heatingControlContext;

    @Value("${hc3.sensor.returnLineSolar}")
    protected String returnLineSolarSensorId;

    @Value("${hc3.sensor.inletLineProcessWater}")
    protected String inletLineProcessWaterSensorId;

    @Value("${hc3.sensor.processWater}")
    protected String processWaterSensorId;

    @Value("${hc3.sensor.inletLineHeating}")
    protected String inletLineHeatingSensorId;

    @Value("${hc3.sensor.outdoor}")
    protected String outdoorSensorId;

    /**
     * @param heatingControlContext
     */
    public SensorDataConsumer(de.karthaus.heatingControl3.model.HeatingControlContext heatingControlContext) {
        this.heatingControlContext = heatingControlContext;
    }

    /**
     * @param data
     * @param envelope
     * @param basicProperties
     * @param channel
     */
    @Queue("${hc3.temperature-queue.name}")
    public void receive(byte[] data, Envelope envelope, BasicProperties basicProperties, Channel channel) {
        logger.debug("Receive Data from Rabbit MQ at Queue:hc-ds18b20");
        boolean map = false;
        if (envelope.getRoutingKey().equalsIgnoreCase(combustionSensorId)) {
            heatingControlContext.setTemp_combustionChamber(new Double(new String(data)));
            map = true;
            logger.debug("Receive combustionChamber temp {}", heatingControlContext.getTemp_combustionChamber());
        }
        if (envelope.getRoutingKey().equalsIgnoreCase(leadingLineSensorId)) {
            heatingControlContext.setFlowTemperature(new Double(new String(data)));
            map = true;
            logger.debug("Receive flow temp {}", heatingControlContext.getFlowTemperature());
        }

        if (envelope.getRoutingKey().equalsIgnoreCase(returnLineSensorId)) {
            heatingControlContext.setReturnTemperature(new Double(new String(data)));
            map = true;
            logger.debug("Receive return temp {}", heatingControlContext.getReturnTemperature());
        }

        if (envelope.getRoutingKey().equalsIgnoreCase(bufferSensorId)) {
            heatingControlContext.setBufferTemperature(new Double(new String(data)));
            map = true;
            logger.debug("Receive Buffer temp {}", heatingControlContext.getBufferTemperature());
        }

        if (envelope.getRoutingKey().equalsIgnoreCase(garageSensorId)) {
            heatingControlContext.setGarageTemperature(new Double(new String(data)));
            map = true;
            logger.debug("Receive Garage temp {}", heatingControlContext.getGarageTemperature());
        }
        if (envelope.getRoutingKey().equalsIgnoreCase(outdoorSensorId)) {
            heatingControlContext.setOutdoorTemperature(new Double(new String(data)));
            map = true;
            logger.debug("Receive outdoor temp {}", heatingControlContext.getOutdoorTemperature());
        }

        // Data can not be mapped

        if (!map) {
            logger.warn("Sensor {} not Mapped to configured sensors...", envelope.getRoutingKey());
        }
    }

}
