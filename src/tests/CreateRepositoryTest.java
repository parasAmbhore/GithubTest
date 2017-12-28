package tests;

import base.Base;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateRepositoryTest extends Base {
        @Test
    public void dCreateRepositoryButtonDisabled() throws InterruptedException {
            logger.info("Test case executing: dCreateRepositoryButtonDisabled" );
        Thread.sleep(3000);
        actions.moveToElement(mCreateProject.getMenu()).click().build().perform();
        logger.debug("Menu is opened");
        wait.until(ExpectedConditions.elementToBeClickable(mCreateProject.getNewRepositoryMenu()));
        actions.moveToElement(mCreateProject.getNewRepositoryMenu()).click().build().perform();
        logger.debug("Create new repository page is opened");
        wait.until(ExpectedConditions.visibilityOf(mCreateProject.getCreateRepositoryButton()));
        logger.debug("Verifying if create new repo button is disabled");
        Assert.assertTrue(!(mCreateProject.getCreateRepositoryButton().isEnabled()));
    }

    @Test(dataProvider = "getRepoData",dependsOnMethods = "dCreateRepositoryButtonDisabled")
    public void eCreateRepositoryTest(String repoName,String description) {
        logger.info("Test case executing: eCreateRepositoryTest" );
        wait.until(ExpectedConditions.visibilityOf(mCreateProject.getMenu1()));
        logger.info("Menu is opened");
        actions.moveToElement(mCreateProject.getMenu()).click().build().perform();
        actions.moveToElement(mCreateProject.getNewRepositoryMenu()).click().build().perform();
        logger.info("Create new repository page is opened");

        String repo=repoName;
        logger.info("Repository name given : " + repo);
        actions.moveToElement(mCreateProject.getRepositoryNameField()).doubleClick().sendKeys(repo).build().perform();
        logger.info("Repository description given is : "+description);
        actions.moveToElement(mCreateProject.getDescriptionField()).click().sendKeys(description).build().perform();

        mCreateProject.getCreateRepositoryButton().click();
        logger.info("Create new repo button clicked");
        try {
            if (mCreateProject.getErrorMessage().isDisplayed()) {
                Assert.assertTrue(mCreateProject.getErrorMessage().getText().contains(repoName));
                System.out.println("Repo Already exists");
                logger.info("Repo creation failed.Repo with name already exists");
                screenshots.takeScreenshots((RemoteWebDriver) driver);
            }
        }catch (Exception e){
            wait.until(ExpectedConditions.visibilityOf(mCreateProject.getGitLink()));
            Assert.assertTrue(mCreateProject.getGitLink().getAttribute("value").contains(repoName));
            logger.info("Repo is created");
            System.out.println("gitRepoLink : "+mCreateProject.getGitLink().getAttribute("value").trim());
            logger.info("Repo link : "+mCreateProject.getGitLink().getAttribute("value").trim());
            properties.put("gitRepoLink",mCreateProject.getGitLink().getAttribute("value").trim());

        }

    }
}
