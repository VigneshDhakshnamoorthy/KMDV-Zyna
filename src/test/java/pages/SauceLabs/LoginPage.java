package pages.SauceLabs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zyna.base.PageBase;

public class LoginPage extends PageBase {

	public LoginPage() {
		super();
	}

	@FindBy(id = "user-name")
	private WebElement username;
	@FindBy(id = "password")
	private WebElement password;
	@FindBy(id = "login-button")
	private WebElement loginbutton;

	public LoginPage Login(String UserNa, String UserPas) {
		ZynaWeb().type(username, UserNa);
		ZynaWeb().type(password, UserPas);
		ZynaWeb().click(loginbutton);
		ZynaWeb().Log("Login Failed");
		return this;

	}

	public HomePage Login() {
		ZynaWeb().type(username, "standard_user");
		ZynaWeb().type(password, "secret_sauce");
		ZynaWeb().click(loginbutton);
		ZynaWeb().Log("Direct Login successfully");
		return new HomePage();

	}

}
