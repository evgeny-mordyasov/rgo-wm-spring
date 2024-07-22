package rgo.wm.spring.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rgo.wm.common.utils.exceptions.BaseException;
import rgo.wm.common.utils.rest.api.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static rgo.wm.common.test.utils.random.StringRandom.randomString;

class ExceptionResponseAdviceTest {

    private ExceptionResponseAdvice advice;

    @BeforeEach
    void setUp() {
        advice = new ExceptionResponseAdvice();
    }

    @Test
    void handleException_baseException() {
        BaseException e = new BaseException(randomString());

        ResponseEntity<HttpResponse> response = advice.handleException(e);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(HttpResponse.FAILURE_STATUS);
    }
}