package pages.SauceLabs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CheckoutInform extends CartPage {

	public CheckoutInform() {
		super();
	}

	@FindBy(id = "first-name")
	private WebElement firstname;
	@FindBy(id = "last-name")
	private WebElement lastname;
	@FindBy(id = "postal-code")
	WebElement postalcode;
	@FindBy(xpath = "//input[@id='continue']")
	private WebElement ClickContinue;

	public void EnterFirstName(String Fname) {
		ZynaWeb().type(firstname, Fname);

	}

	public void EnterLastName(String Lname) {
		ZynaWeb().type(lastname, Lname);

	}

	public void EnterZipCode(String Zipcode) {
		ZynaWeb().type(postalcode, Zipcode);

	}

	public CheckoutOverview ClickContinue() {
		ZynaWeb().jsClick(ClickContinue);
		return new CheckoutOverview();
	}

}
