package zyna.base;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import zyna.common.Zyna;
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
	protected static File reportRoot = new File(projectPathRoot, "Reports");
	protected static File extentReportRoot = new File(reportRoot, "ExtentReports");

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

	public synchronized ExtentTest getExT(String[] testCaseInformation) {
		if (!ExT.containsKey(testCaseInformation[0])) {
			if (testCaseInformation.length == 4) {
			ExT.put(testCaseInformation[0], Ereport.createTest(testCaseInformation[0]).assignCategory(testCaseInformation[1])
					.assignDevice(testCaseInformation[2]).assignAuthor(testCaseInformation[3]));
			}else {
				ExT.put(testCaseInformation[0], Ereport.createTest(testCaseInformation[0]).assignCategory(testCaseInformation[1])
						.assignDevice(testCaseInformation[2]));
				
			}
		}
		return ExT.get(testCaseInformation[0]);
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
		} else if (result.getStatus() == ITestResult.FAILURE) {
			ZynaWeb().addFailScreenshot(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ZynaWeb().addSkipExtent(result.getThrowable());
		}
		if (ZynaWeb().getDriver() != null) {
			ZynaWeb().quit();
		}
	}

	@BeforeSuite
	public void beforeSuite() {

	}

	@AfterSuite
	public void afterSuite() {

	}

	public synchronized static SeleniumUtil ZynaWeb() {
		return sel.get();
	}

	public synchronized static void setSelenium(SeleniumUtil seleniumUtil) {
		sel.set(seleniumUtil);
	}
	
	public void initWeb(ITestResult result) {
		Object[] parameters = result.getParameters();
		String[] testCaseInformation = getInfo(result);
		String BName = null;
		WebDriver WebD = null;
		try {
			if (parameters != null && parameters.length > 0) {
				BName = parameters[0].toString();
				WebD = new BrowserConfig().getBrowser(BName);
				setSelenium(new SeleniumUtil(getExT(testCaseInformation).createNode(BName), WebD, BName, 15));
			} else {
				BName = "Edge";
				WebD = new BrowserConfig().getBrowser(BName);
				setSelenium(new SeleniumUtil(getExT(testCaseInformation), WebD, BName, 15));
			}
		} catch (Exception e) {
			setSelenium(new SeleniumUtil(getExT(testCaseInformation).createNode(BName), 15));
			throw new SkipException(e.toString());

		}
	}

	@DataProvider(name = "browsers", parallel = true)
	public synchronized Object[][] getBrowsers() {
		return new Object[][] { { "Chrome" }, { "Safari" }, { "Firefox" } };
	}
	
	public static String[] getInfo(ITestResult result) {
		String testcaseName = result.getName();
		String className =(result.getTestClass().getName()).split("\\.")[1];
		String packageName =(result.getTestClass().getName()).split("\\.")[0];
		String tcpName = testcaseName +"."+className ;
		String osName = System.getProperty("os.name");
		try {
		String author = Reporter.getCurrentTestResult().getMethod().getConstructorOrMethod().getMethod().getAnnotation(Zyna.class).AuthorName().toString();
		String[] infoNames = {tcpName,packageName,osName,author};
		return infoNames;
		
		} catch (Exception e) {
			String[] infoNames = {tcpName,packageName, osName};
			return infoNames;
		}
	}
	
}
