package screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateGist {
    public WebDriver driver;
    @FindBy(xpath = ".//div[@class='input-group gist-filename-input']/input[2]")
    WebElement gistName;
    @FindBy(xpath = ".//div[@class='CodeMirror-scroll']/div")
    WebElement gistDataField;
    @FindBy(xpath = ".//div[@class='form-actions']/button")
    WebElement createGistButton;

    public CreateGist(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement getGistName() {
        return gistName;
    }

    public WebElement getGistDataField() {
        return gistDataField;
    }

    public WebElement getCreateGistButton() {
        return createGistButton;
    }
}
