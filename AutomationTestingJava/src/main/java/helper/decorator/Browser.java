package helper.decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

/**
 * Created by User on 03.01.2019.
 */
public class Browser implements WebDriver {

    private WebDriver driver;

    public Browser(WebDriver driver){
        this.driver = driver;
    }
    public void get(String s) {
        System.out.println("Go to: "+ s);
        driver.get(s);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
    	System.out.println("Search for Elements by locator: "+by);
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
    	System.out.println("Search for Element by locator: "+by);
        return driver.findElement(by);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
    	System.out.println("The window is closing");
    	driver.close();
    }

    public void quit() {
    	System.out.println("The browser is closing");
    	driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    public Navigation navigate() {
        return driver.navigate();
    }

    public Options manage() {
        return driver.manage();
    }
}
