package pages.SauceLabs;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends HomePage {

	public CartPage() {
		super();
	}

	@FindBy(xpath = "//button[@id='checkout']")
	private WebElement clickoncheckout;
	@FindBy(xpath = "//button[@class='btn btn_secondary btn_small btn_inventory']")
	private WebElement RemoveCart;
	@FindBy(xpath = "//button[@class='btn btn_secondary back btn_large inventory_details_back_button']")
	private WebElement Back;
	@FindBy(xpath = "//*[contains(@class,'cart_quantity')]")
	private List<WebElement> count;

	public CartPage QTYcounitem(int itemcount) {

		for (int i = 1; i <= itemcount; i++) {
			ZynaWeb().Log("Item " + i + " Qty : " + count.get(i).getText());
		}
		return this;

	}

	public CartPage removeProduct(String ProNam) {
		ZynaWeb().jsClick(ZynaWeb().findBy_LinkText(ProNam));
		ZynaWeb().jsClick(RemoveCart);
		ZynaWeb().jsClick(Back);
		return this;
	}

	public CheckoutInform clickCheckout() {
		ZynaWeb().jsClick(clickoncheckout);
		return new CheckoutInform();

	}

}
