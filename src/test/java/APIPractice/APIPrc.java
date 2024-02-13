package APIPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APIPrc {
    //    initialise the Base URI
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Nzg1NzU1ODcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3ODYxODc4NywidXNlcklkIjoiNDg4MyJ9.W_p0B30deB5n63rfDIQvNNpons-xeLBgjGDcl1QX05M";
    @Test
    public void createEmployee() {
        //prepare the request
        RequestSpecification request = given().headers("Content-Type", "application/json").headers("Authorization", token)
                .body("{\n" +
                        "  \"emp_firstname\": \"Alexander\",\n" +
                        "  \"emp_lastname\": \"sales\",\n" +
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
        Assert.assertEquals(empFname, "MR");
    }

    //     write the request to get all the employeess and print the data on console
    @Test
    public void getAllEmployees() {
        RequestSpecification request = given().headers("Content-Type", "application/json").headers("Authorization", token);

        Response resp = request.when().get("/getAllEmployees.php");

     //   System.out.println(resp.asString());
    }

    @Test
    public void getJobTitle() {

        RequestSpecification request = given().headers("Content-Type", "application/json").headers("Authorization", token);

        Response resp = request.when().get("/jobTitle.php");

        String res = resp.jsonPath().getString("Jobs[0].id");
        System.out.println(res);
        resp.prettyPrint();
//          print all the job titles only from the response
//          check the size of array
   //     String array = resp.jsonPath().getString("Jobs");

//         homework
//          find the size of the json array
        List<Object> a = resp.jsonPath().getList("Jobs");
        System.out.println(a.size());

        for (int i = 0; i <a.size(); i++) {
            String x = resp.jsonPath().getString("Jobs[" + i + "].job");
            System.out.println(x);

        }
    }
}

