package pwa.calculator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BaseTest {
    protected WebDriver webDriver;

    @BeforeClass
    public void openApp() throws MalformedURLException {
        URL serverurl = new URL("http://192.168.0.101:4444/wd/hub");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("test-type", "--no-sandbox", "--disable-dev-shm-usage", "--disable-notifications");
        edgeOptions.setExperimentalOption("detach", true);
        webDriver = new RemoteWebDriver(serverurl, edgeOptions);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.get("edge://apps");
        wait.until(visibilityOfElementLocated(By.cssSelector("button[id^='open-install-button-']")));
        webDriver.findElement(By.cssSelector("button[id^='open-install-button-']")).click();
        wait.until(numberOfWindowsToBe(2));
        Set<String> windows = webDriver.getWindowHandles();
        webDriver.switchTo().window(windows.stream().toList().getLast());
        wait.until(invisibilityOfElementLocated(By.cssSelector("div.loader")));
    }

    @AfterClass
    public void closeApp() {
        webDriver.quit();
    }
}
