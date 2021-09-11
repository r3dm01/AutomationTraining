package CommonLib;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Browsers {

    public WebDriver driver;

    public void chromeBrowser(String baseUrl2){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //Url
        driver.navigate().to(baseUrl2);


    }
}
