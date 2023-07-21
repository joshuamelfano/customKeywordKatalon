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

import io.appium.java_client.android.nativekey.KeyEventFlag as KeyEventFlag
import com.kms.katalon.core.testobject.MobileTestObject
import com.kms.katalon.core.testobject.MobileTestObject.MobileLocatorStrategy
class LibTiwi{
	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	@Keyword
	def isElementPresent_Mobile(TestObject to, int timeout){
		try {
			KeywordUtil.logInfo("Finding element with id:" + to.getObjectId())

			WebElement element = MobileElementCommonHelper.findElement(to, timeout)
			if (element != null) {
				KeywordUtil.markPassed("Object " + to.getObjectId() + " is present")
			}
			return truep
		} catch (Exception e) {
			KeywordUtil.markFailed("Object " + to.getObjectId() + " is not present")
		}
		return false;
	}


	@Keyword
	def createWorkingPaper(){
		//GlobalVariable.nameTestCase = RunConfiguration.getExecutionSourceName()
		//GlobalVariable.captureFolder = GlobalVariable.captureFolder + GlobalVariable.nameTestCase  + '/' //nameTestCase +
		println GlobalVariable.captureFolder
		try {
			String sheetName 	= 'WORKING PAPER_'+RunConfiguration.getExecutionSourceName()
			Workbook wb 		= new XSSFWorkbook();
			Sheet sheet			= wb.createSheet(sheetName);
			File files			= new File(GlobalVariable.captureFolder);

			FileOutputStream fileOut	= null
			fileOut 					= new FileOutputStream('Regression Testing adiraku 2.5.0' + '.xlsx',true)

			//header
			Cell cellTitle	= sheet.createRow(0).createCell(0)
			cellTitle.setCellValue(sheetName + " ADIRAKU")


			Cell cellAuthor	= sheet.createRow(1).createCell(0)
			cellAuthor.setCellValue('Automated By: Diah Fajar Pratiwi')

			//result
			int i = 1
			int n = 1
			int z = 0
			for (int excelNo : (1..3)){ //GlobalVariable.getLastRow
				i = 1
				Cell cellNomor	= sheet.createRow(n+2).createCell(0)
				cellNomor.setCellValue(excelNo)

				for(String name : Arrays.asList(files.list().sort())){
					if(name.contains('jpg') || name.contains('jpeg')){
						String nameImage 		= name;
						int vNoImage, vsubImageNo, vNoGlb
						String[] getNamaFile	= nameImage.split('_')
						String imageNo			= getNamaFile[0]
						String subImage			= getNamaFile[1]
						String[] getImage		= subImage.split('.jpeg')
						String subImageNo		= getImage[0]
						vNoImage 	= imageNo.toInteger()
						vsubImageNo	= subImageNo.toInteger()
						vNoGlb 		= excelNo

						if (vNoGlb == vNoImage){
							InputStream inputStream = new FileInputStream(GlobalVariable.captureFolder + nameImage)
							byte[] bytes = IOUtils.toByteArray(inputStream)
							int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG)
							inputStream.close()
							CreationHelper helper = wb.getCreationHelper()
							Drawing drawing = sheet.createDrawingPatriarch()
							ClientAnchor anchor = helper.createClientAnchor()
							int c = 1

							anchor.setCol1(i+1)
							anchor.setRow1(c+3+z)
							anchor.setCol2(i+2)
							anchor.setRow2(c+4+z)

							Picture pict = drawing.createPicture(anchor, pictureIdx)
							Cell cell		= sheet.createRow(c + 3 + z).createCell(i)

							int widthUnits = 30*256;
							sheet.setColumnWidth(i+1, widthUnits);
							short heightUnits = 266*20;
							cell.getRow().setHeight(heightUnits);
							c++
							i+=2
						}
					}
				}
				i = 1
				n+=2
				z+=2
			}
			wb.write(fileOut)
			fileOut.close()
		}
		catch (IOException ioex) {
		}
	}

	@Keyword
	def errScript() {
		GlobalVariable.status = 'Failed automation ' + RunConfiguration.getExecutionSourceName()
		println GlobalVariable.status
		println 'automated by DiahFP'
	}

	@Keyword
	def backtoApp() {
		int pb = 0
		while (Mobile.verifyElementVisible(findTestObject('Object Repository/Page_Dashboard/button_BayarAngsuran'), 3, FailureHandling.OPTIONAL) == false) {
			Mobile.pressBack()
			if (pb==10) {
				//status = 'FAILED'
				break;
			}	
			pb++
		}
	}

	@Keyword
	def captureScreenWA() {
		TestObject btnsendchatWA = findTestObject('Object Repository/Features/WA/btn_sendWA')
		Mobile.delay(3)
		CaptureScreen()
		Mobile.tap(btnsendchatWA, GlobalVariable.shortTimeout)
		Mobile.delay(5)
		CaptureScreen()
	}


	@Keyword
	def keyboardSamsungPINParam(String pin) {
		AndroidDriver<?> keyPIN = MobileDriverFactory.getDriver()
		Mobile.delay(1)
		String number = ""
		for (def hp = 1; hp <= pin.size(); hp++) {

			number = pin.substring(hp-1, hp)

			if (number == '1') {
				//				Mobile.tapAtPosition(232.8, 893.5)
				Mobile.tap(findTestObject('Object Repository/AksesApp/Login/PIN/angka_1_pin'), 1)
			} else if (number == '2') {
				//				Mobile.tapAtPosition(536.5, 891.5)
				Mobile.tap(findTestObject('Object Repository/AksesApp/Login/PIN/angka_2_pin'), 1)
			} else if (number == '3') {
				//				Mobile.tapAtPosition(232.8, 893.5)
				Mobile.tap(findTestObject('Object Repository/AksesApp/Login/PIN/angka_3_pin'), 1)
			} else if (number == '4') {
				Mobile.tap(findTestObject('Object Repository/AksesApp/Login/PIN/angka_4_pin'), 1)
			} else if (number == '5') {
				Mobile.tap(findTestObject('Object Repository/AksesApp/Login/PIN/angka_5_pin'), 1)
			} else if (number == '6') {
				Mobile.tap(findTestObject('Object Repository/AksesApp/Login/PIN/angka_6_pin'), 1)
			} else if (number == '7') {
				Mobile.tap(findTestObject('Object Repository/AksesApp/Login/PIN/angka_7_pin'), 1)
			} else if (number == '8') {
				Mobile.tap(findTestObject('Object Repository/AksesApp/Login/PIN/angka_8_pin'), 1)
			} else if (number == '9') {
				Mobile.tap(findTestObject('Object Repository/AksesApp/Login/PIN/angka_9_pin'), 1)
			} else if (number == '0') {
				Mobile.tap(findTestObject('Object Repository/AksesApp/Login/PIN/angka_0_pin'), 1)
			}

			if (hp == 5) {
				Mobile.delay(3)
				CaptureScreen()
			}

			Mobile.delay(2)
		}
	}

	@Keyword
	def keyboardOtpPositionParam(String pin) {
		KeywordLogger log = new KeywordLogger()
		println "keyboardOtpPositionParam"
		println pin
		Mobile.delay(1)

		for (def hp = 1; hp <= pin.size(); hp++) {
			println hp

			String number = pin.substring(hp - 1, hp)
			println number
			log.logInfo('OTP '+ hp + ':' + number)

			//xiaomi
			//			if (number == '1'){
			//				//Mobile.tap(findTestObject('Object Repository/adiraku/keyboard/OPPO F9/key_number1'), 0)
			//				Mobile.tapAtPosition(147.8, 628.5)
			//			} else if (number == '2'){
			//				//Mobile.tap(findTestObject('Object Repository/adiraku/keyboard/OPPO F9/key_number2'), 0)
			//				Mobile.tapAtPosition(375.5, 638.5)
			//			} else if (number == '3'){
			//				//Mobile.tap(findTestObject('Object Repository/adiraku/keyboard/OPPO F9/key_number3'), 0)
			//				Mobile.tapAtPosition(579.2, 666.5)
			//			} else if (number == '4'){
			//				//Mobile.tap(findTestObject('Object Repository/adiraku/keyboard/OPPO F9/key_number4'), 0)
			//				Mobile.tapAtPosition(155.8, 800.4)
			//			} else if (number == '5'){
			//				//Mobile.tap(findTestObject('Object Repository/adiraku/keyboard/OPPO F9/key_number5'), 0)
			//				Mobile.tapAtPosition(354.5, 807.4)
			//			} else if (number == '6'){
			//				//Mobile.tap(findTestObject('Object Repository/adiraku/keyboard/OPPO F9/key_number6'), 0)
			//				Mobile.tapAtPosition(572.2, 822.4)
			//			} else if (number == '7'){
			//				//Mobile.tap(findTestObject('Object Repository/adiraku/keyboard/OPPO F9/key_number7'), 0)
			//				Mobile.tapAtPosition(162.8, 961.2)
			//			} else if (number == '8'){
			//				//Mobile.tap(findTestObject('Object Repository/adiraku/keyboard/OPPO F9/key_number8'), 0)
			//				Mobile.tapAtPosition(360.5, 975.2)
			//			} else if (number == '9'){
			//				//Mobile.tap(findTestObject('Object Repository/adiraku/keyboard/OPPO F9/key_number9'), 0)
			//				Mobile.tapAtPosition(576.2, 970.2)
			//			} else if (number == '0'){
			//				//Mobile.tap(findTestObject('Object Repository/adiraku/keyboard/OPPO F9/key_number0'), 0)
			//				Mobile.tapAtPosition(367.5, 1143.1)
			//			}


			if (number == '1') {
				Mobile.tapAtPosition(221.0, 1004.2)
			} else if (number == '2') {
				Mobile.tapAtPosition(535.0, 1004.2)
			} else if (number == '3') {
				Mobile.tapAtPosition(832.0, 1004.2)
			} else if (number == '4') {
				Mobile.tapAtPosition(221.0, 1265.5)
			} else if (number == '5') {
				Mobile.tapAtPosition(530.0, 1265.5)
			} else if (number == '6') {
				Mobile.tapAtPosition(858.7, 1265.5)
			} else if (number == '7') {
				Mobile.tapAtPosition(221.0, 1524.0)
			} else if (number == '8') {
				Mobile.tapAtPosition(530.0, 1524.0)
			} else if (number == '9') {
				Mobile.tapAtPosition(858.7, 1524.0)
			} else if (number == '0') {
				Mobile.tapAtPosition(543.7, 1839.3)
			}


			Mobile.delay(2)
			if (hp == 5) {
				Mobile.delay(3)
				CaptureScreen()
			}
		}
	}

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




	/**
	 * Get mobile driver for current session
	 * @return mobile driver for current session
	 */
	@Keyword
	def WebDriver getCurrentSessionMobileDriver() {
		return MobileDriverFactory.getDriver();
	}

	@Keyword
	def keyboardNoHP(String number) {
		AndroidDriver<?> driver = MobileDriverFactory.getDriver()

		for (def hp = 1; hp <= number.size(); hp++) {
			String no = number.substring(hp - 1, hp)
			//xiaomi 6a
			//			if (no == '1') {
			//				driver.pressKeyCode(AndroidKeyCode.KEYCODE_1)
			//			} else if (no == '2') {
			//				driver.pressKeyCode(AndroidKeyCode.KEYCODE_2)
			//			} else if (no == '3') {
			//				driver.pressKeyCode(AndroidKeyCode.KEYCODE_3)
			//			} else if (no == '4') {
			//				driver.pressKeyCode(AndroidKeyCode.KEYCODE_4)
			//			} else if (no == '5') {
			//				driver.pressKeyCode(AndroidKeyCode.KEYCODE_5)
			//			} else if (no == '6') {
			//				driver.pressKeyCode(AndroidKeyCode.KEYCODE_6)
			//			} else if (no == '7') {
			//				driver.pressKeyCode(AndroidKeyCode.KEYCODE_7)
			//			} else if (no == '8') {
			//				driver.pressKeyCode(AndroidKeyCode.KEYCODE_8)
			//			} else if (no == '9') {
			//				driver.pressKeyCode(AndroidKeyCode.KEYCODE_9)
			//			} else if (no == '0') {
			//				driver.pressKeyCode(AndroidKeyCode.KEYCODE_0)
			//			}

			//samsung a51
			if (no == '1') {
				Mobile.Tap(findTestObject('Object Repository/2.0.0/Keyboard/key_1'),0)
			} else if (no == '2') {
				Mobile.Tap(findTestObject('Object Repository/2.0.0/Keyboard/key_2'),0)
			} else if (no == '3') {
				Mobile.Tap(findTestObject('Object Repository/2.0.0/Keyboard/key_3'),0)
			} else if (no == '4') {
				Mobile.Tap(findTestObject('Object Repository/2.0.0/Keyboard/key_4'),0)
			} else if (no == '5') {
				Mobile.Tap(findTestObject('Object Repository/2.0.0/Keyboard/key_5'),0)
			} else if (no == '6') {
				Mobile.Tap(findTestObject('Object Repository/2.0.0/Keyboard/key_6'),0)
			} else if (no == '7') {
				Mobile.Tap(findTestObject('Object Repository/2.0.0/Keyboard/key_7'),0)
			} else if (no == '8') {
				Mobile.Tap(findTestObject('Object Repository/2.0.0/Keyboard/key_8'),0)
			} else if (no == '9') {
				Mobile.Tap(findTestObject('Object Repository/2.0.0/Keyboard/key_9'),0)
			} else if (no == '0') {
				Mobile.Tap(findTestObject('Object Repository/2.0.0/Keyboard/key_0'),0)
			}
		}
	}

	// back to home
	@Keyword
	def backToHome(String message) {
		boolean isHome = Mobile.waitForElementPresent(findTestObject('AksesApp/menu_Beranda'), 2)
		while (!isHome) {
			Mobile.pressBack()
			Mobile.delay(2)
			isHome = Mobile.waitForElementPresent(findTestObject('AksesApp/menu_Beranda'), 2)
		}
		Mobile.verifyElementExist(findTestObject('AksesApp/menu_Beranda'), 5)
		Mobile.tap(findTestObject('AksesApp/menu_Beranda'), 0)
		if(message) KeywordUtil.markFailed(message)
	}


	@Keyword
	def RealDeviceOTP(String otp) {
		Mobile.delay(2)

		if(Mobile.verifyElementExist(findTestObject('Additional Test Case/99.2/text_Sitem bermasalah'), 3, FailureHandling.OPTIONAL) == true) {
			throw 'Sistem Bermasalah'
		} else if (Mobile.verifyElementExist(findTestObject('Additional Test Case/99.2/text_Tidak ada koneksi internet'), 3, FailureHandling.OPTIONAL) == true) {
			throw 'Tidak Ada Koneksi Internet'
		} else if (Mobile.verifyElementExist(findTestObject('Additional Test Case/99.2/text_Gagal mengirimkan SMS'), 3, FailureHandling.OPTIONAL) == true) {
			throw 'Gagal Mengirimkan SMS'
		}

		int i = 0

		int j = 1

		while (i < 6) {
			if (otp.substring(i, j) == '1') {
				Mobile.tapAtPosition(221.0, 1004.2)
			} else if (otp.substring(i, j) == '2') {
				Mobile.tapAtPosition(535.0, 1004.2)
			} else if (otp.substring(i, j) == '3') {
				Mobile.tapAtPosition(832.0, 1004.2)
			} else if (otp.substring(i, j) == '4') {
				Mobile.tapAtPosition(221.0, 1265.5)
			} else if (otp.substring(i, j) == '5') {
				Mobile.tapAtPosition(530.0, 1265.5)
			} else if (otp.substring(i, j) == '6') {
				Mobile.tapAtPosition(858.7, 1265.5)
			} else if (otp.substring(i, j) == '7') {
				Mobile.tapAtPosition(221.0, 1524.0)
			} else if (otp.substring(i, j) == '8') {
				Mobile.tapAtPosition(530.0, 1524.0)
			} else if (otp.substring(i, j) == '9') {
				Mobile.tapAtPosition(858.7, 1524.0)
			} else if (otp.substring(i, j) == '0') {
				Mobile.tapAtPosition(543.7, 1839.3)
			}




			i++

			j++


			CaptureScreen()

		}
	}

	@Keyword
	def RealDevicePIN(String pin) {
		Mobile.delay(2)

		if(Mobile.verifyElementExist(findTestObject('Additional Test Case/99.2/text_Sitem bermasalah'), 3, FailureHandling.OPTIONAL) == true) {
			throw 'Sistem Bermasalah'
		} else if (Mobile.verifyElementExist(findTestObject('Additional Test Case/99.2/text_Tidak ada koneksi internet'), 3, FailureHandling.OPTIONAL) == true) {
			throw 'Tidak Ada Koneksi Internet'
		}

		int i = 0

		int j = 1

		while (i < 6) {

			if (pin.substring(i, j) == '1') {
				Mobile.tapAtPosition(221.0, 1004.2)
			} else if (pin.substring(i, j) == '2') {
				Mobile.tapAtPosition(535.0, 1004.2)
			} else if (pin.substring(i, j) == '3') {
				Mobile.tapAtPosition(832.0, 1004.2)
			} else if (pin.substring(i, j) == '4') {
				Mobile.tapAtPosition(221.0, 1265.5)
			} else if (pin.substring(i, j) == '5') {
				Mobile.tapAtPosition(530.0, 1265.5)
			} else if (pin.substring(i, j) == '6') {
				Mobile.tapAtPosition(858.7, 1265.5)
			} else if (pin.substring(i, j) == '7') {
				Mobile.tapAtPosition(221.0, 1524.0)
			} else if (pin.substring(i, j) == '8') {
				Mobile.tapAtPosition(530.0, 1524.0)
			} else if (pin.substring(i, j) == '9') {
				Mobile.tapAtPosition(858.7, 1524.0)
			} else if (pin.substring(i, j) == '0') {
				Mobile.tapAtPosition(543.7, 1839.3)
			}

			i++

			j++

			if (i==4) {
				CaptureScreen()
			}

		}
	}

	// back to fist state
	@Keyword
	def backToFirst(String message) {
		Mobile.tap(findTestObject('Object Repository/AksesApp/menu_Akun'), 0, FailureHandling.OPTIONAL)
		Mobile.delay(5)
		//		Mobile.verifyElementExist(findTestObject('Object Repository/adiraku/Akun/icon_email'), 0)

		TestObject fieldInputNoHP = findTestObject("Object Repository/AksesApp/Login/field_Nomor Handphone")
		Boolean cekAda = Mobile.verifyElementExist(fieldInputNoHP, 10)
		if(cekAda == true){
			backToHome()
		}else{
			Mobile.callTestCase(findTestCase("MASTER SCRIPT/03 Logout"), [:], FailureHandling.STOP_ON_FAILURE)
		}

		if(message) KeywordUtil.markFailed(message)
	}


	// back to help
	@Keyword
	def backToHelp(String message) {
		boolean isHelp = Mobile.waitForElementPresent(findTestObject('AksesApp/menu_Bantuan'), 2)
		while (!isHelp) {
			Mobile.pressBack()
			Mobile.delay(2)
			isHelp = Mobile.waitForElementPresent(findTestObject('AksesApp/menu_Bantuan'), 2)
		}
		Mobile.tap(findTestObject('AksesApp/menu_Bantuan'), 0)
		if(message) KeywordUtil.markFailed(message)
	}

	// back to help
	@Keyword
	def backToDash(String message) {
		CaptureScreen()
		Mobile.pressBack()
		Mobile.verifyElementExist(findTestObject('Object Repository/v240/newDashboard/btnLogin'), 10)
		CaptureScreen()
	}

	@Keyword
	def KobitonOTP(String otp) {
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

	@Keyword
	def KobitonPIN (String pin) {


		int i = 0
		int j = 1

		while (i < 6) {
			MobileTestObject PINkey0 = findTestObject('Object Repository/v240/Login/login_idx/btn_0')
			PINkey0.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey0.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[8]")

			MobileTestObject PINkey1 = findTestObject('Object Repository/v240/Login/login_idx/btn_1')
			PINkey1.setMobileLocatorStrategy(MobileLocatorStrategy.XPATH)
			PINkey1.setMobileLocator("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[9]")

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


	// is start application
	@Keyword
	def isStartApps() {
		if(GlobalVariable.isStart) {
			Mobile.startApplication(GlobalVariable.url, false)
		}
	}


}


