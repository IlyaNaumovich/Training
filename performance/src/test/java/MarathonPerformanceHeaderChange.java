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

public class MarathonPerformanceHeaderChange {
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

    @Test()
    public void marathonPerformance(){
//        RequestInterceptor requestChanger = new HeaderRemove(
//                "Accept-Language", "fr-CH, fr;q=0.9, en;q=0.8, de;q=0.7, *;q=0.5");

        RequestInterceptor requestChanger = new RequestChanger(
                  "User-Agent", "Mozilla/5.0 (iPad; CPU OS 11_0 like Mac OS X) AppleWebKit/604.1.34 (KHTML, like Gecko) Version/11.0 Mobile/15A5341f Safari/604.1");

//      RequestInterceptor requestChanger = new HeaderRemove(
//                "User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");

        proxy.addRequestInterceptor(requestChanger);
        driver.get("https://www.marathonbet.com");
//        driver.get("https://www.google.by");
    }


    public static class RequestChanger implements RequestInterceptor {
        private String headerBody;
        private String headerName;

        public RequestChanger(String headerName,String headerBody) {
            this.headerBody = headerBody;
            this.headerName = headerName;
        }

        //    @Override
        public void process(BrowserMobHttpRequest request, Har har) {
            Header[] headers = request.getMethod().getHeaders(headerName);
            for (Header header : request.getMethod().getHeaders(headerName)) {
                request.getMethod().removeHeader(header);
            }
            request.addRequestHeader(headerName, headerBody);
        }
    }
}
