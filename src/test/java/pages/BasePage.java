package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BasePage {

    public static WebDriver driver;
    public static Properties prop = new Properties();
    public static Properties loc = new Properties();
    public static FileReader fr;

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        if (driver == null) {
            System.out.println(System.getProperty("user.dir"));
            FileReader conf_fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\config.properties");
            prop.load(conf_fr);
        }
        if (prop.getProperty("browser").equalsIgnoreCase("CHROME")) {
            WebDriverManager.chromedriver().setup();// base
            ChromeOptions options = new ChromeOptions();// base
            options.addArguments("--remote-allow-origins=*");// base
            options.setCapability("platformName", "Windows 10");
            driver = new ChromeDriver(options);
            driver.get(prop.getProperty("testurl"));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));// properties
            driver.manage().window().maximize();

        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();// base
            driver = new FirefoxDriver();
            driver.get(prop.getProperty("testurl"));
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
