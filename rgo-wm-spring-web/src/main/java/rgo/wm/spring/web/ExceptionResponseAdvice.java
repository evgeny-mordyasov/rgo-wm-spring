package rgo.wm.spring.web;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rgo.wm.common.utils.exceptions.BaseException;
import rgo.wm.common.utils.rest.api.ErrorDetail;
import rgo.wm.common.utils.rest.api.HttpResponse;

@ControllerAdvice
public class ExceptionResponseAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResponseAdvice.class);

    @ExceptionHandler
    public ResponseEntity<HttpResponse> handleException(BaseException e) {
        var response = HttpResponse.failure(
                ErrorDetail.ofAsList(ExceptionUtils.getStackTrace(e)));

        LOGGER.error("Request failed.", e);

        return ResponseEntity
                .status(response.status().httpCode())
                .body(response);
    }
}
