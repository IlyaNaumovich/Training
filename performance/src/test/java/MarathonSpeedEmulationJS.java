import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MarathonSpeedEmulationJS {
    protected WebDriver driver;
    private ProxyServer proxy;
    private Har har;
    private String harName;
    BufferedWriter bw;

    @BeforeTest
    public void setUpBw() throws IOException {
        bw = new BufferedWriter(new FileWriter("1.csv"));
        bw.write("mode;DOM content;Load event;URL");
        bw.newLine();
    }

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
        proxy.stop();
        driver.quit();
    }

    @AfterTest
    public void finish() throws IOException {
        bw.flush();
        bw.close();
    }

    @Test(dataProvider ="getConnectionParameters")
    public void marathonPerformance(String connectionName,
                                    Integer downStream,
                                    Integer upStream,
                                    Integer latency) throws IOException {
        proxy.setDownstreamKbps(downStream);
        proxy.setUpstreamKbps(upStream);
        proxy.setLatency(latency);

        String mode = harName = connectionName + "_" + downStream + "_" + upStream + "_" + latency;
            driver.get("https://mobile.marathonbet.com/");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
            long requestStart = (Long) js.executeScript("return window.performance.timing.requestStart;");
            long domContentLoadedEventEnd = (Long) js.executeScript("return window.performance.timing.domContentLoadedEventEnd    ;");

            bw.write(mode + ";"  + (domContentLoadedEventEnd - requestStart) + ";" + (loadEventEnd - requestStart) + ";");
            bw.newLine();
            driver.close();
//        bw.close();
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
                {"Regular 4G", 4000, 3000, 20},
                {"Regular 4G", 4000, 3000, 20},
                {"Regular 4G", 4000, 3000, 20},
                {"Regular 4G", 4000, 3000, 20},
                {"DSL", 2000, 1000, 5},
                {"DSL", 2000, 1000, 5},
                {"DSL", 2000, 1000, 5},
                {"DSL", 2000, 1000, 5},
                {"DSL", 2000, 1000, 5},
                {"WiFi", 30000, 15000, 2},
                {"WiFi", 30000, 15000, 2},
                {"WiFi", 30000, 15000, 2},
                {"WiFi", 30000, 15000, 2},
                {"WiFi", 30000, 15000, 2},
                {"WiFi", 30000, 15000, 2}
        };
    }



//    performForMode("GPRS", 50, 20, 5, bmp, option, bw);
//    performForMode("GPRS", 50, 20, 500, bmp, option, bw);
//    performForMode("GPRS", 50, 20, 5, bmp, option, bw);
//    performForMode("Regular 2G", 250, 50, 250, bmp, option, bw);
//    performForMode("Good 2G", 450, 150, 250, bmp, option, bw);
//    performForMode("Regular 3G", 750, 250, 100, bmp, option, bw);
//    performForMode("Good 3G", 1500, 750, 40, bmp, option, bw);
//    performForMode("Regular 4G", 4000, 3000, 20, bmp, option, bw);
//    performForMode("DSL", 2000, 1000, 5, bmp, option, bw);
//    performForMode("WiFi", 30000, 15000, 2, bmp, option, bw);


}
