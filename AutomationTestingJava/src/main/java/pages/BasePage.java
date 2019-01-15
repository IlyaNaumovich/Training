package pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public abstract class BasePage {

	protected WebDriver driver;
	
	@FindBy(id = "auth_login")
	protected WebElement login_filed_element;

	@FindBy(id = "auth_login_password")
	protected WebElement password_field_element;

	@FindBy(css=".login-pass .btn-login")
	protected WebElement login_button_element;

	@FindBy(className = "field-search")
	protected WebElement search_field_element;

	@FindBy(className = "languageSelectField")
	protected WebElement language_switcher_element;

	@FindBy(css = "#languageSelectField .menu-link")
	protected WebElement language_label_element;

//	public void moveToLanguageSwitcher() {
	public BasePage moveToLanguageSwitcher() {
		Actions action = new Actions(driver);
		action.moveToElement(language_switcher_element).build().perform();
		return this;
	}

	public String getLanguageColour() {
		return language_label_element.getCssValue("color");
	}

	public void search(String phrase) {
		search_field_element.sendKeys(phrase + Keys.ENTER);
	}

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
