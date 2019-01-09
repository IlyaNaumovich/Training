package helper;

import helper.factory.ChromeDriverFactory;
import helper.factory.FirefoxDriverFactory;
import helper.factory.RemoteWebDriverFactory;
import helper.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * Created by User on 03.01.2019.
 */
public class WebDriverSingleton {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private WebDriverSingleton(){

    }

    public static WebDriver init() throws MalformedURLException {

        WebDriverFactory factory;
        String browserName = System.getProperty("browserName");

        if(browserName.equals("chrome")){
            factory = new ChromeDriverFactory();
        } else if(browserName.equals("firefox")){
            factory = new FirefoxDriverFactory();
        } else{
            factory = new RemoteWebDriverFactory();
        }

        if(driver.get() == null){
            driver.set(factory.create());
        }
        return driver.get();
    }

    public static void kill() throws MalformedURLException {
        init().quit();
        driver.set(null);
    }
}
