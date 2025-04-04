package rgo.wm.spring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Precorrelation;

public class InfoHttpLogWriter implements HttpLogWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoHttpLogWriter.class);

    @Override
    public void write(final Precorrelation precorrelation, final String request) {
        LOGGER.info(request);
    }

    @Override
    public void write(final Correlation correlation, final String response) {
        LOGGER.info(response);
    }
}
