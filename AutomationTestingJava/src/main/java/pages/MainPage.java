package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

	@FindBy(id="any_message")
	private WebElement message_popup_element;
	
	private By message_popup = By.id("any_message");
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPopupMessage() {
		return message_popup_element.findElement(By.tagName("p")).getText();
	}
}
