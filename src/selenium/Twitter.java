package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.xml.sax.helpers.AttributesImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Twitter extends Network{
    private WebDriver g_driver;
    public Twitter(String userName, String password){
    this.userName = userName;
    this.password = password;
    }
    public boolean logIn(String username,String password) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://twitter.com/i/flow/login");
        TimeUnit.SECONDS.sleep(3);
        driver.findElement(By.name("username")).sendKeys(this.userName);
        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div")).click();
        TimeUnit.SECONDS.sleep(1);
        Boolean isPresent = driver.findElements(By.name("password")).size() > 0;

        if(!isPresent){
        driver.findElement(By.name("text")).sendKeys("samuel.husein.sa@gmail.com");
        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div")).click();
        }
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.name("password")).sendKeys(this.password);
        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div")).click();
        return true;
    }


    public boolean sendData(byte[] data) throws InterruptedException {


        TimeUnit.SECONDS.sleep(3);
        g_driver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div[2]/div[1]/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div/div/div/label/div[1]/div/div/div/div/div[2]/div/div/div/div")).click();
        TimeUnit.SECONDS.sleep(1);
        g_driver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div[2]/div[1]/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div/div/div/label/div[1]/div/div/div/div/div[2]/div/div/div/div")).sendKeys(new String(data));
        TimeUnit.SECONDS.sleep(1);
        //g_driver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div[2]/div[1]/div/div/div/div[2]/div[3]/div/div/div[2]/div[3]/div")).click();
        g_driver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div[2]/div[1]/div/div/div/div[2]/div[3]/div/div/div[2]/div[3]")).click();
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on Twitter");
            return true;
        } else {
            return false;
        }
    }

    @Override
    void logOut() {
        System.out.println("User: '" + userName + "' was logged out from Twitter");
        g_driver.quit();
    }
}
