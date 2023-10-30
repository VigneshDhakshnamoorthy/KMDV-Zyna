package zyna.base;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import zyna.config.BrowserConfig;
import zyna.config.TestngConfig;
import zyna.util.SeleniumUtil;

@Listeners(TestngConfig.class)
public class TestBase {

	protected static ThreadLocal<SeleniumUtil> sel = new ThreadLocal<SeleniumUtil>();
	protected static HashMap<String, ExtentTest> ExT = new HashMap<String, ExtentTest>();
	protected static ExtentReports Ereport;
	protected static ExtentSparkReporter Espark;
	protected static File projectPathRoot = new File(System.getProperty("user.dir"));
	protected static File reportRoot = new File(projectPathRoot, "Report");

	@BeforeTest
	public void beforeTest() {

	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeClass
	public void beforeClass() {

	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestResult result) {

	}

	public synchronized ExtentTest getExT(String resultName) {
		if (!ExT.containsKey(resultName)) {
			ExT.put(resultName, Ereport.createTest(resultName));
		}
		return ExT.get(resultName);
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			getSelenium().Log(result.getName());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			getSelenium().addFailScreenshot(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			getSelenium().addSkipExtent(result.getThrowable());
		}
		if (getSelenium().getDriver() != null) {
			getSelenium().quit();
		}
	}

	@BeforeSuite
	public void beforeSuite() {

	}

	@AfterSuite
	public void afterSuite() {

	}

	public synchronized static SeleniumUtil getSelenium() {
		return sel.get();
	}

	public synchronized static void setSelenium(SeleniumUtil seleniumUtil) {
		sel.set(seleniumUtil);
	}

	@DataProvider(name = "browsers", parallel = true)
	public synchronized Object[][] getBrowsers() {
		return new Object[][] { { "Chrome" }, { "Safari" }, { "Firefox" } };
	}
}
