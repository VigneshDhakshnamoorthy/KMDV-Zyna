package sample;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import zyna.base.TestBase;

public class sample extends TestBase {

    @Test(dataProvider = "browsers")
	public void sample1(String browserName) {
    	getSelenium().open("http://www.google.co.in");
    	getSelenium().Log(browserName);
    	getSelenium().addElementScreenshot(By.xpath("//*[@name='q']"));
    }
    
    @Test(dataProvider = "browsers")
   	public void sample2(String browserName) {
    	getSelenium().open("http://www.google.co.in");
    	getSelenium().Log(browserName);
    	Assert.assertTrue(false);
   	}
    
    @Test()
   	public void sample3() {
    	getSelenium().open("http://www.google.co.in");
    	getSelenium().Log(getSelenium().getBrowserName());
   	}
}
