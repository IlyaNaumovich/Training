package garbage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class HelloWorld {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.marathonbet.com/en/");

//        driver.findElement(By.className("field-search")).sendKeys("Hello world!\n");
        driver.findElement(By.className("field-search")).sendKeys("Hello world!" + Keys.ENTER);
//        driver.findElement(By.linkText("Chat")).click();

//        driver.findElement(By.className("but-search")).click();

        System.out.println("URL = " + driver.getCurrentUrl());

        String resultValue = driver.findElement(By.className("search-page")).getText();

//        if (resultValue.contains("Hello world!")) {
//            System.out.println("Test passed");
//        } else {
//            System.out.println("Test failed");
//        }

//        Assert.assertTrue(resultValue.contains("Hello world!"));
        Assert.assertTrue(resultValue.contains("Hi world!"));

        driver.quit();
//        driver.close();



    }
}
