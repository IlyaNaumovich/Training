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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MarathonSpeedEmulationHar {
    protected WebDriver driver;
    private ProxyServer proxy;
    private Har har;
    private String harName;

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

    @Test(dataProvider ="getConnectionParameters")
    public void marathonPerformance(String connectionName,
                                    Integer downStream,
                                    Integer upStream,
                                    Integer latency){
        proxy.setDownstreamKbps(downStream);
        proxy.setUpstreamKbps(upStream);
        proxy.setLatency(latency);

        startHARRecording();
        driver.get("https://www.marathonbet.com/su/mobile.htm");
        harName = connectionName + "_" + downStream + "_" + upStream + "_" + latency;
        finishHARRecording();
    }



    public void startHARRecording(){
        proxy.newHar("PerformanceTesting");
    }

    public void finishHARRecording(){
        har = proxy.getHar();
    }

    public void saveHAR() throws IOException {
        if (harName == null) {
            har.writeTo(new File("test.har"));
        } else {
            har.writeTo(new File(harName));
        }
    }

    @DataProvider
    public Object[][] getConnectionParameters() {
        return new Object[][] {
                {"GPRS", 50, 20, 5},
                {"GPRS", 50, 20, 500},
                {"Regular 2G", 250, 50, 250},
                {"Good 2G", 450, 150, 250},
                {"Regular 3G", 750, 250, 100},
                {"Good 3G", 1500, 750, 40},
                {"Regular 4G", 4000, 3000, 20},
                {"DSL", 2000, 1000, 5},
                {"WiFi", 30000, 15000, 2}
        };
    }
}
