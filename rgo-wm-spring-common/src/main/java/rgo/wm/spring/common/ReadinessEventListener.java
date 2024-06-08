package rgo.wm.spring.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;

public class ReadinessEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadinessEventListener.class);

    @EventListener
    public void onEvent(AvailabilityChangeEvent<ReadinessState> event) {
        switch (event.getState()) {
            case ACCEPTING_TRAFFIC -> LOGGER.info("Application is ready to accept traffic.");
            case REFUSING_TRAFFIC -> LOGGER.info("Application is not ready to accept traffic.");
        }
    }
}
