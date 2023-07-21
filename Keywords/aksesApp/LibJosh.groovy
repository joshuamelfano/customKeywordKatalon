package aksesApp

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.TestObjectXpath
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownTestCase
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.openqa.selenium.Keys as Keys
import io.appium.java_client.android.AndroidDriver as AndroidDriver
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import org.openqa.selenium.JavascriptExecutor
import io.appium.java_client.AppiumDriver
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*
import org.apache.poi.ss.usermodel.*
import org.apache.poi.util.IOUtils as IOUtils
import java.io.InputStream as InputStream
import java.awt.Font
import java.io.File as File
import java.awt.font.TextAttribute
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import io.appium.java_client.android.nativekey.KeyEventFlag as KeyEventFlag
import com.kms.katalon.core.testobject.MobileTestObject
import com.kms.katalon.core.testobject.MobileTestObject.MobileLocatorStrategy
import com.kms.katalon.core.webui.driver.DriverFactory

class LibJosh {


	@Keyword

	public String Split(String splitTarget,String separator,int index){

		String [] splittedTarget = splitTarget.split(separator);

		String result = splittedTarget[index];


		return result;
	}

	@Keyword

	public String SplitAndMerge(String splitTarget,String separator){

		String [] splittedTarget = splitTarget.split(separator);

		int indexTotal = splittedTarget.length;
		String kalimatLoop="";

		for(int i=0; i<indexTotal; i++){
			kalimatLoop = splittedTarget[i];
			if(i==indexTotal-1){
				kalimatLoop=kalimatLoop;
			}else{
				kalimatLoop=kalimatLoop+" ";
			}
			return kalimatLoop;
		}
	}


	@Keyword
	static void addGlobalVariable(String name, def value) {
		GroovyShell shell1 = new GroovyShell()
		MetaClass mc = shell1.evaluate("internal.GlobalVariable").metaClass
		String getterName = "get" + name.capitalize()
		mc.'static'."$getterName" = { -> return value }
		mc.'static'."$name" = value
	}


	/*@Keyword
	 def DeleteUserProspectFromDB(String phoneNumber){
	 Connection conn = null
	 ResultSet actorData
	 ResultSet actorData2
	 //Target DB dengan penambahan timezone beserta query-nya
	 conn = DriverManager.getConnection('jdbc:mysql://rm-d9jl3xtniu9x99a4f2o.mysql.ap-southeast-5.rds.aliyuncs.com/ms-loyalty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC',
	 'devadminopen', '@Dira2019')
	 conn.setAutoCommit(false)
	 actorData = CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.executeQuery'(conn,  """SELECT * FROM profile WHERE phone LIKE '${phoneNumber}'""")
	 println(actorData)
	 if (actorData.next()) {
	 String hslQuery1 = actorData.getString('pin')
	 println(hslQuery1)
	 if (hslQuery1 == '') {
	 println(hslQuery1)
	 } else {
	 //menyambung koneksi ke database
	 conn = null
	 actorData
	 actorData2
	 //Target DB dengan penambahan timezone beserta query-nya
	 conn = DriverManager.getConnection('jdbc:mysql://rm-d9jl3xtniu9x99a4f2o.mysql.ap-southeast-5.rds.aliyuncs.com/ms-loyalty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC',
	 'devadminopen', '@Dira2019')
	 conn.setAutoCommit(false)
	 actorData = CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.executeQuery'(conn, """SELECT * FROM profile WHERE phone LIKE '${phoneNumber}'""")
	 println(actorData)
	 if (actorData.next()) {
	 hslQuery1 = actorData.getString('phone')
	 hslQuery2 = actorData.getString('fullname')
	 hslQuery = hslQuery1 + hslQuery2
	 println(hslQuery)
	 }
	 //Cari no telp yang sudah berhasil di daftarkan
	 String value2 = actorData.getObject('phone')
	 String value3 = actorData.getObject('fullname')
	 String value1 = value2+value3
	 println(value1)
	 //menghapus pin yang sudah di daftarkan
	 actorData2 = CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.executeUpdate'(conn, """DELETE FROM profile WHERE phone LIKE '${phoneNumber}'""")
	 conn.setAutoCommit(false)
	 println(actorData2)
	 if (actorData2 == null) {
	 println('PIN sudah di hapus')
	 } else {
	 println('PIN Belum Terhapus')
	 }
	 CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.closeConnection'(conn)
	 WebUI.delay(3)
	 }
	 }
	 } */

	@Keyword
	def CaptureScreen(){
		GlobalVariable.captureCount = (GlobalVariable.captureCount + 1)
		Thread.sleep(1000)
		GlobalVariable.a = (GlobalVariable.a + 1)

		if (GlobalVariable.captureCount >= 10){
			GlobalVariable.formatCapture = '000'
		}else if (GlobalVariable.captureCount >= 100){
			GlobalVariable.formatCapture = '00'
		}else if (GlobalVariable.captureCount >= 1000){
			GlobalVariable.formatCapture = '0'
		}else if (GlobalVariable.captureCount >= 10000){
			GlobalVariable.formatCapture = ''
		}else if (GlobalVariable.captureCount < 10){
			GlobalVariable.formatCapture = '0000'
		}

		GlobalVariable.NamaFile = ( GlobalVariable.captureFolder + GlobalVariable.noRow + '_' + GlobalVariable.formatCapture + GlobalVariable.captureCount + '.jpeg')
		Mobile.takeScreenshot(GlobalVariable.NamaFile)

		//GlobalVariable.NamaFile = ('./Screenshoot/' + RunConfiguration.getExecutionSourceName() + '/' + GlobalVariable.noRow + '_' + GlobalVariable.formatCapture + GlobalVariable.captureCount + '.jpeg')
	}


	@Keyword
	def inputPadNumber(String padNumber) {

		int i = 0
		int j = 1

		while (i < 6) {
			//switch case
			String substringOTP = padNumber.substring(i,j)

			switch(substringOTP){
				case '1':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_1'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '2':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_2'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)

					break;

				case '3':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_3'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '4':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_4'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '5':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_5'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '6':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_6'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '7':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_7'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '8':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_8'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '9':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_9'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '0':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_0'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;
			}

			//if else

			/*
			 if (otp.substring(i, j) == '1') {
			 Mobile.tap(key1,1)
			 } else if (otp.substring(i, j) == '2') {
			 Mobile.tap(key2,1)
			 } else if (otp.substring(i, j) == '3') {
			 Mobile.tap(key3,1)
			 } else if (otp.substring(i, j) == '4') {
			 Mobile.tap(key4,1)
			 } else if (otp.substring(i, j) == '5') {
			 Mobile.tap(key5,1)
			 } else if (otp.substring(i, j) == '6') {
			 Mobile.tap(key6,1)
			 } else if (otp.substring(i, j) == '7') {
			 Mobile.tap(key7,1)
			 } else if (otp.substring(i, j) == '8') {
			 Mobile.tap(key8,1)
			 } else if (otp.substring(i, j) == '9') {
			 Mobile.tap(key9,1)
			 } else if (otp.substring(i, j) == '0') {
			 Mobile.tap(key0,1)
			 }
			 */
			i++
			j++

		}

	}
	
	
	@Keyword
	def inputPadNumber8Chars(String padNumber) {

		int i = 0
		int j = 1

		while (i < 8) {
			//switch case
			String substringOTP = padNumber.substring(i,j)

			switch(substringOTP){
				case '1':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_1'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '2':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_2'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)

					break;

				case '3':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_3'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '4':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_4'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '5':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_5'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '6':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_6'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '7':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_7'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '8':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_8'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '9':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_9'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;

				case '0':
					Mobile.tap(findTestObject('Page_OTP_PIN/button_Pad_0'), 1);
					Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
					Mobile.takeScreenshot(((RunConfiguration.getReportFolder() + '/Pad Number ') + substringOTP) + 'Inputted_' + i + '.png', FailureHandling.STOP_ON_FAILURE)
					break;
			}

			//if else

			/*
			 if (otp.substring(i, j) == '1') {
			 Mobile.tap(key1,1)
			 } else if (otp.substring(i, j) == '2') {
			 Mobile.tap(key2,1)
			 } else if (otp.substring(i, j) == '3') {
			 Mobile.tap(key3,1)
			 } else if (otp.substring(i, j) == '4') {
			 Mobile.tap(key4,1)
			 } else if (otp.substring(i, j) == '5') {
			 Mobile.tap(key5,1)
			 } else if (otp.substring(i, j) == '6') {
			 Mobile.tap(key6,1)
			 } else if (otp.substring(i, j) == '7') {
			 Mobile.tap(key7,1)
			 } else if (otp.substring(i, j) == '8') {
			 Mobile.tap(key8,1)
			 } else if (otp.substring(i, j) == '9') {
			 Mobile.tap(key9,1)
			 } else if (otp.substring(i, j) == '0') {
			 Mobile.tap(key0,1)
			 }
			 */
			i++
			j++

		}

	}

	@Keyword
	def RealDeviceOTPXPATH(String otp) {
		int keyIndex1 = 8
		int keyIndex2 = 9
		int keyIndex3 = 10
		int keyIndex4 = 11
		int keyIndex5 = 13
		int keyIndex6 = 15
		int keyIndex7 = 12
		int keyIndex8 = 14
		int keyIndex9 = 16
		int keyIndex0 = 18

		int i = 0
		int j = 1

		while (i < 6) {
			MobileTestObject key0 = findTestObject('Object Repository/v240/Login/login_idx/btn_0')
			key0.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			key0.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex0+"]")

			MobileTestObject key1 = findTestObject('Object Repository/v240/Login/login_idx/btn_1')
			key1.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			key1.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex1+"]")

			MobileTestObject key2 = findTestObject('Object Repository/v240/Login/login_idx/btn_2')
			key2.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			key2.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex2+"]")

			MobileTestObject key3 = findTestObject('Object Repository/v240/Login/login_idx/btn_3')
			key3.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			key3.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex3+"]")

			MobileTestObject key4 = findTestObject('Object Repository/v240/Login/login_idx/btn_4')
			key4.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			key4.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex4+"]")

			MobileTestObject key5 = findTestObject('Object Repository/v240/Login/login_idx/btn_5')
			key5.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			key5.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex5+"]")

			MobileTestObject key6 = findTestObject('Object Repository/v240/Login/login_idx/btn_6')
			key6.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			key6.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex6+"]")

			MobileTestObject key7 = findTestObject('Object Repository/v240/Login/login_idx/btn_7')
			key7.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			key7.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex7+"]")

			MobileTestObject key8 = findTestObject('Object Repository/v240/Login/login_idx/btn_8')
			key8.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			key8.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex8+"]")

			MobileTestObject key9 = findTestObject('Object Repository/v240/Login/login_idx/btn_9')
			key9.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			key9.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex9+"]")

			//switch case

			String substringOTP = otp.substring(i,j)

			switch(substringOTP){
				case '1':
					Mobile.tap(key1,1);
					break;

				case '2':
					Mobile.tap(key2,1)
					break;

				case '3':
					Mobile.tap(key3,1);
					break;

				case '4':
					Mobile.tap(key4,1);
					break;

				case '5':
					Mobile.tap(key5,1);
					break;

				case '6':
					Mobile.tap(key6,1);
					break;

				case '7':
					Mobile.tap(key7,1);
					break;

				case '8':
					Mobile.tap(key8,1);
					break;

				case '9':
					Mobile.tap(key9,1);
					break;

				case '0':
					Mobile.tap(key0,1)
					break;
			}

			//if else

			/*
			 if (otp.substring(i, j) == '1') {
			 Mobile.tap(key1,1)
			 } else if (otp.substring(i, j) == '2') {
			 Mobile.tap(key2,1)
			 } else if (otp.substring(i, j) == '3') {
			 Mobile.tap(key3,1)
			 } else if (otp.substring(i, j) == '4') {
			 Mobile.tap(key4,1)
			 } else if (otp.substring(i, j) == '5') {
			 Mobile.tap(key5,1)
			 } else if (otp.substring(i, j) == '6') {
			 Mobile.tap(key6,1)
			 } else if (otp.substring(i, j) == '7') {
			 Mobile.tap(key7,1)
			 } else if (otp.substring(i, j) == '8') {
			 Mobile.tap(key8,1)
			 } else if (otp.substring(i, j) == '9') {
			 Mobile.tap(key9,1)
			 } else if (otp.substring(i, j) == '0') {
			 Mobile.tap(key0,1)
			 }
			 */
			i++
			j++

			keyIndex1 = keyIndex1 - 1
			keyIndex2 = keyIndex2 - 1
			keyIndex3 = keyIndex3 - 1
			keyIndex4 = keyIndex4 - 1
			keyIndex5 = keyIndex5 - 1
			keyIndex6 = keyIndex6 - 1
			keyIndex7 = keyIndex7 - 1
			keyIndex8 = keyIndex8 - 1
			keyIndex9 = keyIndex9 - 1
			keyIndex0 = keyIndex0 - 1

			if (i==4) {
				CaptureScreen()
			}

		}

	}

	/*@Keyword
	 def CountItemResultInFAQ(){
	 WebDriver driver = DriverFactory.getWebDriver()
	 static final String TOTAL_ITEM_FAQ = '//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[1]'
	 try {
	 WebElement temp = driver.findElement(By.xpath(TOTAL_ITEM_FAQ))
	 List list = temp.findElements(By.xpath(TOTAL_ITEM_FAQ))
	 int totalItemsFAQ = list.size()
	 KeywordUtil.logInfo("total number of items in FAQ: "+list.size())
	 return list.size()
	 } catch (Exception e) {
	 KeywordUtil.logInfo("total number of items in FAQ: 0")
	 return 0
	 }
	 }*/

	@Keyword
	def NavigateToEveryFAQ(){
		WebDriver driver = DriverFactory.getWebDriver()
		String TOTAL_ITEM_FAQ = '//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[1]'

		try {
			WebElement temp = driver.findElement(By.xpath(TOTAL_ITEM_FAQ))
			List list = temp.findElements(By.xpath(TOTAL_ITEM_FAQ))
			int totalItemsFAQ = list.size()
			KeywordUtil.logInfo("total number of items in FAQ: "+list.size())

			for(int faqNumber = 2; faqNumber <= totalItemsFAQ; faqNumber++ ){
				String ITEM_FAQ = '//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup['+faqNumber+']/android.view.ViewGroup[1]/android.widget.TextView[1]'

				Mobile.tap(findTestObject(ITEM_FAQ), 1)
				Mobile.delay(10, FailureHandling.STOP_ON_FAILURE)
				Mobile.pressBack()
			}
		} catch (Exception e) {
			KeywordUtil.logInfo("total number of items in FAQ: 0")
			return 0
		}
	}

	@Keyword
	def RealDevicePINXPATH (String pin) {


		int i = 0
		int j = 1

		while (i < 6) {
			MobileTestObject PINkey0 = findTestObject('Object Repository/v240/Login/login_idx/btn_0')
			PINkey0.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey0.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[8]")

			MobileTestObject PINkey1 = findTestObject('Object Repository/v240/Login/login_idx/btn_1')
			PINkey1.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey1.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup["+keyIndex1+"]")

			MobileTestObject PINkey2 = findTestObject('Object Repository/v240/Login/login_idx/btn_2')
			PINkey2.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey2.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[10]")

			MobileTestObject PINkey3 = findTestObject('Object Repository/v240/Login/login_idx/btn_3')
			PINkey3.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey3.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[11]")

			MobileTestObject PINkey4 = findTestObject('Object Repository/v240/Login/login_idx/btn_4')
			PINkey4.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey4.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[13")

			MobileTestObject PINkey5 = findTestObject('Object Repository/v240/Login/login_idx/btn_5')
			PINkey5.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey5.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[15]")

			MobileTestObject PINkey6 = findTestObject('Object Repository/v240/Login/login_idx/btn_6')
			PINkey6.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey6.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[12]")

			MobileTestObject PINkey7 = findTestObject('Object Repository/v240/Login/login_idx/btn_7')
			PINkey7.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey7.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[14]")

			MobileTestObject PINkey8 = findTestObject('Object Repository/v240/Login/login_idx/btn_8')
			PINkey8.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey8.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[16]")

			MobileTestObject PINkey9 = findTestObject('Object Repository/v240/Login/login_idx/btn_9')
			PINkey9.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey9.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[18]")

			////

			MobileTestObject cPINkey0 = findTestObject('Object Repository/v240/Login/login_idx/btn_0')
			cPINkey0.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			cPINkey0.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[7]")

			MobileTestObject cPINkey1 = findTestObject('Object Repository/v240/Login/login_idx/btn_1')
			cPINkey1.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			cPINkey1.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[8]")

			MobileTestObject cPINkey2 = findTestObject('Object Repository/v240/Login/login_idx/btn_2')
			cPINkey2.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			cPINkey2.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[9]")

			MobileTestObject cPINkey3 = findTestObject('Object Repository/v240/Login/login_idx/btn_3')
			cPINkey3.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			cPINkey3.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[10]")

			MobileTestObject cPINkey4 = findTestObject('Object Repository/v240/Login/login_idx/btn_4')
			cPINkey4.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			cPINkey4.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[12]")

			MobileTestObject cPINkey5 = findTestObject('Object Repository/v240/Login/login_idx/btn_5')
			cPINkey5.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			cPINkey5.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[14]")

			MobileTestObject cPINkey6 = findTestObject('Object Repository/v240/Login/login_idx/btn_6')
			cPINkey6.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			cPINkey6.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[11]")

			MobileTestObject cPINkey7 = findTestObject('Object Repository/v240/Login/login_idx/btn_7')
			cPINkey7.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			cPINkey7.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[13]")

			MobileTestObject cPINkey8 = findTestObject('Object Repository/v240/Login/login_idx/btn_8')
			cPINkey8.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			cPINkey8.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[15]")

			MobileTestObject cPINkey9 = findTestObject('Object Repository/v240/Login/login_idx/btn_9')
			cPINkey9.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			cPINkey9.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[17]")

			println(pin.substring(i, j))

			if (pin.substring(i, j) == '1') {
				if (i > 0) {
					Mobile.tap(cPINkey1,1)
				}else {
					Mobile.tap(PINkey1,1)
				}
			} else if (pin.substring(i, j) == '2') {
				if (i > 0) {
					Mobile.tap(cPINkey2,1)
				}else {
					Mobile.tap(PINkey2,1)
				}
			} else if (pin.substring(i, j) == '3') {
				if (i > 0) {
					Mobile.tap(cPINkey3,1)
				}else {
					Mobile.tap(PINkey3,1)
				}
			} else if (pin.substring(i, j) == '4') {
				if (i > 0) {
					Mobile.tap(cPINkey4,1)
				}else {
					Mobile.tap(PINkey4,1)
				}
			} else if (pin.substring(i, j) == '5') {
				if (i > 0) {
					Mobile.tap(cPINkey5,1)
				}else {
					Mobile.tap(PINkey5,1)
				}
			} else if (pin.substring(i, j) == '6') {
				if (i > 0) {
					Mobile.tap(cPINkey6,1)
				}else {
					Mobile.tap(PINkey6,1)
				}
			} else if (pin.substring(i, j) == '7') {
				if (i > 0) {
					Mobile.tap(cPINkey7,1)
				}else {
					Mobile.tap(PINkey7,1)
				}
			} else if (pin.substring(i, j) == '8') {
				if (i > 0) {
					Mobile.tap(cPINkey8,1)
				}else {
					Mobile.tap(PINkey8,1)
				}
			} else if (pin.substring(i, j) == '9') {
				if (i > 0) {
					Mobile.tap(cPINkey9,1)
				}else {
					Mobile.tap(PINkey9,1)
				}
			} else if (pin.substring(i, j) == '0') {
				if (i > 0) {
					Mobile.tap(cPINkey0,1)
				}else {
					Mobile.tap(PINkey0,1)
				}
			}

			i++
			j++

			if (i==4) {
				CaptureScreen()
			}

		}

	}

}
