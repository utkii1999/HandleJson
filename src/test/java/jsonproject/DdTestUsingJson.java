package jsonproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class DdTestUsingJson {
	WebDriver driver;

	@BeforeClass
	void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);

	}

	@Test(dataProvider = "dp")
	void Login(String data) {

		String users[] = data.split(",");
		driver.get("https://demo.nopcommerce.com/login");
		driver.findElement(By.id("Email")).sendKeys(users[0]);//username
		driver.findElement(By.id("Password")).sendKeys(users[1]);//password
		driver.findElement(By.cssSelector("div.master-wrapper-page:nth-child(6) div.master-wrapper-content div.master-column-wrapper div.center-1 div.page.login-page div.page-body div.customer-blocks div.returning-wrapper.fieldset form:nth-child(1) div.buttons:nth-child(3) > button.button-1.login-button")).click();

		String act_title = driver.getTitle();
		String exp_title = "nopCommerce demo store. Login";
		Assert.assertEquals(act_title, exp_title);

	}

	@DataProvider(name = "dp")
	public String[] readJson() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader(".\\jsonfiles\\testdata.json");
		Object obj = parser.parse(reader);
		JSONObject userloginsJsonobj = (JSONObject) obj;

		JSONArray userloginarray = (JSONArray) userloginsJsonobj.get("userlogins");

		String[] arr = new String[userloginarray.size()];

		for (int i = 0; i < userloginarray.size(); i++) {
			JSONObject users = (JSONObject) userloginarray.get(i);
			String user = (String) users.get("username");
			String pass = (String) users.get("password");

			arr[i] = user + "," + pass;

		}
		return arr;

	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

}
