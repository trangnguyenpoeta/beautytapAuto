
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
import org.json.JSONArray
import org.json.JSONObject
import org.apache.commons.lang.StringUtils


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
	def VerifyProductOnSearchResult(String productName,float regularPrice,String regularPriceColor,float salePrice,String salePriceColor) {
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
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Shop/lbl_message'), GlobalVariable.TIMEOUT, FailureHandling.OPTIONAL);

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
		println "Number item in cart: " + numberItem;
	}

	//Verify number item in cart
	@Keyword
	def VerifyNumberItemInCart(int numberItem) {

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

	//Checkout via Credit card
	@Keyword
	def checkoutViaCreditCard(String cardNumber,String cardType,String expirationMonth,String expirationYear, String cvv){

		WebUI.check(findTestObject('Object Repository/Page_Checkout/radio_creditCard'));
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_cardNumber'), cardNumber);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_cardType'), cardType, false);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_expirationMonth'), expirationMonth, false);
		WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_expirationYear'), expirationYear, false);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_cardCVV'), 	cvv);
		WebUI.check(findTestObject('Object Repository/Page_Checkout/chk_terms'));
		WebUI.click(findTestObject('Object Repository/Page_Checkout/btn_placeOrder'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Checkout/lbl_orderReceived'), GlobalVariable.LONG_TIMEOUT, FailureHandling.OPTIONAL);

	}

	//Checkout via Paypal
	@Keyword
	def checkoutViaPaypal(String paypalEmail,String paypalPassword){
		WebUI.check(findTestObject('Object Repository/Page_Checkout/radio_paypal'));
		WebUI.check(findTestObject('Object Repository/Page_Checkout/chk_terms'));
		WebUI.click(findTestObject('Object Repository/Page_Checkout/btn_placeOrder'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Paypal/txt_email'), GlobalVariable.LONG_TIMEOUT, FailureHandling.OPTIONAL);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Paypal/txt_email'), paypalEmail);
		GeneralAction.enterText(findTestObject('Object Repository/Page_Paypal/txt_password'), paypalPassword);
		WebUI.click(findTestObject('Object Repository/Page_Paypal/btn_login'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Paypal/btn_payNow'), GlobalVariable.LONG_TIMEOUT);
		WebUI.click(findTestObject('Object Repository/Page_Paypal/btn_payNow'));
		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Checkout/lbl_orderReceived'), GlobalVariable.LONG_TIMEOUT);

	}

	//Fill customer information
	//billingInformation: {"firstname":"FIRSTNAME","lastname":"LASTNAME","country":"COUNTRY","address":"ADDRESS","city":"CITY","state":"STATE","zip":"ZIP","email":"EMAIL"}
	//shippingInformation: {"firstname":"FIRSTNAME","lastname":"LASTNAME","country":"COUNTRY","address":"ADDRESS","city":"CITY","state":"STATE","zip":"ZIP"}
	//createAccount: yes,no
	//shipToDifferentAddess: yes,no
	@Keyword
	def fillCustomerInformation(JSONObject billingInformation,String createAccount,String accountUsername,String accountPassword,String shipToDifferentAddess,JSONObject shippingInformation,String orderNote){
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
			WebUI.delay(GlobalVariable.SHORT_TIMEOUT);
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_shippingAddress'), shippingAddress);
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_shippingCity'), shippingCity);
			WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Checkout/drop_shippingState'), shippingState,false);
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_shippingZip'), shippingZip);
		}
		if(orderNote!=''){
			GeneralAction.enterText(findTestObject('Object Repository/Page_Checkout/txt_orderNote'), orderNote);
		}
	}

	//Fill customer information without shipping other address
	//billingInformation: {"firstname":"FIRSTNAME","lastname":"LASTNAME","country":"COUNTRY","address":"ADDRESS","city":"CITY","state":"STATE","zip":"ZIP","email":"EMAIL"}

	@Keyword
	def fillCustomerInformation(JSONObject billingInformation,String createAccount,String accountUsername,String accountPassword,String orderNote){
		println billingInformation;
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
	}

	//Verify product details
	//color: pink,grey
	@Keyword
	def VerifyProductDetails(String productName,String variation,float regularPrice,String regularPriceColor,float salePrice,String salePriceColor){
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

	}

	//Verify Check Out Order Details
	//products: [{"productname":"PRODUCTNAME","variation":"VARIATION","quantity":"QUANTITY","price":"PRICE"},{"productname":"PRODUCTNAME","variation":"VARIATION","quantity":"QUANTITY","price":"PRICE"}]
	//free shipping price=0
	//shippingType:normal,EMS,free,freeEMS
	@Keyword
	def VerifyOrderDetailsOnCheckout(JSONArray products,float subtotal,String shippingType,String shippingLable,float shippingPrice,float total){
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
		TestObject obj_subtotal =new TestObject()
		obj_subtotal.addProperty("xpath",ConditionType.EQUALS,"//tr[@class='cart-subtotal']/td/span")
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
			String getShippingText=WebUI.getText(obj_shippingLable);
			if(StringUtils.containsIgnoreCase(getShippingText, shippingLable)==true){
				println "Shipping label is: "+shippingLable;
			}else{
				result = 'false';
				println "Shipping label does not contains: "+shippingLable;
			}
		//Normal shipping	
		}else {
			TestObject obj_shippingSelection,obj_shippingLable,obj_shippingPrice=new TestObject();
			if(shippingType=='normal'){
				obj_shippingSelection.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[1]/input");
				obj_shippingLable.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[1]/label");
				obj_shippingPrice.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[1]/label/span");
				
			}else if(shippingType=='EMS'){
				obj_shippingSelection.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[2]/input");
				obj_shippingLable.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[2]/label");
				obj_shippingPrice.addProperty("xpath",ConditionType.EQUALS,"//ul[@id='shipping_method']/li[2]/label/span");
			}
			boolean radioIsChecked= WebUI.verifyElementHasAttribute(obj_shippingSelection,'checked' , GlobalVariable.TIMEOUT);
			String currentLabel =WebUI.getText(obj_shippingLable).trim();
			float currentShipPrice = WebUI.getText(obj_shippingPrice).trim().replace('$', '');
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

	}

	//Calculate total
	@Keyword
	def public static calculateTotal(int quantity,float price){
		return Float.parseFloat(String.format("%.2f", price*quantity));
	}
}