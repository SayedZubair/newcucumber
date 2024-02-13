import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredpractice {
    //    initialise the Base URI
    String baseURI =  RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzQ1MjE3NjQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDU2NDk2NCwidXNlcklkIjoiNDk4NyJ9.KV9ZCeahpWR_Un_b_NSiiAJlX32Dc1l4GIdA1qZw8cQ";

    @Test
    public void createEmployee(){
        //prepare the request
        RequestSpecification request = given().headers("Content-Type", "application/json").headers("Authorization", token)
                .body("{\n" +
                        "  \"emp_firstname\": \"sayed\",\n" +
                        "  \"emp_lastname\": \"hashimi\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1989-01-14\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"QA Engineer\"\n" +
                        "}");
//         send the request and get response
        Response resp = request.when().post("/createEmployee.php");
//         print the response
        resp.prettyPrint();
//extract the first name from the response

        String empFname = resp.jsonPath().getString("Employee.emp_firstname");
        System.out.println(empFname);

//         assert that the first name is MR
        Assert.assertEquals(empFname,"MR");
    }


}
