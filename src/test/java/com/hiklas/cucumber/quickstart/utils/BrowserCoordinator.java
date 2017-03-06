package com.hiklas.cucumber.quickstart.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BrowserCoordinator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserCoordinator.class);

    @Autowired
    private YamlConfiguration config;
    
    @Autowired
    private BrowserFactory browserFactory;
    
    private WebDriver webDriver;

    public synchronized WebDriver getCurrentDriver() {
        if (webDriver == null) {
            try {
                webDriver = browserFactory.getWebDriver();
            }
            catch (UnreachableBrowserException e) {
                webDriver = browserFactory.getWebDriver();
            }
            catch (WebDriverException e) {
                webDriver = browserFactory.getWebDriver();
            }
            finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                  public void run() {
                    close();
                }                
                }));
            }
        }
        return webDriver;
    }

    public void close() {
        try {
            getCurrentDriver().quit();
            webDriver = null;
            LOGGER.info("closing the browser");
        }
        catch (UnreachableBrowserException e) {
            LOGGER.info("cannot close browser: unreachable browser");
        }
    }
    
    public String pageUrl(String screen_name) {
      return config.base_url() + config.url_for(screen_name);
    }
}
