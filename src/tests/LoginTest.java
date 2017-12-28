package tests;

import base.Base;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginTest extends Base {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        Object[][] arrayObject = mExcelData.getExcelData(properties.getProperty("userDetails"), "Sheet1");
        return arrayObject;
    }

    @Test(dataProvider = "loginData")
    public void aLoginTest(String userName,String password) throws Exception {
        logger.info("Test case executing : aLoginTest");
        Thread.sleep(3000);
        if(mHomeScreen.userLoginStatus()){
            actions.moveToElement(mHomeScreen.getUserMenuLoggedIn()).click().build().perform();
            logger.info("Getting menu");
            wait.until(ExpectedConditions.elementToBeClickable(mHomeScreen.getSignOutButton()));
            actions.moveToElement(mHomeScreen.getSignOutButton()).click().build().perform();
            logger.info("user signed out");
        }
        wait.until(ExpectedConditions.visibilityOf(mHomeScreen.getSignInMenu()));
        login(userName,password);
        if(mHomeScreen.userErrorStatus()){

            Assert.assertTrue(mHomeScreen.getErrorMessage().getText().trim().toLowerCase().equals("incorrect username or password."));
            logger.info("login failed");
            screenshots.takeScreenshots((RemoteWebDriver)driver);
            driver.navigate().to(properties.getProperty("url"));
            return;

        }
        actions.moveToElement(mHomeScreen.getUserMenu()).click().build().perform();
        Assert.assertTrue(mHomeScreen.getUserName().getText().toLowerCase().trim().equals(userName));
        logger.info("User successfully logged in");

    }

}
