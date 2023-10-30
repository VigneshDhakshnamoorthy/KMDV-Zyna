package zyna.util;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.ExceptionInfo;
import com.aventstack.extentreports.model.service.ExceptionInfoService;

public class SeleniumUtil {

	private WebDriver driver;
	private ExtentTest Etest;
	private String BrowserName;
	private String testNAme;
	private int waitTime;

	public SeleniumUtil(ExtentTest ext, WebDriver dr, String BrowserName, int wait_Time) {
		this.driver = dr;
		this.Etest = ext;
		this.BrowserName = BrowserName;
		this.waitTime = wait_Time;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	public SeleniumUtil(ExtentTest ext, int wait_Time) {
		this.Etest = ext;
		this.waitTime = wait_Time;
	}

	public String getBrowserImage(String BrowserName) {
		switch (BrowserName) {
		case "chrome":
			return "<i class=\"fa fa-chrome\" aria-hidden=\"true\" /i>";
		case "edge":
			return "<i class=\"fa fa-edge\" aria-hidden=\"true\" /i>";
		case "firefox":
			return "<i class=\"fa fa-firefox\" aria-hidden=\"true\" /i>";
		case "opera":
			return "<i class=\"fa fa-opera\" aria-hidden=\"true\" /i>";
		case "safari":
			return "<i class=\"fa fa-safari\" aria-hidden=\"true\" /i>";
		case "chromeheadless":
			return "<i class=\"fa fa-chrome\" aria-hidden=\"true\" /i>";
		case "firefoxheadless":
			return "<i class=\"fa fa-firefox\" aria-hidden=\"true\" /i>";
		}
		return BrowserName;
	}

	public void open(String URLWeb) {
		driver.get(URLWeb);
	}

	public void quit() {
		driver.quit();
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public String getBrowserName() {
		return BrowserName;
	}

	public String getMethodName() {
		return testNAme;
	}

	public void Log(Object logMessage) {
		logConsole(logMessage);
		logExtent(logMessage);
	}

	public void Log(Object[] logMessage) {
		logConsole(logMessage);
		logExtent(logMessage);
	}

	public void addScreenshot() {
		String base64img = "data:image/png;base64," + getScreenshot();
		String base64imgA = "<a href=\"" + base64img
				+ "\" data-featherlight=\"image\"><span class=\"badge log pass-bg\">Screenshot Click"
				+ " Here</span></a>";
		logExtent(base64imgA);
	}
	
	public void addElementScreenshot(By by) {
		String base64img = "data:image/png;base64," + getScreenshot(by);
		String base64imgA = "<a href=\"" + base64img
				+ "\" data-featherlight=\"image\"><span class=\"badge log pass-bg\">Element Screenshot Click"
				+ " Here</span></a>";
		logExtent(base64imgA);
	}

	public void addFailScreenshot(Throwable throwable) {
		String base64img = "data:image/png;base64," + getScreenshot();
		String base64imgA = "<a href=\"" + base64img
				+ "\" data-featherlight=\"image\"><span class=\"badge log fail-bg\">Screenshot Click"
				+ " Here</span></a>";
		ExceptionInfo exceptionInfo = ExceptionInfoService.createExceptionInfo(throwable);
		String getErrorMessage = "<strong>" + throwable.toString() + "</strong>";
		String stackTrace = "<textarea readonly=\"\" class=\"code-block\">" + exceptionInfo.getStackTrace()
				+ "</textarea>";
		logFailExtent(getErrorMessage + "<br>"+ base64imgA + "<br>" + stackTrace);
	}
	
	public void addSkipExtent(Throwable throwable) {
		ExceptionInfo exceptionInfo = ExceptionInfoService.createExceptionInfo(throwable);
		String getErrorMessage = "<strong>" + throwable.getMessage().toString() + "</strong>";
		String stackTrace = "<textarea readonly=\"\" class=\"code-block\">" + exceptionInfo.getStackTrace()
				+ "</textarea>";
		logSkipExtent(getErrorMessage + "<br>" + stackTrace);
	}

	public String getScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}
	
	public String getScreenshot(By by) {
		return ((TakesScreenshot) getElement(by)).getScreenshotAs(OutputType.BASE64);
	}

	public void logConsole(Object logMessage) {
		System.out.println(logMessage.toString());
	}

	public void logConsole(Object[] logMessage) {
		System.out.println(Arrays.toString(logMessage));
	}

	public void logExtent(Object logMessage) {
		Etest.pass(logMessage.toString());
	}

	public void logExtent(Object[] logMessage) {
		Etest.pass(Arrays.toString(logMessage));
	}

	public void logSkipExtent(Object logMessage) {
		Etest.skip(logMessage.toString());
	}
	
	

	public void logFailExtent(Object logMessage) {
		Etest.fail(logMessage.toString());
	}

	public void logList(List<WebElement> ListWebelement) {
		Log(getList(ListWebelement).toArray());
	}

	public void logList(By by) {
		Log(getList(by).toArray());
	}

	public void logCList(List<WebElement> ListWebelement) {
		logConsole(getList(ListWebelement).toArray());
	}

	public void logCList(By by) {
		logConsole(getList(by).toArray());
	}

	public void logEList(List<WebElement> ListWebelement) {
		logExtent(getList(ListWebelement).toArray());

	}

	public void logEList(By by) {
		logExtent(getList(by).toArray());

	}

	public void navigateTo(String webUrl) {
		driver.navigate().to(webUrl);
		Log("Navigate URL -- " + webUrl);
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public void back() {
		driver.navigate().back();
	}

	public void forward() {
		driver.navigate().forward();
	}

	public void maximize() {
		driver.manage().window().maximize();
	}

	public void minimize() {
		driver.manage().window().minimize();
	}

	public Dimension windowDimension() {
		return driver.manage().window().getSize();
	}

	public int screenHeight() {
		Dimension windowDimension = windowDimension();
		return windowDimension.getHeight();
	}

	public int screenWidth() {
		Dimension windowDimension = windowDimension();
		return windowDimension.getWidth();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String currentTab() {
		return driver.getWindowHandle();
	}

	public Set<String> getTabs() {
		return driver.getWindowHandles();
	}

	public int countTabs() {
		return driver.getWindowHandles().size();
	}

	public void sleep(int Seconds) {
		try {
			Thread.sleep(Seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sleepMilliSeconds(int MilliSeconds) {
		try {
			Thread.sleep(MilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Boolean waitPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				if (js.executeScript("return document.readyState").toString().contains("active")
						|| js.executeScript("return document.readyState").toString().equals("complete")) {
					flag = true;
					break;
				} else {
					sleepMilliSeconds(1);
				}
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean switchTab(String tab) {
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				driver.switchTo().window(tab);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		Log("Switch to Tab -- " + tab);
		return flag;

	}

	public Boolean switchFrame(String frameName) {
		defaultFrame();
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				driver.switchTo().frame(frameName);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		Log("Switch to FrameName -- " + frameName);
		return flag;

	}

	public Boolean switchFrame(int frameIndex) {
		defaultFrame();
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				driver.switchTo().frame(frameIndex);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		Log("Switch to FrameIndex -- " + frameIndex);
		return flag;

	}

	public void parentFrame() {
		driver.switchTo().parentFrame();
		Log("Switch to Parent Frame");
	}

	public void defaultFrame() {
		driver.switchTo().defaultContent();
		Log("Switch to Default Content");

	}

	public WebElement getElement(By by) {
		WebElement findElement = null;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				findElement = driver.findElement(by);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		if (!flag) {
			Log("Element Not Found : " + by);
		}
		Assert.assertTrue(flag);
		return findElement;
	}

	public List<WebElement> getElements(By by) {
		List<WebElement> findElements = null;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				findElements = driver.findElements(by);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		if (!flag) {
			Log("Elements Not Found : " + by);
		}
		Assert.assertTrue(flag);
		return findElements;
	}

	public WebElement findBy_Xpath(String Xpath) {
		return getElement(By.xpath(Xpath));
	}

	public WebElement findBy_ID(String id) {
		return getElement(By.id(id));
	}

	public WebElement findBy_LinkText(String linkText) {
		return getElement(By.linkText(linkText));
	}

	public WebElement findBy_CssSelector(String cssSelector) {
		return getElement(By.cssSelector(cssSelector));
	}

	public WebElement findBy_Name(String name) {
		return getElement(By.name(name));
	}

	public WebElement findBy_PartialLinkText(String partialLinkText) {
		return getElement(By.partialLinkText(partialLinkText));
	}

	public WebElement findBy_ClassName(String className) {
		return getElement(By.className(className));
	}

	public WebElement findBy_TagName(String tagName) {
		return getElement(By.tagName(tagName));
	}

	public enum locatorType {
		XPATH, ID, LINKTEXT, CSSSELECTOR, NAME, PARTIALLINKTEXT, CLASSNAME
	}

	public List<WebElement> findAll_Xpath(String Xpath) {
		return getElements(By.xpath(Xpath));
	}

	public List<WebElement> findAll_ID(String id) {
		return getElements(By.id(id));
	}

	public List<WebElement> findAll_LinkText(String linkText) {
		return getElements(By.linkText(linkText));
	}

	public List<WebElement> findAll_CssSelector(String cssSelector) {
		return getElements(By.cssSelector(cssSelector));
	}

	public List<WebElement> findAll_Name(String name) {
		return getElements(By.name(name));
	}

	public List<WebElement> findAll_PartialLinkText(String partialLinkText) {
		return getElements(By.partialLinkText(partialLinkText));
	}

	public List<WebElement> findAll_ClassName(String className) {
		return getElements(By.className(className));
	}

	public List<WebElement> findAll_TagName(String tagName) {
		return getElements(By.tagName(tagName));
	}

	public By getBY(WebElement ele) {
		By by = null;
		String[] split = (ele.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");
		String selector = split[0].trim();
		String value = split[1].trim();

		switch (selector) {
		case "id":
			by = By.id(value);
			break;
		case "className":
			by = By.className(value);
			break;
		case "tagName":
			by = By.tagName(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "cssSelector":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			throw new IllegalStateException("locator : " + selector + " not found!!!");
		}
		return by;
	}

	public WebElement refreshElement(WebElement ele) {
		By by = getBY(ele);
		return getElement(by);
	}

	public String[] getLocatorString(WebElement ele) {
		By by = getBY(ele);
		return by.toString().split(": ");

	}

	public String[] getLocatorString(By by) {
		return by.toString().split(": ");

	}

	public SearchContext getShadowRoot(WebElement ele) {
		return ele.getShadowRoot();
	}

	public SearchContext getShadowRoot(By by) {
		return getElement(by).getShadowRoot();
	}

	public WebElement getShadowContent(By shadowHost, By shadowContent) {
		return getElement(shadowHost).getShadowRoot().findElement(shadowContent);
	}

	public boolean isDisplayed(By by) {
		return driver.findElement(by).isDisplayed();
	}

	public boolean isDisplayed(WebElement ele) {
		return ele.isDisplayed();
	}

	public boolean isEnabled(By by) {
		return driver.findElement(by).isEnabled();
	}

	public boolean isEnabled(WebElement ele) {
		return ele.isEnabled();
	}

	public boolean isSelected(By by) {
		return driver.findElement(by).isSelected();
	}

	public boolean isSelected(WebElement ele) {
		return ele.isSelected();
	}

	public Boolean waitDisplayed(WebElement ele) {
		Boolean flag = false;
		for (int i = 1; i <= waitTime * 1000; i++) {
			if (isDisplayed(ele)) {
				flag = true;
				break;
			} else {
				sleepMilliSeconds(1);
			}
		}
		if (!flag) {
			Log("Element Not Displayed Until " + waitTime + " Seconds");
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean waitDisplayed(By by) {
		Boolean flag = false;
		for (int i = 1; i <= waitTime * 1000; i++) {
			if (isDisplayed(by)) {
				flag = true;
				break;
			} else {
				sleepMilliSeconds(1);
			}
		}
		if (!flag) {
			Log("Element Not Displayed Until " + waitTime + " Seconds");
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean waitEnabled(WebElement ele) {
		Boolean flag = false;
		for (int i = 1; i <= waitTime * 1000; i++) {
			if (isEnabled(ele)) {
				flag = true;
				break;
			} else {
				sleepMilliSeconds(1);
			}
		}
		if (!flag) {
			Log("Element Not Enabled Until " + waitTime + " Seconds");
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean waitEnabled(By by) {
		Boolean flag = false;
		for (int i = 1; i <= waitTime * 1000; i++) {
			if (isEnabled(by)) {
				flag = true;
				break;
			} else {
				sleepMilliSeconds(1);
			}
		}
		if (!flag) {
			Log("Element Not Enabled Until " + waitTime + " Seconds");
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean waitSelected(WebElement ele) {
		Boolean flag = false;
		for (int i = 1; i <= waitTime * 1000; i++) {
			if (isSelected(ele)) {
				flag = true;
				break;
			} else {
				sleepMilliSeconds(1);
			}
		}
		if (!flag) {
			Log("Element Not Selected Until " + waitTime + " Seconds");
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean waitSelected(By by) {
		Boolean flag = false;
		for (int i = 1; i <= waitTime * 1000; i++) {
			if (isSelected(by)) {
				flag = true;
				break;
			} else {
				sleepMilliSeconds(1);
			}
		}
		if (!flag) {
			Log("Element Not Selected Until " + waitTime + " Seconds");
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public WaitUtil waitUNtil() {
		return new WaitUtil(driver, waitTime, 1);

	}

	public String getText(WebElement ele) {
		String getText = null;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				getText = ele.getText();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return getText;

	}

	public String getText(By by) {
		WebElement ele = getElement(by);
		return getText(ele);
	}

	public String getAttribute(WebElement ele, String AttributeName) {

		String attribute = null;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				attribute = ele.getAttribute(AttributeName);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return attribute;

	}

	public String getAttribute(By by, String AttributeName) {
		WebElement ele = getElement(by);
		return getAttribute(ele, AttributeName);
	}

	public Boolean setAttribute(WebElement ele, String Attribute, String Value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", ele, Attribute, Value);
				flag = getAttribute(ele, Attribute).equalsIgnoreCase(Value);
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Point getLocation(WebElement ele) {

		Point getLocation = null;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				getLocation = ele.getLocation();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return getLocation;
	}

	public Point getLocation(By by) {
		WebElement ele = getElement(by);
		return getLocation(ele);

	}

	public Boolean click(WebElement ele) {
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				ele.click();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean click(By by) {
		WebElement ele = getElement(by);
		return click(ele);
	}

	public void click(locatorType type, String locator) {
		if (locatorType.XPATH == type) {
			click(findBy_Xpath(locator));
		} else if (locatorType.ID == type) {
			click(findBy_ID(locator));
		} else if (locatorType.LINKTEXT == type) {
			click(findBy_LinkText(locator));
		} else if (locatorType.CSSSELECTOR == type) {
			click(findBy_CssSelector(locator));
		} else if (locatorType.NAME == type) {
			click(findBy_Name(locator));
		} else if (locatorType.PARTIALLINKTEXT == type) {
			click(findBy_PartialLinkText(locator));
		} else if (locatorType.CLASSNAME == type) {
			click(findBy_ClassName(locator));
		}
	}

	public Boolean clear(WebElement ele) {
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				ele.clear();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean clear(By by) {
		WebElement ele = getElement(by);
		return clear(ele);
	}

	public Boolean type(WebElement ele, Object Value) {
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				ele.sendKeys(Value.toString());
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean type(By by, Object Value) {
		WebElement ele = getElement(by);
		return type(ele, Value);
	}

	public Boolean typeENTER(WebElement ele, Object Value) {
		return type(ele, Value.toString() + Keys.ENTER);
	}

	public Boolean typeENTER(By by, Object Value) {
		WebElement ele = getElement(by);
		return type(ele, Value.toString() + Keys.ENTER);
	}

	public Boolean typeTAB(WebElement ele, Object Value) {
		return type(ele, Value.toString() + Keys.TAB);
	}

	public Boolean typeTAB(By by, Object Value) {
		WebElement ele = getElement(by);
		return type(ele, Value.toString() + Keys.TAB);
	}

	public Boolean clickList(List<WebElement> ListWebelement, String ClickValue) {
		Boolean flag = false;
		for (WebElement we : ListWebelement) {
			if (we.getText().toString().equalsIgnoreCase(ClickValue)) {
				click(we);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean clickList(By by, String ClickValue) {
		List<WebElement> ListWebelement = getElements(by);
		return clickList(ListWebelement, ClickValue);
	}

	public Boolean clickList(List<WebElement> ListWebelement, int IndexValue) {
		return click(ListWebelement.get(IndexValue));
	}

	public Boolean clickList(By by, int IndexValue) {
		List<WebElement> ListWebelement = getElements(by);
		return clickList(ListWebelement, IndexValue);
	}

	public int countList(List<WebElement> ListWebelement) {
		return ListWebelement.size();
	}

	public int countList(By by) {
		List<WebElement> ListWebelement = getElements(by);
		return ListWebelement.size();
	}

	public List<String> getList(List<WebElement> ListWebelement) {
		List<String> strings = new ArrayList<String>();
		for (WebElement we : ListWebelement) {
			strings.add(we.getText().toString());
		}
		return strings;
	}

	public List<String> getList(By by) {
		List<WebElement> ListWebelement = getElements(by);
		return getList(ListWebelement);
	}

	public boolean listContains(List<WebElement> ListWebelement, String containValue) {
		List<String> strings = new ArrayList<String>();
		for (WebElement we : ListWebelement) {
			strings.add(we.getText().toString());
		}
		return strings.contains(containValue);
	}

	public boolean listContains(By by, String containValue) {
		List<WebElement> ListWebelement = getElements(by);
		return listContains(ListWebelement, containValue);
	}

	public Alert getAlert() {
		Alert alert = null;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				alert = driver.switchTo().alert();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return alert;
	}

	public void acceptAlert() {
		getAlert().accept();
	}

	public void dismissAlert() {
		getAlert().dismiss();
	}

	public String textAlert() {
		return getAlert().getText();
	}

	public void typeAlert(String alertSend) {
		getAlert().sendKeys(alertSend);
	}

	public Boolean jsClick(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				js.executeScript("arguments[0].click();", ele);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean jsClick(By by) {
		WebElement ele = getElement(by);
		return jsClick(ele);
	}

	public Boolean jsType(WebElement ele, Object Value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				js.executeScript("arguments[0].value='" + Value.toString() + "';", ele);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean jsType(By by, Object Value) {
		WebElement ele = getElement(by);
		return jsType(ele, Value);
	}

	public Boolean executeScript(String Script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				js.executeScript(Script);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean executeScript(String Script, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				js.executeScript(Script, ele);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean scrollIntoView(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				js.executeScript("arguments[0].scrollIntoView();", ele);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean scrollIntoView(By by) {
		WebElement ele = getElement(by);
		return scrollIntoView(ele);
	}

	public Boolean scrollTO_XY(int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				for (int j = 1; j <= y; j += 6) {
					js.executeScript("window.scrollTo(" + x + "," + j + ")");
				}
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean scrollTO_XY(Point location) {
		int x = location.getX();
		int y = location.getY();
		return scrollTO_XY(x, y);
	}

	public Boolean scrollTO_XY(WebElement ele) {
		Point location = getLocation(ele);
		return scrollTO_XY(location);
	}

	public Boolean scrollTO_XY(By by) {
		WebElement ele = getElement(by);
		return scrollTO_XY(ele);
	}

	public Boolean scrollBY_XY(int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				for (int j = 1; j <= y; j += 6) {
					js.executeScript("window.scrollBy(" + x + "," + j + ")");
				}
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean scrollBY_XY(Point location) {
		int x = location.getX();
		int y = location.getY();
		return scrollBY_XY(x, y);
	}

	public Boolean scrollBY_XY(WebElement ele) {
		Point location = getLocation(ele);
		return scrollBY_XY(location);
	}

	public Boolean scrollBY_XY(By by) {
		WebElement ele = getElement(by);
		return scrollBY_XY(ele);
	}

	public Boolean selectDropDown(WebElement ele, String VisibleText) {
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Select sel = new Select(ele);
				sel.selectByVisibleText(VisibleText);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean selectDropDown(By by, String VisibleText) {
		WebElement ele = getElement(by);
		return selectDropDown(ele, VisibleText);
	}

	public String getDropDownSelectValue(WebElement ele) {
		String getFirstSelectedOption = null;
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Select sel = new Select(ele);
				getFirstSelectedOption = sel.getFirstSelectedOption().toString();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return getFirstSelectedOption;
	}

	public String getDropDownSelectValue(By by) {
		WebElement ele = getElement(by);
		return getDropDownSelectValue(ele);
	}

	public void clickSleep(By by, int Seconds) {
		click(by);
		sleep(Seconds);
	}

	public void clickSleep(WebElement ele, int Seconds) {
		click(ele);
		sleep(Seconds);
	}

	public Boolean moveTo(WebElement ele) {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.moveToElement(ele).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean moveTo(By by) {
		WebElement ele = getElement(by);
		return moveTo(ele);

	}

	public Boolean contextClick(WebElement ele) {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.contextClick(ele).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean contextClick(By by) {
		WebElement ele = getElement(by);
		return contextClick(ele);

	}

	public Boolean contextClick() {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.contextClick().perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean doubleClick(WebElement ele) {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.doubleClick(ele).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean doubleClick(By by) {
		WebElement ele = getElement(by);
		return doubleClick(ele);
	}

	public Boolean doubleClick() {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.doubleClick().perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean dragAndDrop(WebElement source, WebElement target) {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.dragAndDrop(source, target).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				source = refreshElement(source);
				target = refreshElement(target);

			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean dragAndDrop(By source, By target) {
		WebElement Source = getElement(source);
		WebElement Target = getElement(target);
		return dragAndDrop(Source, Target);
	}

	public Boolean actionClick(WebElement ele) {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.click(ele).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean actionClick(By by) {
		WebElement ele = getElement(by);
		return actionClick(ele);

	}

	public Boolean actionClick() {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.click().perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean actionType(WebElement ele, Object Value) {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.sendKeys(ele, Value.toString()).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
				ele = refreshElement(ele);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean actionType(By by, Object Value) {
		WebElement ele = getElement(by);
		return actionType(ele, Value);

	}

	public Boolean actionType(Object Value) {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.sendKeys(Value.toString()).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean actionENTER() {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.sendKeys(Keys.ENTER).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean actionESCAPE() {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.sendKeys(Keys.ESCAPE).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean actionRETURN() {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.sendKeys(Keys.RETURN).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean actionTAB() {
		Actions actions = new Actions(driver);
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				actions.sendKeys(Keys.TAB).perform();
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}
		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean actionDOWN(int count) {
		Boolean flag = false;

		for (int value = 0; value < count; value++) {
			Actions actions = new Actions(driver);

			for (int i = 1; i <= waitTime * 1000; i++) {
				try {
					actions.sendKeys(Keys.ARROW_DOWN).perform();
					flag = true;
					break;
				} catch (Exception e) {
					sleepMilliSeconds(1);
				}
			}

			Assert.assertTrue(flag);
		}

		return flag;

	}

	public Boolean actionUP(int count) {
		Boolean flag = false;

		for (int value = 0; value < count; value++) {
			Actions actions = new Actions(driver);

			for (int i = 1; i <= waitTime * 1000; i++) {
				try {
					actions.sendKeys(Keys.ARROW_UP).perform();
					flag = true;
					break;
				} catch (Exception e) {
					sleepMilliSeconds(1);
				}
			}

			Assert.assertTrue(flag);
		}

		return flag;

	}

	public Boolean actionRIGHT(int count) {

		Boolean flag = false;

		for (int value = 0; value < count; value++) {
			Actions actions = new Actions(driver);

			for (int i = 1; i <= waitTime * 1000; i++) {
				try {
					actions.sendKeys(Keys.ARROW_RIGHT).perform();
					flag = true;
					break;
				} catch (Exception e) {
					sleepMilliSeconds(1);
				}
			}

			Assert.assertTrue(flag);
		}

		return flag;
	}

	public Boolean actionLEFT(int count) {

		Boolean flag = false;

		for (int value = 0; value < count; value++) {
			Actions actions = new Actions(driver);

			for (int i = 1; i <= waitTime * 1000; i++) {
				try {
					actions.sendKeys(Keys.ARROW_LEFT).perform();
					flag = true;
					break;
				} catch (Exception e) {
					sleepMilliSeconds(1);
				}
			}

			Assert.assertTrue(flag);
		}

		return flag;
	}

	public Boolean robotDOWN(int count) {
		Boolean flag = false;

		for (int value = 0; value < count; value++) {

			for (int i = 1; i <= waitTime * 1000; i++) {
				try {
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					flag = true;
					break;
				} catch (Exception e) {
					sleepMilliSeconds(1);
				}
			}

			Assert.assertTrue(flag);
		}
		return flag;

	}

	public Boolean robotUP(int count) {
		Boolean flag = false;

		for (int value = 0; value < count; value++) {

			for (int i = 1; i <= waitTime * 1000; i++) {
				try {
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyRelease(KeyEvent.VK_UP);
					flag = true;
					break;
				} catch (Exception e) {
					sleepMilliSeconds(1);
				}
			}

			Assert.assertTrue(flag);
		}
		return flag;

	}

	public Boolean robotRIGHT(int count) {
		Boolean flag = false;

		for (int value = 0; value < count; value++) {

			for (int i = 1; i <= waitTime * 1000; i++) {
				try {
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_RIGHT);
					robot.keyRelease(KeyEvent.VK_RIGHT);
					flag = true;
					break;
				} catch (Exception e) {
					sleepMilliSeconds(1);
				}
			}

			Assert.assertTrue(flag);
		}
		return flag;

	}

	public Boolean robotLEFT(int count) {
		Boolean flag = false;

		for (int value = 0; value < count; value++) {

			for (int i = 1; i <= waitTime * 1000; i++) {
				try {
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_LEFT);
					robot.keyRelease(KeyEvent.VK_LEFT);
					flag = true;
					break;
				} catch (Exception e) {
					sleepMilliSeconds(1);
				}
			}

			Assert.assertTrue(flag);
		}
		return flag;

	}

	public Boolean robotENTER() {

		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean robotTAB() {

		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean robotESCAPE() {

		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean robotBACKSPACE() {

		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean robotSELECT() {

		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean robotCOPY() {

		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean robotPASTE() {

		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean robotCUT() {

		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean mouseLeftClick() {
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean mouseRightClick() {
		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;
	}

	public Boolean mouseMove(int x, int y) {

		Boolean flag = false;

		for (int i = 1; i <= waitTime * 1000; i++) {
			try {
				Robot robot = new Robot();
				robot.mouseMove(x, y);
				flag = true;
				break;
			} catch (Exception e) {
				sleepMilliSeconds(1);
			}
		}

		Assert.assertTrue(flag);
		return flag;

	}

	public Boolean mouseMove(Point location) {
		int x = location.getX();
		int y = location.getY();
		return mouseMove(x, y);
	}

	public Boolean mouseMove(WebElement ele) {
		Point location = getLocation(ele);
		return mouseMove(location);
	}

	public Boolean mouseMove(By by) {
		WebElement ele = getElement(by);
		return mouseMove(ele);
	}

	public String IntToString(int value) {
		return Integer.toString(value);
	}

	public int StringToInt(String value) {
		return Integer.parseInt(value);
	}

	public boolean compare(String value1, String value2) {
		return value1.equalsIgnoreCase(value2);
	}

	public int compare(int value1, int value2) {
		return Integer.compare(value1, value2);
	}

	public int compare(Double value1, Double value2) {
		return Double.compare(value1, value2);
	}

	public LocalDate Today() {
		return LocalDate.now();
	}

	public LocalDate Yesterday() {
		return LocalDate.now().minusDays(1);
	}

	public LocalDate Tomorrow() {
		return LocalDate.now().plusDays(2);
	}

	public LocalDate localDate(int Year, int Month, int day) {
		return LocalDate.of(Year, Month, day);
	}

	public String[] toCharArray(String StringArray) {
		char[] charArray = StringArray.toCharArray();
		String[] stringArray = new String[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			stringArray[i] = String.valueOf(charArray[i]);
		}
		return stringArray;
	}

	public int[] toCharArray(int IntArray) {
		String StringInt = Integer.toString(IntArray);
		char[] charArray = StringInt.toCharArray();
		int[] intArray = new int[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			intArray[i] = Integer.parseInt(String.valueOf(charArray[i]));
		}
		return intArray;
	}

	public int getRandom(int min, int max) {
		return RandomUtils.nextInt(min, max + 1);
	}

	public int getRandom(int[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	public String getRandom(String[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	public boolean StringEquals(String value1, String value2) {
		return value1.equalsIgnoreCase(value2);
	}

	public String replaceBetweenDelimiters(String input, String startDelimiter, String endDelimiter,
			String replacement) {
		String regex = Pattern.quote(startDelimiter) + "(.*?)" + Pattern.quote(endDelimiter);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		if (matcher.find()) {
			return matcher.replaceFirst(Matcher.quoteReplacement(replacement));
		}

		return input;
	}
}
