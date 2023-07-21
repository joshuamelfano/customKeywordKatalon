package function

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class CountTotalItem {

	static final String TOTAL_PROMO_XPATH = '//*[@id="promo-desktop"]/div[2]/div[2]/div/div[1]/div'
	
@Keyword 
def getTotalPromo(TestObject to){
WebUI.scrollToElement(to, 0)
WebUI.delay(5)
WebDriver driver = DriverFactory.getWebDriver()		
try {
		
		WebElement temp = driver.findElement(By.xpath(TOTAL_PROMO_XPATH))
		List list = temp.findElements(By.xpath(TOTAL_PROMO_XPATH))
		KeywordUtil.logInfo("total number of promo: "+list.size())
		return list.size()
			

} catch (Exception e) {
		KeywordUtil.logInfo("total number of promo: 0")
		return 0
		}
		
		
	}
	
}