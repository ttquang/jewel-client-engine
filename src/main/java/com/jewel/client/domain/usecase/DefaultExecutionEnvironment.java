package com.jewel.client.domain.usecase;

import com.jewel.client.domain.model.PropertyLevel;
import com.jewel.client.domain.model.Step;
import com.jewel.client.domain.model.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class DefaultExecutionEnvironment extends TestRunner {
    WebDriver webDriver;

    public DefaultExecutionEnvironment(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public DefaultExecutionEnvironment(WebDriver webDriver, Map<String, String> properties) {
        this.webDriver = webDriver;
        constructPropertyHandler(PropertyLevel.ENVIRONMENT, properties);
    }

    protected WebElement findElement(String selector) {
        return this.webDriver.findElement(By.xpath(selector));
    }

    protected void select(String selector, String type, String value) {
        Select select = new Select(findElement(selector));
        if ("Index".equals(type)) {
            select.selectByIndex(Integer.valueOf(value));
        } else if ("Label".equals(type)) {
            select.selectByVisibleText(value);
        } else if ("Value".equals(type)) {
            select.selectByValue(value);
        }
    }

    protected void select(WebElement webElement, String type, String value) {
        Select select = new Select(webElement);
        if ("Index".equals(type)) {
            select.selectByIndex(Integer.valueOf(value));
        } else if ("Label".equals(type)) {
            select.selectByVisibleText(value);
        } else if ("Value".equals(type)) {
            select.selectByValue(value);
        }
    }

    @Override
    public void execute(Step step) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if ("1".equals(step.getType())) {
            String selector = step.getProperty(step.getParameters().get("selector"));
            WebElement webElement = findElement(selector);

            if ("SWITCH_TO".equals(step.getParameters().get("action"))) {
                this.webDriver.switchTo().frame(webElement);
            } else if ("CLICK".equals(step.getParameters().get("action"))) {
                webElement.click();
            } else if ("SEND_KEY".equals(step.getParameters().get("action"))) {
                String value = step.getProperty(step.getParameters().get("value"));
                webElement.sendKeys(value);
            } else if ("SELECT".equals(step.getParameters().get("action"))) {
                String type = "Value";
                String value = step.getProperty(step.getParameters().get("value"));
                select(selector, type, value);
            } else if ("SWITCH_TO_DEFAULT".equals(step.getParameters().get("action"))) {
                this.webDriver.switchTo().defaultContent();
            }
        } else if ("2".equals(step.getType())) {
            String url = step.getProperty(step.getParameters().get("url"));
            this.webDriver.navigate().to(url);
        } else if ("3".equals(step.getType())) {
            String selector = step.getProperty(step.getParameters().get("selector"));
            String value = findElement(selector).getAttribute("value");
            step.putProperty("{" + step.getParameters().get("target") + "}", value);
        } else if ("82".equals(step.getType())) {
            if ("SWITCH_TO_DEFAULT".equals(step.getParameters().get("action"))) {
                this.webDriver.switchTo().defaultContent();
            } else if ("SWITCH_TO".equals(step.getParameters().get("action"))) {
                String selector = step.getProperty(step.getParameters().get("selector"));
                WebElement webElement = findElement(selector);
                this.webDriver.switchTo().frame(webElement);
            }
        }
    }

}
