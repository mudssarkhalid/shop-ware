package shopWare;

import org.testng.annotations.Test;

public class shopWareLogin extends reusableAnnotations {

    @Test
    public void logIn() throws InterruptedException {

        //navigate to Ultimate QA
        driver.navigate().to("https://courses.ultimateqa.com/users/sign_in");

        //click on the email field and enter the email address
        reusableActions.clickAction(driver, "//*[@name='user[email]']", logger, "Email Field");
        reusableActions.sendKeysAction(driver, "//*[@name='user[email]']", "test@shop-ware.com", logger, "Email address");

        //click on the password field, and enter the password
        reusableActions.clickAction(driver, "//*[@id='user[password]']", logger, "Password Field");
        reusableActions.sendKeysAction(driver, "//*[@id='user[password]']", "technicalTest", logger, "Password");

        //click on the Submit button
        reusableActions.clickAction(driver, "//*[@class='button button-primary g-recaptcha']", logger, "Submit Button");
        Thread.sleep(5000);

    }//Test 1

    @Test(dependsOnMethods = {"logIn"})
    public void confirmLogIn() {

        //verify the user was able to log in
        reusableActions.presenceOfElement(driver, "//p[contains(text(),'Welcome back, Shopware T!')]", logger, "Welcome message");

    }//Test 2

}//class