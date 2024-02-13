package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.Log;

public class AddLanguageSteps extends CommonMethods {
    @When("user user clicks on admin button")
    public void user_user_clicks_on_admin_button() {
        click(addLanguage.adminoption);
    }
    @When("user clicks on qualifications option")
    public void user_clicks_on_qualifications_option() {
click(addLanguage.QualificationOption);
    }


    @When("user clicks on languages option")
    public void user_clicks_on_languages_option() {
        Log.startTestCase("tc0001");
        click(addLanguage.languageOption);
    }



    @When("user clicks on Add button")
    public void user_clicks_on_add_button() {
      click(addLanguage.addbtn);
    }
    @When("user enters a language name")
    public void user_enters_a_language_name() {
sendText(addLanguage.languageNameTextField,"Itaian");
    }
    @Then("language should be added successfully")
    public void language_should_be_added_successfully() {
        System.out.println("Italian language has been added successfully");
    }

}
