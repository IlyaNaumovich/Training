package helper.factory;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * Created by User on 03.01.2019.
 */
public abstract class  WebDriverFactory {

    public abstract WebDriver create() throws MalformedURLException;

}
