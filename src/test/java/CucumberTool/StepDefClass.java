//package CucumberTool;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.util.concurrent.TimeUnit;
//
//public class StepDefClass {
//    WebDriver driver;
//    @Given("user is navigated to HRMS application")
//    public void user_is_navigated_to_hrms_application() {
//        WebDriverManager.chromedriver().setup();
//        driver=new ChromeDriver();
//        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login%22");
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//    }
//
//    @When("user enters valid username and password")
//    public void user_enters_valid_username_and_password() {
//        WebElement usernamefield=driver.findElement(By.id("txtUsername"));
//        usernamefield.sendKeys("admin");
//        WebElement passwordField = driver.findElement(By.id("txtPassword"));
//        passwordField.sendKeys("Hum@nhrm123");
//    }
//
//    @When("user clicks on login button")
//    public void user_clicks_on_login_button() {
//        WebElement loginbtn = driver.findElement(By.id("btnLogin"));
//        loginbtn.click();
//    }
//
//    @Then("user is successfully logged in")
//    public void user_is_successfully_logged_in() {
//        WebElement welcomemsg = driver.findElement(By.id("welcome"));
//        if(welcomemsg.isDisplayed()){
//            System.out.println("test is passed");
//        }else{
//            System.out.println("test is failed ");
//        }
//    }
//    @Then("user verify dashboard page")
//    public void user_verify_dashboard_page() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//}
