package com.jewel.client.domain.exception;

import com.jewel.client.domain.model.Step;

public class StepRuntimeException extends RuntimeException {

    public StepRuntimeException() {}

    public StepRuntimeException(Step testStep) {
        super(testStep.toString());
    }

}
