import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MarathonPerformance {
    protected WebDriver driver;
    private ProxyServer proxy;
    private Har har;

    @BeforeMethod
    public void setUp(){
        proxy = new ProxyServer(4444);
        proxy.start();
        proxy.setRequestTimeout(60000);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PROXY, proxy.seleniumProxy());

        driver = new ChromeDriver(caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() throws IOException {
        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            System.out.println(request.getUrl() + " : " + response.getStatus()
                    + ", " + entry.getTime() + "ms");

        }

        saveHAR();
        proxy.stop();
        driver.quit();
    }

    @Test
    public void marathonPerformance(){
        startHARRecording();
//        driver.get("https://www.marathonbet.com");
        driver.get("https://www.marathonbet.com/su/mobile.htm");
        finishHARRecording();
    }

    public void startHARRecording(){
        proxy.newHar("PerformanceTesting");
    }

    public void finishHARRecording(){
        har = proxy.getHar();
    }

    public void saveHAR() throws IOException {
        har.writeTo(new File("test.har"));
    }

}
