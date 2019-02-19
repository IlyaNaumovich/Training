import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import helper.Dictionary;
import helper.WebDriverSingleton;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import pages.MainPage;
import pages.mobile.MobileMainPage;

public class MobileTest {

	private WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
    @Parameters({"browserName"})
    public void BeforeMethod(@Optional("androidChrome") String browserName) throws MalformedURLException {
        driver = WebDriverSingleton.init();

       // driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
       // driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        

       // String url = "https://www.marathonbet.com";

        //driver.get(url);

    }
	
	
	@Test
	public void firstTest() throws MalformedURLException {
		
	/*	DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "amelnikov");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		//caps.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		caps.setCapability(MobileCapabilityType.APP, "/home/artsem/Downloads/marathonbetby.apk");
		
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"),caps);
		
		Set<String> context = driver.getContextHandles();
		
		System.out.println(context);*/
	}
	
	
	@Test(groups = {"login", "regression"}, priority = 20)
    public void negativeLogin() throws MalformedURLException {
        System.out.println("Mobile_negativeLogin");

        MobileMainPage mainPage = new MobileMainPage(WebDriverSingleton.init());
        mainPage.login("Hello", "Hi");

        //String actualMessage = mainPage.getPopupMessage();
        
        /*String realMessage = Dictionary.getTranslationForDoesntMeetReq(lan);
        String infoMessage = String.format("The message should be '%s'", realMessage);

        Assert.assertEquals(actualMessage, realMessage, infoMessage);*/
        
        Assert.assertTrue(mainPage.isCapchaDisplayed(),"Capcha is not displayed");
    }
	
    @AfterMethod(alwaysRun = true)
    public void finish() throws MalformedURLException {
        WebDriverSingleton.kill();
    }
}
	