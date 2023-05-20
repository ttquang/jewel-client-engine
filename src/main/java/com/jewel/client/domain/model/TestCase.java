package com.jewel.client.domain.model;

import com.jewel.client.domain.exception.StepRuntimeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase extends Element {
    TestSuite testSuite;
    List<Step> steps = new ArrayList<>();

    public TestCase(String name) {
        super(name);
        this.constructPropertyHandler(PropertyLevel.TEST_CASE, new HashMap<>());
    }

    public TestCase(String name, Map<String, String> properties) {
        super(name);
        this.constructPropertyHandler(PropertyLevel.TEST_CASE, properties);
    }


    public void addStep(Step step) {
        step.propertyHandler.nextHandler = this.propertyHandler;
        step.testCase = this;
        this.steps.add(step);
    }

    public void execute() {
        for (Step step : steps) {
            try {
                step.run();
            } catch (StepRuntimeException ex) {
                ex.printStackTrace();
                throw new StepRuntimeException(step);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new StepRuntimeException(step);
            }
        }
    }

    public void delegate(Step step) {
        testSuite.delegate(step);
    }

}
