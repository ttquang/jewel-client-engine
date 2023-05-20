package com.jewel.client.config;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriverService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class AppStartupRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "D:/webdriver/chromedriver.exe");
        System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, "D:/webdriver/msedgedriver.exe");
    }
}
