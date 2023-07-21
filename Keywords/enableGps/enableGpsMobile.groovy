package enableGps

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import internal.GlobalVariable

import org.json.JSONObject
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions


@Keyword
def enableGPSMobileWebChrome () {
	ChromeOptions options = new ChromeOptions();

	JSONObject jsonObject = new JSONObject();
	jsonObject.put("profile.default_content_settings.geolocation", 2);

	options.setExperimentalOption("prefs", jsonObject);
	WebDriver driver = new ChromeDriver(options);
}

@Keyword
def enableGPSMobileWebChromeEmu () {

	DesiredCapabilities caps = new DesiredCapabilities();
	caps.setCapability("version", "5");
	caps.setCapability("device", "emulator-5554");
	caps.setCapability("platform", "ANDROID");
	caps.setCapability("browser", "CHROME");

	AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

	while (driver.findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size()>0) {
		driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
	}
}
