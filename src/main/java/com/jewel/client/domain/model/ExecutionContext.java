package com.jewel.client.domain.model;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContext {
    String testSuite;
    Map<String, String> parameters = new HashMap<>();

    public ExecutionContext() {
    }

    public String getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(String testSuite) {
        this.testSuite = testSuite;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
