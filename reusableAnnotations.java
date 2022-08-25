package shopWare;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class reusableAnnotations {

    //set all global variables with public static
    public static WebDriver driver;
    public static ExtentReports reports;
    public static ExtentTest logger;

    //set Before suite for report path and set driver
    @BeforeSuite
    public void setChromeDriver(){
        //set the driver
        driver = reusableActions.setDriver();
        //define the path to the report
        reports = new ExtentReports("src/main/java/shopWare/Shop-Ware.html", true);
    }//@BeforeSuite

    //set Before method to capture test name of each @test, so it can be added to the html report
    @BeforeMethod
    public void getTestName(Method testName){
        logger = reports.startTest(testName.getName());
    }//@BeforeMethod

    //set after method to end the logger for each @test
    @AfterMethod
    public void endLogger(){
        reports.endTest(logger);
    }//@AfterMethod

    //after suite to flush the report and quit the driver
    @AfterSuite
    public void quitSession() {
        driver.quit();
        reports.flush();
    }//@AfterSuite
}//class
