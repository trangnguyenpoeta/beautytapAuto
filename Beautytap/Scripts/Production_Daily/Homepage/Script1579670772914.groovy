import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
'Verify header menu'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Home/menu_Header'), GlobalVariable.SHORT_TIMEOUT)
'Verify main banner exist'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Home/banner_Main'), GlobalVariable.SHORT_TIMEOUT)
'Verify Static image exist'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Home/banner_Static'), GlobalVariable.SHORT_TIMEOUT)
'Verify Top seller exist'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Home/module_TopSeller'), GlobalVariable.SHORT_TIMEOUT)
'Verify List Module exist'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Home/module_List'), GlobalVariable.SHORT_TIMEOUT)
'Verify Community Module exist'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Home/module_Community'), GlobalVariable.SHORT_TIMEOUT)
WebUI.closeBrowser()