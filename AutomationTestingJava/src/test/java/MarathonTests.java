import helper.Dictionary;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MarathonTests {
    @Test
    @Parameters({"Language"})
    public void helloWorldTest(@Optional("en") String lan) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String url = "https://www.marathonbet.com/%lan%/".replace("%lan%", lan);

        driver.get(url);

        driver.findElement(By.className("field-search")).sendKeys("Hello world!" + Keys.ENTER);

        Assert.assertTrue(driver.findElements(By.className("search-page")).size() >0, "Search page should be opened");

        String resultValue = driver.findElement(By.className("search-page")).getText();

        Assert.assertTrue(resultValue.contains("Hello world!"), "Text should contain 'Hello world!'");

        driver.quit();
    }

    @Test
    @Parameters({"Language"})
    public void negativeLogin(@Optional String lan) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        if (lan == null) {
            lan = "en";
        }

        String url = "https://www.marathonbet.com/" + lan + "/";
        driver.get(url);

        driver.findElement(By.id("auth_login")).sendKeys("Hello");

//        WebElement loginField =  driver.findElement(By.id("auth_login"));
//        loginField.sendKeys("Hello");

        driver.findElement(By.id("auth_login")).sendKeys(Keys.TAB);
        driver.findElement(By.id("auth_login_password")).sendKeys("Hi");
        driver.findElement(By.className("login-pass")).findElement(By.className("btn-login")).click();

        String actualMessage = driver.findElement(By.id("any_message")).findElement(By.tagName("p")).getText();

        String realMessage = Dictionary.getTranslationForDoesntMeetReq(lan);
//        Dictionary dictionary = new Dictionary();
//
//        String realMessage = dictionary.getTranslationForDoesntMeetReq(lan);

//        Assert.assertTrue(message.contentEquals("The password does not meet the requirements"),
//                "The message should be 'The password does not meet the requirements'");

//        Assert.assertEquals(actualMessage, "The password does not meet the requirements",
//                "The message should be 'The password does not meet the requirements'");

        String infoMessage = String.format("The message should be '%s'", realMessage);

        Assert.assertEquals(actualMessage, realMessage,infoMessage);

        driver.quit();
    }

    @Test
    @Parameters({"Language"})
    public void colorOfLanguageSwitcherChanged(@Optional String lan) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        if (lan == null) {
            lan = "en";
        }

        String url = "https://www.marathonbet.com/" + lan + "/";
        driver.get(url);

        Actions action = new Actions(driver);
        WebElement languageSwitcher = driver.findElement(By.id("languageSelectField"));
        WebElement languageLabel = languageSwitcher.findElement(By.className("menu-link"));

        String initialColour = languageLabel.getCssValue("color");
        Assert.assertEquals(initialColour, "rgba(255, 255, 255, 1)", "Colour should be white");
//        Assert.assertEquals(initialColour, "rgba(255, 255, 255, 0)", "Colour should be white");

        action.moveToElement(languageSwitcher).build().perform();

        String finalColour = languageLabel.getCssValue("color");
        Assert.assertEquals(finalColour, "rgba(255, 242, 0, 1)", "Colour should be yellow");


        driver.quit();
    }

    @Test
    @Parameters({"Language"})
    public void colorOfLanguageSwitcherChangedSoftAssert(@Optional String lan) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        if (lan == null) {
            lan = "en";
        }

        String url = "https://www.marathonbet.com/" + lan + "/";
        driver.get(url);

        SoftAssert softAssert =new SoftAssert();

        Actions action = new Actions(driver);
        WebElement languageSwitcher = driver.findElement(By.id("languageSelectField"));
        WebElement languageLabel = languageSwitcher.findElement(By.className("menu-link"));

        String initialColour = languageLabel.getCssValue("color");
//        Assert.assertEquals(initialColour, "rgba(255, 255, 255, 1)", "Colour should be white");
//        Assert.assertEquals(initialColour, "rgba(255, 255, 255, 0)", "Colour should be white");

        softAssert.assertEquals(initialColour, "rgba(255, 255, 255, 0)", "Colour should be white");

        action.moveToElement(languageSwitcher).build().perform();

        String finalColour = languageLabel.getCssValue("color");
//        Assert.assertEquals(finalColour, "rgba(255, 242, 0, 1)", "Colour should be yellow");
        softAssert.assertEquals(finalColour, "rgba(255, 242, 55, 1)", "Colour should be yellow");

        softAssert.assertAll();

        driver.quit();
    }





}
