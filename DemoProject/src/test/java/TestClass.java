import CommonLib.Browsers;
import Elements.DPSelectors;
import Inputs.Inputs;
import Utilities.ExcelUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestClass {

    Browsers browsers = new Browsers();
    Inputs inputs = new Inputs();
    DPSelectors dpSelectors = new DPSelectors();


    @BeforeClass
    public void openBrowser(){

        //Browser Setup
        browsers.chromeBrowser(inputs.baseUrl2);

    }

    @Test(priority = 1, description = "Test some methods", enabled = false, invocationCount = 1)
    public void testMethod(){

        try {


            WebElement submitBtn = browsers.driver.findElement(By.xpath(dpSelectors.SubmitBtn));
            //getText()
            System.out.println(submitBtn.getText());
            //getAttribute()
            System.out.println(submitBtn.getAttribute("class"));
            System.out.println("A");


            //isDisplayed()
            WebElement emailText = browsers.driver.findElement(By.xpath(dpSelectors.emailTxt));
            Thread.sleep(1000);
            boolean isTextboxdisplayed = emailText.isDisplayed();
            Assert.assertTrue(isTextboxdisplayed);
            System.out.println("B");

            //isSelected()
            Thread.sleep(1000);
            boolean isTextboxSelected = emailText.isSelected();
            Thread.sleep(2000);
            Assert.assertFalse(isTextboxSelected);
            System.out.println("C");

            //isEnabled()
            Thread.sleep(1000);
            WebElement checkBox1 = browsers.driver.findElement(By.xpath(dpSelectors.checkBox1));
            checkBox1.click();
            boolean isCheckboxEnabled = checkBox1.isEnabled();
            Assert.assertTrue(isCheckboxEnabled);
            System.out.println("D");


        }
        catch (Exception exc){
            System.out.println(exc.getMessage());


        }
    }

    @Test(priority = 2, description = "Test Select Class", enabled = false, invocationCount = 1)
    public void selectClass(){

        try{

            WebElement DropDown1 = browsers.driver.findElement(By.xpath(dpSelectors.dropDown1));
            Select select1 = new Select(DropDown1);
            select1.selectByVisibleText("1");
            Thread.sleep(3000);
            System.out.println("A");

            select1.selectByIndex(3);
            System.out.println("B");

            select1.selectByValue("5");
            Thread.sleep(3000);

        }catch (Exception exc){

            System.out.println(exc.getMessage());
        }


    }

    @Test(priority = 3, description = "Test alerts and frames", enabled = true, invocationCount = 1)
    public void alertsFrames(){

        try{

            WebElement promptBtn = browsers.driver.findElement(By.xpath(dpSelectors.promptBtn));
            promptBtn.click();
            System.out.println("PROMPT MESSAGE : " + browsers.driver.switchTo().alert().getText());
            browsers.driver.switchTo().alert().sendKeys("abc");
            browsers.driver.switchTo().alert().accept();

            WebElement alertBtn = browsers.driver.findElement(By.xpath(dpSelectors.alertBtn));
            alertBtn.click();
            System.out.println("ALERT MESSAGE : " + browsers.driver.switchTo().alert().getText());
            browsers.driver.switchTo().alert().accept();

            WebElement confirmBtn = browsers.driver.findElement(By.xpath(dpSelectors.confirmBtn));
            confirmBtn.click();
            System.out.println("MESSAGE : " + browsers.driver.switchTo().alert().getText());
            browsers.driver.switchTo().alert().accept();

            WebElement dropDown3 = browsers.driver.findElement(By.xpath(dpSelectors.dropDown3));
            Select select2 = new Select(dropDown3);
            select2.selectByVisibleText("Audi");
            select2.selectByIndex(1);

            WebElement mapFrame = browsers.driver.findElement(By.xpath(dpSelectors.iFrame1));
            browsers.driver.switchTo().frame(mapFrame);
                WebElement mapZoom = browsers.driver.findElement(By.xpath(dpSelectors.iFrameBtn));
                boolean mapPresent = mapZoom.isDisplayed();
                if(mapPresent){
                    System.out.println("Map is Displayed");
                    Utility.screenShot(browsers.driver, "Map");
                    mapZoom.click();
                }

            browsers.driver.switchTo().parentFrame();

            String ogWindow = browsers.driver.getWindowHandle();

            WebElement page3 = browsers.driver.findElement(By.xpath(dpSelectors.page3));
            page3.click();
            Thread.sleep(3000);

            int windowNumbers = browsers.driver.getWindowHandles().size();
            System.out.println("Number of Tabs : " +windowNumbers);
            if(windowNumbers>1){

                System.out.println("New Tab opened.");
                browsers.driver.switchTo().window(ogWindow);
            }

        }catch (Exception exc){

            System.out.println(exc.getMessage());
        }
    }

    @Test(priority = 4, description = "DataProvider", enabled = true, dataProvider = "credentials for facebook")
    public void dataTest(String username, String password){

        try{

            browsers.driver.navigate().to("https://facebook.com");

            WebElement emailText = browsers.driver.findElement(By.xpath("//input[@id=\"email\"]"));
            emailText.clear();
            emailText.sendKeys(username);

            WebElement passwordText = browsers.driver.findElement(By.xpath("//input[@id=\"pass\"]"));
            passwordText.clear();
            passwordText.sendKeys(password);

            WebElement loginBtn = browsers.driver.findElement(By.xpath("//button[@type=\"submit\"]"));
            loginBtn.click();
            Thread.sleep(1000);
        }catch (Exception exc){

            System.out.println(exc.getMessage());
        }
    }

    @DataProvider(name = "credentials for facebook")
    public Object[][] credentials(){

        Object[][] loginCredentials = new Object[2][2];
        //R1C1
        loginCredentials[0][0] = "abc@xyz.com";
        //R1C2
        loginCredentials[0][1] = "qwerty";
        //R2C1
        loginCredentials[1][0] = "xyz@abc.com";
        //R2C2
        loginCredentials[1][1] = "mnbvcxz";

        return loginCredentials;

    }

    @DataProvider(name = "Login_credentials")
    public Object[][] methodName() {
        ExcelUtils excelUtils= new ExcelUtils("/home/r3dm0nk/IdeaProjects/DemoProject1/LoginFacebook.xlsx"); // create an object of ExcelUtils class. ie, we created.
        int rows= excelUtils.getRowCount("");  // pass sheet index to get num of rows
        Object[][] data=new Object[rows-1][2]; // create an object array to values we read from excel sheet.
        for (int i=0;i<rows-1;i++)
        {
            data[i][0]=excelUtils.getData(); //dataconfig.getData(0,i,1); // getData
            data[i][1]=excelUtils.getData() ; //dataconfig.getData(0,i, 2);
        }
        return data;
    }

    @AfterClass
    public void tearDown() throws InterruptedException{

        Thread.sleep(2000);
        browsers.driver.close();
        browsers.driver.quit();
    }
}
