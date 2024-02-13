package utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class APIPayloadConstant {
    public static String createEmployeePayload(){
        String createEmployeePayload =
                "{\n" +
                        "  \"emp_firstname\": \"sara\",\n" +
                        "  \"emp_lastname\": \"bou\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2011-01-12\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"QA Engineer\"\n" +
                        "}";
        return createEmployeePayload;
    }
// we need to convert is to string becasue the method takes a string
    //the best approach is to create a file and create a reader to to read it
    public static String createEmployeeJsonBody(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "sara");
        obj.put("emp_lastname", "bou");
        obj.put("emp_middle_name", "ms");
        obj.put("emp_gender", "F");
        obj.put("emp_birthday", "2011-01-12");
        obj.put("emp_status", "confirmed");
        obj.put("emp_job_title","QA Engineer");
        return obj.toString();
    }

    public static String createEmployeePayloadDynamic(String firstname, String lastname, String middlename,
                                                      String gender, String dob,
                                                      String empStatus, String jobTitle){

        //this object accept array too, if a person has many skills, all the skills should be added in an array
        List<String>name=new ArrayList<>();

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", firstname);
        obj.put("emp_lastname", lastname);
        obj.put("emp_middle_name", middlename);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", dob);
        obj.put("emp_status", empStatus);
        obj.put("emp_job_title",jobTitle);
        obj.put("skills",name);
        return obj.toString();
    }

    public static String adminPayload(){
        String adminPayload =
                "{\n" +
                        "  \"email\": \"batch14@test.com\",\n" +
                        "  \"password\": \"Test@123\"\n" +
                        "}";
        return adminPayload;
    }

    //    to read the payload from the file
    public static String readPayloadFile(String filePath) throws IOException {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(filePath)));
        return data;
    }




}