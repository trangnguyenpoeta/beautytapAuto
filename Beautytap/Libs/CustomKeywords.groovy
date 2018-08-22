
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import com.kms.katalon.core.testobject.TestObject

import org.json.JSONObject

import org.json.JSONArray


def static "beautytap.GeneralAction.login"(
    	String type	
     , 	String email	
     , 	String password	) {
    (new beautytap.GeneralAction()).login(
        	type
         , 	email
         , 	password)
}

def static "beautytap.GeneralAction.logout"(
    	String closeBrowser	) {
    (new beautytap.GeneralAction()).logout(
        	closeBrowser)
}

def static "beautytap.GeneralAction.signup"(
    	String username	
     , 	String email	
     , 	String password	
     , 	String name	
     , 	String birthday	) {
    (new beautytap.GeneralAction()).signup(
        	username
         , 	email
         , 	password
         , 	name
         , 	birthday)
}

def static "beautytap.GeneralAction.signupViaFacebook"(
    	String email	
     , 	String password	) {
    (new beautytap.GeneralAction()).signupViaFacebook(
        	email
         , 	password)
}

def static "beautytap.GeneralAction.openMailBox"(
    	String mailBoxType	
     , 	String username	
     , 	String password	) {
    (new beautytap.GeneralAction()).openMailBox(
        	mailBoxType
         , 	username
         , 	password)
}

def static "beautytap.GeneralAction.verifyEmailExist"(
    	String mailBoxType	
     , 	String subject	) {
    (new beautytap.GeneralAction()).verifyEmailExist(
        	mailBoxType
         , 	subject)
}

def static "beautytap.GeneralAction.openEmail"(
    	String mailBoxType	
     , 	String subject	) {
    (new beautytap.GeneralAction()).openEmail(
        	mailBoxType
         , 	subject)
}

def static "beautytap.GeneralAction.clickEmailLink"(
    	String mailBoxType	
     , 	String linkType	) {
    (new beautytap.GeneralAction()).clickEmailLink(
        	mailBoxType
         , 	linkType)
}

def static "beautytap.GeneralAction.openBeautytap"(
    	String url	) {
    (new beautytap.GeneralAction()).openBeautytap(
        	url)
}

def static "beautytap.GeneralAction.clickNavigationMenu"(
    	String navigationMenu	) {
    (new beautytap.GeneralAction()).clickNavigationMenu(
        	navigationMenu)
}

def static "beautytap.GeneralAction.enterText"(
    	TestObject control	
     , 	String text	) {
    (new beautytap.GeneralAction()).enterText(
        	control
         , 	text)
}

def static "beautytap.ShopAction.globalSearch"(
    	String keyword	) {
    (new beautytap.ShopAction()).globalSearch(
        	keyword)
}

def static "beautytap.ShopAction.selectProductOnSearchResult"(
    	String productName	) {
    (new beautytap.ShopAction()).selectProductOnSearchResult(
        	productName)
}

def static "beautytap.ShopAction.selectProductVariation"(
    	String variation	) {
    (new beautytap.ShopAction()).selectProductVariation(
        	variation)
}

def static "beautytap.ShopAction.VerifyProductOnSearchResult"(
    	String productName	
     , 	float regularPrice	
     , 	String regularPriceColor	
     , 	float salePrice	
     , 	String salePriceColor	) {
    (new beautytap.ShopAction()).VerifyProductOnSearchResult(
        	productName
         , 	regularPrice
         , 	regularPriceColor
         , 	salePrice
         , 	salePriceColor)
}

def static "beautytap.ShopAction.addProductToCart"(
    	int quantity	) {
    (new beautytap.ShopAction()).addProductToCart(
        	quantity)
}

def static "beautytap.ShopAction.VerifyProductIsAddedToCart"(
    	String productName	) {
    (new beautytap.ShopAction()).VerifyProductIsAddedToCart(
        	productName)
}

def static "beautytap.ShopAction.goToCart"(
    	String productName	) {
    (new beautytap.ShopAction()).goToCart(
        	productName)
}

def static "beautytap.ShopAction.getNumberItemInCart"() {
    (new beautytap.ShopAction()).getNumberItemInCart()
}

def static "beautytap.ShopAction.VerifyNumberItemInCart"(
    	int numberItem	) {
    (new beautytap.ShopAction()).VerifyNumberItemInCart(
        	numberItem)
}

def static "beautytap.ShopAction.VerifyProductInCart"(
    	String productName	
     , 	String variation	
     , 	float price	
     , 	int quantity	
     , 	float total	) {
    (new beautytap.ShopAction()).VerifyProductInCart(
        	productName
         , 	variation
         , 	price
         , 	quantity
         , 	total)
}

def static "beautytap.ShopAction.processToCheckout"() {
    (new beautytap.ShopAction()).processToCheckout()
}

def static "beautytap.ShopAction.loginAmazonPay"(
    	String page	
     , 	String amazonEmail	
     , 	String amazonPassword	) {
    (new beautytap.ShopAction()).loginAmazonPay(
        	page
         , 	amazonEmail
         , 	amazonPassword)
}

def static "beautytap.ShopAction.checkoutViaAmazonPay"() {
    (new beautytap.ShopAction()).checkoutViaAmazonPay()
}

def static "beautytap.ShopAction.checkoutViaCreditCard"(
    	String cardNumber	
     , 	String cardType	
     , 	String expirationMonth	
     , 	String expirationYear	
     , 	String cvv	) {
    (new beautytap.ShopAction()).checkoutViaCreditCard(
        	cardNumber
         , 	cardType
         , 	expirationMonth
         , 	expirationYear
         , 	cvv)
}

def static "beautytap.ShopAction.checkoutViaPaypal"(
    	String paypalEmail	
     , 	String paypalPassword	) {
    (new beautytap.ShopAction()).checkoutViaPaypal(
        	paypalEmail
         , 	paypalPassword)
}

def static "beautytap.ShopAction.fillCustomerInformation"(
    	JSONObject billingInformation	
     , 	String createAccount	
     , 	String accountUsername	
     , 	String accountPassword	
     , 	String shipToDifferentAddess	
     , 	JSONObject shippingInformation	
     , 	String orderNote	) {
    (new beautytap.ShopAction()).fillCustomerInformation(
        	billingInformation
         , 	createAccount
         , 	accountUsername
         , 	accountPassword
         , 	shipToDifferentAddess
         , 	shippingInformation
         , 	orderNote)
}

def static "beautytap.ShopAction.fillCustomerInformation"(
    	JSONObject billingInformation	
     , 	String createAccount	
     , 	String accountUsername	
     , 	String accountPassword	
     , 	String orderNote	) {
    (new beautytap.ShopAction()).fillCustomerInformation(
        	billingInformation
         , 	createAccount
         , 	accountUsername
         , 	accountPassword
         , 	orderNote)
}

def static "beautytap.ShopAction.VerifyProductDetails"(
    	String productName	
     , 	String variation	
     , 	float regularPrice	
     , 	String regularPriceColor	
     , 	float salePrice	
     , 	String salePriceColor	) {
    (new beautytap.ShopAction()).VerifyProductDetails(
        	productName
         , 	variation
         , 	regularPrice
         , 	regularPriceColor
         , 	salePrice
         , 	salePriceColor)
}

def static "beautytap.ShopAction.VerifyOrderDetailsOnCheckout"(
    	JSONArray products	
     , 	float subtotal	
     , 	String shippingType	
     , 	String shippingLable	
     , 	float shippingPrice	
     , 	float total	) {
    (new beautytap.ShopAction()).VerifyOrderDetailsOnCheckout(
        	products
         , 	subtotal
         , 	shippingType
         , 	shippingLable
         , 	shippingPrice
         , 	total)
}

def static "beautytap.ShopAction.calculateTotal"(
    	int quantity	
     , 	float price	) {
    (new beautytap.ShopAction()).calculateTotal(
        	quantity
         , 	price)
}

def static "beautytap.ShopAction.VerifyOrderReceivedDetails"(
    	JSONArray products	
     , 	float subtotal	
     , 	float shippingPrice	
     , 	String shippingLabel	
     , 	String paymentMethod	
     , 	float total	) {
    (new beautytap.ShopAction()).VerifyOrderReceivedDetails(
        	products
         , 	subtotal
         , 	shippingPrice
         , 	shippingLabel
         , 	paymentMethod
         , 	total)
}

def static "beautytap.ShopAction.getOrderNumberOnOrderReceived"() {
    (new beautytap.ShopAction()).getOrderNumberOnOrderReceived()
}

def static "beautytap.AdminAction.deleteUser"(
    	String email	) {
    (new beautytap.AdminAction()).deleteUser(
        	email)
}
