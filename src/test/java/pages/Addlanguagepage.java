package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class Addlanguagepage extends CommonMethods {

    @FindBy(xpath = "//b[text()='Admin']")
    public  WebElement adminoption;

    @FindBy(xpath = "//a[@id='menu_admin_Qualifications']")
    public  WebElement QualificationOption;

@FindBy(xpath = "//a[text()='Languages']")
public  WebElement languageOption;


    @FindBy(xpath = "//input[@id='btnAdd']")
    public  WebElement addbtn;

    @FindBy(xpath = "//input[@id='language_name']")
    public  WebElement languageNameTextField;
    public Addlanguagepage(){
        PageFactory.initElements(driver,this);
    }

}
