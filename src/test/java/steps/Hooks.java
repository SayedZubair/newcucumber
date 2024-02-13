package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {
    @Before //we should use the cucumber. java
    public void preCondition(){
        openBrowserAndLaunchApplication();
    }

    //we use special class called scenario class from cucumber this class holds the complete information of
    // ur execution
    @After
    public void postCondition(Scenario scenario){
        byte[] pic;
        if(scenario.isFailed()){
            // this wiil create failed folder under screensht faild screenshot will be availbe inside failed folder
            pic=takeScreenshot("failed/"+scenario.getName());

        }else {
            //this will create pass folder under screenshot --scenario class holds all my information about the scenario
            pic=takeScreenshot("passed/" + scenario.getName());
        }
        //till this point the screenshot will be availbe only inside folder
        //to attach the screenshot in our report
        scenario.attach(pic,"image/png",scenario.getName());

        //
      //  closeBrowser();
    }
}
