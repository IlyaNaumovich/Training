import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by User on 31.01.2019.
 */
public class MarathonSelenide {
    @BeforeTest
    public void setup() {
        Configuration.baseUrl = "https://www.marathonbet.com";
    }

    @BeforeMethod
    public void start() {
        String lang = System.getProperty("Language");
        if (lang == null) {
            lang = "en";
        }
        open("/" + lang);
    }

    @Test
    public void searchTest() {
        $(".field-search").sendKeys("Hello world" + Keys.ENTER);
        $$(".search-page").filter(Condition.text("Hello world")).shouldHaveSize(5);
    }

    @Test
    public void loginTest() {
        $(By.id("auth_login")).sendKeys("Hello" + Keys.TAB);
        $("#auth_login_password").sendKeys("World");
        $(".login-pass .btn-login").click();
        $("#any_message").shouldHave(Condition.text("The password does not meet the requirements"));
    }
}
