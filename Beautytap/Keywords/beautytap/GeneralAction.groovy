package beautytap

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import javax.xml.xpath.XPath

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class GeneralAction {

	//Login Beautytap via FB or email or username
	@Keyword
	def login(String type,String email,String password) {
		// enter your code here
		// you can use either Groovy or Java
		if(type=='email'){
			WebUI.clearText(findTestObject("Page_login/txt_username"));
			WebUI.sendKeys(findTestObject("Page_login/txt_username"), email) ;
			WebUI.clearText(findTestObject("Page_login/txt_password"));
			WebUI.sendKeys(findTestObject("Page_login/txt_password"), password) ;
			WebUI.click(findTestObject("Page_login/btn_login"));
			WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		}
		else if(type=='facebook') {
			WebUI.click(findTestObject("Page_Login/btn_loginFB"));
			WebUI.switchToWindowIndex(1);
			WebUI.waitForElementVisible(findTestObject("Popup_FacebookLogin/txt_email"), GlobalVariable.TIMEOUT);
			WebUI.sendKeys(findTestObject("Popup_FacebookLogin/txt_email"), email) ;
			WebUI.sendKeys(findTestObject("Popup_FacebookLogin/txt_password"), password) ;
			WebUI.click(findTestObject("Popup_FacebookLogin/btn_login"));
			WebUI.switchToWindowIndex(0);
			WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		}
	}

	//Logout
	@Keyword
	def logout(String closeBrowser) {
		WebUI.click(findTestObject("Page_General/link_avatar"));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_General/menu_logout'), GlobalVariable.TIMEOUT);
		WebUI.click(findTestObject("Page_General/menu_logout"));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		if(closeBrowser!=null){
			WebUI.closeBrowser();
		}

	}

	//Signup Beautytap
	@Keyword
	def signup(String username,String email,String password,String name,String birthday ) {
		WebUI.sendKeys(findTestObject("Page_Signup/txt_username"), username);
		WebUI.sendKeys(findTestObject("Page_Signup/txt_email"), email);
		WebUI.sendKeys(findTestObject("Page_Signup/txt_password"), password);
		WebUI.sendKeys(findTestObject("Page_Signup/txt_name"), name);
		WebUI.sendKeys(findTestObject("Page_Signup/txt_birthday"), birthday);
		WebUI.click(findTestObject("Page_Signup/btn_signup"));
		WebUI.waitForPageLoad(GlobalVariable.LONG_TIMEOUT);
	}

	//Signup Via Facebook Beautytap
	@Keyword
	def signupViaFacebook(String email,String password ) {
		WebUI.click(findTestObject("Page_Signup/btn_signupFB"));
		WebUI.switchToWindowIndex(1);
		WebUI.sendKeys(findTestObject("Popup_FacebookLogin/txt_email"), email);
		WebUI.sendKeys(findTestObject("Popup_FacebookLogin/txt_password"), password);
		WebUI.click(findTestObject("Popup_FacebookLogin/btn_login"));
		try {
			if(WebUI.getText(findTestObject('Object Repository/Popup_FacebookLogin/btn_confirmPermission')).length()>0){
				WebUI.click(findTestObject("Popup_FacebookLogin/btn_confirmPermission"));
			}

		} catch (Exception e) {
			e.printStackTrace()
		}
		WebUI.switchToWindowIndex(0);
		WebUI.delay(GlobalVariable.TIMEOUT/2);
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT, FailureHandling.CONTINUE_ON_FAILURE);
	}

	//Open mailbox
	//MailBoxtype:gmail,yopmail,mailinator
	@Keyword
	def openMailBox(String mailBoxType,String username,String password ) {

		if(mailBoxType=='mailinator'){
			WebUI.navigateToUrl("https://www.mailinator.com");
			WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
			WebUI.sendKeys(findTestObject("Page_mailinator/txt_email"), username);
			WebUI.click(findTestObject("Page_mailinator/btn_go"));
			WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
			TestObject lbl_inbox = new TestObject();
			lbl_inbox.addProperty("xpath",ConditionType.EQUALS,"//div[@class='lb_all_sub-item_text ng-binding' and contains(text(),'"+ username + "')]")
			WebUI.click(lbl_inbox);
		}

	}

	//Check mail exist
	//MailBoxtype:gmail,yopmail,mailinator

	@Keyword
	def verifyEmailExist(String mailBoxType, String subject ) {

		if(mailBoxType=='mailinator'){
			TestObject email = new TestObject();
			email.addProperty("xpath",ConditionType.EQUALS,"//div[starts-with(@class,'all_message-min_text') and text()='" + subject + "']")
			WebUI.verifyElementPresent(email, GlobalVariable.TIMEOUT);
		}

	}

	//Open email
	//MailBoxtype:gmail,yopmail,mailinator
	@Keyword
	def openEmail(String mailBoxType, String subject ) {

		if(mailBoxType=='mailinator'){
			TestObject obj_email = new TestObject();
			obj_email.addProperty("xpath",ConditionType.EQUALS,"//div[starts-with(@class,'all_message-min_text') and text()='" + subject + "']")
			WebUI.click(obj_email);
		}

	}

	//Click email link
	//MailBoxtype:gmail,yopmail,mailinator
	//LinkType:activate,resetpass
	@Keyword
	def clickEmailLink(String mailBoxType, String linkType ) {

		if(mailBoxType=='mailinator'){

			WebUI.switchToFrame(findTestObject("Page_Mailinator/iframe_messageBody"), GlobalVariable.TIMEOUT);
			if(linkType=='activate'){
				TestObject obj_link = new TestObject();
				obj_link.addProperty("xpath",ConditionType.EQUALS,"//a[contains(text(),'activate')]");
				String url = WebUI.getText(obj_link);
				WebUI.navigateToUrl(url);
			}else if(linkType=='resetpass'){
				TestObject obj_link = new TestObject();
				obj_link.addProperty("xpath",ConditionType.EQUALS,"//a[contains(text(),'.com/wp-login.php?action=rp&key=')]");
				String url = WebUI.getText(obj_link);
				WebUI.navigateToUrl(url);
			}

		}

	}

	//Open Beautytap
	@Keyword
	def openBeautytap(String url ) {
		WebUI.openBrowser(url);
		WebUI.maximizeWindow(FailureHandling.OPTIONAL);
		WebUI.refresh();
		WebUI.waitForPageLoad(GlobalVariable.LONG_TIMEOUT);
	}

	//Click Navigation menu
	// Menu: shop,editorial,community,login,signup
	@Keyword
	def clickNavigationMenu(String navigationMenu ) {
		TestObject obj_menu = new TestObject();
		switch (navigationMenu){
			case 'shop': obj_menu = findTestObject('Object Repository/Page_General/link_shop');break;
			case 'editorial': obj_menu = findTestObject('Object Repository/Page_General/link_editorial');break;
			case 'community': obj_menu = findTestObject('Object Repository/Page_General/link_community');break;
			case 'login': obj_menu = findTestObject('Object Repository/Page_General/link_login');break;
			case 'signup': obj_menu = findTestObject('Object Repository/Page_General/link_signup');break;
		}
		WebUI.click(obj_menu);
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);

	}

	//Enter text to textbox
	@Keyword
	def public static enterText(TestObject control,String text){
		WebUI.clearText(control);
		WebUI.sendKeys(control, text);
	}
}
