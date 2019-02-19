package pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.BasePageMobile;

public class MobileMainPage extends BasePageMobile{

	
	public MobileMainPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void login(String login, String password) {
		
		clickLoginMobileButton();
		fillLogin(login);
		fillPassword(password);
		clickPopupLogin();
	}
}
