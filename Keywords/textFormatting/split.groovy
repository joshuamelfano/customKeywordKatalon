package textFormatting

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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

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

public String SplitWhatsappBitly(String splitTarget){

	String [] splittedTarget = splitTarget.split("[()]");
	String result = splittedTarget[1];


	return result;
}

@Keyword

public String LowerCaseServiceBerkala(String splitTarget){

	String [] splittedTarget = splitTarget.split(" ");

	String word1 = splittedTarget[0].toLowerCase();

	String word2 = splittedTarget[1].toLowerCase();

	String word3 = splittedTarget[2].toLowerCase();

	String word4 = splittedTarget[3];

	String wordingServiceBerkala = word1 + " " + word2 + " " + word3 + " " + word4;

	return wordingServiceBerkala;
}
