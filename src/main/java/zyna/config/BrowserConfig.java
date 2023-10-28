package zyna.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserConfig {

	public WebDriver getBrowser(String BrowserName) {
		WebDriver dov = null;
		switch (BrowserName.toLowerCase()) {
		case "firefox":
			dov = new FirefoxDriver();
			break;
		case "chrome":
			dov = new ChromeDriver();
			break;
		case "edge":
			dov = new EdgeDriver();
			break;
		case "safari":
			dov = new SafariDriver();
			break;
		case "firefoxheadless":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-headless");
			dov = new FirefoxDriver(firefoxOptions);
			break;
		case "chromeheadless":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless=new");
			dov = new ChromeDriver(chromeOptions);
			break;
		default:
			System.out.println("Update Proper BrowserName in File");

		}
		return dov;
	}

}
