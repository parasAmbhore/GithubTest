package screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProject {
    public WebDriver driver;

    @FindBy(xpath = ".//li[@class='dropdown js-menu-container']/details")
    WebElement menu;
    @FindBy(xpath=".//li[@class='dropdown js-menu-container']/details/ul/a")
    WebElement newRepositoryMenu;
    @FindBy(xpath = ".//li[@class='dropdown js-menu-container']/details/ul/a[3]")
    WebElement newGistMenu;
    @FindBy(xpath = ".//div[@class='owner-reponame clearfix']/dl[2]/dd")
    WebElement repositoryNameField;
    @FindBy(xpath = ".//div[@class='with-permission-fields']/dl/dd")
    WebElement descriptionField;
    @FindBy(xpath = ".//div[@class='with-permission-fields']/button")
    WebElement createRepositoryButton;
    @FindBy(xpath = ".//dd[@class='error']")
    WebElement errorMessage;
    @FindBy(xpath = ".//div[@class='TableObject-item TableObject-item--primary']/div/span/input")
    WebElement gitLink;
    @FindBy(xpath = ".//li[@class='dropdown js-menu-container']")
    WebElement getMenu1;

    public CreateProject(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement getNewGistMenu() {
        return newGistMenu;
    }

    public WebElement getMenu1() {
        return getMenu1;
    }

    public WebElement getMenu() {
        return menu;
    }

    public WebElement getNewRepositoryMenu() {
        return newRepositoryMenu;
    }

    public WebElement getCreateRepositoryButton() {
        return createRepositoryButton;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getRepositoryNameField() {
        return repositoryNameField;
    }

    public WebElement getDescriptionField() {
        return descriptionField;
    }

    public WebElement getGitLink() {
        return gitLink;
    }
}
