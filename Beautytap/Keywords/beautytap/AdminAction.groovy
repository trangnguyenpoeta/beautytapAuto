package beautytap

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL

import org.json.JSONArray
import org.json.JSONObject
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
import beautytap.GeneralAction
import org.openqa.selenium.Keys as Keys;

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
		WebUI.clearText(findTestObject('Page_Admin/txt_searchUser'));
		WebUI.sendKeys(findTestObject('Page_Admin/txt_searchUser'), email);
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
	def public static selectAdminMenu(String menu,String submenu){
		println "START KEYWORD selectAdminMenu";
		if(submenu==null){
			submenu='';
		}
		if(submenu!=''){
			TestObject obj_menu=new TestObject();
			TestObject obj_submenu=new TestObject();
			obj_menu.addProperty("xpath",ConditionType.EQUALS,"//div[@class='wp-menu-name' and text()='"+ menu +"']");
			obj_submenu.addProperty("xpath",ConditionType.EQUALS,"//li/a[starts-with(text(),'"+ submenu +"')]");
			WebUI.click(obj_menu);
			WebUI.waitForPageLoad(GlobalVariable.LONG_TIMEOUT);
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

	//change order status
	//status: Pending payment,Processing,On hold,Completed,Cancelled,Refunded,Failed
	@Keyword
	def changeOrderStatus(String orderNumber, String status){
		println "START KEYWORD changeOrderStatus";
		AdminAction.selectAdminMenu("WooCommerce", "Orders");
		orderNumber =orderNumber.replace('#','');
		GeneralAction.enterText(findTestObject('Object Repository/Page_Admin/txt_searchOrder'), orderNumber);
		WebUI.click(findTestObject('Object Repository/Page_Admin/btn_searchOrder'));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		TestObject obj_order = new TestObject();
		obj_order.addProperty("xpath",ConditionType.EQUALS,"//strong[contains(text(),'"+ orderNumber +"')]");
		WebUI.click(obj_order);
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Admin/drop_orderStatus'), status, false);
		WebUI.click(findTestObject('Object Repository/Page_Admin/btn_saveOrder'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Admin/lbl_orderUpdatedMessage'), GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL);
		println "END KEYWORD changeOrderStatus";
	}

	//Schedule sale product
	//variation:[{"variation":"VARIATION","saleprice":"SALEPRICE","regularprice":"REGULARPRICE"},{"variation":"VARIATION","saleprice":"SALEPRICE","regularprice":"REGULARPRICE"}]
	//limitStock: yes,no
	@Keyword
	def scheduleSaleProduct(String productName,float price,JSONArray variations,String startDate, String endDate,String limitStock){
		println "START KEYWORD scheduleSaleProduct";
		if(variations==null){
			variations='';
		}
		TestObject obj_product = new TestObject();
		obj_product.addProperty("xpath", ConditionType.EQUALS, "//a[text()='"+ productName +"']")
		GeneralAction.enterText(findTestObject('Object Repository/Page_Admin/txt_searchProduct'), productName);
		WebUI.click(findTestObject('Object Repository/Page_Admin/btn_searchProduct'))
		WebUI.click(obj_product);
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT*2);
		//Simple product
		if(variations.length()==0){
			if(WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Admin/link_schedule'), GlobalVariable.SHORT_TIMEOUT,FailureHandling.OPTIONAL)==true){
				WebUI.click(findTestObject('Object Repository/Page_Admin/link_schedule'));
				WebUI.delay(GlobalVariable.SHORT_TIMEOUT);
			}
			GeneralAction.enterText(findTestObject('Object Repository/Page_Admin/txt_salePrice'), price.toString());
			GeneralAction.enterText(findTestObject('Object Repository/Page_Admin/txt_salePriceDateFrom'),startDate);
			GeneralAction.enterText(findTestObject('Object Repository/Page_Admin/txt_salePriceDateTo'), endDate);
			if(limitStock=='yes'){
				WebUI.check(findTestObject('Object Repository/Page_Admin/chk_soldIndividualScheduled'));
			}else if (limitStock=='no'){
				WebUI.uncheck(findTestObject('Object Repository/Page_Admin/chk_soldIndividualScheduled'));
			}
			WebUI.focus(findTestObject('Object Repository/Page_Admin/txt_productName'));
			WebUI.delay(GlobalVariable.SHORT_TIMEOUT*2);
		}else{
			//Variation product
			WebUI.click(findTestObject('Object Repository/Page_Admin/link_variation'));
			WebUI.delay(GlobalVariable.SHORT_TIMEOUT*2);
			for(int i;i<variations.length();i++){
				JSONObject obj_variation = (JSONObject) variations.get(i);
				String variationName = obj_variation.get("variation");
				float variationPrice = Float.parseFloat(obj_variation.get("saleprice"));
				float variationRegularPrice = Float.parseFloat(obj_variation.get("regularprice"));
				TestObject variationHeader = new TestObject();
				TestObject scheduleLink = new TestObject();
				TestObject salePriceTextBox = new TestObject();
				TestObject regularPriceTextBox = new TestObject();
				TestObject startDateTextbox = new TestObject();
				TestObject endDateTextbox = new TestObject();
				TestObject soldIndividual = new TestObject();
				variationHeader.addProperty("xpath",ConditionType.EQUALS,"//div[@class='woocommerce_variation wc-metabox closed']/descendant::option[@selected='selected' and text()='"+ variationName +"']/ancestor::h3");
				scheduleLink.addProperty("xpath",ConditionType.EQUALS,"//div[starts-with(@class,'woocommerce_variation wc-metabox open')]/descendant::option[@selected='selected' and text()='"+ variationName +"']/ancestor::h3/parent::div/descendant::a[@class='sale_schedule' and text()='Schedule' and not(@style='display: none;')]");
				salePriceTextBox.addProperty("xpath",ConditionType.EQUALS,"//div[starts-with(@class,'woocommerce_variation wc-metabox open')]/descendant::option[@selected='selected' and text()='"+ variationName +"']/ancestor::h3/parent::div/descendant::input[starts-with(@id,'variable_sale_price')]");
				regularPriceTextBox.addProperty("xpath",ConditionType.EQUALS,"//div[starts-with(@class,'woocommerce_variation wc-metabox open')]/descendant::option[@selected='selected' and text()='"+ variationName +"']/ancestor::h3/parent::div/descendant::input[starts-with(@id,'variable_regular_price')]");
				startDateTextbox.addProperty("xpath",ConditionType.EQUALS,"//div[starts-with(@class,'woocommerce_variation wc-metabox open')]/descendant::option[@selected='selected' and text()='"+ variationName +"']/ancestor::h3/parent::div/descendant::input[starts-with(@name,'variable_sale_price_dates_from')]");
				endDateTextbox.addProperty("xpath",ConditionType.EQUALS,"//div[starts-with(@class,'woocommerce_variation wc-metabox open')]/descendant::option[@selected='selected' and text()='"+ variationName +"']/ancestor::h3/parent::div/descendant::input[starts-with(@name,'variable_sale_price_dates_to')]");
				soldIndividual.addProperty("xpath",ConditionType.EQUALS,"//div[starts-with(@class,'woocommerce_variation wc-metabox open')]/descendant::option[@selected='selected' and text()='"+ variationName +"']/ancestor::h3/parent::div/descendant::input[starts-with(@id,'_sold_individually_scheduled')]");
				WebUI.click(variationHeader);
				GeneralAction.enterText(regularPriceTextBox, variationRegularPrice.toString());
				GeneralAction.enterText(salePriceTextBox, variationPrice.toString());
				if(WebUI.verifyElementPresent(scheduleLink, GlobalVariable.SHORT_TIMEOUT, FailureHandling.OPTIONAL)==true){
					WebUI.click(scheduleLink);
				}
				GeneralAction.enterText(startDateTextbox,startDate );
				GeneralAction.enterText(endDateTextbox, endDate);
				if(limitStock=='yes'){
					WebUI.check(soldIndividual);
				}else if (limitStock=='no'){
					WebUI.uncheck(soldIndividual);
				}
				
			}
			WebUI.click(findTestObject('Object Repository/Page_Admin/btn_saveChanges'));
			WebUI.focus(findTestObject('Object Repository/Page_Admin/txt_productName'));
			WebUI.delay(GlobalVariable.SHORT_TIMEOUT*2);
		}
		WebUI.click(findTestObject('Object Repository/Page_Admin/btn_updateProduct'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Admin/lbl_productUpdatedMessage'), GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL)
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		println "END KEYWORD scheduleSaleProduct";
	}

	//End Class
}
