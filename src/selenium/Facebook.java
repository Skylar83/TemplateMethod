package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Facebook extends Network{
    private WebDriver g_driver;
    public Facebook(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public boolean logIn(String userName,String password)throws InterruptedException  {
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        this.g_driver = driver;
        driver.get("https://facebook.com/login");
        driver.findElement(By.id("email")).sendKeys(this.userName);
        driver.findElement(By.id("pass")).sendKeys(this.password);
        driver.findElement(By.id("loginbutton")).click();
        return true;
    }

    public boolean sendData(byte[] data) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        g_driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[1]/a")).click();
        TimeUnit.SECONDS.sleep(1);
        g_driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[3]/div/div[2]/div/div/div/div[1]/div")).click();
        TimeUnit.SECONDS.sleep(1);
        g_driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[4]/div/div/div[1]/div/div[2]/div/div/div/form/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div/div/div/div[2]/div/div/div/div")).sendKeys(new String(data));
        g_driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[4]/div/div/div[1]/div/div[2]/div/div/div/form/div/div[1]/div/div/div/div[3]/div[2]/div/div")).click();
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on Facebook");
            return true;
        } else {
            return false;
        }
    }

    @Override
    void logOut() {
        System.out.println("User: '" + userName + "' was logged out from Facebook");
    g_driver.quit();
    }
}
