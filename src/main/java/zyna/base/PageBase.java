package zyna.base;

import org.openqa.selenium.support.PageFactory;


public class PageBase extends TestBase {

	public PageBase() {
		ZynaWeb().waitPageLoad();
		PageFactory.initElements(ZynaWeb().getDriver(), this);
	}
	
	public void openApp(String WebURL) {
		ZynaWeb().open(WebURL);
	}
	
	public void sleep(int seconds) {
		ZynaWeb().sleep(seconds);
	}
}
