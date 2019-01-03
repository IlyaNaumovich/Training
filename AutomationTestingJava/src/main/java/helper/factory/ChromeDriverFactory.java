package helper.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by User on 03.01.2019.
 */
public class ChromeDriverFactory extends WebDriverFactory{

    @Override
    public WebDriver create() {
        return new ChromeDriver();
    }
}
