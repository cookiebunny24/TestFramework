package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ZohoLoginPage;

public class ZohoLoginTest extends BasePage {
WebDriver driver;
    @Test(priority = 0)
    public void loginTest() throws InterruptedException {
        ZohoLoginPage zohoPage = new ZohoLoginPage(driver);
        zohoPage.clickSignIn();
        zohoPage.typeUsername("ercathina@gmail.com");
        zohoPage.typePassword("Engineer24@@");
        zohoPage.clickOnCrm();
    }
}
