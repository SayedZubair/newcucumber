package APIPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*; //is used for verification and validion
//to change the order of execution we use fix method order since it is executing firt get then create employee
@FixMethodOrder(MethodSorters.NAME_ASCENDING)// this method will execute in ascending order
public class HardcodedExample {
    //one thing to remember
//base urI will be Base URL here
    //end then using when keyword we will send the end point


    //we get this from swagger
    //String baseURI = RestAssured.baseURI = "hrm.syntaxtechs.net/syntaxapi/api";
    //we add http://
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //we need to perfrom crud operation
    //we need token here
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzQwODc5MzgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDEzMTEzOCwidXNlcklkIjoiNDg4MyJ9.aE8zTe811_dmthWHuxdIXVSCXZrDvcBpj0iLhcHpyMQ";
static String employee_id;
    @Test
    public void bgetOneemployee() {
        //prepare the request
        //to prepare the request, we use request specification
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .queryParam("employee_id", employee_id);

        //to hit the end point/ to make the request which will return response
        Response response = request.when().get("/getOneEmployee.php");

         System.out.println(response.asString());
        response.prettyPrint();
        //verifying the status code
        response.then().assertThat().statusCode(200);

        //using jsonpath method, we are extracting the value of any key  from the response body
        String firstName = response.jsonPath().getString("employee.emp_firstname");
       // System.out.println(firstName);


        //first way of assertion
        Assert.assertEquals(firstName, "sayed");

        //second way of assertion to verify the value in response body using hamcrest matchers
        response.then().assertThat().body("employee.emp_firstname", equalTo("sayed"));
    }
    @Test
    public void acreateEmployee(){
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                        "  \"emp_firstname\": \"sayed\",\n" +
                        "  \"emp_lastname\": \"hashimi\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1989-01-14\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"QA Engineer\"\n" +
                        "}");
        Response response=request.when().post("/createEmployee.php");
        response.prettyPrint();
response.prettyPrint();
//to verify the response status code
        response.then().assertThat().statusCode(201);
        response.then().assertThat().statusCode(201);
        //getting the employee id from the response and use it as static one
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
        response.then().assertThat().body("Employee.emp_lastname", equalTo("hashimi"));
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("ms"));
        //verify console header
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
    }
    @Test
    public void cupdateEmployee(){
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json").
                body("{\n" +
                        "  \"employee_id\": \""+ employee_id +"\",\n" +
                        "  \"emp_firstname\": \"ahmad\",\n" +
                        "  \"emp_lastname\": \"habib\",\n" +
                        "  \"emp_middle_name\": \"mah\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2016-01-15\",\n" +
                        "  \"emp_status\": \"dev\",\n" +
                        "  \"emp_job_title\": \"manager\"\n" +
                        "}");
        Response response = request.when().put("/updateEmployee.php");

        //verification using hamcrest.Matchers
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
    }
    //cross checking the updated employee
    @Test
public void dgetUpdatedEmployee(){
    RequestSpecification request = given().header("Authorization", token)
            .header("Content-Type", "application/json")
            .queryParam("employee_id", employee_id);

    //to hit the end point/ to make the request which will return response
    Response response = request.when().get("/getOneEmployee.php");

    // System.out.println(response.asString());
    response.prettyPrint();
    //verifying the status code
    response.then().assertThat().statusCode(200);
    response.then().assertThat().body("employee.emp_job_title", equalTo("Manager"));

}
}