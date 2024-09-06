package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ZohoLoginPage extends BasePage{

    private By signin= By.xpath("(//*[text()='Sign In'])[1]");

    private By email=By.xpath("//input[@placeholder='Email address or mobile number']");

    private By password_field=By.xpath("//input[@placeholder='Enter password']");


    private By  next_btn=By.id("nextbtn");

    private  By crm_option=By.xpath("//*[text()='CRM']");

    WebDriver driver;
    WebDriverWait wait;

    public ZohoLoginPage(WebDriver driver) { // create a constructor and pass the driver instance
        this.driver=BasePage.driver;
        wait= new WebDriverWait(BasePage.driver, Duration.ofSeconds(10));
    }

//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='MyBiz Account']"))));

    public void clickSignIn() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(signin));
        driver.findElement(signin).click();
    }
    public void typeUsername(String username) throws InterruptedException { //pass a parameter so we don't hardcode values in the object class.
        wait.until(ExpectedConditions.elementToBeClickable(next_btn));
        driver.findElement(email).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(next_btn));
        driver.findElement(next_btn).click();
    }

    public void typePassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(next_btn));
        driver.findElement(password_field).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(next_btn));
        driver.findElement(next_btn).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(next_btn)));
        }

    public void clickOnCrm() {
        wait.until(ExpectedConditions.elementToBeClickable(crm_option));
        driver.findElement(crm_option).click();
        wait.until(ExpectedConditions.urlContains("crm"));
    }

}

