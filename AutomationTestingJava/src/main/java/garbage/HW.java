package garbage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HW {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.marathonbet.com/en/");

        driver.findElement(By.id("auth_login")).sendKeys("Hello!");
        driver.findElement(By.className("pass")).click();
        driver.findElement(By.id("auth_login_password")).sendKeys("hello");

        driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        driver.findElement(By.xpath("//button[@type='submit']"));

//        driver.findElement(By.className("login")).click();
        driver.findElement(By.className("login-pass")).findElement(By.className("btn-login")).click();

        System.out.println(driver.findElement(By.id("any_message")).getText());


        String resultValue = driver.findElement(By.className("search-page")).getText();

        if (resultValue.contains("Hello world!")) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }

        driver.quit();
//        driver.close();



    }
}
