package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //feature we use to provide the path of all the features files
        features =
                "src/test/resources/features/",//the last / means run all the files in
        //features directory
        glue = "APISteps",//another cucumber option, sticking step decalration with step difi
        //when u set dry run to true, it stops the actual execution it will quickly scan all the gherkin steps whether
        //they are implemented or not
        dryRun=false,
        //dry run to fasle-it starts the execution
        // tags="@sprint3 or @sprint1",//the desired senario is either in sprnt1 or sprnt2
        tags=" @api",
        //to remove irrelavant information from console, you need to set monochrome to true
        monochrome = true,
        //pretty keyword prints the steps in the console to increse readiblity
        //to generate the reports we need plugin of runner class
        //this failed txt file holds all the scenarious which are failed during execution
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json","rerun:target/failed.txt"}
)
public class APIRunner {
}
