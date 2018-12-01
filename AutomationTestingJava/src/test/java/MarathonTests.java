import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MarathonTests {

    @Test
    @Parameters({"Language"})
    public void helloWorldTest(String lan) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String url = "https://www.marathonbet.com/%lan%/".replace("%lan%", lan);

        driver.get(url);

        driver.findElement(By.className("field-search")).sendKeys("Hello world!" + Keys.ENTER);

        String resultValue = driver.findElement(By.className("search-page")).getText();

        Assert.assertTrue(resultValue.contains("Hello world!"), "Text should contain 'Hello world!'");

        driver.quit();
    }
}
