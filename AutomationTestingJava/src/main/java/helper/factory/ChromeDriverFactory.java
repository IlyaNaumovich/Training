package helper.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by User on 03.01.2019.
 */
public class ChromeDriverFactory extends WebDriverFactory{

    @Override
    public WebDriver create() {
        ChromeOptions options = new ChromeOptions();
        if (System.getProperty("headless") != null) {
            if (System.getProperty("headless").equalsIgnoreCase("true")) {
                options.addArguments("--headless");
            }
        }
        return new ChromeDriver(options);
    }
}
