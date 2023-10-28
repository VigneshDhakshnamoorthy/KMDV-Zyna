package sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import zyna.base.TestBase;
import zyna.config.BrowserConfig;

public class sample extends TestBase {

    @Test(dataProvider = "browsers")
	public void sample1(String browserName) {
    	WebDriver browser = new BrowserConfig().getBrowser(browserName);
    	browser.get("http://www.google.com");
    	browser.quit();
	}
}
