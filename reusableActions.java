package shopWare;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class reusableActions {
    //declare global timeout
    public static int timeout = 10;

    //reusable function for web driver
    public static WebDriver setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }//method

    //click method
    public static void clickAction(WebDriver driver,String xpath,ExtentTest logger, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        Actions actions = new Actions(driver);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            element.click();
            logger.log(LogStatus.PASS,"Successfully clicked on element " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to click on element " + elementName + " " + e);
            logger.log(LogStatus.FAIL, "Failed to click on element " + elementName + " " + e);
            getScreenShot(driver,elementName,logger);
        }//exception
    }//method

    //send keys method
    public static void sendKeysAction(WebDriver driver,String xpath, String userValue, ExtentTest logger,String elementName){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            element.click();
            element.clear();
            element.sendKeys(userValue);
            logger.log(LogStatus.PASS,"Successfully entered user value on element " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to enter userValue on element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Failed to enter userValue on element " + elementName + " " + e);
            getScreenShot(driver,elementName,logger);
        }//exception
    }//method

    //presence of element method
    public static void presenceOfElement(WebDriver driver,String xpath,ExtentTest logger, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        boolean elementState = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).isDisplayed();
        try {
            if (elementState == true){
                System.out.println("The " + elementName + " is visible.");
                logger.log(LogStatus.PASS,elementName + " is visible.");
            }else {
                System.out.println("The " + elementName + " is not visible.");
                logger.log(LogStatus.FAIL,elementName + " is not visible.");
                getScreenShot(driver,elementName,logger);
            }//conditional
        } catch (Exception e){
            logger.log(LogStatus.FAIL,"Failed to verify visibility of " + elementName + e);
            getScreenShot(driver,elementName,logger);
        }//exception
    }//method

    //get screen shot method
    public static void getScreenShot(WebDriver driver,String imageName,ExtentTest logger) {
        try {
            String fileName = imageName + ".png";
            String directory = null;
            String snPath = null;
            directory = "src/main/java/HTML_Reports/Screenshots/";
            snPath = "Screenshots//";
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(directory + fileName));
            //String imgPath = directory + fileName;
            String image = logger.addScreenCapture(snPath + fileName);
            logger.log(LogStatus.FAIL, "", image);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Error Occurred while taking screenshot");
            e.printStackTrace();
        }//exception
    }//method

}//class
