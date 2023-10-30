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
		checkoutOverview.Login().AddProduct("Sauce Labs Onesie").Check_Cart_item_count(1)
		.clickonCart().QTYcounitem(1).clickCheckout().sleep(10);
	}

	@Test(dataProvider = "browsers")
	public void sample2(String browserName) {
		ZynaWeb().open("http://www.google.co.in");
		Assert.assertTrue(false);
	}

	@Zyna(AuthorName = "Vignesh")
	@Test()
	public void sample3() {
		CheckoutOverview checkoutOverview = new CheckoutOverview();
		checkoutOverview.openApp("https://www.saucedemo.com/");
		checkoutOverview.Login().AddProduct("Sauce Labs Onesie").Check_Cart_item_count(1)
		.clickonCart().QTYcounitem(1).clickCheckout().sleep(10);
	}
}
