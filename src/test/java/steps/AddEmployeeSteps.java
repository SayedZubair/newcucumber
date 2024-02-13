package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.DataBaseUtility;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    String id;
    String fName, lName;

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {

        click(dashboard.pimOption);
    }

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {

        click(dashboard.addEmployeeOption);
    }

    @When("user enter firstname and lastname")
    public void user_enter_firstname_and_lastname() {

        sendText(addEmployee.firstNameField, "joshpan");
        sendText(addEmployee.lastNameField, "veranullah");
    }

    @When("user clicks on Save button")
    public void user_clicks_on_save_button() {
        click(addEmployee.saveButton);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
//        System.out.println("Employee Added");
////        //MUST HAVE VERIFICATION STEP
click(addEmployee.EmployeeListOption);
sendText(addEmployee.EmpIDSearchFeild,id);

click(addEmployee.searchButton);
        String actualId = addEmployee.idResultTableIdField.getText();
        Assert.assertEquals(actualId,id);




    }

    @When("user enter {string} and {string}")
    public void user_enter_and(String firstName, String lastName) {
        fName=firstName;
        lName=lastName;

        sendText(addEmployee.firstNameField, firstName);
        sendText(addEmployee.lastNameField, lastName);
    }

    @When("user enter {string} and {string} for adding multiple employees")
    public void user_enter_and_for_adding_multiple_employees(String firstNameValue, String lastNameValue) {
        sendText(addEmployee.firstNameField, firstNameValue);
        sendText(addEmployee.lastNameField, lastNameValue);
    }

    @When("user adds multiple employees and verify they are added successfully")
    public void user_adds_multiple_employees_and_verify_they_are_added_successfully(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> employeeNames = dataTable.asMaps();

        //getting the map from list of maps
        for (Map<String, String> employee : employeeNames) {
            //getting the  keys and values from every map
            String firstNameValue = employee.get("firstName");
            String middleNameValue = employee.get("middleName");
            String lastNameValue = employee.get("lastName");

            sendText(addEmployee.firstNameField, firstNameValue);
            sendText(addEmployee.lastNameField, lastNameValue);
            sendText(addEmployee.middleNameField, middleNameValue);

            click(addEmployee.saveButton);
            Thread.sleep(2000);
            //till this point one employee has been added
            //verifying the employee is home-work
            click(dashboard.addEmployeeOption);
            Thread.sleep(2000);
        }
    }

    @When("user adds multiple employee from excel using {string} and verify it")
    public void user_adds_multiple_employee_from_excel_using_and_verify_it(String sheetName) throws InterruptedException {

        List<Map<String, String>> empFromExcel = ExcelReader.excelListIntoMap(Constants.TESTDATA_FILEPATH, sheetName);


        //it returns one map from list of maps
        Iterator<Map<String, String>> itr = empFromExcel.iterator();
        while (itr.hasNext()) {
            //it returns the key and value for employee from excel
            Map<String, String> mapNewEmp = itr.next();
            System.out.println(mapNewEmp);
            sendText(addEmployee.firstNameField, mapNewEmp.get("firstName"));
            sendText(addEmployee.middleNameField, mapNewEmp.get("middleName"));
            sendText(addEmployee.lastNameField, mapNewEmp.get("lastName"));
            String empIdValue = addEmployee.empIdLocator.getAttribute("value");
            sendText(addEmployee.photograph, mapNewEmp.get("photograph"));
            if (!addEmployee.checkBox.isSelected()) {
                click(addEmployee.checkBox);
            }
            sendText(addEmployee.createusernameField, mapNewEmp.get("username"));
            sendText(addEmployee.createpasswordField, mapNewEmp.get("password"));
            sendText(addEmployee.confirmpasswordField, mapNewEmp.get("confirmPassword"));

            click(addEmployee.saveButton);
            System.out.println("click taken on save button");
            //verification is in home-work
            Thread.sleep(3000);

            click(dashboard.empListOption);
            Thread.sleep(2000);
            System.out.println("click taken on emp list option");

            //to search the employee, we use emp id what we captured from attribute
            sendText(employeeList.empSearchIdField, empIdValue);
            click(employeeList.searchButton);

            //verifying the employee added from the excel file
            //we take the locator of one employee because we know that after searching the employee by the
            // id i entered  system should return only one employee information so i check one unique emplyee
            //with one row

            List<WebElement> rowData = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
// this is the data we retrieve each time add an employee  from the ui to assert it with the one we added from excel

            for (int i = 0; i < rowData.size(); i++) {
                System.out.println("I am inside the loop and worried about josh");
                //getting the text of every element from here and storing it into string
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);

                String expectedData = empIdValue + " " + mapNewEmp.get("firstName") + " " + mapNewEmp.get("middleName") + " " + mapNewEmp.get("lastName");

                //verifying the exact details  of the employee
                Assert.assertEquals(expectedData, rowText);

            }

            click(dashboard.addEmployeeOption);
            Thread.sleep(2000);
        }
    }

    @When("user captures employee id")
    public void user_captures_employee_id() {
        id=addEmployee.empIdLocator.getAttribute("value");
    }

    @Then("added employee is displayed in database")
    public void added_employee_is_displayed_in_database() {

        String query=DataBaseSteps.getFnameLnameQuery()+id;
        List<Map<String, String>> dataFromDatabase=DataBaseUtility.getListOfMapsFromRset(query);

        //get 0 means get the first map from the list, in this list based on query we have only one map,
        //if there were more than 1 map in the list, we should pass the index of the desired map from the list
        // we can have multipe maps in the list based on the query.
        String fNameFromDb=dataFromDatabase.get(0).get("emp_firstname");
        String lNameFromDb=dataFromDatabase.get(0).get("emp_lastname");

        Assert.assertEquals(fName, fNameFromDb);
        Assert.assertEquals(lName, lNameFromDb);
    }
}

