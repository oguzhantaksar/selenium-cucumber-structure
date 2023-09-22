package tako.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.jv.Lan;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import tako.TestComponents.BaseTest;
import takoFramework.pageobjects.*;

import java.io.IOException;
import java.util.List;


public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductsPage productsPage;
    public ThanksPage thanksPage;
    public OrdersPage ordersPage;
    public CartPage cartPage;

    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException {

       landingPage = launchApp();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password) throws IOException {

        productsPage = landingPage.Login(username,password);
    }

    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String productName) throws IOException {

        List<WebElement> products = productsPage.getProductsList();
        productsPage.addProductToCart(productName);

    }

    @When("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName) {

        CartPage cartPage = productsPage.goToCartPage();
        Boolean match = cartPage.validateCart(productName);
        Assert.assertTrue(match);
        OrderPage orderPage = cartPage.goToOrderPage();
        orderPage.selectCountry("india");
        thanksPage = orderPage.placeOrder();

    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void Message_is_displayed_on_ConfirmationPage(String string) {

        String thanksMessage = thanksPage.controlThanks();
        Assert.assertTrue(thanksMessage.equalsIgnoreCase(string));
        TearDown();
    }

    @Then("{string} error is displayed on LandingPage")
    public void Error_is_displayed_on_LandingPage(String string) {

        Assert.assertEquals(string, landingPage.getNegativeLoginText());
    }

    @When("I navigate to orders page")
    public void I_navigate_to_orders_page() {

        ordersPage = productsPage.goToOrdersPage();

    }


    @Then("{} exists on the orders page")
    public void existsOnTheOrdersPage(String arg0) {

        Boolean match = ordersPage.validateOrder(arg0);
        Assert.assertTrue(match);
    }

    @Then("{} does not exists in cart")
    public void doesNotExistsInCart(String arg0) {

        cartPage = productsPage.goToCartPage();
        Boolean match = cartPage.validateCart(arg0);
        Assert.assertFalse(match);


    }
}
