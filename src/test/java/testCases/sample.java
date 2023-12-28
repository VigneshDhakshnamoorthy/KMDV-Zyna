package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.SauceLabs.CheckoutOverview;
import zyna.base.TestBase;
import zyna.common.Zyna;

public class sample extends TestBase {

	@Zyna(AuthorName = "Vignesh")
	@Test(dataProvider = "browsers")
	public void sample1(String browserName) {
		CheckoutOverview checkoutOverview = new CheckoutOverview();
		checkoutOverview.openApp("https://www.saucedemo.com/");
		ZynaWeb().addPdfScreenshot();
		checkoutOverview.Login().AddProduct("Sauce Labs Onesie").Check_Cart_item_count(1).clickonCart().QTYcounitem(1)
				.clickCheckout().sleep(10);
	}

	@Test(dataProvider = "browsers")
	public void sample2(String browserName) {
		ZynaWeb().open("http://www.google.co.in");
		ZynaWeb().addPdfText("Step 1");
		ZynaWeb().addPdfScreenshot();
		ZynaWeb().addPdfText("Step 2");
		ZynaWeb().addPdfScreenshot();
		ZynaWeb().addPdfText("Step 3");
		ZynaWeb().addPdfScreenshot();
		ZynaWeb().addPdfText("Step 4");
		ZynaWeb().addPdfScreenshot();
		ZynaWeb().addPdfText("Step 5");
		ZynaWeb().addPdfText("Step 6");
		ZynaWeb().addPdfScreenshot();

		Assert.assertTrue(false);
	}

	@Zyna(AuthorName = "Vignesh")
	@Test()
	public void sample3() {
		CheckoutOverview checkoutOverview = new CheckoutOverview();
		checkoutOverview.openApp("https://www.saucedemo.com/");
		ZynaWeb().addPdfScreenshot();
		checkoutOverview.Login();
		ZynaWeb().addPdfScreenshot();
		checkoutOverview.AddProduct("Sauce Labs Onesie");
		ZynaWeb().addPdfScreenshot();
		checkoutOverview.Check_Cart_item_count(1);
		ZynaWeb().addPdfScreenshot();
		checkoutOverview.clickonCart();
		ZynaWeb().addPdfScreenshot();
		checkoutOverview.QTYcounitem(1);
		ZynaWeb().addPdfScreenshot();
		checkoutOverview.clickCheckout().sleep(2);
		ZynaWeb().addPdfScreenshot();

	}
}
