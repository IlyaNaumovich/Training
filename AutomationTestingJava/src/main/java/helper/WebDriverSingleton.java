package helper;

import helper.decorator.Browser;
import helper.factory.AndroidDriverFactory;
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
        	
        	MyLogger.debug("ChromeDriver selected");
        	
            factory = new ChromeDriverFactory();
        } 
        else if(browserName.equals("firefox")){
        	
        	MyLogger.debug("FirefoxDriver selected");
        	
            factory = new FirefoxDriverFactory();
        } else if(browserName.equals("androidChrome")) {
        	
        	MyLogger.debug("AndroidDriver selected");
        	
        	factory = new AndroidDriverFactory();
        }
        else{
        	
        	MyLogger.debug("RemoteDriver selected");
        	
            factory = new RemoteWebDriverFactory();
        }

        try {
            if(driver.get() == null){
            	
            	MyLogger.debug("New browser init");
                driver.set(new Browser(factory.create()));
            }
            
        }catch(MalformedURLException e) {
        	MyLogger.error(e.getMessage());
        }

        return driver.get();
    }

    public static void kill() throws MalformedURLException {
        init().quit();
        driver.set(null);
    }
}
