package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MainPage extends BasePage {

	@FindBy(id="any_message")
	private WebElement message_popup_element;
	
	private By message_popup = By.id("any_message");
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPopupMessage() {
		Assert.assertTrue(message_popup_element.isDisplayed(), "Popup element should be displayed");
		return message_popup_element.findElement(By.tagName("p")).getText();
	}

	public void open() {
		String lang = System.getProperty("Language");
		if (lang == null) {
			lang = "en";
		}

		String url = "https://www.marathonbet.com/%lan%/".replace("%lan%", lang);

		driver.get(url);
	}
}
