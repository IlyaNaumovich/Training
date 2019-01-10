package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public abstract class BasePage {

	protected WebDriver driver;
	
	@FindBy(id = "auth_login")
	protected WebElement login_filed_element;
	@FindBy(id = "auth_login_password")
	protected WebElement password_field_element;
	@FindBy(css=".login-pass .btn-login")
	protected WebElement login_button_element;
	
	protected By login_field = By.id("auth_login");
	protected By password_field = By.id("auth_login_password");
	protected By login_button = By.cssSelector(".login-pass .btn-login");
	
	/*public void fillLogin(String login) {
		driver.findElement(login_field).sendKeys(login);
	}
	
	public void fillPassword(String password) {
		driver.findElement(password_field).sendKeys(password);
	}
	
	public void clickLoginButton() {
		driver.findElement(login_button).click();
	}*/
	
	public void fillLogin(String login) {
		login_filed_element.sendKeys(login);
	}
	
	public void fillPassword(String password) {
		password_field_element.sendKeys(password);
	}
	
	public void clickLoginButton() {
		login_button_element.click();
	}
	
	public void login(String login, String password) {
		fillLogin(login);
		login_filed_element.sendKeys(Keys.TAB);
		fillPassword(password);
		clickLoginButton();
	}
	
}
