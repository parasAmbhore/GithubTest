package tests;

import base.Base;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class CreateGistTest extends Base {

    @Test
    public void bCreatePublicGistButtonDisabled(){
        logger.info("Test case executing: bCreatePublicGistButtonDisabled" );
        wait.until(ExpectedConditions.elementToBeClickable(mCreateProject.getMenu1()));
        logger.info("Menu bar found,clicking on it");
        actions.moveToElement(mCreateProject.getMenu()).click().build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(mCreateProject.getNewGistMenu()));
        actions.moveToElement(mCreateProject.getNewGistMenu()).click().build().perform();
        logger.info("Created gist menu is opened");
        wait.until(ExpectedConditions.visibilityOf(mCreateGist.getCreateGistButton()));

        Assert.assertTrue(!mCreateGist.getCreateGistButton().isEnabled());
        logger.info("Create gist button is disabled");
    }


    @Test(dependsOnMethods = "bCreatePublicGistButtonDisabled")
    public void cCreatePublicGistTest(){
        logger.info("Test case executing: cCreatePublicGistTest" );
        BufferedInputStream bufferedInStream = null;
        wait.until(ExpectedConditions.visibilityOf(mCreateProject.getMenu1()));
        logger.info("Menu bar found,clicking on it");
        actions.moveToElement(mCreateProject.getMenu()).click().build().perform();
        actions.moveToElement(mCreateProject.getNewGistMenu()).click().build().perform();
        logger.info("Created gist menu is opened");
        actions.moveToElement(mCreateGist.getGistName()).click().sendKeys(properties.getProperty("GistName")).build().perform();
        logger.info("Creating new gist with name : "+properties.getProperty("GistName"));

        // Returns IO Exception if occurs
        try {
            FileInputStream gistData = new FileInputStream(properties.getProperty("GistData"));
            bufferedInStream = new BufferedInputStream(gistData);
            int checkByte;
            while ((checkByte = bufferedInStream.read()) != -1) {
                actions.moveToElement(mCreateGist.getGistDataField()).click().sendKeys(String.valueOf((char)checkByte)).build().perform();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        closeFileAndBuffer(bufferedInStream);

        mCreateGist.getCreateGistButton().click();
        logger.info("create gist button clicked");
        System.out.println("gistUrl : "+driver.getCurrentUrl());
        logger.info("gist created : "+driver.getCurrentUrl());
        properties.put("gistUrl",driver.getCurrentUrl());

    }
}
