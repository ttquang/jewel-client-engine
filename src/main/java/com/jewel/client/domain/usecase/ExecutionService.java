package com.jewel.client.domain.usecase;

import com.jewel.client.domain.model.ExecutionContext;
import com.jewel.client.domain.model.TestRunner;
import com.jewel.client.domain.model.TestSuite;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ExecutionService {

    public void execute(TestSuite testSuite) throws InterruptedException {
        ExecutionContext context = new ExecutionContext();
        Map<String, String> parameters = new HashMap<>();
        context.setParameters(parameters);

        WebDriver webDriver = null;
        try {
            webDriver = WebDriverFactory.get();
            TestRunner environment = new DefaultExecutionEnvironment(webDriver, context.getParameters());
            testSuite.runWith(environment);

            for (String key : testSuite.getProperties()) {
                System.out.println(key + " : " + testSuite.getPropertyValue(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(webDriver)) {
                webDriver.quit();
            }
        }
    }

}
