package zyna.util;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class WaitUtil {
	private FluentWait<WebDriver> fluentWait;

	public WaitUtil(WebDriver driver,int timeOutInSeconds,int pollingEveryInMiliSec) {
		fluentWait = new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofSeconds(timeOutInSeconds))
		        .pollingEvery(Duration.ofSeconds(pollingEveryInMiliSec))
		        .ignoring(NoSuchElementException.class)
		        .ignoring(ElementNotInteractableException.class)
		        .ignoring(StaleElementReferenceException.class)
		        .ignoring(NoSuchFrameException.class);
	}
	
	public Alert alertIsPresent() {
		return fluentWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public Boolean attributeContains(By by, String attribute, String value ) {
		return fluentWait.until(ExpectedConditions.attributeContains(by, attribute, value));
	}
	
	public Boolean attributeContains(WebElement webElement, String attribute, String value ) {
		return fluentWait.until(ExpectedConditions.attributeContains(webElement, attribute, value));
	}
	
	public Boolean attributeToBe(By by, String attribute, String value ) {
		return fluentWait.until(ExpectedConditions.attributeToBe(by, attribute, value));
	}
	
	public Boolean attributeToBe(WebElement webElement, String attribute, String value ) {
		return fluentWait.until(ExpectedConditions.attributeToBe(webElement, attribute, value));
	}
	
	public Boolean attributeToBeNotEmpty(WebElement webElement, String attribute ) {
		return fluentWait.until(ExpectedConditions.attributeToBeNotEmpty(webElement, attribute));
	}

	public Boolean elementSelectionStateToBe(By by, boolean bool) {
		return fluentWait.until(ExpectedConditions.elementSelectionStateToBe(by, bool));
	}

	public Boolean elementSelectionStateToBe(WebElement webElement, boolean bool) {
		return fluentWait.until(ExpectedConditions.elementSelectionStateToBe(webElement, bool));
	}
	
	public WebElement elementToBeClickable(By by) {
		return fluentWait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public WebElement elementToBeClickable(WebElement webElement) {
		return fluentWait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	
	public Boolean elementToBeSelected(By by) {
		return fluentWait.until(ExpectedConditions.elementToBeSelected(by));
	}
	
	public Boolean elementToBeSelected(WebElement webElement) {
		return fluentWait.until(ExpectedConditions.elementToBeSelected(webElement));
	}
	
	public WebDriver frameToBeAvailableAndSwitchToIt(By by) {
		return fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	}
	
	public WebDriver frameToBeAvailableAndSwitchToIt(String stringValue) {
		return fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(stringValue));
	}
	
	public WebDriver frameToBeAvailableAndSwitchToIt(int intValue) {
		return fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(intValue));
	}
	
	public WebDriver frameToBeAvailableAndSwitchToIt(WebElement webElement) {
		return fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
	}
	
	public Boolean invisibilityOf(WebElement webElement) {
		return fluentWait.until(ExpectedConditions.invisibilityOf(webElement));
	}
	
	public Boolean invisibilityOfAllElements(List<WebElement> webElements) {
		return fluentWait.until(ExpectedConditions.invisibilityOfAllElements(webElements));
	}
	
	public Boolean invisibilityOfElementLocated(By by) {
		return fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public Boolean invisibilityOfElementWithText(By by, String text) {
		return fluentWait.until(ExpectedConditions.invisibilityOfElementWithText(by,text));
	}
	
	public Boolean javaScriptThrowsNoExceptions(String javaScript) {
		return fluentWait.until(ExpectedConditions.javaScriptThrowsNoExceptions(javaScript));
	}
	
	public Object jsReturnsValue(String javaScript) {
		return fluentWait.until(ExpectedConditions.jsReturnsValue(javaScript));
	}
	
	public List<WebElement> numberOfElementsToBe(By by, int count) {
		return fluentWait.until(ExpectedConditions.numberOfElementsToBe(by,count));
	}
	
	public List<WebElement> numberOfElementsToBeLessThan(By by, int count) {
		return fluentWait.until(ExpectedConditions.numberOfElementsToBeLessThan(by,count));
	}
	
	public List<WebElement> numberOfElementsToBeMoreThan(By by, int count) {
		return fluentWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by,count));
	}
	
	public Boolean numberOfWindowsToBe(int count) {
		return fluentWait.until(ExpectedConditions.numberOfWindowsToBe(count));
	}
	
	public List<WebElement> presenceOfAllElementsLocatedBy(By by) {
		return fluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}
	
	public WebElement presenceOfElementLocated(By by) {
		return fluentWait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public WebElement presenceOfNestedElementLocatedBy(By parent, By child) {
		return fluentWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child));
	}
	
	public List<WebElement> presenceOfNestedElementsLocatedBy(By parent, By child) {
		return fluentWait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(parent, child));
	}
	
	public Boolean stalenessOf(WebElement webElement) {
		return fluentWait.until(ExpectedConditions.stalenessOf(webElement));
	}
	
	public Boolean textToBe(By by, String text) {
		return fluentWait.until(ExpectedConditions.textToBe(by,text));
	}
	
	public Boolean textToBePresentInElementLocated(By by, String text) {
		return fluentWait.until(ExpectedConditions.textToBePresentInElementLocated(by,text));
	}
	
	public Boolean textToBePresentInElement(WebElement webElement, String text) {
		return fluentWait.until(ExpectedConditions.textToBePresentInElement(webElement,text));
	}
	
	public Boolean textToBePresentInElementValue(WebElement webElement, String text) {
		return fluentWait.until(ExpectedConditions.textToBePresentInElementValue(webElement,text));
	}
	
	public Boolean titleContains(String title) {
		return fluentWait.until(ExpectedConditions.titleContains(title));
	}
	
	public Boolean titleIs(String title) {
		return fluentWait.until(ExpectedConditions.titleIs(title));
	}
	
	public Boolean urlContains(String url) {
		return fluentWait.until(ExpectedConditions.urlContains(url));
	}
	
	public Boolean urlToBe(String url) {
		return fluentWait.until(ExpectedConditions.urlToBe(url));
	}
	
	
	public WebElement visibilityOf(WebElement webElement) {
		return fluentWait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public List<WebElement> visibilityOfAllElements(List<WebElement> webElements) {
		return fluentWait.until(ExpectedConditions.visibilityOfAllElements(webElements));
	}
	
	public WebElement visibilityOfElementLocated(By by) {
		return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public List<WebElement> visibilityOfAllElementsLocatedBy(By by) {
		return fluentWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}
	
	public List<WebElement> visibilityOfNestedElementsLocatedBy(By parent, By child) {
		return fluentWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, child));
	}
	
}
