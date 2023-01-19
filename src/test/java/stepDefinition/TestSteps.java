package stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import common.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestSteps extends BaseClass {
	// WebDriver driver;
	String titleExpected = "Adactin.com - Search Hotel";
	String usName = "";
	private BaseClass base;

	public void Stepsof_Featurefile(BaseClass base) {
		WebDriver driver;

		String expectedLoginTitle = "https://adactinhotelapp.com/";
		String expectedTitle = "Adactin.com - Search Hotel";
	}

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome_Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@After
	public void teardown(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		driver.quit();
	}

	@Given("when the user is on the login page")
	public void when_the_user_is_on_the_login_page() {
		driver = new ChromeDriver();
		driver.get("https://adactinhotelapp.com/");
		driver.manage().window().maximize();

		System.out.println("In login page");
	}

	@When("user enters valid username and password")
	public void user_enters_valid_username_and_password() {
		// Find the user name element
		WebElement uName = driver.findElement(By.id("username"));
		// Enter user name
		uName.sendKeys("RanjiniRam");
		usName = "RanjiniRam";
		// find the password element and enter the password directly
		driver.findElement(By.id("password")).sendKeys("anup@123");
		System.out.println("Entered info");
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		driver.findElement(By.cssSelector("input.login_button[value=Login]")).click();
		System.out.println("Am in clicking the login button?");
	}

	@When("Login is successfull")
	public void login_is_successfull() {
		String msgActual = driver.findElement(By.id("username_show")).getAttribute("value");
		String msgExpected = "Hello " + usName + "!";
		Assert.assertEquals(msgExpected, msgActual);
	}

	@When("Search page is displayed")
	public void search_page_is_displayed() {
		String titleActual = driver.getTitle();
		Assert.assertEquals(titleExpected, titleActual);
	}

	@When("User selects \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\"$")
	public void user_selects(String location, String roomno, String chekin, String chekout, String adults) {

		// Drop down to select LOCATION
		new Select(driver.findElement(By.className("search_combobox"))).selectByValue(location);
		// Drop down to select Number of Rooms
		new Select(driver.findElement(By.id("room_nos"))).selectByVisibleText(roomno);
		// Date picker-Enter check in date
		WebElement checkinDate = driver.findElement(By.id("datepick_in"));
		checkinDate.clear();
		checkinDate.sendKeys(chekin);
		// Date picker-Enter checkout date
		WebElement checkoutDate = driver.findElement(By.id("datepick_out"));
		checkoutDate.clear();
		checkoutDate.sendKeys(chekout);
		// Select Adults per room
		new Select(driver.findElement(By.id("adult_room"))).selectByValue(adults);

	}

	@When("User click on search button")
	public void user_click_on_search_button() {
		driver.findElement(By.id("Submit")).click();
	}

	@When("Search results should be displayed")
	public void search_results_should_be_displayed() {
		System.out.println("Successful search page");
	}

	@When("User selects hotel")
	public void user_selects_hotel() {
		driver.findElement(By.id("radiobutton_1")).click();
	}

	@When("User click on continue button")
	public void user_click_on_continue_button() {
		driver.findElement(By.id("continue")).click();
	}

	@When("Book a hotel page should be displayed")
	public void book_a_hotel_page_should_be_displayed() {
		System.out.println("Successful search page");
	}

	@Then("user fills book hotel form \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\"$")
	public void user_fills_book_hotel_form(String firtsName, String lastName, String adress, String cardNo,
			String cardType, String expMonth, String expYear, String cvv) {
		WebElement fName = driver.findElement(By.id("first_name"));
		fName.sendKeys(firtsName);
		WebElement lName = driver.findElement(By.id("last_name"));
		lName.sendKeys(lastName);
		WebElement addr = driver.findElement(By.id("address"));
		addr.sendKeys(adress);
		WebElement cNo = driver.findElement(By.id("cc_num"));
		cNo.sendKeys(cardNo);
		new Select(driver.findElement(By.id("cc_type"))).selectByValue(cardType);
		new Select(driver.findElement(By.id("cc_exp_month"))).selectByVisibleText(expMonth);
		new Select(driver.findElement(By.id("cc_exp_year"))).selectByValue(expYear);
		driver.findElement(By.id("cc_cvv")).sendKeys(cvv);
	}

	@When("User click on BookNow button")
	public void user_click_on_book_now_button() throws InterruptedException {
		driver.findElement(By.id("book_now")).click();
		Thread.sleep(5000);
	}

	@When("user click on my itinerary")
	public void user_click_on_my_itinerary() {
		System.out.println("Entering itenary page function");
		JavascriptExecutor executorjs = (JavascriptExecutor) driver;
		executorjs.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.id("my_itinerary")).click();
	}

	@Then("Itenary page should be displayed")
	public void itenary_page_should_be_displayed() {
		System.out.println("Entering itenary page function");

	}
}
