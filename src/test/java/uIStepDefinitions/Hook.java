package uIStepDefinitions;


import io.cucumber.java.*;
import uIStepDefinitions.utility.BaseUtil;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hook extends BaseUtil {
	private final BaseUtil base;

	public Hook(BaseUtil base) {
		this.base = base;
	}

	@Before
	public void InitTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\yasub.hashmi\\Drivers\\chromedriver_win32\\chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                
                base.Driver = new ChromeDriver(chromeOptions);

	}
	
	@After
	public void AfterScenario()
	{
		base.Driver.quit();
	}
}
