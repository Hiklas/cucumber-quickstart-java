package com.hiklas.cucumber.quickstart.utils;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.concurrent.TimeUnit;

@Component
public class BrowserFactory {

    @Autowired
    private YamlConfiguration config;
    

    /**
     * creates the browser driver specified in the system property "browser"
     * if no property is set then a firefox browser driver is created.
     * The allow properties are firefox, safari and chrome
     * e.g to run with safari, pass in the option -Dbrowser=safari at runtime
     *
     * @return WebDriver
     */
    public WebDriver getWebDriver() {
        BrowserType browserType = BrowserType.browserForName(config.browser());

        WebDriver webDriver;

        switch (browserType) {
        case CHROME:
            webDriver = createChromeDriver();
            break;
        case SAFARI:
            webDriver = createSafariDriver();
            break;
        case FIREFOX:
            webDriver = createFirefoxDriver();
            break;
        case HTMLUNIT:
            webDriver = createHtmlUnitDriver();
            break;
        default:
            webDriver = createHtmlUnitDriver();
            break;
        }
        addAllBrowserSetup(webDriver);
        return webDriver;
    }

    private WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        return new ChromeDriver();
    }

    private WebDriver createSafariDriver() {
        return new SafariDriver();
    }

    private WebDriver createHtmlUnitDriver() {
        return new HtmlUnitDriver();
    }

    private WebDriver createFirefoxDriver() {
        return new FirefoxDriver();
    }

    private void addAllBrowserSetup(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        org.openqa.selenium.Dimension dim = new org.openqa.selenium.Dimension((int) (screenSize.getWidth() * 0.8), (int) (screenSize.getHeight() * 0.8));
        driver.manage().window().setSize(dim);
    }

}
