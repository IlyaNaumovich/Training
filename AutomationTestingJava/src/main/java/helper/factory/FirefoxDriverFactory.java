package helper.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by User on 03.01.2019.
 */
public class FirefoxDriverFactory extends WebDriverFactory {
    @Override
    public WebDriver create() {
    	//System.setProperty("webdriver.gecko.driver", "/home/artsem/drivers/geckodriver");
        return new FirefoxDriver();
    }
}
