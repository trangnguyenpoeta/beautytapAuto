
package beautytap

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.supercsv.cellprocessor.ParseInt
import org.testng.Assert

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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import java.lang.Object
import beautytap.GeneralAction as GeneralAction
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import org.openqa.selenium.Keys as Keys

public class ShopAction {

	//Search product or article by clicking Search icon in Header
	@Keyword
	def globalSearch(String keyword) {
		//Set Clipboard
		StringSelection selection = new StringSelection(keyword);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		//End clipboard
		WebUI.click(findTestObject('Object Repository/Page_General/icon_search'));
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT);
		WebUI.clearText(findTestObject('Object Repository/Page_General/txt_search'));
		WebUI.sendKeys(findTestObject('Object Repository/Page_General/txt_search'), Keys.chord(Keys.CONTROL, 'v'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_General/div_searchResult'), GlobalVariable.LONG_TIMEOUT);


	}

	//Select product on search result
	@Keyword
	def selectProductOnSearchResult(String productName) {
		TestObject obj_product = new TestObject();
		obj_product.addProperty("xpath",ConditionType.EQUALS,"//div[@id='searchNav' and @style='height: 100%;']/descendant::div[contains(@class,'row search-content scrollbar-macosx scroll-content')]/descendant::a[contains(text(),'"+ productName +"')][1]");
		WebUI.click(obj_product);
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);

	}

	//Select product variation
	@Keyword
	def selectProductVariation(String variation) {
		TestObject obj_variation = new TestObject();
		obj_variation.addProperty("xpath",ConditionType.EQUALS,"//select[starts-with(@name,'attribute_pa')]/option[text()='"+ variation + "']");
		WebUI.click(findTestObject('Object Repository/Page_Shop/drop_chooseOption'));
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT/5);
		WebUI.click(obj_variation);

	}

	//Select product on search result
	//color= pink,grey
	@Keyword
	def VerifyProductOnSearchResult(String productName,String regularPrice,String regularPriceColor,String salePrice,String salePriceColor) {
		if(regularPriceColor!=null && regularPriceColor=="pink"){
			regularPriceColor = "rgba(255, 35, 134, 1)";
		}
		if(regularPriceColor!=null && regularPriceColor=="grey"){
			regularPriceColor = "rgba(195, 195, 195, 1)";
		}
		if(salePriceColor!=null && salePriceColor=="pink"){
			salePriceColor = "rgba(255, 35, 134, 1)";
		}
		if(salePriceColor!=null && salePriceColor=="grey"){
			salePriceColor = "rgba(195, 195, 195, 1)";
		}
		String result = "true";
		TestObject obj_product =new TestObject();
		obj_product.addProperty("xpath",ConditionType.EQUALS,"//div[@id='searchNav' and @style='height: 100%;']/descendant::div[contains(@class,'row search-content scrollbar-macosx scroll-content')]/descendant::a[contains(text(),'"+ productName +"')][1]");
		try {
			if(WebUI.verifyElementPresent(obj_product, GlobalVariable.LONG_TIMEOUT, FailureHandling.OPTIONAL)==true){
				//Check Regular Price
				if(regularPrice!=null){
					TestObject obj_regularPrice = new TestObject();
					obj_regularPrice.addProperty("xpath",ConditionType.EQUALS,"//div[@id='searchNav' and @style='height: 100%;']/descendant::div[contains(@class,'row search-content scrollbar-macosx scroll-content')]/descendant::a[contains(text(),'"+ productName +"')][1]//ancestor::div[@class='row item-content']/descendant::span[@class='woocommerce-Price-amount amount'][1]");
					println "regular price "+ WebUI.getText(obj_regularPrice).trim();
					println regularPrice;
					if(WebUI.getText(obj_regularPrice).trim()!=regularPrice){
						result = "false" ;
					}
					if(regularPriceColor!=null){
						println "regular color" + WebUI.getCSSValue(obj_regularPrice, "color");
						println regularPriceColor;
						if(WebUI.getCSSValue(obj_regularPrice, "color")!=regularPriceColor){
							result = "false" ;
						}
					}
				}
				//Check Sale Price
				if(salePrice!=null){
					TestObject obj_salePrice = new TestObject();
					obj_salePrice.addProperty("xpath",ConditionType.EQUALS,"//div[@id='searchNav' and @style='height: 100%;']/descendant::div[contains(@class,'row search-content scrollbar-macosx scroll-content')]/descendant::a[contains(text(),'"+ productName +"')][1]//ancestor::div[@class='row item-content']/descendant::span[@class='woocommerce-Price-amount amount'][2]");
					println "sale price "+ WebUI.getText(obj_salePrice).trim();
					println salePrice;
					if(WebUI.getText(obj_salePrice).trim()!=salePrice){
						result = "false";
					}
					if(salePriceColor!=null){
						println "regular color " + WebUI.getCSSValue(obj_salePrice, "color");
						println salePriceColor;
						if(WebUI.getCSSValue(obj_salePrice, "color")!=salePriceColor){
							result = "false";
						}
					}
				}

			} else{
				result = "false";
			}

			if(result=="true"){
				KeywordUtil.markPassed("Keyword VerifyProductOnSearchResult is Passed");
			}else{
				KeywordUtil.markFailed("Keyword VerifyProductOnSearchResult is Failed");
			}

		} catch (Exception e) {
			e.printStackTrace()
		}

	}

	//Add Product to Cart
	@Keyword
	def addProductToCart(int quantity) {
		if(quantity!=null){
			WebUI.clearText(findTestObject('Object Repository/Page_Shop/txt_quantity'));
			WebUI.sendKeys(findTestObject('Object Repository/Page_Shop/txt_quantity'), quantity.toString());
		}
		WebUI.click(findTestObject('Object Repository/Page_Shop/btn_addToCart'));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);

	}

	//Verify product is added to cart successfully
	@Keyword
	def VerifyProductIsAddedToCart(String productName) {
		TestObject obj_successMessage = new TestObject();
		obj_successMessage.addProperty("xpath",ConditionType.EQUALS,"//div[@class='woocommerce-message' and contains(text(),'"+ productName +"” have been added to your cart.')]");
		WebUI.verifyElementPresent(obj_successMessage, GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL);

	}

	//Go to cart
	@Keyword
	def goToCart(String productName) {
		WebUI.click(findTestObject('Object Repository/Page_General/icon_cart'));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);

	}

	//Get number item in cart
	@Keyword
	def getNumberItemInCart() {
		int numberItem;
		TestObject obj_cartnumber = new TestObject();
		obj_cartnumber.addProperty("xpath",ConditionType.EQUALS,"//div[@class='header-right-top pull-right']/a[@class='shop-cart']/span[@class='number-cart']");
		if(WebUI.verifyElementPresent(obj_cartnumber, GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL)==true){
			return numberItem = WebUI.getText(obj_cartnumber);
		}else{
			return numberItem= 0;
		}

	}

	//Verify number item in cart
	@Keyword
	def VerifyNumberItemInCart(int numberItem) {

		TestObject obj_cartnumber = new TestObject();
		obj_cartnumber.addProperty("xpath",ConditionType.EQUALS,"//div[@class='header-right-top pull-right']/a[@class='shop-cart']/span[@class='number-cart']");
		if(WebUI.verifyElementPresent(obj_cartnumber, GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL)==true){
			int currentNumber = Integer.parseInt(WebUI.getText(obj_cartnumber));
			if( currentNumber==numberItem ){
				KeywordUtil.markPassed("Number item in cart is "+ currentNumber.toString()+ ", expected is " + numberItem);
			}else{
				KeywordUtil.markFailed("Number item in cart is "+ currentNumber.toString()+ ", expected is " + numberItem);
			}
		}else{
			if(numberItem==0){
				KeywordUtil.markPassed("Number item in cart is 0 ,expected is " + numberItem);
			}else{
				KeywordUtil.markFailed("Number item in cart is 0 ,expected is " + numberItem);
			}
		}

	}

	//Verify product in to cart
	@Keyword
	def VerifyProductInCart(String productName, String variation, float price, int quantity, float total ) {
		if(variation!=""){
			productName = productName + " – " + variation;
		}
		println productName;
		String result = "true";
		TestObject obj_product = new TestObject();
		TestObject obj_price = new TestObject();
		TestObject obj_quantity = new TestObject();
		TestObject obj_total = new TestObject();
		obj_product.addProperty("xpath",ConditionType.EQUALS,"//a[text()='"+ productName +"']");
		obj_price.addProperty("xpath",ConditionType.EQUALS,"//a[text()='"+productName+ "']/ancestor::tr/td[4]/span") ;
		obj_quantity.addProperty("xpath",ConditionType.EQUALS,"//a[text()='"+productName+ "']/ancestor::tr/td[5]/div/input") ;
		obj_total.addProperty("xpath",ConditionType.EQUALS,"//a[text()='"+productName+ "']/ancestor::tr/td[6]/span") ;

		if(WebUI.verifyElementPresent(obj_product, GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL)==true){
			float priceInCart = Float.parseFloat(WebUI.getText(obj_price).trim().toString().replace('$', '')).round(2)  ;

			println "Price in cart " + priceInCart;
			println  "Price input " + price;
			if(priceInCart!=price){
				result="false";
			}
			int quantityInCart = Integer.parseInt(WebUI.getAttribute(obj_quantity, "value").trim());
			println "Quantity in cart "+ quantityInCart;
			println "Quantity input " + quantity;
			if(quantityInCart!=quantity){
				result="false";
			}
			float totalInCart = Float.parseFloat(WebUI.getText(obj_total).trim().toString().replace('$', ''));
			println "Total in cart " + totalInCart;
			float totalInput = Float.parseFloat(String.format("%.2f", price*quantity));
			println "Total input " + totalInput;
			if(totalInCart != totalInput){
				result="false";
			}
		}else{
			result="false";
		}
		//Check result
		if(result=="true"){
			KeywordUtil.markPassed("Keyword VerifyProductInCart is Passed");
		}else{
			KeywordUtil.markFailed("Keyword VerifyProductInCart is Failed" );
		}

	}


	//Process to Checkout
	@Keyword
	def processToCheckout() {
		WebUI.click(findTestObject('Object Repository/Page_Cart/btn_checkout'));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);

	}

	//Checkout via AmazonPay
	//page:cart,checkout
	@Keyword
	def checkoutViaAmazonPay(String page,String amazonEmail,String amazonPassword) {
		if(page=='cart'){
			WebUI.click(findTestObject('Object Repository/Page_Cart/btn_payWithAmazon'));
		}
		if(page=='checkout'){
			WebUI.click(findTestObject('Object Repository/Page_Checkout/btn_amazonPay'));
		}
		WebUI.switchToWindowIndex(1);
		WebUI.waitForElementPresent(findTestObject('Object Repository/Popup_Amazon/txt_email'), GlobalVariable.TIMEOUT);
		GeneralAction.enterText(findTestObject('Object Repository/Popup_Amazon/txt_email'), amazonEmail);
		GeneralAction.enterText(findTestObject('Object Repository/Popup_Amazon/txt_password'), amazonPassword);
		WebUI.click(findTestObject('Object Repository/Popup_Amazon/btn_signin'));
		WebUI.switchToWindowIndex(0);
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT*2);
		WebUI.check(findTestObject('Object Repository/Page_Checkout/chk_terms'));
		WebUI.click(findTestObject('Object Repository/Page_Checkout/btn_placeOrder'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Checkout/lbl_orderReceived'), GlobalVariable.LONG_TIMEOUT);
	}

}