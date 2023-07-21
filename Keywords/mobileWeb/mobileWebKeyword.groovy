package mobileWeb

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.builtin.GetDeviceHeightKeyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.sun.org.apache.xalan.internal.xslt.Process
import java.time.Duration
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.DesiredCapabilities as DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.*
import io.appium.java_client.AppiumDriver
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption

import java.net.URL as URL
import org.json.JSONObject as JSONObject
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import java.util.NoSuchElementException as NoSuchElementException
import java.util.concurrent.TimeUnit as TimeUnit
import com.kms.katalon.core.util.internal.PathUtil as PathUtil


@Keyword
def AcceptGeolocation (String deviceNameCaps) {

	DesiredCapabilities caps = new DesiredCapabilities()

	caps.setCapability('device', deviceNameCaps)
	caps.setCapability('udid', GlobalVariable.udid)
	caps.setCapability('platform', 'ANDROID')
	caps.setCapability('browser', 'CHROME')


	AppiumDriver driver = new AndroidDriver(new URL('http://127.0.0.1:4723/wd/hub'), caps)

	driver.context('NATIVE_APP')

	'Melakukan proses menunggu sampai web momotor ter-load'
	WebUI.delay(5)

	WebElement wait = new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath('.//android.widget.Button[@text=\'Allow\']')))

	driver.findElement(By.xpath('.//android.widget.Button[@text=\'Allow\']')).click()

	try {
		driver.findElement(By.id('com.android.chrome:id/infobar_close_button')).click()
	}
	catch (NoSuchElementException) {
		println('TranslatorNotPresent')
	}
}

@Keyword
def CloseTranslateGoogleInfoBar (String deviceNameCaps) {

	DesiredCapabilities caps = new DesiredCapabilities()

	caps.setCapability('device', deviceNameCaps)
	caps.setCapability('udid', GlobalVariable.udid)
	caps.setCapability('platform', 'ANDROID')
	caps.setCapability('browser', 'CHROME')


	AppiumDriver driver = new AndroidDriver(new URL('http://127.0.0.1:4723/wd/hub'), caps)

	driver.context('NATIVE_APP')

	try {
		WebElement wait = new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id('com.android.chrome:id/infobar_close_button')))
		driver.findElement(By.id('com.android.chrome:id/infobar_close_button')).click()
	}
	catch (NoSuchElementException) {
		println('TranslatorNotPresent')
	}
}

@Keyword
def StartSessionAppiumDefault (String pathAppium) {
	process = Runtime.getRuntime().exec(pathAppium)
}


@Keyword
def TentukanJumlahDP (String hargaTertera, int persenDP) {
	def intVal = Integer.parseInt(hargaTertera.replaceAll(/[^\d]/, ''))

	int totalDP = intVal * (persenDP/100)

	String totalDPString = totalDP.toString()

	return totalDPString
}

@Keyword
def randomizeNumberPhone() {
	def randomMizer
	def phoneNumber
	Random generator = new Random()
	randomMizer = generator.nextInt(89999999) +  10000000

	phoneNumber = '0813' + randomMizer

	return phoneNumber
}

@Keyword
def clickSet (String deviceNameCaps) {

	DesiredCapabilities caps = new DesiredCapabilities()

	caps.setCapability('device', deviceNameCaps)
	caps.setCapability('udid', GlobalVariable.udid)
	caps.setCapability('platform', 'ANDROID')
	caps.setCapability('browser', 'CHROME')


	AppiumDriver driver = new AndroidDriver(new URL('http://127.0.0.1:4723/wd/hub'), caps)

	driver.context('NATIVE_APP')

	WebElement wait = new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id('android:id/button1')))

	try {
		driver.findElement(By.id('android:id/button1')).click()
	}
	catch (NoSuchElementException) {
		println('SetDateNotPresent')
	}
}

@Keyword
def scrollHorizontally(deviceNameCaps) {
	DesiredCapabilities caps = new DesiredCapabilities()

	caps.setCapability('device', deviceNameCaps)
	caps.setCapability('udid', GlobalVariable.udid)
	caps.setCapability('platform', 'ANDROID')
	caps.setCapability('browser', 'CHROME')

	AppiumDriver driver = new AndroidDriver(new URL('http://127.0.0.1:4723/wd/hub'), caps)

	int  y = driver.manage().window().getSize().height / 2;
	int start_x = (int) (driver.manage().window().getSize().width * 0.1);
	int end_x = (int) (driver.manage().window().getSize().width * 0.8);
	TouchAction dragNDrop = new TouchAction(driver)
			.press(PointOption.point(end_x, y))
			.moveTo(PointOption.point(start_x, y))
			.release()
	dragNDrop.perform();
}

