package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 15.01.2019.
 */
public class MainPageMobile extends BasePageMobile {
    public MainPageMobile(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
