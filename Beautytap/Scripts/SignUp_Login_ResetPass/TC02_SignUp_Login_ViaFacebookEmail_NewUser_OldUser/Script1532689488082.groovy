import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import org.junit.Before as Before
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

CustomKeywords.'beautytap.AdminAction.deleteUser'(GlobalVariable.FB_EMAIL);

CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)

'SIGNUP VIA FACEBOOK NOT EXIST IN SYSTEM'
CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('signup')

CustomKeywords.'beautytap.GeneralAction.signupViaFacebook'(GlobalVariable.FB_EMAIL, GlobalVariable.FB_PASSWORD)

'VP1: Verify user is logged in after Signup via Facebook'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_General/link_avatar'), GlobalVariable.TIMEOUT)

CustomKeywords.'beautytap.GeneralAction.logout'('closeBrowser')


'LOGIN VIA FACEBOOK NOT EXIST IN SYSTEM'
CustomKeywords.'beautytap.AdminAction.deleteUser'(GlobalVariable.FB_EMAIL);

CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)

CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('login')

CustomKeywords.'beautytap.GeneralAction.login'('facebook', GlobalVariable.FB_EMAIL, GlobalVariable.FB_PASSWORD)

'VP2: Verify user is logged in via Facebook '
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_General/link_avatar'), GlobalVariable.TIMEOUT)

CustomKeywords.'beautytap.GeneralAction.logout'('closeBrowser')

'SIGNUP VIA FACEBOOK ALREADY EXIST IN SYSTEM'
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)

CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('signup')

CustomKeywords.'beautytap.GeneralAction.signupViaFacebook'(GlobalVariable.FB_EMAIL, GlobalVariable.FB_PASSWORD)

'VP3: Verify user is logged in after Signup via Facebook'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_General/link_avatar'), GlobalVariable.TIMEOUT)

CustomKeywords.'beautytap.GeneralAction.logout'('closeBrowser')

'LOGIN VIA FACEBOOK ALREADY EXIST IN SYSTEM'
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)

CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('login')

CustomKeywords.'beautytap.GeneralAction.login'('facebook', GlobalVariable.FB_EMAIL, GlobalVariable.FB_PASSWORD)

'VP4: Verify user is logged in via Facebook'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_General/link_avatar'), GlobalVariable.TIMEOUT)

CustomKeywords.'beautytap.GeneralAction.logout'('closeBrowser')


