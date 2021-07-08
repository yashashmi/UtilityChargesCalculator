package uIStepDefinitions;


import io.cucumber.java.*;
import uIStepDefinitions.utility.BaseUtil;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Hook extends BaseUtil {
	private final BaseUtil base;

	public Hook(BaseUtil base) {
		this.base = base;
	}

	@Before
	public void InitTest() {
		System.out.println("Test Init: Before");
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\yasub.hashmi\\Drivers\\chromedriver_win32//chromedriver.exe");
		// base.Driver= new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\yasub.hashmi\\Drivers\\geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.setHeadless(false);
		base.Driver = new FirefoxDriver(options);

	}
	
	@After
	public void AfterScenario()
	{
		base.Driver.quit();
	}
}
