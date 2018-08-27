package beautytap

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.supercsv.cellprocessor.ParseInt
import org.testng.Assert

import com.google.gson.JsonObject
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
import beautytap.ShopAction as ShopAction
import groovy.json.JsonParser

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import org.openqa.selenium.Keys as Keys;
import org.stringtemplate.v4.compiler.STParser.list_return
import org.json.JSONArray
import org.json.JSONObject
import org.apache.commons.lang.StringUtils

public class ShopAction {

	//Search product or article by clicking Search icon in Header
	@Keyword
	def globalSearch(String keyword) {
		println "START KEYWORD globalSearch";
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
		println "END KEYWORD globalSearch";
	}

	//Select product on search result
	@Keyword
	def selectProductOnSearchResult(String productName) {
		println "START KEYWORD selectProductOnSearchResult";
		TestObject obj_product = new TestObject();
		obj_product.addProperty("xpath",ConditionType.EQUALS,"//div[@id='searchNav' and @style='height: 100%;']/descendant::div[contains(@class,'row search-content scrollbar-macosx scroll-content')]/descendant::a[contains(text(),'"+ productName +"')][1]");
		WebUI.click(obj_product);
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		println "END KEYWORD selectProductOnSearchResult";
	}

	//Select product variation
	@Keyword
	def selectProductVariation(String variation) {
		println "START KEYWORD selectProductVariation";
		TestObject obj_variation = new TestObject();
		obj_variation.addProperty("xpath",ConditionType.EQUALS,"//select[starts-with(@name,'attribute_pa')]/option[text()='"+ variation + "']");
		WebUI.click(findTestObject('Object Repository/Page_Shop/drop_chooseOption'));
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT/5);
		WebUI.click(obj_variation);
		println "END KEYWORD selectProductVariation";
	}

	//Select product on search result
	//color= pink,grey
	@Keyword
	def VerifyProductOnSearchResult(String productName,float regularPrice,String regularPriceColor,float salePrice,String salePriceColor) {
		println "START KEYWORD VerifyProductOnSearchResult";
		if(regularPrice==null){
			regularPrice= 0;
		}
		if(salePrice==null){
			salePrice = 0;
		}

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
				if(regularPrice!= 0){
					TestObject obj_regularPrice = new TestObject();
					obj_regularPrice.addProperty("xpath",ConditionType.EQUALS,"//div[@id='searchNav' and @style='height: 100%;']/descendant::div[contains(@class,'row search-content scrollbar-macosx scroll-content')]/descendant::a[contains(text(),'"+ productName +"')][1]//ancestor::div[@class='row item-content']/descendant::span[@class='woocommerce-Price-amount amount'][1]");
					float currentRegularPrice = Float.parseFloat(WebUI.getText(obj_regularPrice).trim().replace('$', ''));
					println "Regular price "+ currentRegularPrice;
					println regularPrice;
					if(currentRegularPrice!=regularPrice){
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
				if(salePrice!=0){
					TestObject obj_salePrice = new TestObject();
					obj_salePrice.addProperty("xpath",ConditionType.EQUALS,"//div[@id='searchNav' and @style='height: 100%;']/descendant::div[contains(@class,'row search-content scrollbar-macosx scroll-content')]/descendant::a[contains(text(),'"+ productName +"')][1]//ancestor::div[@class='row item-content']/descendant::span[@class='woocommerce-Price-amount amount'][2]");
					float currentSalePrice = Float.parseFloat(WebUI.getText(obj_salePrice).trim().replace('$',''));
					println "Sale price "+ currentSalePrice;
					println salePrice;
					if(currentSalePrice!=salePrice){
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
		println "END KEYWORD VerifyProductOnSearchResult";
	}

	//Add Product to Cart
	@Keyword
	def addProductToCart(int quantity) {
		println "START KEYWORD addProductToCart";
		if(quantity!=null){
			WebUI.clearText(findTestObject('Object Repository/Page_Shop/txt_quantity'));
			WebUI.sendKeys(findTestObject('Object Repository/Page_Shop/txt_quantity'), quantity.toString());
		}
		WebUI.click(findTestObject('Object Repository/Page_Shop/btn_addToCart'));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Shop/lbl_message'), GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL);
		println "END KEYWORD addProductToCart";
	}

	//Verify product is added to cart successfully
	@Keyword
	def VerifyProductIsAddedToCart(String productName) {
		println "START KEYWORD VerifyProductIsAddedToCart";
		TestObject obj_successMessage = new TestObject();
		obj_successMessage.addProperty("xpath",ConditionType.EQUALS,"//div[@class='woocommerce-message' and contains(text(),'"+ productName +"” have been added to your cart.')]");
		WebUI.verifyElementPresent(obj_successMessage, GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL);
		println "END KEYWORD VerifyProductIsAddedToCart";
	}

	//Go to cart
	@Keyword
	def goToCart(String productName) {
		println "START KEYWORD goToCart";
		WebUI.click(findTestObject('Object Repository/Page_General/icon_cart'));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		println "END KEYWORD goToCart";
	}

	//Get number item in cart
	@Keyword
	def getNumberItemInCart() {
		println "START KEYWORD getNumberItemInCart";
		int numberItem;
		TestObject obj_cartnumber = new TestObject();
		obj_cartnumber.addProperty("xpath",ConditionType.EQUALS,"//div[@class='header-right-top pull-right']/a[@class='shop-cart']/span[@class='number-cart']");
		if(WebUI.verifyElementPresent(obj_cartnumber, GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL)==true){
			return numberItem = WebUI.getText(obj_cartnumber);
		}else{
			return numberItem= 0;
		}
		println "Number item in cart: " + numberItem;
		println "END KEYWORD getNumberItemInCart";
	}

	//Verify number item in cart
	@Keyword
	def VerifyNumberItemInCart(int numberItem) {
		println "START KEYWORD VerifyNumberItemInCart";
		TestObject obj_cartnumber = new TestObject();
		obj_cartnumber.addProperty("xpath",ConditionType.EQUALS,"//div[@class='header-right-top pull-right']/a[@class='shop-cart']/span[@class='number-cart']");
		if(WebUI.verifyElementPresent(obj_cartnumber, GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL)==true){
			int currentNumber = Integer.parseInt(WebUI.getText(obj_cartnumber));
			println "Current item in cart: " + currentNumber;
			if( currentNumber==numberItem ){
				KeywordUtil.markPassed("Keyword VerifyNumberItemInCart is Passed, Number item in cart is "+ currentNumber.toString()+ ", expected is " + numberItem);
			}else{
				KeywordUtil.markFailed("Keyword VerifyNumberItemInCart is Failed, Number item in cart is "+ currentNumber.toString()+ ", expected is " + numberItem);
			}
		}else{
			if(numberItem==0){
				KeywordUtil.markPassed("Keyword VerifyNumberItemInCart is Passed, Number item in cart is 0 ,expected is " + numberItem);
			}else{
				KeywordUtil.markFailed("Keyword VerifyNumberItemInCart is Failed, Number item in cart is 0 ,expected is " + numberItem);
			}
		}
		println "END KEYWORD VerifyNumberItemInCart";
	}

	//Verify product in to cart
	@Keyword
	def VerifyProductInCart(String productName, String variation, float price, int quantity, float total ) {
		println "START KEYWORD VerifyProductInCart";
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
		println "END KEYWORD VerifyProductInCart";
	}


	//Process to Checkout
	@Keyword
	def processToCheckout() {
		WebUI.click(findTestObject('Object Repository/Page_Cart/btn_checkout'));
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);

	}

	//Login AmazonPay
	//page:cart,checkout
	@Keyword
	def loginAmazonPay(String page,String amazonEmail,String amazonPassword) {
		println "START KEYWORD loginAmazonPay";
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
		WebUI.delay(GlobalVariable.TIMEOUT);
		println "END KEYWORD loginAmazonPay";
	}

	//Checkout via amazonPay
	@Keyword
	def checkoutViaAmazonPay(){
		println "START KEYWORD checkoutViaAmazonPay";
		WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Checkout/chk_terms'), GlobalVariable.TIMEOUT);
		WebUI.check(findTestObject('Object Repository/Page_Checkout/chk_terms'));
		WebUI.click(findTestObject('Object Repository/Page_Checkout/btn_placeOrder'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Checkout/lbl_orderReceived'), GlobalVariable.LONG_TIMEOUT);
		println "END KEYWORD checkoutViaAmazonPay";
	}

	//Checkout via Credit card
	@Keyword
	def checkoutViaCreditCard(String cardNumber,String cardType,String expirationMonth,String expirationYear, String cvv){
		println "START KEYWORD checkoutViaCreditCard";
		WebUI.check(findTestObject('Object Repository/Page_Checkout/radio_creditCard'));
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT*2);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_cardNumber'), cardNumber);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_cardType'), cardType, false);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_expirationMonth'), expirationMonth, false);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_expirationYear'), expirationYear, false);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_cardCVV'), 	cvv);
		WebUI.check(findTestObject('Object Repository/Page_Checkout/chk_terms'));
		WebUI.click(findTestObject('Object Repository/Page_Checkout/btn_placeOrder'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Checkout/lbl_orderReceived'), GlobalVariable.LONG_TIMEOUT, FailureHandling.OPTIONAL);
		println "END KEYWORD checkoutViaCreditCard";
	}

	//Checkout via Paypal
	@Keyword
	def checkoutViaPaypal(String paypalEmail,String paypalPassword){
		println "START KEYWORD checkoutViaPaypal";
		WebUI.check(findTestObject('Object Repository/Page_Checkout/radio_paypal'));
		WebUI.check(findTestObject('Object Repository/Page_Checkout/chk_terms'));
		WebUI.click(findTestObject('Object Repository/Page_Checkout/btn_placeOrder'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Paypal/txt_email'), GlobalVariable.LONG_TIMEOUT, FailureHandling.OPTIONAL);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Paypal/txt_email'), paypalEmail);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Paypal/txt_password'), paypalPassword);
		WebUI.click(findTestObject('Object Repository/Page_Paypal/btn_login'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Paypal/btn_payNow'), GlobalVariable.LONG_TIMEOUT);
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT)
		WebUI.click(findTestObject('Object Repository/Page_Paypal/btn_payNow'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Checkout/lbl_orderReceived'), GlobalVariable.LONG_TIMEOUT);
		println "END KEYWORD checkoutViaPaypal";
	}

	//Fill customer information
	//billingInformation: {"firstname":"FIRSTNAME","lastname":"LASTNAME","country":"COUNTRY","address":"ADDRESS","city":"CITY","state":"STATE","zip":"ZIP","email":"EMAIL"}
	//shippingInformation: {"firstname":"FIRSTNAME","lastname":"LASTNAME","country":"COUNTRY","address":"ADDRESS","city":"CITY","state":"STATE","zip":"ZIP"}
	//createAccount: yes,no
	//shipToDifferentAddess: yes,no
	@Keyword
	def fillCustomerInformation(JSONObject billingInformation,String createAccount,String accountUsername,String accountPassword,String shipToDifferentAddess,JSONObject shippingInformation,String orderNote){
		println "START KEYWORD fillCustomerInformation";
		String billingFirstName = billingInformation.get("firstname");
		String billingLastName = billingInformation.get("lastname");
		String billingCountry = billingInformation.get("country");
		String billingAddress = billingInformation.get("address");
		String billingCity = billingInformation.get("city");
		String billingState = billingInformation.get("state");
		String billingZip = billingInformation.get("zip");
		String billingEmail = billingInformation.get("email");
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingFirstName'), billingFirstName);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingLastName'), billingLastName);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_billingCountry'), billingCountry,false);
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT*2);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingAddress'), billingAddress);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingCity'), billingCity);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_billingState'), billingState,false);
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT*2);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingZip'), billingZip);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingEmail'), billingEmail);
		if(createAccount=='yes'){
			WebUI.check(findTestObject('Object Repository/Page_Checkout/chk_createAccount'));
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_accountUserName'), accountUsername);
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_accountPassword'), accountPassword);
		}
		if(shipToDifferentAddess=='yes'){
			String shippingFirstName = shippingInformation.get("firstname");
			String shippingLastName = shippingInformation.get("lastname");
			String shippingCountry = shippingInformation.get("country");
			String shippingAddress = shippingInformation.get("address");
			String shippingCity = shippingInformation.get("city");
			String shippingState = shippingInformation.get("state");
			String shippingZip = shippingInformation.get("zip");
			WebUI.check(findTestObject('Object Repository/Page_Checkout/chk_shipToDifferentAddress'));
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_shippingFirstName'), shippingFirstName);
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_shippingLastName'), shippingLastName);
			WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_shippingCountry'), shippingCountry,false);
			WebUI.delay(GlobalVariable.SHORT_TIMEOUT*2);
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_shippingAddress'), shippingAddress);
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_shippingCity'), shippingCity);
			WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_shippingState'), shippingState,false);
			WebUI.delay(GlobalVariable.SHORT_TIMEOUT*2);
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_shippingZip'), shippingZip);
		}
		if(orderNote!=''){
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_orderNote'), orderNote);
		}
		println "START KEYWORD fillCustomerInformation";
	}

	//Fill customer information without shipping other address
	//billingInformation: {"firstname":"FIRSTNAME","lastname":"LASTNAME","country":"COUNTRY","address":"ADDRESS","city":"CITY","state":"STATE","zip":"ZIP","email":"EMAIL"}

	@Keyword
	def fillCustomerInformation(JSONObject billingInformation,String createAccount,String accountUsername,String accountPassword,String orderNote){
		println "START KEYWORD fillCustomerInformation";
		String billingFirstName = billingInformation.get("firstname");
		String billingLastName = billingInformation.get("lastname");
		String billingCountry = billingInformation.get("country");
		String billingAddress = billingInformation.get("address");
		String billingCity = billingInformation.get("city");
		String billingState = billingInformation.get("state");
		String billingZip = billingInformation.get("zip");
		String billingEmail = billingInformation.get("email");
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingFirstName'), billingFirstName);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingLastName'), billingLastName);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_billingCountry'), billingCountry,false);
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingAddress'), billingAddress);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingCity'), billingCity);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_billingState'), billingState,false);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingZip'), billingZip);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_billingEmail'), billingEmail);
		if(createAccount=='yes'){
			WebUI.check(findTestObject('Object Repository/Page_Checkout/chk_createAccount'));
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_accountUserName'), accountUsername);
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_accountPassword'), accountPassword);
		}
		WebUI.uncheck(findTestObject('Object Repository/Page_Checkout/chk_shipToDifferentAddress'));
		if(orderNote!=''){
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_orderNote'), orderNote);
		}
		//Check credit card radio button to trigger calculate shipping
		WebUI.check(findTestObject('Object Repository/Page_Checkout/radio_creditCard'));
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT);
		println "END KEYWORD fillCustomerInformation";
	}

	//Verify product details
	//color: pink,grey
	@Keyword
	def VerifyProductDetails(String productName,String variation,float regularPrice,String regularPriceColor,float salePrice,String salePriceColor){
		println "START KEYWORD VerifyProductDetails";
		if(variation==null){
			variation ='';
		}
		if(regularPrice==null){
			regularPrice=0;
		}
		if(regularPriceColor==null){
			regularPriceColor='';
		}
		if(salePrice==null){
			salePrice=0;
		}
		if(salePriceColor==null){
			salePriceColor='';
		}
		String result='true';
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
		TestObject obj_productname =new TestObject();
		obj_productname.addProperty("xpath",ConditionType.EQUALS,"//h1");
		String currentProductName = WebUI.getText(obj_productname).trim();
		println "Current product name: "+currentProductName;
		println productName;
		if(currentProductName!=productName){
			result = 'false';
		}
		//Simple product
		if(variation ==''){
			//Simple not sale
			if(salePrice==0){
				TestObject obj_regularPrice=new TestObject();
				obj_regularPrice.addProperty("xpath",ConditionType.EQUALS,"//h4[@class='price']/span");
				float currentResularPrice= Float.parseFloat(WebUI.getText(obj_regularPrice).trim().replace('$', ''));
				println "Current regular price: " + currentResularPrice;
				println regularPrice;
				if(currentResularPrice!=regularPrice){
					result = 'false';
				}
				String currentColor = WebUI.getCSSValue(obj_regularPrice, "color");
				println "Current regular price color" + currentColor;
				println regularPriceColor;
				if(currentColor!=regularPriceColor){
					result = 'false';
				}

				//Simple sale
			}else{
				//Regular price
				TestObject obj_regularPrice=new TestObject();
				obj_regularPrice.addProperty("xpath",ConditionType.EQUALS,"//h4[@class='price']/del/span");
				float currentResularPrice= Float.parseFloat(WebUI.getText(obj_regularPrice).trim().replace('$', ''));
				println "Current regular price: " + currentResularPrice;
				println regularPrice;
				if(currentResularPrice!=regularPrice){
					result = 'false';
				}
				String currentColor = WebUI.getCSSValue(obj_regularPrice, "color");
				println "Current regular price color" + currentColor;
				println regularPriceColor;
				if(currentColor!=regularPriceColor){
					result = 'false';
				}
				//Sale price
				TestObject obj_salePrice=new TestObject();
				obj_salePrice.addProperty("xpath",ConditionType.EQUALS,"//h4[@class='price']/ins/span");
				float currentSalePrice= Float.parseFloat(WebUI.getText(obj_salePrice).trim().replace('$', ''));
				println "Current salse price: " + currentSalePrice;
				println salePrice;
				if(currentSalePrice!=salePrice){
					result = 'false';
				}
				String currentSaleColor = WebUI.getCSSValue(obj_salePrice, "color");
				println "Current sale price color" + currentSaleColor;
				println salePriceColor
				if(currentSaleColor!=salePriceColor){
					result = 'false';
				}
			}

			//Variation product
		}else{
			if(WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/Page_Shop/drop_chooseOption'), variation, false, GlobalVariable.TIMEOUT)==false){
				result = 'false';
			}

			//Variation not sale
			if(salePrice==0){
				TestObject obj_regularPrice=new TestObject();
				obj_regularPrice.addProperty("xpath",ConditionType.EQUALS,"//span[@class='price']/span");
				float currentResularPrice= Float.parseFloat(WebUI.getText(obj_regularPrice).trim().replace('$', ''));
				println "Current regular price: " + currentResularPrice;
				println regularPrice;
				if(currentResularPrice!=regularPrice){
					result = 'false';
				}
				String currentColor = WebUI.getCSSValue(obj_regularPrice, "color");
				println "Current regular price color" + currentColor;
				println regularPriceColor;
				if(currentColor!=regularPriceColor){
					result = 'false';
				}

				//Variation sale
			}else{
				//Regular price
				TestObject obj_regularPrice=new TestObject();
				obj_regularPrice.addProperty("xpath",ConditionType.EQUALS,"//span[@class='old-price']/span");
				float currentResularPrice= Float.parseFloat(WebUI.getText(obj_regularPrice).trim().replace('$', ''));
				println "Current regular price: " + currentResularPrice;
				println regularPrice;
				if(currentResularPrice!=regularPrice){
					result = 'false';
				}
				String currentColor = WebUI.getCSSValue(obj_regularPrice, "color");
				println "Current regular price color" + currentColor;
				println regularPriceColor;
				if(currentColor!=regularPriceColor){
					result = 'false';
				}
				//Sale price
				TestObject obj_salePrice=new TestObject();
				obj_salePrice.addProperty("xpath",ConditionType.EQUALS,"//span[@class='price']/span");
				float currentSalePrice= Float.parseFloat(WebUI.getText(obj_salePrice).trim().replace('$', ''));
				println "Current salse price: " + currentSalePrice;
				println salePrice;
				if(currentSalePrice!=salePrice){
					result = 'false';
				}
				String currentSaleColor = WebUI.getCSSValue(obj_salePrice, "color");
				println "Current sale price color" + currentSaleColor;
				println salePriceColor;
				if(currentSaleColor!=salePriceColor){
					result = 'false';
				}
			}

		}
		//Verify result
		if(result=='true'){
			KeywordUtil.markPassed("Keyword VerifyProductDetails is Passed");
		}else{
			KeywordUtil.markFailed("Keyword VerifyProductDetails is Failed");
		}
		println "END KEYWORD VerifyProductDetails";
	}

	//Verify Check Out Order Details
	//products: [{"productname":"PRODUCTNAME","variation":"VARIATION","quantity":"QUANTITY","price":"PRICE"},{"productname":"PRODUCTNAME","variation":"VARIATION","quantity":"QUANTITY","price":"PRICE"}]
	//free shipping price=0
	//shippingType:normal,EMS,free,freeEMS
	@Keyword
	def VerifyOrderDetailsOnCheckout(JSONArray products,float subtotal,String shippingType,String shippingLable,float shippingPrice,float total){
		println "START KEYWORD VerifyOrderDetailsOnCheckout";
		String result = 'true';
		float orderSubtotal = 0;
		//Check product line
		for(int i;i<products.length();i++){
			JSONObject obj_product = (JSONObject) products.get(i);
			String productName = obj_product.get('productname');
			String variation = obj_product.get('variation');
			if(variation!=''){
				productName = productName + ' – ' + variation;
			}
			int quantity = Integer.parseInt(obj_product.get('quantity'));
			float price = ShopAction.calculateTotal(1, Float.parseFloat(obj_product.get('price')));
			float productTotal = ShopAction.calculateTotal(quantity, price);
			orderSubtotal = orderSubtotal + productTotal;
			String xpath = "//td[@class='product-name' and contains(text(),'"+productName+"')]/strong[@class='product-quantity' and text()='× "+ quantity+ "']/ancestor::tr/td[@class='product-total']/span[starts-with(text(),'"+ productTotal +"')]";
			TestObject obj_productLine = new TestObject();
			obj_productLine.addProperty("xpath",ConditionType.EQUALS,xpath);
			if(WebUI.verifyElementPresent(obj_productLine, GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL)==false){
				result = 'false';
				println "Product "+ i +" in array does not exist";
			}else{
				println "Product "+ i +" in array exist";
			}
			orderSubtotal=ShopAction.calculateTotal(1, orderSubtotal);

		}
		//Check subtotal
		TestObject obj_subtotal =new TestObject();
		obj_subtotal.addProperty("xpath",ConditionType.EQUALS,"//tr[@class='cart-subtotal']/td/span");
		float currentSubtotal = Float.parseFloat(WebUI.getText(obj_subtotal).trim().replace('$', ''));
		if(orderSubtotal==currentSubtotal && orderSubtotal==subtotal){
			println "Subtotal is correct: "+ currentSubtotal;
		}else{
			result = 'false'
			println "Subtotal product line: "+ orderSubtotal;
			println "Subtotal sum line: "+ currentSubtotal;
			println "Subtotal input: "+ subtotal;
		}
		//Check shipping
		//Free shipping/Free EMS shipping
		if(shippingType=='free' || shippingType=='freeEMS'){
			TestObject obj_shippingLable = new TestObject();
			obj_shippingLable.addProperty("xpath",ConditionType.EQUALS,"//td[@data-title='Shipping']");
			String getShippingText = WebUI.getText(obj_shippingLable);
			if(StringUtils.containsIgnoreCase(getShippingText, shippingLable)==true){
				println "Shipping label is: "+shippingLable;
			}else{
				result = 'false';
				println "Shipping label does not contains: "+shippingLable;
			}
			//Normal shipping
		}else {

			if(shippingType=='normal'){
				TestObject obj_shippingSelection=new TestObject();
				TestObject obj_shippingLable=new TestObject();
				TestObject  obj_shippingPrice =new TestObject();
				obj_shippingSelection.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[1]/input");
				obj_shippingLable.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[1]/label");
				obj_shippingPrice.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[1]/label/span");
				boolean radioIsChecked= WebUI.verifyElementHasAttribute(obj_shippingSelection,'checked' , GlobalVariable.TIMEOUT,FailureHandling.OPTIONAL);
				String currentLabel =WebUI.getText(obj_shippingLable).trim();
				println "Current ship label:"+currentLabel;
				float currentShipPrice = Float.parseFloat(WebUI.getText(obj_shippingPrice).trim().replace('$', ''));
				shippingLable = shippingLable + ': $' + String.format("%.2f", shippingPrice);
				println "Expected ship label:" +shippingLable;
				if(radioIsChecked==true && currentLabel==shippingLable && currentShipPrice==shippingPrice){
					println "Expected status: " +radioIsChecked;
					println "Expected lable: " +currentLabel;
					println "Expected shipping price: " +currentShipPrice;
				}else{
					println "Current status: " +radioIsChecked;
					println "Expected status: true" ;
					println "Current lable: " +currentLabel;
					println "Expected lable: " +shippingLable;
					println "Current shipping price: " +currentShipPrice;
					println "Expected shipping price: " + shippingPrice;
					result = 'false';
				}

			}else if(shippingType=='EMS'){
				TestObject obj_shippingSelection=new TestObject();
				TestObject obj_shippingLable=new TestObject();
				TestObject  obj_shippingPrice =new TestObject();
				obj_shippingSelection.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[2]/input");
				obj_shippingLable.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[2]/label");
				obj_shippingPrice.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[2]/label/span");
				boolean radioIsChecked= WebUI.verifyElementHasAttribute(obj_shippingSelection,'checked' , GlobalVariable.TIMEOUT,FailureHandling.OPTIONAL);
				String currentLabel =WebUI.getText(obj_shippingLable).trim();
				float currentShipPrice = Float.parseFloat(WebUI.getText(obj_shippingPrice).trim().replace('$', ''));
				shippingLable = shippingLable + ': $' + String.format("%.2f", shippingPrice);
				if(radioIsChecked==true && currentLabel==shippingLable && currentShipPrice==shippingPrice){
					println "Expected status: " +radioIsChecked;
					println "Expected lable: " +currentLabel;
					println "Expected shipping price: " +currentShipPrice;
				}else{
					println "Current status: " +radioIsChecked;
					println "Expected status: true" ;
					println "Current lable: " +currentLabel;
					println "Expected lable: " +shippingLable;
					println "Current shipping price: " +currentShipPrice;
					println "Expected shipping price: " + shippingPrice;
					result = 'false';
				}
			}

		}
		//Check total
		float currentTotal = currentSubtotal + shippingPrice;
		TestObject obj_total=new TestObject();
		obj_total.addProperty("xpath",ConditionType.EQUALS,"//tr[@class='order-total']/td/span");
		float expectedTotal = Float.parseFloat(WebUI.getText(obj_total).trim().replace('$', ''));
		if(currentTotal!=expectedTotal){
			result = 'false';
			println "Current total: " +currentTotal;
			println "Expected total:" +expectedTotal ;
		}
		//Check result
		if(result=='true'){
			KeywordUtil.markPassed("Keyword VerifyOrderDetailsOnCheckout is Passed");
		}else{
			KeywordUtil.markFailed("Keyword VerifyOrderDetailsOnCheckout is Failed");
		}
		println "END KEYWORD VerifyOrderDetailsOnCheckout";
	}

	//Calculate total
	@Keyword
	def public static calculateTotal(int quantity,float price){
		return Float.parseFloat(String.format("%.2f", price*quantity));
	}

	//Verify Order received Details
	//products: [{"productname":"PRODUCTNAME","variation":"VARIATION","quantity":"QUANTITY","price":"PRICE"},{"productname":"PRODUCTNAME","variation":"VARIATION","quantity":"QUANTITY","price":"PRICE"}]
	//free shipping price=0
	//shippingType:normal,EMS,free,freeEMS
	@Keyword
	def VerifyOrderReceivedDetails(JSONArray products,float subtotal,float shippingPrice,String shippingLabel,String paymentMethod,float total){
		println "START KEYWORD VerifyOrderReceivedDetails";
		String result = 'true';
		float orderSubtotal = 0;
		//Check product line
		for(int i;i<products.length();i++){
			JSONObject obj_product = (JSONObject) products.get(i);
			String productName = obj_product.get('productname');
			String variation = obj_product.get('variation');
			if(variation!=''){
				productName = productName + ' – ' + variation;
			}
			int quantity = Integer.parseInt(obj_product.get('quantity'));
			float price = ShopAction.calculateTotal(1, Float.parseFloat(obj_product.get('price')));
			float productTotal = ShopAction.calculateTotal(quantity, price);
			orderSubtotal = orderSubtotal + productTotal;
			String xpath = "//td[@class='woocommerce-table__product-name product-name']/a[contains(text(),'"+productName+"')]/following-sibling::strong[text()='× "+ quantity +"']/parent::td/parent::tr/td[@class='woocommerce-table__product-total product-total']/span[starts-with(text(),'"+ productTotal +"')]";
			TestObject obj_productLine = new TestObject();
			obj_productLine.addProperty("xpath",ConditionType.EQUALS,xpath);
			if(WebUI.verifyElementPresent(obj_productLine, GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL)==false){
				result = 'false';
				println "Product "+ i +" in array does not exist";
			}else{
				println "Product "+ i +" in array exist";
			}
			orderSubtotal=ShopAction.calculateTotal(1, orderSubtotal);

		}
		//Check subtotal
		TestObject obj_subtotal =new TestObject();
		obj_subtotal.addProperty("xpath",ConditionType.EQUALS,"//th[text()='Subtotal:']/parent::tr/td/span");
		float currentSubtotal = Float.parseFloat(WebUI.getText(obj_subtotal).trim().replace('$', ''));
		if(orderSubtotal==currentSubtotal && orderSubtotal==subtotal){
			println "Subtotal is correct: "+ currentSubtotal;
		}else{
			result = 'false'
			println "Subtotal product line: "+ orderSubtotal;
			println "Subtotal sum line: "+ currentSubtotal;
			println "Subtotal input: "+ subtotal;
		}
		//Check shipping

		TestObject obj_shippingLable=new TestObject();
		TestObject  obj_shippingPrice =new TestObject();
		obj_shippingLable.addProperty("xpath",ConditionType.EQUALS,"//th[text()='Shipping:']/parent::tr/td|//th[text()='Shipping:']/parent::tr/td/small");
		obj_shippingPrice.addProperty("xpath",ConditionType.EQUALS,"//th[text()='Shipping:']/parent::tr/td/span");
		String currentLabel =WebUI.getText(obj_shippingLable).trim();
		if(WebUI.verifyElementPresent(obj_shippingPrice, GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL)==true){
			float currentShipPrice = Float.parseFloat(WebUI.getText(obj_shippingPrice).trim().replace('$', ''));
			shippingLabel = "via "+ shippingLabel;
			if(currentLabel!=shippingLabel && currentShipPrice!=shippingPrice){
				println "Current ship label: "+currentLabel;
				println "Expected ship label:" + shippingLabel;
				println "Current ship price: "+currentShipPrice;
				println "Expected ship price:" + shippingPrice;
				result = 'false'
			}
		}else{
			float currentShipPrice =0;
			if(currentLabel!=shippingLabel && currentShipPrice!=shippingPrice){
				println "Current ship label: "+currentLabel;
				println "Expected ship label:" + shippingLabel;
				println "Current ship price: "+currentShipPrice;
				println "Expected ship price:" + shippingPrice;
				result = 'false'
			}
		}
		//Check Payment method
		TestObject obj_paymentMethod=new TestObject();
		obj_paymentMethod.addProperty("xpath",ConditionType.EQUALS,"//th[text()='Payment method:']/parent::tr/td");
		String currentMethod = WebUI.getText(obj_paymentMethod).trim();
		if(currentMethod != paymentMethod){
			result = 'false'
			println "Current method: "+currentMethod;
			println "Expected method:" + paymentMethod;
		}

		//Check total
		float currentTotal = currentSubtotal + shippingPrice;
		TestObject obj_total=new TestObject();
		obj_total.addProperty("xpath",ConditionType.EQUALS,"//th[text()='Total:']/parent::tr/td/span");
		float expectedTotal = Float.parseFloat(WebUI.getText(obj_total).trim().replace('$', ''));
		if(currentTotal!=expectedTotal){
			result = 'false';
			println "Current total: " +currentTotal;
			println "Expected total:" +expectedTotal ;
		}
		//Check result
		if(result=='true'){
			KeywordUtil.markPassed("Keyword VerifyOrderReceivedDetails is Passed");
		}else{
			KeywordUtil.markFailed("Keyword VerifyOrderReceivedDetails is Failed");
		}
		println "END KEYWORD VerifyOrderReceivedDetails";
	}

	//Get order number on Order received
	//{"ordernumber":"ORDERNUMBER","date":"DATE"}
	@Keyword
	def getOrderInfoOnOrderReceived(){
		println "START KEYWORD getOrderNumberOnOrderReceived";
		TestObject obj_orderNumber=new TestObject();
		TestObject obj_orderDate=new TestObject();
		obj_orderNumber.addProperty("xpath",ConditionType.EQUALS,"//li[contains(text(),'Order number:')]/strong");
		obj_orderDate.addProperty("xpath",ConditionType.EQUALS,"//li[contains(text(),'Date')]/strong");
		String ordernumber = WebUI.getText(obj_orderNumber).trim();
		String orderdate = WebUI.getText(obj_orderDate).trim();
		String json='{"ordernumber":"'+ ordernumber +'","date":"'+ orderdate +'"}';
		JSONObject orderInfo =new JSONObject(json);
		return orderInfo;
		println "END KEYWORD getOrderNumberOnOrderReceived";
	}

	//Select category
	@Keyword
	def selectCategory(String category,String subCategory ){
		println "START KEYWORD selectCategory";
		if(subCategory==null){
			subCategory='';
		}
		TestObject obj_category= new TestObject();
		obj_category.addProperty("xpath",ConditionType.EQUALS,'//div[@class="tabs-inner border-none"]/ul/li/a[text()="'+ category +'"]');
		WebUI.click(obj_category);
		if(subCategory!=''){
			WebUI.delay(2);
			TestObject obj_subcategory= new TestObject();
			obj_subcategory.addProperty("xpath",ConditionType.EQUALS,"//div[@id='category_prd_makeup']/descendant::div[@class='item-category']/h4/a[text()='"+ subCategory +"']|//div[@id='category_prd_makeup']/descendant::div[@class='item-category']/following::a[text()='"+ subCategory +"']");
			WebUI.click(obj_subcategory);
			WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		}else{
			WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		}
		println "END KEYWORD selectCategory";
	}

	//Find product on product list
	@Keyword
	def findProductOnProductList(String productName){
		println "START KEYWORD findProductOnProductList";
		TestObject obj_lastpage = new TestObject();
		TestObject obj_product = new TestObject();
		obj_lastpage.addProperty("xpath",ConditionType.EQUALS,"//a[@class='next page-numbers' and text()='Next']/preceding::a[1]");
		obj_product.addProperty("xpath",ConditionType.EQUALS,"//a[text()='"+ productName +"']");
		int totalPage = Integer.parseInt(WebUI.getText(obj_lastpage).trim());
		println "Total page "+totalPage;
		for(int i=1;i<=totalPage;i++){
			if(WebUI.verifyElementPresent(obj_product, GlobalVariable.SHORT_TIMEOUT, FailureHandling.OPTIONAL)==false){
				println "Product not found on page "+i;
				int nextpage = i+1;
				TestObject obj_clickpage = new TestObject();
				obj_clickpage.addProperty("xpath",ConditionType.EQUALS,"//a[@class='page-numbers' and text()='"+ nextpage+"']");
				WebUI.click(obj_clickpage);
				WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
			}else{
				println "Product found on page "+i;
				break;
			}
		}
		println "END KEYWORD findProductOnProductList";
	}

	//Select product on product list
	@Keyword
	def selectProductOnProductList(String productName){
		println "START KEYWORD selectProductOnProductList";
		TestObject obj_product = new TestObject();
		obj_product.addProperty("xpath",ConditionType.EQUALS,"//a[text()='"+ productName +"']");
		WebUI.click(obj_product);
		WebUI.waitForPageLoad(GlobalVariable.TIMEOUT);
		println "END KEYWORD selectProductOnProductList";
	}
	//Verify product on product list
	//productType: simple,variation
	@Keyword
	def VerifyProductOnProductList(String productName,float regularPrice,String regularPriceColor,float salePrice,String salePriceColor){
		println "START KEYWORD VerifyProductOnProductList";
		if(regularPrice==null){
			regularPrice= 0;
		}
		if(salePrice==null){
			salePrice = 0;
		}
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
		obj_product.addProperty("xpath",ConditionType.EQUALS,"//a[text()='"+ productName +"']");
		try {
			if(WebUI.verifyElementPresent(obj_product, GlobalVariable.LONG_TIMEOUT, FailureHandling.OPTIONAL)==true){
				//Check Regular Price
				if(regularPrice!= 0){
					TestObject obj_regularPrice = new TestObject();
					obj_regularPrice.addProperty("xpath",ConditionType.EQUALS,"//a[text()='"+ productName +"']/parent::div/descendant::span[@class='woocommerce-Price-amount amount'][1]");
					float currentRegularPrice = Float.parseFloat(WebUI.getText(obj_regularPrice).trim().replace('$', ''));
					println "Current regular price "+ currentRegularPrice;
					println "Expected regular price "+ regularPrice;
					if(currentRegularPrice!=regularPrice){
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
				if(salePrice!=0){
					TestObject obj_salePrice = new TestObject();
					obj_salePrice.addProperty("xpath",ConditionType.EQUALS,"//a[text()='"+ productName +"']/parent::div/descendant::span[@class='woocommerce-Price-amount amount'][2]");
					float currentSalePrice = Float.parseFloat(WebUI.getText(obj_salePrice).trim().replace('$',''));
					println "Current Sale price "+ currentSalePrice;
					println "Expected Sale price "+salePrice;
					if(currentSalePrice!=salePrice){
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
				KeywordUtil.markPassed("Keyword VerifyProductOnProductList is Passed");
			}else{
				KeywordUtil.markFailed("Keyword VerifyProductOnProductList is Failed");
			}

		} catch (Exception e) {
			e.printStackTrace()
		}
		println "END KEYWORD VerifyProductOnProductList";
	}

	//Select shipping
	//shipping: normal,EMS
	@Keyword
	def selectShipping(String shipping){
		println "START KEYWORD selectShipping";
		String xpath ;
		if(shipping=='normal'){
			xpath = "//ul[@id='shipping_method']/li[1]/input";
		}else{
			xpath = "//ul[@id='shipping_method']/li[2]/input";
		}
		TestObject obj_shipping = new TestObject();
		obj_shipping.addProperty("xpath",ConditionType.EQUALS,xpath);
		WebUI.check(obj_shipping);
		WebUI.delay(GlobalVariable.SHORT_TIMEOUT);
		println "END KEYWORD selectShipping";
	}

	//Get reward details
	//rewardDetails: {"multiplier":"MULTIPLIER","lifetime":"LIFETIME","pending":"PENDING","redeemable":"REDEEMABLE","pointvalue":"POINTVALUE"}
	@Keyword
	def getRewardDetails(){
		println "START KEYWORD getRewardDetails";
		TestObject obj_multiplier =new TestObject();
		TestObject obj_lifetime =new TestObject();
		TestObject obj_pending =new TestObject();
		TestObject obj_redeemable =new TestObject();
		TestObject obj_pointvalue =new TestObject();
		TestObject obj_currentlevel =new TestObject();
		obj_multiplier.addProperty("xpath",ConditionType.EQUALS,"//div[@class='rewards-title' and contains(text(),'Rewards Multiplier')]");
		obj_lifetime.addProperty("xpath",ConditionType.EQUALS,"//span[text()='Total Lifetime Loyalty Points']/parent::div/h3");
		obj_pending.addProperty("xpath",ConditionType.EQUALS,"//span[text()='Total Pending Loyalty Points']/parent::div/h3");
		obj_redeemable.addProperty("xpath",ConditionType.EQUALS,"//span[text()='Loyalty Points Redeemable']/parent::div/h3");
		obj_pointvalue.addProperty("xpath",ConditionType.EQUALS,"//span[text()='Points Value']/parent::div/h3");
		obj_currentlevel.addProperty("xpath",ConditionType.EQUALS,"//div[@class='current-level']/div[@class='level-title']");
		float multiplier = Float.parseFloat(WebUI.getText(obj_multiplier).substring(0, WebUI.getText(obj_multiplier).indexOf("x")).trim());
		float lifetime = Float.parseFloat(WebUI.getText(obj_lifetime).trim());
		float pending = Float.parseFloat(WebUI.getText(obj_pending).trim());
		float redeemable = Float.parseFloat(WebUI.getText(obj_redeemable).trim());
		float pointvalue = Float.parseFloat(WebUI.getText(obj_pointvalue).trim().replace('$', ''));
		String currentlevel =WebUI.getText(obj_currentlevel).trim();
		println "Multiplier: " + multiplier;
		println "Lifetime: " +lifetime;
		println "Pending: " +pending;
		println "Redeemable: " +redeemable;
		println "Point value: " +pointvalue;
		String json = '{"multiplier":"'+ multiplier +'","lifetime":"'+ lifetime +'","pending":"'+ pending +'","redeemable":"'+ redeemable +'","pointvalue":"'+ pointvalue +'","currentlevel":"'+ currentlevel +'"}';
		JSONObject obj_rewardDetails = new JSONObject(json);
		return obj_rewardDetails;
		println "END KEYWORD getRewardDetails";
	}

	//Verify Reward Point
	@Keyword
	def VerifyRewardPoints(float lifetime,float pending,float redeemable,float pointvalue){
		println "START KEYWORD VerifyRewardPoints";
		TestObject obj_lifetime =new TestObject();
		TestObject obj_pending =new TestObject();
		TestObject obj_redeemable =new TestObject();
		TestObject obj_pointvalue =new TestObject();
		String result = "true";
		obj_lifetime.addProperty("xpath",ConditionType.EQUALS,"//span[text()='Total Lifetime Loyalty Points']/parent::div/h3");
		obj_pending.addProperty("xpath",ConditionType.EQUALS,"//span[text()='Total Pending Loyalty Points']/parent::div/h3");
		obj_redeemable.addProperty("xpath",ConditionType.EQUALS,"//span[text()='Loyalty Points Redeemable']/parent::div/h3");
		obj_pointvalue.addProperty("xpath",ConditionType.EQUALS,"//span[text()='Points Value']/parent::div/h3");
		float currentlifetime = Float.parseFloat(WebUI.getText(obj_lifetime).trim());
		float currentpending = Float.parseFloat(WebUI.getText(obj_pending).trim());
		float currentredeemable = Float.parseFloat(WebUI.getText(obj_redeemable).trim());
		float currentpointvalue = Float.parseFloat(WebUI.getText(obj_pointvalue).trim().replace('$', ''));
		if(currentlifetime!=lifetime||currentpending!=pending||currentpointvalue!=pointvalue||currentredeemable!=redeemable){
			result="false";
			println "Current lifetime: " + currentlifetime;
			println "Expected Lifetime: " +lifetime;
			println "Current Pending: " +currentpending;
			println "Expected Pending: " +pending;
			println "Current Redeemable: " +currentredeemable;
			println "Expected Redeemable: " +redeemable;
			println "Current Point value: " +currentpointvalue;
			println "Expected Point value: " +pointvalue;
		}
		if(result=="true"){
			KeywordUtil.markPassed("Keyword VerifyRewardPoints is Passed");
		}else{
			KeywordUtil.markFailed("Keyword VerifyRewardPoints is Failed");
		}
		println "END KEYWORD VerifyRewardPoints";
	}

	//Get reward history
	//status: pending,processing,completed,cancelled,on-hold,refunded
	//rewardHistory json object: {"date":"DATE","pointredeemed":"POINTREDEEMED","total":"TOTAL","multiplier":"MULTIPLIER","loyaltypoint":"LOYALTYPOINT","totalpoint":"TOTALPOINT"}
	@Keyword
	def getRewardHistory(String orderNumber, String status){
		println "SART KEYWORD getRewardHistory";
		orderNumber =orderNumber.replace('#','');
		TestObject obj_date = new TestObject();
		TestObject obj_pointRedeemed = new TestObject();
		TestObject obj_total = new TestObject();
		TestObject obj_multiplier = new TestObject();
		TestObject obj_loyaltyPoint = new TestObject();
		TestObject obj_totalPoint = new TestObject();
		obj_date.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[2]")
		obj_pointRedeemed.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[4]")
		obj_total.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[5]")
		obj_multiplier.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[6]")
		obj_loyaltyPoint.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[7]")
		obj_totalPoint.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[8]")
		String currentDate =WebUI.getText(obj_date).trim();
		String currentPointRedeemed=WebUI.getText(obj_pointRedeemed).trim();
		String currentTotal=WebUI.getText(obj_total).trim().replace('$', '');
		String currentMultiplier = WebUI.getText(obj_multiplier).trim()
		String currentLoyaltyPoint = WebUI.getText(obj_loyaltyPoint).trim();
		String currentTotalPoint = WebUI.getText(obj_totalPoint).trim();
		String json='{"date":"'+ currentDate +'","pointredeemed":"'+ currentPointRedeemed +'","total":"'+ currentTotal +'","multiplier":"'+ currentMultiplier +'","loyaltypoint":"'+ currentLoyaltyPoint +'","totalpoint":"'+ currentTotalPoint +'"}';
		JSONObject rewardHistory =new JSONObject(json);
		println rewardHistory;
		return rewardHistory;
		println "END KEYWORD getRewardHistory";
	}

	//Verify Reward History
	@Keyword
	def VerifyRewardHistory(String orderNumber,String date,String status,String pointRedeemed,String total,String multiplier,String loyaltyPoint,String totalPoint){
		println "SART KEYWORD VerifyRewardHistory";
		String result="true";		
		orderNumber =orderNumber.replace('#','');
		TestObject obj_orderNumber = new TestObject();
		TestObject obj_date = new TestObject();
		TestObject obj_status = new TestObject();
		TestObject obj_pointRedeemed = new TestObject();
		TestObject obj_total = new TestObject();
		TestObject obj_multiplier = new TestObject();
		TestObject obj_loyaltyPoint = new TestObject();
		TestObject obj_totalPoint = new TestObject();
		obj_date.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[2]")
		obj_pointRedeemed.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[4]")
		obj_total.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[5]")
		obj_multiplier.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[6]")
		obj_loyaltyPoint.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[7]")
		obj_totalPoint.addProperty("xpath",ConditionType.EQUALS,"//td/a[text()='#"+ orderNumber +"']/ancestor::tr/td[text()='"+ status +"']/parent::tr/td[8]")
		if (WebUI.verifyElementPresent(obj_date, GlobalVariable.TIMEOUT)==true){
		String currentDate =WebUI.getText(obj_date).trim();
		String currentPointRedeemed=WebUI.getText(obj_pointRedeemed).trim();
		String currentTotal=WebUI.getText(obj_total).trim().replace('$', '');
		String currentMultiplier = WebUI.getText(obj_multiplier).trim()
		String currentLoyaltyPoint = WebUI.getText(obj_loyaltyPoint).trim();
		String currentTotalPoint = WebUI.getText(obj_totalPoint).trim();
			if(currentDate!=date || currentPointRedeemed!=pointRedeemed||currentTotal!=total||currentLoyaltyPoint!=loyaltyPoint||currentMultiplier!=multiplier||currentTotalPoint!=totalPoint){
				result="false";
				println "Current date:" +currentDate;
				println "Expected date:" +date;
				println "Current Point Redeemed:" +currentPointRedeemed;
				println "Expected Point Redeemed:" +pointRedeemed;
				println "Current Total:" +currentTotal;
				println "Expected Total:" +total;
				println "Current Multiplier:" +currentMultiplier;
				println "Expected Multiplier:" +multiplier;
				println "Current Loyalty Point:" +currentLoyaltyPoint;
				println "Expected Loyalty Point:" +loyaltyPoint;
				println "Current Total Point:" +currentTotalPoint;
				println "Expected Total Point:" +totalPoint;
			}else{
			println "Reward History is correct!";
			}
		} else{
			result = "false";
		}
		
		if(result=="true"){
			KeywordUtil.markPassed("Keyword VerifyRewardHistory is Passed");
		}else{
			KeywordUtil.markFailed("Keyword VerifyRewardHistory is Failed");
		}
		println "END KEYWORD VerifyRewardHistory";
	}
		
		
		
	//End Class
}
