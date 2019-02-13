import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import net.lightbody.bmp.proxy.http.BrowserMobHttpRequest;
import net.lightbody.bmp.proxy.http.RequestInterceptor;
import org.apache.http.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MarathonPerformanceHeaderRemove {
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
    }

    @AfterMethod
    public void tearDown() throws IOException {
        proxy.stop();
        driver.quit();
    }

    @Test
    public void marathonPerformance() throws InterruptedException {

        RequestInterceptor requestChanger = new HeaderRemove(
                  "User-Agent");
//        RequestInterceptor requestChanger = new HeaderRemove(
//                  "Accept-Language");


        proxy.addRequestInterceptor(requestChanger);
        driver.get("https://www.marathonbet.com");

        Thread.sleep(2000);
//        driver.get("https://www.google.by");
    }


    public static class HeaderRemove implements RequestInterceptor {
        private String headerName;

        public HeaderRemove(String headerName) {
            this.headerName = headerName;
        }

        //    @Override
        public void process(BrowserMobHttpRequest request, Har har) {
            for (Header header : request.getMethod().getHeaders(headerName)) {
                request.getMethod().removeHeader(header);
            }
        }
    }
}
