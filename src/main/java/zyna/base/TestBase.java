package zyna.base;

import org.testng.annotations.*;

import zyna.common.SeleniumUtil;
import zyna.config.TestngConfig;

@Listeners(TestngConfig.class)
public class TestBase {

	private static ThreadLocal<SeleniumUtil> sel = new ThreadLocal<SeleniumUtil>();

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

	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		getSelenium().quit();
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
		return new Object[][] { { "Chrome" }, { "Edge" }, { "Firefox" } };
	}
}
