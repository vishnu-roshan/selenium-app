package Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class testng2 {
    public String username = "Your username";
    public String accesskey= "";
    public static RemoteWebDriver driver = null;
    public String gridURL = "https://hub.lambdatest.com/wd/hub";

    @BeforeClass
    public void setup() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "95");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("user", "vishnu.rtestsigma");
        ltOptions.put("accessKey", "HqaciHt4GK2plhmGxdZXZ60oeUMkZF5rDYru60OEWTBu41sRHK");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.0.0");
        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL(gridURL), capabilities);
        System.out.println(driver);
    }
    @Test
    public static void Testcase(){
        driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);
        try {


            driver.get("https://www.amazon.com/");
            WebElement Search = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
            Search.sendKeys("iphone");
            WebElement submit = driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]"));
            submit.click();
            WebElement price = driver.findElement(By.xpath("//*[contains(text(),\"Apple iPhone\")]//ancestor::div[@class=\"a-section\"]//span[@class=\"a-price\"]"));

            String Price = price.getText();

            System.out.println("Price: "+Price);
            JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
            jsExecute.executeScript("lambda-status=" + "Passed");

        }

        catch (Exception e){
            JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
            jsExecute.executeScript("lambda-status=" + "Failed");

            e.printStackTrace();

        }
    }


    @AfterClass
    public static void tearDown() {
        try {
            driver.quit();
        } catch (

                Exception e) {
            e.printStackTrace();
            driver.quit();
        }
    }



}
