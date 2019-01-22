package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	@FindBy(xpath = "//div[@id='g-recaptcha']//iframe")
	WebElement notRobotFrame;

	@FindBy(xpath = "//a[contains(@href ,'join')]/span")
	WebElement joinButton;

	public void ctrlClickJoinUs() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Actions builder = new Actions(driver);

		builder
//				.keyDown(Keys.LEFT_CONTROL)
				.click(joinButton)
//				.moveToElement(joinButton)
//				.click()
//				.keyUp(Keys.LEFT_CONTROL)
				.build()
				.perform();

		int d = 0;

	}

	public void clickLoginMobileButton() {
		loginButtonElement.click();
	}

	public void fillLogin(String login) {
		usernameInputElement.sendKeys(login);
	}

	public void fillPassword(String password) {
		passwordInputElement.sendKeys(password);
	}

	public void clickNotRobot() {
		WebDriverWait wait =new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(notRobotFrame));

		driver.switchTo().frame(notRobotFrame);
		WebElement body = driver.findElement(By.tagName("body"));
		body.sendKeys(Keys.TAB);
		body.sendKeys(Keys.TAB);
		body.sendKeys(Keys.TAB);
		body.sendKeys(Keys.TAB);
		body.sendKeys(Keys.ENTER);

		int d = 0;



//		WebDriverWait wait =new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOf(notRobotCheckbox));
//		notRobotCheckbox.click();
	}
}
