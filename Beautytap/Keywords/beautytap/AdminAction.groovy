package beautytap

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
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

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType

public class AdminAction {

	@Keyword
	def deleteUser(String email) {
		println "START KEYWORD deleteUser";
		WebUI.openBrowser(GlobalVariable.SITE_URL+"/login");
		WebUI.maximizeWindow();
		WebUI.clearText(findTestObject("Page_login/txt_username"));
		WebUI.sendKeys(findTestObject("Page_login/txt_username"), GlobalVariable.ADMIN_USERNAME) ;
		WebUI.clearText(findTestObject("Page_login/txt_password"));
		WebUI.sendKeys(findTestObject("Page_login/txt_password"), GlobalVariable.ADMIN_PASSWORD) ;
		WebUI.click(findTestObject("Page_login/btn_login"));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		WebUI.navigateToUrl(GlobalVariable.SITE_URL+"/admin");
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		WebUI.click(findTestObject('Object Repository/Page_Admin/menu_users'));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		WebUI.clearText(findTestObject('Object Repository/Page_Admin/txt_searchBox'));
		WebUI.sendKeys(findTestObject('Object Repository/Page_Admin/txt_searchBox'), email);
		WebUI.click(findTestObject('Object Repository/Page_Admin/btn_searchUser'));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		TestObject obj_user =new TestObject();
		obj_user.addProperty("xpath",ConditionType.EQUALS,"//tr/descendant::a[text()='"+ email+ "']");
		try {
			if(WebUI.getText(obj_user).length()>0){
				TestObject obj_userRow = new TestObject();
				obj_userRow.addProperty("xpath",ConditionType.EQUALS,"//a[text()='"+ email+ "']/ancestor::tr");
				String row_id = WebUI.getAttribute(obj_userRow, "id");
				TestObject obj_deleteLink = new TestObject();
				obj_deleteLink.addProperty("xpath",ConditionType.EQUALS,"//tr[@id='"+row_id+"']/td/div[@class='row-actions']/span[@class='delete']/a")
				WebUI.mouseOver(obj_user);
				WebUI.delay(GlobalVariable.SHORT_TIMEOUT);
				WebUI.click(obj_deleteLink);
				WebUI.acceptAlert();
				WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
				WebUI.click(findTestObject('Object Repository/Page_Admin/btn_confirmDeleteUser'));
				WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
		WebUI.closeBrowser();
		println "END KEYWORD deleteUser";
	}
	
	//Select Admin menu
	@Keyword
	def selectAdminMenu(String menu,String submenu){
		println "START KEYWORD selectAdminMenu";
		if(submenu==null){
			submenu='';
		}
		if(submenu!=''){
			TestObject obj_menu=new TestObject();
			TestObject obj_submenu=new TestObject();
			obj_menu.addProperty("xpath",ConditionType.EQUALS,"//div[@class='wp-menu-name' and text()='"+ menu +"']");
			obj_menu.addProperty("xpath",ConditionType.EQUALS,"//li/a[starts-with(text(),'"+ submenu +"')]");
			WebUI.mouseOver(obj_menu);
			WebUI.delay(2);
			WebUI.click(obj_submenu);
			WebUI.waitForPageLoad(GlobalVariable.LONG_TIMEOUT);
		}else{
			TestObject obj_menu=new TestObject();
			obj_menu.addProperty("xpath",ConditionType.EQUALS,"//div[@class='wp-menu-name' and text()='"+ menu +"']");
			WebUI.click(obj_menu);
			WebUI.waitForPageLoad(GlobalVariable.LONG_TIMEOUT);
		}
		
		println "END KEYWORD selectAdminMenu";		
	}
	
//End Class	
}
