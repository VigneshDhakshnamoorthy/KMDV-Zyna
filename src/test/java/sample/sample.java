package sample;

import org.testng.annotations.Test;

import zyna.base.TestBase;

public class sample extends TestBase {

    @Test(dataProvider = "browsers")
	public void sample1(String browserName) {
    	getSelenium().open("http://www.google.com");
    	getSelenium().Log(browserName);
    }
    
    @Test(dataProvider = "browsers")
   	public void sample2(String browserName) {
    	getSelenium().open("http://www.google.com");
    	getSelenium().Log(browserName);
   	}
    
    @Test(dataProvider = "browsers")
   	public void sample3(String browserName) {
    	getSelenium().open("http://www.google.com");
    	getSelenium().Log(browserName);
   	}
}
