package pages.SauceLabs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends LoginPage {

	public HomePage() {
		super();
	}

	@FindBy(xpath = "//span[@class='shopping_cart_badge']")
	private WebElement Cart_item_count;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	private WebElement clickonCart;

	@FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory']")
	private WebElement AddCart;

	@FindBy(xpath = "//button[@class='btn btn_secondary back btn_large inventory_details_back_button']")
	private WebElement Back;

	public HomePage AddProduct(String ProNam) {
		WebElement productName = ZynaWeb().findBy_LinkText(ProNam);
		ZynaWeb().scrollBY_XY(productName);
		ZynaWeb().sleep(1);
		ZynaWeb().jsClick(productName);
		ZynaWeb().Log(ProNam + " - Product Clicked");
		ZynaWeb().sleep(1);
		ZynaWeb().actionClick(ZynaWeb().waitUNtil().elementToBeClickable(AddCart));
		ZynaWeb().Log(ProNam + " - AddCart Button Clicked");
		ZynaWeb().sleep(1);
		ZynaWeb().actionClick(Back);
		ZynaWeb().Log(ProNam + " - Back Button Clicked");
		ZynaWeb().sleep(1);
		return this;

	}

	public HomePage Check_Cart_item_count(int CompareValue) {
		ZynaWeb().sleep(1);
		Assert.assertTrue(ZynaWeb().StringToInt(Cart_item_count.getText()) == CompareValue);
		return this;

	}

	public CartPage clickonCart() {
		ZynaWeb().jsClick(clickonCart);
		return new CartPage();
	}

}
