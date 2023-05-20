package com.jewel.client.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jewel.client.application.dto.StepDTO;
import com.jewel.client.application.dto.TestCaseDTO;
import com.jewel.client.application.dto.TestSuiteDTO;
import com.jewel.client.domain.model.Step;
import com.jewel.client.domain.model.TestCase;
import com.jewel.client.domain.model.TestSuite;
import com.jewel.client.domain.usecase.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class CustomCommandLineRunner implements CommandLineRunner {

    private String testSuiteDir = "D:/Jewel-Test-Tool/testsuites";

    @Autowired
    private ExecutionService executionService;

    @Override
    public void run(String... args) throws Exception {
        File testSuiteDir = new File(this.testSuiteDir);
        for (File testSuiteFile : testSuiteDir.listFiles()) {
            if (testSuiteFile.getName().contains(".json")) {
                ObjectMapper objectMapper = new ObjectMapper();
                TestSuiteDTO testSuiteDTO = objectMapper.readValue(testSuiteFile, TestSuiteDTO.class);
                TestSuite testSuite = new TestSuite(testSuiteDTO.getName(), testSuiteDTO.getProperties());

                for (TestCaseDTO testCaseDTO : testSuiteDTO.getTestCases()) {
                    TestCase testCase = new TestCase(testCaseDTO.getName(), testCaseDTO.getProperties());
                    for (StepDTO stepDTO : testCaseDTO.getSteps()) {
                        Step step = new Step(stepDTO.getName(), stepDTO.getType(), stepDTO.getParameters());
                        testCase.addStep(step);
                    }

                    testSuite.addTestCase(testCase);
                }

                this.executionService.execute(testSuite);
            }
        }
    }
}
