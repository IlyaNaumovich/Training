package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePageMobile {

	protected WebDriver driver;
	
	@FindBy(xpath = "//button[@data-test='button--open-login-popup']")
	WebElement loginButtonElement;

	@FindBy(xpath = "//input[@data-test='input--login']")
	WebElement usernameInputElement;

	@FindBy(xpath = "//input[@data-test='input--password']")
	WebElement passwordInputElement;

	@FindBy(xpath = "//div[@class='recaptcha-checkbox-checkmark']")
	WebElement notRobotCheckbox;

	public void clickLoginMobileButton() {
		loginButtonElement.click();

		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
	}



}
