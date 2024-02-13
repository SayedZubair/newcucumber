Feature: API workflow test

  Background: for generating the token before every request
    #to generate the token for all the request, we kept it here in background
    Given a JWT is generated
# sometime we need to create only one token in 12 hours so we need to as follow
  #current time +12 hours else { generate new one }..
  #we create a method under backgroud
  #generateTOkenIF12hours(){
  #read textfile and get timestamp()
  #get the current time
  #if(timepassed>12hours){
  #generetetoken()
  #update the token file
  #add the current the stamp
 # }
  #  else{ use the token you read the file
  #}

  @api
  Scenario: API test case for creating the employee
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the response body contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global to be used for other request

#data table to send multiple keys and values we write the body when we create one employee  we don't write F we write
  #Female because we once the emp is created it shows Female or male
  @api
  Scenario: Getting the created employee
    Given a request is prepared for getting a created employee
    When a GET call is made to get this employee
    Then the status code for this emp is 200
    And the employee id "employee.employee_id" should match with global emp id
    And the retrieved data at "employee" object should match with the data used for creating the employee
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |sara         |bou         |ms             |Female    |2011-01-12  |confirmed |QA Engineer  |

  @api
  Scenario: API test case for creating the employee using json body
    Given a request is prepared for creating an employee by passing json body
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the response body contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global to be used for other request

  @apidynamic1
  Scenario: API test case for creating the employee using highly dynamic body
    Given a request is prepared for creating an employee with dynamic data "Sara" , "bou"  , "ms" , "F" , "2011-01-12" , "confirmed" , "QA Engineer"
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the response body contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global to be used for other request
