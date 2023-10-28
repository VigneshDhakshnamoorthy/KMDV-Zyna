package zyna.base;

import org.testng.annotations.*;

import zyna.common.SeleniumUtil;
import zyna.config.TestngConfig;

@Listeners(TestngConfig.class)
public class TestBase {
	
	ThreadLocal<SeleniumUtil> sel = new ThreadLocal<SeleniumUtil>();
	@BeforeTest
	public void beforeTest() {
		
	}
	
	@AfterTest
	public void afterTest() {
		
	}

	
	@BeforeSuite
	public void beforeSuite() {
		
	}
	
	@AfterSuite
	public void afterSuite() {
		
	}
	
	
	@DataProvider(name = "browsers")
    public Object[][] getBrowsers() {
        return new Object[][] {
            { "Chrome" },
            { "Firefox" }
        };
    }
}
