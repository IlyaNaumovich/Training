package helper.factory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 03.01.2019.
 */
public class RemoteWebDriverFactory extends WebDriverFactory {
    @Override
    public WebDriver create() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browserName", "chrome");
        cap.setPlatform(Platform.ANY);


        //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);

    }
}
