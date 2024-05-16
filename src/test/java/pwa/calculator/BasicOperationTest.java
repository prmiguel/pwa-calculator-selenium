package pwa.calculator;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class BasicOperationTest extends BaseTest {

    @Test
    public void additionOf2Number() {
        webDriver.findElement(By.cssSelector("div[data-value='1']")).click();
        webDriver.findElement(By.cssSelector("div[data-value='+']")).click();
        webDriver.findElement(By.cssSelector("div[data-value='3']")).click();
        webDriver.findElement(By.cssSelector("div.equal")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.output")).getText(), "4");
    }
}
