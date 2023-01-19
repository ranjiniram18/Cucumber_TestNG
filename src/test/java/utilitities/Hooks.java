package utilitities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.edge.EdgeDriver;

import common.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseClass {
	private BaseClass base;

	public Hooks(BaseClass base) {
		this.base = base;
	}

	@Before
	public void setUp() {
		System.setProperty("webdriver.edge.driver", "C:\\Driver\\Selenium_New\\msedgedriver.exe");
		base.driver = new EdgeDriver();
		base.driver.get("http://adactinhotelapp.com");
		base.driver.manage().window().maximize();
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		base.driver.quit();
	}
}
