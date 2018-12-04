package garbage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.marathonbet.com/su/");

        driver.findElement(By.id("auth_login")).sendKeys("Hello");
        System.out.println(driver.findElement(By.id("auth_login_password")).isDisplayed());
        driver.findElement(By.id("auth_login")).sendKeys(Keys.TAB);
        System.out.println(driver.findElement(By.id("auth_login_password")).isDisplayed());
//        driver.findElement(By.id("auth_login")).sendKeys(Keys.TAB + "Hi");

        driver.findElement(By.id("auth_login_password")).sendKeys("Hi");
        driver.findElement(By.className("login-pass")).findElement(By.className("btn-login")).click();

        String message = driver.findElement(By.id("any_message")).findElement(By.tagName("p")).getText();

        if (message.contains("The password does not meet the requirements")) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }

        driver.quit();
//        driver.close();



    }
}
