import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import java.util.Random as Random

//SET VARIABLE
String r_string = new Math().random().toString().substring(2, 8)

String username = 'auto' + r_string

String email = username + '@mailinator.com'

String password = '123456'

String newpassword = 'test123'

String name = 'Auto User ' + r_string

String birthday = '01/01/1990'

String activation_subject = GlobalVariable.SITE_TITLE + ' Activate your account'

String resetpass_subject = GlobalVariable.SITE_TITLE + ' Password Reset'

//SIGNUP
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)

CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('signup')

CustomKeywords.'beautytap.GeneralAction.signup'(username, email, password, name, birthday)

'VP1: Verify message displays: Registration successful. A verification email has been sent to you. Please check your inbox.'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Signup/lbl_sucessMessage'), GlobalVariable.TIMEOUT)

CustomKeywords.'beautytap.GeneralAction.openMailBox'('mailinator', username, password)

'VP2: Verify activation email exist'
CustomKeywords.'beautytap.GeneralAction.verifyEmailExist'('mailinator', activation_subject)

CustomKeywords.'beautytap.GeneralAction.openEmail'('mailinator', activation_subject)

CustomKeywords.'beautytap.GeneralAction.clickEmailLink'('mailinator', 'activate')

WebUI.waitForPageLoad(GlobalVariable.LONG_TIMEOUT)

'VP3: Verify message display: Your account is now active!'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_General/lbl_activeSuccessMessage'), GlobalVariable.LONG_TIMEOUT)

//LOGIN
CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('login')

'Login with username and password'
CustomKeywords.'beautytap.GeneralAction.login'('email', username, password)

'VP4: Verify avatar icon displays'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_General/link_avatar'), GlobalVariable.TIMEOUT)

CustomKeywords.'beautytap.GeneralAction.logout'()

//Verify user can login with Email of registered account
CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('login')

'Login with email and password'
CustomKeywords.'beautytap.GeneralAction.login'('email', email, password)

'VP5: Verify avatar icon displays'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_General/link_avatar'), GlobalVariable.TIMEOUT)

CustomKeywords.'beautytap.GeneralAction.logout'()

'RESET PASSWORD'
CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('login')

WebUI.click(findTestObject('Object Repository/Page_Login/link_forgotPassword'))

WebUI.sendKeys(findTestObject('Object Repository/Page_ForgotPassword/txt_usernameOrEmail'), username)

WebUI.click(findTestObject('Object Repository/Page_ForgotPassword/btn_forgotPassword'))

'VP6:Verify message display: Your password has been reset! Please check your email for the confirmation link.'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_ForgotPassword/lbl_sucessMessage'), GlobalVariable.TIMEOUT)

CustomKeywords.'beautytap.GeneralAction.openMailBox'('mailinator', username, password)

'VP7: Verify reset password email exist'
CustomKeywords.'beautytap.GeneralAction.verifyEmailExist'('mailinator', resetpass_subject)

CustomKeywords.'beautytap.GeneralAction.openEmail'('mailinator', resetpass_subject)

CustomKeywords.'beautytap.GeneralAction.clickEmailLink'('mailinator', 'resetpass')

WebUI.delay(GlobalVariable.SHORT_TIMEOUT * 4)

WebUI.clearText(findTestObject('Object Repository/Page_ForgotPassword/txt_newPassword'))

WebUI.sendKeys(findTestObject('Object Repository/Page_ForgotPassword/txt_newPassword'), newpassword)

WebUI.click(findTestObject('Object Repository/Page_ForgotPassword/btn_resetPassword'))

'VP8: Verify message displays: Your password has been reset.'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_ForgotPassword/lbl_resetPasswordMessage'), GlobalVariable.TIMEOUT)

//Verify user cannot login with old password
CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('login')

CustomKeywords.'beautytap.GeneralAction.login'('email', username, password)

'VP9: Verify message displays: Wrong Password when logging in with old password'
WebUI.verifyElementText(findTestObject('Object Repository/Page_Login/lbl_errorMessage'), 'Wrong password!')

//Verify user can login with username and new password
CustomKeywords.'beautytap.GeneralAction.login'('email', username, newpassword)

'VP10: User can login successfully with username and new password'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_General/link_avatar'), GlobalVariable.TIMEOUT)

CustomKeywords.'beautytap.GeneralAction.logout'()

CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('login')

CustomKeywords.'beautytap.GeneralAction.login'('email', email, newpassword)

'VP11: Verify user can login with email and new password'
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_General/link_avatar'), GlobalVariable.TIMEOUT)

CustomKeywords.'beautytap.GeneralAction.logout'('closeBrowser')

