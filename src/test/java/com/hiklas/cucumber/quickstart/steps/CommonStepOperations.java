package com.hiklas.cucumber.quickstart.steps;

import com.hiklas.cucumber.quickstart.utils.BrowserCoordinator;
import com.hiklas.cucumber.quickstart.utils.YamlConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:cucumber-spring.xml")
public class CommonStepOperations {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommonStepOperations.class);

  @Autowired
  protected YamlConfiguration config;
    
  @Autowired
  @Qualifier("browserCoordinatorSingleton")
  protected BrowserCoordinator coordinator;
  
  protected WebDriver webDriver;

  protected void setupWebDriver() {
    LOGGER.debug("Getting the current web driver instance");
    webDriver = coordinator.getCurrentDriver();
    LOGGER.debug("... got the current web driver instance");
  }
  
  /**
   * Wrap the WebDriver findElement method to handle exceptions.
   * 
   * Rather than have to handle exceptions every time we find an element just
   * wrap the call here and return null if an element isn't found.
   * 
   * @param by
   * @return The WebElement if it is found or null otherwise
   */
  protected WebElement findElement(By by) {
    WebElement result = null;
    try {
      result = webDriver.findElement(by);
    } catch (NoSuchElementException nsee) {
      // Do nothing
    }
    return result;
  }
  
}
