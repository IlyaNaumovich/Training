package helper.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 03.01.2019.
 */
public class ChromeDriverFactory extends WebDriverFactory{

    @Override
    public WebDriver create() {
    	   	
    	//System.setProperty("webdriver.chrome.driver", "/home/artsem/drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        if (System.getProperty("headless") != null) {
            if (System.getProperty("headless").equalsIgnoreCase("true")) {
                options.addArguments("--headless");
            }
        }

        if (System.getProperty("mobile") !=null) {
            Map<String, Object> deviceMetrics = new HashMap<String, Object>();
            deviceMetrics.put("width", 500);
            deviceMetrics.put("height", 1024);
            deviceMetrics.put("pixelRatio", 2);

            Map<String, Object> mobileEmulation = new HashMap<String, Object>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent", "Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");

            options.setExperimentalOption("mobileEmulation", mobileEmulation);

        }
        return new ChromeDriver(options);
    }
}
