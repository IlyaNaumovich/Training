package helper.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidDriverFactory extends WebDriverFactory{

	
	@Override
	public WebDriver create() throws MalformedURLException {
		// TODO Auto-generated method stub
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator_5554");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		//caps.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		caps.setCapability("chromedriverExecutableDir", "/home/artsem/drivers/");
		caps.setCapability(MobileCapabilityType.APP, "/home/artsem/Downloads/marathonbetby.apk");
		caps.setCapability(MobileCapabilityType.AUTO_WEBVIEW, "true");
		
		AndroidDriver driver = new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), caps);
		
		Set<String> context = driver.getContextHandles();
		
		System.out.println(context);
		
		driver.context(context.toArray()[0]);
		return driver;
	}

}
