package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen {

    public WebDriver driver;

    @FindBy(xpath = ".//span/div/a[@href='/login']")
    WebElement signInMenu;

    @FindBy(xpath = ".//div[@class='auth-form-body mt-3']/input")
    WebElement loginField;

    @FindBy(xpath = ".//div[@class='auth-form-body mt-3']/input[2]")
    WebElement passwordField;

    @FindBy(xpath = ".//div[@class='auth-form-body mt-3']/input[3]")
    WebElement loginButton;
    @FindBy(xpath = ".//div[@class='HeaderMenu d-flex flex-justify-between flex-auto']/div[2]/ul/li[3]/details/summary")
    WebElement userMenu;
    @FindBy(xpath = "//*[@id=\"user-links\"]/li[3]/details/ul/li[1]/strong")
    WebElement userName;
    @FindBy(xpath = ".//li[@class='dropdown js-menu-container'][3]/details/summary")
    WebElement userMenuLoggedIn;
    @FindBy(xpath = ".//button[@class='dropdown-item dropdown-signout']")
    WebElement signOutButton;
    @FindBy(xpath = ".//form/div[3]/div/div")
    WebElement errorMessage;

    public HomeScreen(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginField() {
        return loginField;
    }

    public WebElement getSignInMenu() {
        return signInMenu;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getUserMenu() {
        return userMenu;
    }

    public WebElement getSignOutButton() {
        return signOutButton;
    }

    public WebElement getUserName() {
        return userName;
    }

    public WebElement getUserMenuLoggedIn() {
        return userMenuLoggedIn;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public boolean userLoginStatus(){
        try{
            driver.findElement(By.xpath(".//li[@class='dropdown js-menu-container'][2]/details/summary"));
            return true;
        }catch (Exception e){
            return false;
        }}
    public boolean userErrorStatus(){
        try{
            driver.findElement(By.xpath(".//form/div[3]/div/div"));
            return true;
        }catch (Exception e){
            return false;
        }


    }

}
