package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.DOMConfiguration;
import pages.LoginPage;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends PageInitializer {

public static WebDriver driver;
    public static void openBrowserAndLaunchApplication(){
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (ConfigReader.getPropertyValue("browser")){
            case "chrome":
             WebDriverManager.chromedriver().version("114").setup();
                ChromeOptions options=new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
               WebDriverManager.chromedriver().setup();

                driver =  new ChromeDriver(options);

//              ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.setHeadless(true);
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("invalid browser name");
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        //latest implicit wait syntax for selenium 4
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));

        //here we call the method to initialize the class variables , if we dont call the methods
        //we can never have the variables initialzed.
        // before performing any kind of the test in any pages we need to have all the pages objects
        //ready.
        intializePageObjects();

        //to configure the file and the pattern of it, we to call the file
        DOMConfigurator.configure("log4j.xml");

        Log.startTestCase("My first test case is Login test");
        Log.info("My Login test is going on ");
        Log.warning("My test case might be failed");
    }
    public static void closeBrowser(){
Log.info("My test case is about to complete");
Log.endTestCase("This is my login test again");
        driver.quit();
    }

    //we use this method instead of send keys method throughout the framework
    public static void sendText(WebElement element, String textToSend){
        element.clear();
        element.sendKeys(textToSend);
    }

    //to get webdriver wait
    public static WebDriverWait getWait(){
         WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
    }

    //this method will wait for the element to be clickable
    public static void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    //this method will perform click operation but before perform click, it will wait
    //for the element to be clickabl
    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }
//this method will return JavascripExcutor Object
    public static JavascriptExecutor getJSExecutor(){

        //since driver is the parent and if we call method it will the execute the drivers methods
        // as we need the Javascript Executor methods therefore we cast and say that convert and
        //the driver from web driver to javascrpit executor
        JavascriptExecutor js=(JavascriptExecutor) driver;
        return js;
    }
    //this function will perform click on element using javaScripy excutor
    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();",element);
    }
    //selecting the dropdown
    public static void selectDropdown(WebElement element,String text){
        Select s =new Select(element);
        s.selectByVisibleText(text);
    }

    public static byte[] takeScreenshot(String fileName){//file name is the screen shot name .
        //this the same scenariou as java scrpt executor
        TakesScreenshot ts = (TakesScreenshot) driver;
        // this mean convert out screen shot to bytes, we dont want file
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        //this means give me the screen shot as file not byte
        File sourceFile =  ts.getScreenshotAs(OutputType.FILE);

        // working with files, can always throw an exception thats y we need to put it in try catcch
        try {
            //this is coming from common io dependency
            //here we are copying and pasting
            FileUtils.copyFile(sourceFile,
                    // new file actually creates the screenshot folder in our project, if we already had the screenshot
                    //folder using new file will only paste the screenshot in that folder
                    // in this new file upto screenshot _filepath is our screenshot path as new file takes the path+ the
                    //file name then we pass the file name with the date to avoid the overriden of the screenshot
                    new File(Constants.SCREENSHOT_FILEPATH+ fileName + " " +
                            getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));

            // till here we save our scrren shot in our project folder we do not need to return it

        } catch (IOException e) {
            e.printStackTrace();
        }

        return picBytes;
    }

    public static String getTimeStamp(String pattern){
        Date date = new Date();
        //here if we use only date it does not have a good format thatsy we need The simpleDate Format to have a better
        //design of our date and time
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
