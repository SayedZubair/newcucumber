package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {

    @FindBy(xpath = "//a[text()='Employee List']")
    public WebElement EmployeeListOption;
    @FindBy(id = "firstName")
    public WebElement firstNameField;

    @FindBy(id = "lastName")
    public WebElement lastNameField;
    @FindBy(id = "middleName")
    public WebElement middleNameField;
    @FindBy(id = "photofile")
    public WebElement photograph;

    @FindBy(id = "chkLogin")
    public WebElement checkBox;

    @FindBy(id = "user_name")
    public WebElement createusernameField;

    @FindBy(id = "user_password")
    public WebElement createpasswordField;

    @FindBy(id = "employeeId")
    public WebElement empIdLocator;

    @FindBy(id = "re_password")
    public WebElement confirmpasswordField;
    @FindBy(id = "btnSave")

    public WebElement saveButton;

    @FindBy(xpath = "//input[@id='empsearch_id']")
    public  WebElement EmpIDSearchFeild;

    @FindBy(xpath = "//input[@id='searchBtn']")
    public WebElement searchButton;

    @FindBy(xpath="//table/tbody/tr/td[2]")
    public WebElement idResultTableIdField;

    public AddEmployeePage(){
        //page factory is the concept of Selinium which we use to implement page object model desifn pattern which is
        //respoanivke to initillaze all the objects of the class
        PageFactory.initElements(driver, this);
    }

}
