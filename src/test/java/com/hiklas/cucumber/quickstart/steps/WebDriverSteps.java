package com.hiklas.cucumber.quickstart.steps;


import static org.junit.Assert.assertThat;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

/**
 * Step definitions for the basic operations using WebDriver
 * 
 * @author Fiona Bianchi
 */
@ContextConfiguration("classpath:cucumber-spring.xml")
public class WebDriverSteps extends CommonStepOperations {

  private static final int MINIUMUM_SIZE_OF_URL_STRING = 10;

  private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverSteps.class);
  
    
    @PostConstruct
    public void postConstructor() {
      LOGGER.debug("postConstructor");
      setupWebDriver();
    }
    
    @Given("^I go to the (.*) page")
    public void i_go_to_the_given_page(String page_name) throws Throwable {
      LOGGER.debug("BEGIN i_go_to_the_given_page({})", page_name);
      
      String page_url = coordinator.pageUrl(page_name);
      
      assertThat(page_url, notNullValue());
      
      // Slightly arbitrary value but the 'http://' is 7 characters anyway
      assertThat(page_url.length(), greaterThan(MINIUMUM_SIZE_OF_URL_STRING));
      
      LOGGER.debug("Going to page: '{}', using url: '{}'", page_name, page_url);
      
      webDriver.get(page_url);
      
      LOGGER.debug("END i_go_to_the_given_page({})", page_name);
    }
    
    
    @Given("^I am on the (.*) page$")
    public void i_am_on_the_given_page(String page_name) throws Throwable {   
      LOGGER.debug("BEGIN i_am_on_the_given_page({})", page_name);
      
      String currentUrl = webDriver.getCurrentUrl();
      String expectedUrlForPage  = coordinator.pageUrl(page_name);
      
      assertThat(expectedUrlForPage, notNullValue());
      assertThat(expectedUrlForPage.length(), greaterThan(MINIUMUM_SIZE_OF_URL_STRING));
      
      LOGGER.debug("Checking we are on page: {}, with url: {}", page_name, expectedUrlForPage);
      
      String currentTitle = webDriver.getTitle();
      String expectedTitleForPage = config.title_for(page_name); 
      
      assertThat(expectedTitleForPage, notNullValue());
      assertThat(expectedTitleForPage.length(), greaterThan(0));
      
      LOGGER.debug("Checking for expected title: {}", expectedTitleForPage);
      
      assertThat(currentUrl, equalTo(expectedUrlForPage));
      assertThat(currentTitle, equalTo(expectedTitleForPage));
      
      // TODO: Need to check the CSS IDs we expect to see
      LOGGER.debug("END i_am_on_the_given_page({})", page_name);
    }

    @Given("^I enter a (.*) of (.*)$")
    public void i_enter_a_given_field(String element_to_enter, String value_to_enter) throws Throwable {
      LOGGER.debug("BEGIN i_enter_a_given_field({},{})", value_to_enter, element_to_enter);
      
      String elementID = config.element_id(element_to_enter);
      
      assertThat(elementID, notNullValue());
      assertThat(elementID.length(), greaterThan(0));
      
      LOGGER.debug("Element ID is: {}", elementID);

      WebElement webElement = findElement(By.id(elementID));
      
      assertThat(webElement, notNullValue());
      
      LOGGER.debug("Sending keys to webElement ...");
      webElement.sendKeys(value_to_enter);
      LOGGER.debug("... sent");

      LOGGER.debug("END i_enter_a_given_field({},{})", value_to_enter, element_to_enter);
    }

    @When("^I click the (.*) button$")
    public void i_press_the_given_button(String button_id) {
      LOGGER.debug("BEGIN i_press_the_given_button({})", button_id);
      String elementID = config.element_id(button_id);
      
      assertThat(elementID, notNullValue());
      assertThat(elementID.length(), greaterThan(0));
      
      LOGGER.debug("Element ID is: {}", elementID);
      
      WebElement buttonToPress = findElement(By.id(elementID));
      
      assertThat(buttonToPress, notNullValue());
      
      buttonToPress.click();
      
      LOGGER.debug("END i_press_the_given_button({})", button_id);
    }
    
  
    @SuppressWarnings({ "rawtypes" })
    @Then("^I am (able|unable) to see (.*)$")
    public void i_am_able_unable_to_see(String ableOrNot, String toWhat) {
      LOGGER.debug("BEGIN i_am_able_unable_to_see({},{})", ableOrNot, toWhat);
      
      int got_count = 0;
      
      List listOfElements = config.element_group(toWhat);
      for(Object elementToFind : listOfElements) {
        assertThat(elementToFind, instanceOf(String.class));
        String elementStringToFind = (String)elementToFind;
        
        String elementIDToFind = config.element_id(elementStringToFind);
        
        LOGGER.debug("CSS ID to find: {}", elementIDToFind);
        
        assertThat(elementIDToFind, notNullValue());
        assertThat(elementIDToFind.length(), greaterThan(0));
        
        WebElement webElement = findElement(By.id(elementIDToFind));
        
        if(webElement==null) {
          LOGGER.debug("Didn't find element name '{}' ID '{}'", 
              elementStringToFind, elementIDToFind);
        } else {
          got_count++;
          LOGGER.debug("Found element name '{}' ID '{}'", 
              elementStringToFind, elementIDToFind);
        }
      }
      
      if("able".equalsIgnoreCase(ableOrNot)) {
        LOGGER.debug("Should be able to see all elements, found {} of {}", 
            got_count, listOfElements.size());
        assertThat(got_count, equalTo(listOfElements.size()));
      } else {
        LOGGER.debug("Should be unable to see all elements, found {} of {}", 
            got_count, listOfElements.size());
        assertThat(got_count, equalTo(0));
      } 
      
      LOGGER.debug("END i_am_able_unable_to_see({},{})", ableOrNot, toWhat);
    }
    
    @Then("^I can choose to (.*)$")
    public void i_can_choose_to(String toWhat) {
      LOGGER.debug("BEGIN i_can_choose_to({})", toWhat);
      
      LOGGER.error("PENDING: i_can_choose_to");

      LOGGER.debug("END i_can_choose_to({})", toWhat);      
    }
    
    
    @Then("^I am shown an (.*) informing me I need to (.*)$")
    public void i_am_shown_an_informing_me_i_need_to(String shownWhat, String whatMessage) throws Throwable {
      LOGGER.debug("BEGIN i_am_shown_an_informing_me_i_need_to({}, {})", shownWhat, whatMessage);
      
      String cssClassForMessages = config.element_id(shownWhat);
      
      LOGGER.debug("Looking up element for '{}', got '{}'", shownWhat, cssClassForMessages);
      assertThat(cssClassForMessages, notNullValue());
      assertThat(cssClassForMessages.length(), greaterThan(0));
      
      String expectedMessage = config.message(whatMessage);
      assertThat(expectedMessage, notNullValue());
      assertThat(expectedMessage.length(), greaterThan(0));
      
      LOGGER.debug("Expecting message '{}'", expectedMessage);
      
      List<WebElement> cssElements = webDriver.findElements(By.className(cssClassForMessages));
      
      assertThat(cssElements, notNullValue());
      assertThat(cssElements.size(), greaterThan(0));
      
      LOGGER.debug("Found {} elements that match {}", cssElements.size(), cssClassForMessages);
      
      boolean found = false;
      
      for(WebElement webElement : cssElements) {
        String spanText = webElement.getText();
        
        LOGGER.debug("Comparing expected message '{}' with element text '{}'", expectedMessage, spanText);
        if(expectedMessage.equals(spanText)) {
          LOGGER.debug("Found text");
          found = true;
        }
      }
      
      assertThat(found, equalTo(true));
      
      LOGGER.debug("END i_am_shown_an_informing_me_i_need_to({})", shownWhat, whatMessage);
    }
    
}
