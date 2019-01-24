package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStepdefs {
    @Before()
    public void start() {
        System.out.println("Я МОРАЛЬНО НАСТРАИВАЮСЬ НА ВЫПОЛНЕНИЕ ТЕСТОВ");
    }

    @And("^I click login button$")
    public void iClickLoginButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I click login button");
    }

    @Given("^I (am on the|navigate to) main Marathon page$")
    public void iAmOnTheMainMarathonPage(String actionType) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Action type: " + actionType);
        System.out.println("I am on the main Marathon page");
    }

    @When("^I enter Hello as a username$")
    public void iEnterHelloAsAUsername() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I enter Hello as a username");;
    }

    @And("^I enter World as a password$")
    public void iEnterWorldAsAPassword() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I enter World as a password");;
    }

    @Then("^I should see the notification of wrong credentials$")
    public void iShouldSeeTheNotificationOfWrongCredentials() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I should see the notification of wrong credentials");;
    }

    @When("^I login with Hello and World credentials$")
    public void iLoginWithHelloAndWorldCredentials() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I login with Hello and World credentials");;
    }

    @When("^I enter \"([^\"]*)\" as a username$")
    public void iEnterAsAUsername(String username) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Username: " + username);
        System.out.println("Step: I enter \"([^\"]*)\" as a username");
    }

    @And("^I enter \"([^\"]*)\" as a password$")
    public void iEnterAsAPassword(String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Password: " + password);
        System.out.println("Step: I enter \"([^\"]*)\" as a password");
    }
}
