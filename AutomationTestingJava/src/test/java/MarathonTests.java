import helper.Dictionary;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Test
public class MarathonTests {
    private ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();
    private String lan;

    @BeforeClass
    public void BeforeClass() {
        System.out.println("BeforeClass");
    }

    @BeforeGroups
    public void BeforeGroups() {
        System.out.println("BeforeGroups");
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"Language", "browserName"})
    public void BeforeMethod(@Optional("en") String lang,@Optional("chrome") String browserName) throws MalformedURLException {
        //driver = new ChromeDriver();
        DesiredCapabilities cap = new DesiredCapabilities();
        if(browserName.equals("chrome")){
            cap.setCapability("browserName", "chrome");
            cap.setPlatform(Platform.ANY);

            //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
            drivers.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap));
        }else if(browserName.equals("firefox")){
            //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new FirefoxOptions());
            drivers.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new FirefoxOptions()));
        }


        drivers.get().manage().window().maximize();

        if (lang == null) {
            lang = "en";
        }

        String url = "https://www.marathonbet.com/%lan%/".replace("%lan%", lang);

        drivers.get().get(url);

        lan = lang;
    }

    @BeforeSuite
    public void BeforeSuite() {
        System.out.println("BeforeSuite");
    }

    @BeforeTest
    public void BeforeTest() {
        System.out.print("BeforeTest");
    }

    @AfterMethod(alwaysRun = true)
    public void finish() {
        drivers.get().quit();
        drivers.set(null);
    }

    @Test(groups = {"search"}, dependsOnMethods = {"negativeLogin"})
    public void helloWorldTest() {
        WebDriver driver = drivers.get();
        driver.findElement(By.className("field-search")).sendKeys("Hello world!" + Keys.ENTER);

        Assert.assertTrue(driver.findElements(By.className("search-page")).size() > 0, "Search page should be opened");

        String resultValue = driver.findElement(By.className("search-page")).getText();

        Assert.assertTrue(resultValue.contains("Hello world!"), "Text should contain 'Hello world!'");
    }

    @Test(groups = {"login", "regression"}, priority = 20)
    public void negativeLogin() {
        WebDriver driver = drivers.get();
        System.out.println("negativeLogin");
        driver.findElement(By.id("auth_login")).sendKeys("Hello");
        driver.findElement(By.id("auth_login")).sendKeys(Keys.TAB);
        driver.findElement(By.id("auth_login_password")).sendKeys("Hi");
        driver.findElement(By.className("login-pass")).findElement(By.className("btn-login")).click();

        String actualMessage = driver.findElement(By.id("any_message")).findElement(By.tagName("p")).getText();
        String realMessage = Dictionary.getTranslationForDoesntMeetReq(lan);
        String infoMessage = String.format("The message should be '%s'", realMessage);

        Assert.assertEquals(actualMessage, realMessage, infoMessage);
    }

    @Test(groups = {"login", "regression"}, priority = 20, dataProvider = "users")
    public void negativeLogin_dataProvider(String login, String password) {
        WebDriver driver = drivers.get();
        System.out.println("negativeLogin");
        driver.findElement(By.id("auth_login")).sendKeys(login);
        driver.findElement(By.id("auth_login")).sendKeys(Keys.TAB);
        driver.findElement(By.id("auth_login_password")).sendKeys(password);
        driver.findElement(By.className("login-pass")).findElement(By.className("btn-login")).click();

        String actualMessage = driver.findElement(By.id("any_message")).findElement(By.tagName("p")).getText();
        String realMessage = Dictionary.getTranslationForDoesntMeetReq(lan);
        String infoMessage = String.format("The message should be '%s'", realMessage);

        Assert.assertEquals(actualMessage, realMessage, infoMessage);
    }

    @Test(description = "Colour changed",
            groups = {"css", "regression"}, priority = 50)
    public void colorOfLanguageSwitcherChanged() {
        WebDriver driver = drivers.get();
        System.out.println("colorOfLanguageSwitcherChanged");
        Actions action = new Actions(driver);
        WebElement languageSwitcher = driver.findElement(By.id("languageSelectField"));
        WebElement languageLabel = languageSwitcher.findElement(By.className("menu-link"));

        String initialColour = languageLabel.getCssValue("color");
        Assert.assertEquals(initialColour, "rgba(255, 255, 255, 1)", "Colour should be white");

        action.moveToElement(languageSwitcher).build().perform();

        String finalColour = languageLabel.getCssValue("color");
        Assert.assertEquals(finalColour, "rgba(255, 242, 0, 1)", "Colour should be yellow");
    }

    @Test(groups = {"css"})
    public void colorOfLanguageSwitcherChangedSoftAssert() {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = drivers.get();
        Actions action = new Actions(driver);
        WebElement languageSwitcher = driver.findElement(By.id("languageSelectField"));
        WebElement languageLabel = languageSwitcher.findElement(By.className("menu-link"));

        String initialColour = languageLabel.getCssValue("color");
        softAssert.assertEquals(initialColour, "rgba(255, 255, 255, 0)", "Colour should be white");

        action.moveToElement(languageSwitcher).build().perform();

        String finalColour = languageLabel.getCssValue("color");
        softAssert.assertEquals(finalColour, "rgba(255, 242, 55, 1)", "Colour should be yellow");

        softAssert.assertAll();
    }

    @Test
    public void verifyLinks() throws IOException {
        Set<String> linkSet = new HashSet<String>();
        WebDriver driver = drivers.get();
        for (WebElement element : driver.findElements(By.xpath("//*[@href]"))) {
            String ref = element.getAttribute("href");

            if (ref.length() > 0) {
                linkSet.add(ref);
            }
        }

        for (String url : linkSet) {
            try {
                URL obj = new URL(url);
                HttpURLConnection con;

                if (url.contains("https://")) {
                    con = (HttpsURLConnection) obj.openConnection();
                } else {
                    con = (HttpURLConnection) obj.openConnection();
                }
                con.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                if (responseCode > 200) {
                    System.out.println("For url " + url + " got response code" + responseCode);
                }

            } catch (Exception e) {
                System.out.println(e);
                System.out.println(url);
            }
        }

        int d = 0;
    }

    @DataProvider(name="users", parallel = true)
    public Object [][] getCredentials(Method m) {

        if(m.getName().equals("negativeLogin_dataProvider")){
            System.out.println(m.getName());
            return new Object[][] {
                    {"Hello", "World"},
                    {"Привет", "Валет"},
                    {"1230", "222"},
                    {"sdf1233", "!!!!!"}

            };
        }else{
            System.out.println("Something else");
            return new Object[][] {
                    {"Hello", "World"},
                    {"Привет", "Валет"},
                    {"1230", "222"},

            };
        }

    }
}
