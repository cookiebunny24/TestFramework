package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NLeadsPage extends BasePage{
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    private By lead_header= By.xpath("//a//*[text()='Leads']");
    private By create_lead=By.xpath("//*[@data-zcqa='cv_createbtn']");
    private By none_salutation=By.xpath("//*[text()='First Name']/..//*[text()='-None-']");
    private By list_of_salutation=By.xpath("//lyte-drop-item[text()='Mr.']");
    private By input_first_name=By.id("Crm_Leads_FIRSTNAME_LInput");
    private By input_title=By.id("Crm_Leads_DESIGNATION_LInput");
    private  By lead_source=By.xpath("//*[text()='Lead Source']/..//*[text()='-None-']");
    private By lead_seardch=By.xpath("//input[@id='LEADSOURCE'] | (//*[@is-search='true']//input[@id='inputId'])[1]");
    private  By input_phone=By.xpath("//input[@id='Crm_Leads_PHONE_LInput']");
    private By input_mobile=By.xpath("//input[@id='Crm_Leads_MOBILE_LInput']");
    private By input_company=By.xpath("//*[@id='Crm_Leads_COMPANY_label']/..//input");
    private By last_name=By.xpath("//input[@id='Crm_Leads_LASTNAME_LInput']");
    private By lead_status_none=By.xpath("//*[text()='Lead Status']/..//*[text()='-None-']");
    private By num_of_employees=By.xpath("//input[@id='Crm_Leads_EMPCT_LInput']");
    private By annual_rev=By.xpath("//input[@id='Crm_Leads_ANNUALREVENUE_LInput']");
    private By skype_id=By.xpath("//input[@id='Crm_Leads_SKYPEIDENTITY_LInput']");
    private  By twitter=By.xpath("//input[@id='Crm_Leads_TWITTER_LInput']");
    private  By lead_email=By.xpath("//input[@id='Crm_Leads_EMAIL_LInput']");
    private  By industry_none=By.xpath("//*[text()='Industry']/..//*[text()='-None-']");
    private By industry_search=By.xpath("//input[@id='INDUSTRY'] | (//*[@is-search='true']//input[@id='inputId'])[2]");

    //Address information for locators
    private By street=By.xpath("//input[@id='Crm_Leads_LANE_LInput']");
    private By state =By.xpath("//input[@id='Crm_Leads_STATE_LInput']");
    private By city =By.xpath("//input[@id='Crm_Leads_CITY_LInput']");
    private By country =By.xpath("//*[@id='Crm_Leads_COUNTRY_FValue']//input");
    private By zipcode =By.xpath("//input[@id='Crm_Leads_CODE_LInput']");

    //Address information for locators
    private  By description=By.xpath("//lyte-input[@id='cruxTextArea']");
    private  By save_btn=By.xpath("//*[@id='crm_create_savebutn']");

    //Edit LeadPAge
    private By search_box=By.xpath("//input[@placeholder='Search']");
    private By filter_by_leadname=By.id("field_Full_Name");
    private  By filter_by_mobile=By.id("field_Mobile");
    private By filter_by_twitter=By.id("field_Twitter");
    private  By filter_apply_btn=By.xpath("//*[text()='Apply Filter']");
    private  By type_here=By.xpath("//*[@id='id_Full_Name']//input");
    private  By listed_lead=By.xpath("//a[contains(@id,'listView_')]/lyte-text[contains(@class, 'wfrepTextMaxWidth')]");
    private By edit_btn=By.xpath("//button/..//*[text()='Edit']");
    private  By save_filter=By.xpath("//*[text()='Save filter']");
    private  By clear_filter=By.xpath("//*[text()='Clear Filter']");

    //Delete
    private By more_with_3dots=By.xpath("//*[contains(@class,'lyte-button dv_moreBtn')]");
    private By delete_lead=By.xpath("//button[contains(@class, 'PupHome_button_deletebtn')]");

    //Account details
    private By num_of_acc= By.xpath("//span[@id='totalCountNo']");


    // create a constructor and pass the driver instance
    public NLeadsPage(WebDriver driver) {
        this.driver=BasePage.driver;
        wait= new WebDriverWait(BasePage.driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) BasePage.driver;
    }

    public void clickOnLead() throws InterruptedException {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='ZOHO CRM']")));
        wait.until(ExpectedConditions.elementToBeClickable(lead_header));
        driver.findElement(lead_header).click();
        wait.until(ExpectedConditions.urlContains("list"));
    }

    public void clickOnCreatedLead() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(create_lead));
        driver.findElement(create_lead).click();
    }
    public void fillLeadForms(){
        wait.until(ExpectedConditions.elementToBeClickable(none_salutation));
        driver.findElement(none_salutation).click();
        driver.findElement(list_of_salutation).click();
        driver.findElement(input_first_name).sendKeys(prop.getProperty("f_name"));
        driver.findElement(input_mobile).sendKeys(prop.getProperty("mobile"));
        driver.findElement(input_phone).sendKeys(prop.getProperty("phones"));
        driver.findElement(last_name).sendKeys(prop.getProperty("l_name"));
        driver.findElement(input_title).sendKeys(prop.getProperty("title"));
        driver.findElement(input_company).sendKeys(prop.getProperty("company"));
        fillLeadSource(prop.getProperty("lead_source"));
        leadStatus(prop.getProperty("lead_status"));
        fillIndustry(prop.getProperty("industry"));
        driver.findElement(num_of_employees).sendKeys(prop.getProperty("employees"));
        driver.findElement(lead_email).sendKeys(prop.getProperty("lead_email"));
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(annual_rev));
        driver.findElement(annual_rev).sendKeys(prop.getProperty("annual_rev"));
        driver.findElement(skype_id).sendKeys(prop.getProperty("skype_id"));
        driver.findElement(twitter).sendKeys(prop.getProperty("twitter"));
        // Scroll to the bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        leadAddress();
        wait.until(ExpectedConditions.elementToBeClickable(save_btn));
        driver.findElement(save_btn).click();
    }

    public void fillLeadSource(String srchText) {
        wait.until(ExpectedConditions.elementToBeClickable(lead_source));
        driver.findElement(lead_source).click();
        driver.findElement(lead_seardch).sendKeys(srchText);
        driver.findElement(By.xpath("//*[text()='" + srchText + "']")).click();
    }
    public void leadAddress(){
        wait.until(ExpectedConditions.elementToBeClickable(street));
        driver.findElement(street).sendKeys(prop.getProperty("street"));
        wait.until(ExpectedConditions.elementToBeClickable(state));
        driver.findElement(state).sendKeys(prop.getProperty("state"));
        wait.until(ExpectedConditions.elementToBeClickable(city));
        driver.findElement(city).sendKeys(prop.getProperty("city"));
        wait.until(ExpectedConditions.elementToBeClickable(zipcode));
        driver.findElement(zipcode).sendKeys(prop.getProperty("zipcode"));
        wait.until(ExpectedConditions.elementToBeClickable(country));
        driver.findElement(country).sendKeys(prop.getProperty("country"));
        List<WebElement> ele=driver.findElements(By.xpath("//lyte-drop-body[@id='Lyte_Drop_Body_11']"));
        System.out.println(ele.size());
        driver.findElement(description).sendKeys(prop.getProperty("description"));
    }
    public void leadStatus(String status){
        wait.until(ExpectedConditions.elementToBeClickable(lead_status_none));
        driver.findElement(lead_status_none).click();
        driver.findElement(By.xpath("//*[text()='" + status + "']")).click();
    }
    public void fillIndustry(String industry){
        wait.until(ExpectedConditions.elementToBeClickable(industry_none));
        driver.findElement(industry_none).click();
        driver.findElement(industry_search).sendKeys(industry);
        driver.findElement(By.xpath("//*[text()='" + industry + "']")).click();
    }

    public String fetchLeadName(){
        String name;
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(save_btn)));
        name = driver.findElement(By.id("titlecard_LASTNAME")).getText();
        return  name;
    }

    public void applyFilterForLead(){
        wait.until(ExpectedConditions.elementToBeClickable(search_box));
        driver.findElement(search_box).sendKeys(prop.getProperty("search_filter_lead_name"));
        wait.until(ExpectedConditions.elementToBeClickable(filter_by_leadname));
        driver.findElement(filter_by_leadname).click();
        driver.findElement(type_here).sendKeys(prop.getProperty("f_name")+" "+prop.getProperty("l_name"));
        wait.until(ExpectedConditions.elementToBeClickable(filter_apply_btn));
        driver.findElement(filter_apply_btn).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(filter_apply_btn));
    }

    public void clickOnSelectedLeadFromList(String name){
        wait.until(ExpectedConditions.elementToBeClickable(save_filter));
        wait.until(ExpectedConditions.elementToBeClickable(listed_lead));
        driver.findElement(listed_lead).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(listed_lead)));
    }
    public void clickOnEdit(){
        wait.until(ExpectedConditions.elementToBeClickable(edit_btn));
        driver.findElement(edit_btn).click();

    }

    public void modifyLead(){
        wait.until(ExpectedConditions.urlContains("edit"));
        wait.until(ExpectedConditions.elementToBeClickable(save_btn));
        driver.findElement(input_mobile).clear();
        driver.findElement(input_mobile).sendKeys(prop.getProperty("new_mobile"));
        driver.findElement(input_phone).clear();
        driver.findElement(input_phone).sendKeys(prop.getProperty("new_phones"));
        driver.findElement(input_title).clear();
        driver.findElement(input_title).sendKeys(prop.getProperty("new_title"));
        driver.findElement(input_company).clear();
        driver.findElement(input_company).sendKeys(prop.getProperty("new_company"));
        driver.findElement(save_btn).click();
    }

    public void mobileFilter(String text) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(search_box));
        driver.findElement(search_box).sendKeys("Mobile");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(filter_by_mobile));
        driver.findElement(filter_by_mobile).click();
        driver.findElement(By.xpath("//lyte-input[@id='id_Mobile']")).sendKeys(text);
        driver.findElement(search_box).clear();
    }
    public void leadNameFilter(String text){
        wait.until(ExpectedConditions.elementToBeClickable(search_box));
        driver.findElement(search_box).sendKeys("Lead Name");
        wait.until(ExpectedConditions.elementToBeClickable(filter_by_leadname));
        driver.findElement(filter_by_leadname).click();
        driver.findElement(type_here).sendKeys(text);
        driver.findElement(search_box).clear();
    }
    public void twitterFilter(String text) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(search_box));
        driver.findElement(search_box).sendKeys("Twitter");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(filter_by_twitter));
        driver.findElement(filter_by_twitter).click();
        driver.findElement(By.xpath("//lyte-input[@id='id_Twitter']")).sendKeys(text);
        driver.findElement(search_box).clear();
    }

    public void selectFilterFromList(String filter, String text) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(search_box));
        driver.findElement(search_box).sendKeys(filter);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='"+ filter +"']")));
        driver.findElement(By.xpath("//*[@title='"+ filter +"']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(filter_apply_btn));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//lyte-input[@lt-prop-placeholder='Type here']")).sendKeys(text);
        driver.findElement(search_box).clear();
    }

    public void clickOnMoreActions(String action) {
        wait.until(ExpectedConditions.presenceOfElementLocated(more_with_3dots));
        driver.findElement(more_with_3dots).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[contains(@id,'drop_action_') and text()='"+ action +"']"))));
        driver.findElement(By.xpath("//*[contains(@id,'drop_action_') and text()='"+ action+ "']")).click();
    }
    public void clickOnApplyFilterBTN(){
        driver.findElement(filter_apply_btn).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(filter_apply_btn));
    }
    public void deleteLead(){
        wait.until(ExpectedConditions.elementToBeClickable(delete_lead));
        driver.findElement(delete_lead).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(delete_lead));
        driver.findElement(lead_header).click();
        wait.until(ExpectedConditions.elementToBeClickable(clear_filter));
        driver.findElement(clear_filter).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(clear_filter));
    }

    public String fetchAcountCount(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(num_of_acc));
        String text= driver.findElement(num_of_acc).getText();
        System.out.println(text);
        return  text;
    }
}



