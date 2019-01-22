package helper;

import helper.decorator.Browser;
import helper.factory.ChromeDriverFactory;
import helper.factory.FirefoxDriverFactory;
import helper.factory.RemoteWebDriverFactory;
import helper.factory.WebDriverFactory;

import java.net.MalformedURLException;

/**
 * Created by User on 03.01.2019.
 */
public class WebDriverSingleton {

    public static ThreadLocal<Browser> driver = new ThreadLocal<Browser>();

    private WebDriverSingleton(){

    }

    public static Browser init(){

        WebDriverFactory factory;
        String browserName = System.getProperty("browserName");
        if (browserName == null) {
            browserName = "chrome";
        }

        if(browserName.equals("chrome")){
            factory = new ChromeDriverFactory();
        } else if(browserName.equals("firefox")){
            factory = new FirefoxDriverFactory();
        } else{
            factory = new RemoteWebDriverFactory();
        }

        try {
            if(driver.get() == null){
                driver.set(new Browser(factory.create()));
            }
            
        }catch(MalformedURLException e) {
        	e.printStackTrace();
        }

        return driver.get();
    }

    public static void kill() throws MalformedURLException {
        init().quit();
        driver.set(null);
    }
}
