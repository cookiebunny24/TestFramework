package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.NLeadsPage;
import pages.ZohoLoginPage;

public class ZohoLeadTest extends BasePage {
    WebDriver driver;

//Creating a lead here
    @Test(priority = 0, enabled = true)
    public void createLeadTest() throws InterruptedException {
        ZohoLoginPage zohoPage = new ZohoLoginPage(driver);
        NLeadsPage leadsPage = new NLeadsPage(driver);
        zohoPage.clickSignIn();
        zohoPage.typeUsername("glitzgorgeous01@gmail.com");
        zohoPage.typePassword("Engineer24@@");
        zohoPage.clickOnCrm();
        leadsPage.clickOnLead();
        leadsPage.clickOnCreatedLead();
        leadsPage.fillLeadForms();
        Assert.assertEquals(leadsPage.fetchLeadName(), "Mr. Heena Khanna");
    }

//Modifying created lead
    @Test(priority =1, enabled = true)
    public void clickOnEditLeadTest() throws InterruptedException {
        ZohoLoginPage zohoPage = new ZohoLoginPage(driver);
//        LeadsPage leadsPage=new LeadsPage(driver);
        NLeadsPage leadsPage = new NLeadsPage(driver);
        zohoPage.clickSignIn();
        zohoPage.typeUsername("glitzgorgeous01@gmail.com");
        zohoPage.typePassword("Engineer24@@");
        zohoPage.clickOnCrm();
        leadsPage.clickOnLead();
        leadsPage.applyFilterForLead();
        leadsPage.clickOnSelectedLeadFromList(prop.getProperty("f_name")+ " "+prop.getProperty("l_name"));
        leadsPage.clickOnEdit();
        leadsPage.modifyLead();
    }

    //Below method we are applying multiple filters and deleting the modified lead
    @Test(priority =2, enabled = true)
    public void applyFiltersAndDelete() throws InterruptedException {
        String acc_count="";
        ZohoLoginPage zohoPage = new ZohoLoginPage(driver);
//        LeadsPage leadsPage=new LeadsPage(driver);
        NLeadsPage leadsPage = new NLeadsPage(driver);
        zohoPage.clickSignIn();
        zohoPage.typeUsername("glitzgorgeous01@gmail.com");
        zohoPage.typePassword("Engineer24@@");
        zohoPage.clickOnCrm();
        leadsPage.clickOnLead();
        acc_count=leadsPage.fetchAcountCount();
        leadsPage.leadNameFilter(prop.getProperty("f_name")+ " "+prop.getProperty("l_name"));
        leadsPage.clickOnApplyFilterBTN();
        leadsPage.mobileFilter(prop.getProperty("new_mobile"));
        leadsPage.clickOnApplyFilterBTN();
//        leadsPage.twitterFilter(prop.getProperty("twitter"));
//        leadsPage.clickOnApplyFilterBTN();
        leadsPage.clickOnSelectedLeadFromList(prop.getProperty("f_name")+ " "+prop.getProperty("l_name"));
        leadsPage.clickOnMoreActions("Delete");
        leadsPage.deleteLead();
    }
}