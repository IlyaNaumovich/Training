package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.Dictionary;
import helper.WebDriverSingleton;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.SearchPage;

import java.net.MalformedURLException;

public class MyStepdefs {
    MainPage mainPage;
    SearchPage searchPage;

    @Before()
    public void start() throws MalformedURLException {
//        System.out.println("Я МОРАЛЬНО НАСТРАИВАЮСЬ НА ВЫПОЛНЕНИЕ ТЕСТОВ");
        mainPage = new MainPage(WebDriverSingleton.init());
        searchPage = new SearchPage(WebDriverSingleton.init());


    }

    @After
    public void finish(Scenario scenario) throws MalformedURLException {
        if (scenario.isFailed()) {
            System.out.println("Scenario " + scenario.getName() + "is failed");
        } else {
            System.out.println("Scenario " + scenario.getName() + "is passed");
        }

        WebDriverSingleton.kill();
    }

    @And("^I click login button$")
    public void iClickLoginButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        System.out.println("I click login button");
        mainPage.clickLoginButton();
    }

    @Given("^I (am on the|navigate to) main Marathon page$")
    public void iAmOnTheMainMarathonPage(String actionType) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        System.out.println("Action type: " + actionType);
//        System.out.println("I am on the main Marathon page");
        mainPage.open();
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
//        System.out.println("I should see the notification of wrong credentials");;
        String lan = System.getProperty("Language");
        if (lan == null) {
            lan = "en";
        }

        String actualMessage = mainPage.getPopupMessage();
        String realMessage = Dictionary.getTranslationForDoesntMeetReq(lan);
        String infoMessage = String.format("The message should be '%s'", realMessage);

        Assert.assertEquals(actualMessage, realMessage, infoMessage);
    }

    @When("^I login with Hello and World credentials$")
    public void iLoginWithHelloAndWorldCredentials() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I login with Hello and World credentials");;
        mainPage.login("Hello", "World");
    }

    @When("^I enter \"([^\"]*)\" as a username$")
    public void iEnterAsAUsername(String username) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        System.out.println("Username: " + username);
//        System.out.println("Step: I enter \"([^\"]*)\" as a username");
        mainPage.fillLogin(username);
        mainPage.fillLogin(Keys.TAB.toString());
    }

    @And("^I enter \"([^\"]*)\" as a password$")
    public void iEnterAsAPassword(String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        System.out.println("Password: " + password);
//        System.out.println("Step: I enter \"([^\"]*)\" as a password");
//        throw new Exception("Smth wrong");
        mainPage.fillPassword(password);
    }

    @When("^Я ввожу \"([^\"]*)\" в поле поиска$")
    public void яВвожуВПолеПоиска(String searchPhrase) throws Throwable {
        System.setProperty("Search phrase", searchPhrase);
        mainPage.search(searchPhrase);
    }

    @And("^I click search button$")
    public void iClickSearchButton() throws Throwable {
    }

    @Then("^I should be redirected to search page$")
    public void iShouldBeRedirectedToSearchPage() throws Throwable {
        Assert.assertTrue(searchPage.isSearchResultBlockExist(), "Search page should be opened");
    }

    @And("^I see the right text$")
    public void iSeeTheRightText() throws Throwable {
        String searchPhrase = System.getProperty("Search phrase");
        String resultValue = searchPage.getSearchResultBlockText();
//        Assert.assertTrue(resultValue.contains("Hello world!"), "Text should contain 'Hello world!'");
        Assert.assertTrue(resultValue.contains(searchPhrase), "Text should contain '"+ searchPhrase + "'");
    }
}
