import helper.Dictionary;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class MarathonTests {
    private WebDriver driver;
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
    @Parameters({"Language"})
    public void BeforeMethod(@Optional("en") String lang) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        if (lang == null) {
            lang = "en";
        }

        String url = "https://www.marathonbet.com/%lan%/".replace("%lan%", lang);

        driver.get(url);

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
    public void finish(){
        driver.quit();
    }

    @Test(groups = {"search"}, dependsOnMethods = {"negativeLogin"})
    public void helloWorldTest() {
        driver.findElement(By.className("field-search")).sendKeys("Hello world!" + Keys.ENTER);

        Assert.assertTrue(driver.findElements(By.className("search-page")).size() >0, "Search page should be opened");

        String resultValue = driver.findElement(By.className("search-page")).getText();

        Assert.assertTrue(resultValue.contains("Hello world!"), "Text should contain 'Hello world!'");
    }

    @Test(groups = {"login", "regression"}, priority = 20)
    public void negativeLogin() {
        System.out.println("negativeLogin");
        driver.findElement(By.id("auth_login")).sendKeys("Hello");
        driver.findElement(By.id("auth_login")).sendKeys(Keys.TAB);
        driver.findElement(By.id("auth_login_password")).sendKeys("Hi");
        driver.findElement(By.className("login-pass")).findElement(By.className("btn-login")).click();

        String actualMessage = driver.findElement(By.id("any_message")).findElement(By.tagName("p")).getText();
        String realMessage = Dictionary.getTranslationForDoesntMeetReq(lan);
        String infoMessage = String.format("The message should be '%s'", realMessage);

        Assert.assertEquals(actualMessage, realMessage,infoMessage);
    }

    @Test(description = "Colour changed",
        groups = {"css", "regression"}, priority = 50)
    public void colorOfLanguageSwitcherChanged() {
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
        SoftAssert softAssert =new SoftAssert();

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
}
