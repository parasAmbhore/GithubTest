package base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import screens.CreateGist;
import screens.CreateProject;
import screens.HomeScreen;
import utils.ConfigData;
import utils.ExcelData;
import utils.TakeScreenshot;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by : Paras.ambhore
 */

/***
 * Base of the test execution
 * Does Automation setup, Initializes driver which is
 * used to launch and drive the Browser
 */
public class Base {
    public static WebDriver driver;
    protected Actions actions;
    protected ConfigData mConfigData=new ConfigData();
    protected WebDriverWait wait;
    protected Properties properties=mConfigData.getConfigData();
    protected ExcelData mExcelData=new ExcelData();
    protected TakeScreenshot screenshots;
    //Objects for Screen Classes
    protected HomeScreen mHomeScreen;
    protected CreateProject mCreateProject;
    protected CreateGist mCreateGist;
    protected Date mDate=new Date();
    protected int mRepoName=mDate.getHours()+mDate.getMinutes()+mDate.getSeconds();
    protected static Logger logger= Logger.getLogger("Logger");



    @Parameters("BrowserName")
    @BeforeTest
    public void defaultSignin(String browserName) throws InterruptedException {
        PropertyConfigurator.configure("C:\\Test\\GithHub\\TestData\\logger.properties");
        switch (browserName.toLowerCase().trim())
        {
            case "chrome" :
                System.setProperty("webdriver.chrome.driver", properties.getProperty("PathForChrome"));
                logger.debug("Setting Path for Chrome driver at : "+properties.getProperty("PathForChrome"));
                driver = new ChromeDriver();
                logger.debug("Chrome browser launched");

                break;
            case "firefox": System.setProperty("webdriver.gecko.driver", properties.getProperty("PathForGecko"));
                logger.debug("Setting Path for Firefox driver at : "+properties.getProperty("PathForGecko"));
                driver = new FirefoxDriver();
                logger.debug("Firefox browser launched");
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("PathForChrome"));
                driver = new ChromeDriver();
                logger.debug("No browser was mentioned, launching Chrome browser as default");

                break;
        }
        driver.manage().window().maximize();
        logger.debug("Browser windows maximised");
        driver.get(properties.getProperty("url"));
        logger.debug(properties.getProperty("url") +" Opened");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        logger.info("Setting Implicit wait as 60 secs");
        mHomeScreen=new HomeScreen(driver);
        String userName=properties.getProperty("DefaultUser").trim();
        String password=properties.getProperty("DefaultPassword").trim();
        Thread.sleep(3000);
        login(userName,password);

    }

    @BeforeClass
    public void initialize(){
        actions=new Actions(driver);
        mCreateProject =new CreateProject(driver);
        wait=new WebDriverWait(driver,90);
        logger.info("Explicit wait set to 90 secs");
        mCreateGist =new CreateGist(driver);
        screenshots=new TakeScreenshot();
        mHomeScreen=new HomeScreen(driver);
    }

    @AfterMethod
    public void tracksFailedCases(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Test cases Passed..");
            logger.info("Test case passed");
            driver.get(properties.getProperty("url"));

        }
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
           screenshots.takeScreenshots((RemoteWebDriver) driver);
           driver.get(properties.getProperty("url"));
           logger.info("Test case failed");
        }
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }



    public void login(String userName,String password){

        mHomeScreen.getSignInMenu().click();
        logger.debug("Sign in menu opened");
        mHomeScreen.getLoginField().clear();
        mHomeScreen.getLoginField().sendKeys(userName);
        logger.debug("User Name entered : "+userName);
        mHomeScreen.getPasswordField().clear();
        mHomeScreen.getPasswordField().sendKeys(password);
        logger.debug("Passed is provided");
        mHomeScreen.getLoginButton().click();
        logger.debug("User logged in succesfully ");
    }

    @DataProvider
    public Object[][] getRepoData(){
        Object[][] userData = new Object[2][2];

        // 1st user's Data
        userData[0][0] ="Hello";
        userData[0][1]="Test Repo";

        userData[1][0]=String.valueOf(mRepoName);
        userData[1][1]=userData[0][1];
        return  userData;
    }

    public void closeFileAndBuffer(BufferedInputStream bufferedInStream){
        try{
        }
        // to close the file explicitly
        finally {
            try {
                if (bufferedInStream != null)
                    bufferedInStream.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }





}
