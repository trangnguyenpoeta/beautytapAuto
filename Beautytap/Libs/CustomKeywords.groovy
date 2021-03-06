
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
     , 	String repassword	) {
    (new beautytap.GeneralAction()).signup(
        	username
         , 	email
         , 	password
         , 	repassword)
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

def static "beautytap.GeneralAction.selectProfileMenu"(
    	String menu	) {
    (new beautytap.GeneralAction()).selectProfileMenu(
        	menu)
}

def static "beautytap.GeneralAction.selectProfileSubMenu"(
    	String submenu	) {
    (new beautytap.GeneralAction()).selectProfileSubMenu(
        	submenu)
}

def static "beautytap.GeneralAction.goToRewardHistory"() {
    (new beautytap.GeneralAction()).goToRewardHistory()
}

def static "beautytap.GeneralAction.VerifyNotificationText"(
    	String notificationText	) {
    (new beautytap.GeneralAction()).VerifyNotificationText(
        	notificationText)
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

def static "beautytap.ShopAction.selectSearchResult"(
    	String resuleName	) {
    (new beautytap.ShopAction()).selectSearchResult(
        	resuleName)
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

def static "beautytap.ShopAction.goToCart"() {
    (new beautytap.ShopAction()).goToCart()
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

def static "beautytap.ShopAction.selectAmazonPayAddress"(
    	String amazonAddress	) {
    (new beautytap.ShopAction()).selectAmazonPayAddress(
        	amazonAddress)
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
     , 	String accountPassword	) {
    (new beautytap.ShopAction()).fillCustomerInformation(
        	billingInformation
         , 	createAccount
         , 	accountUsername
         , 	accountPassword)
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

def static "beautytap.ShopAction.VerifyProductDetails"(
    	String productName	
     , 	String variation	
     , 	float regularPrice	
     , 	String regularPriceColor	
     , 	float salePrice	
     , 	String salePriceColor	
     , 	String limitStock	) {
    (new beautytap.ShopAction()).VerifyProductDetails(
        	productName
         , 	variation
         , 	regularPrice
         , 	regularPriceColor
         , 	salePrice
         , 	salePriceColor
         , 	limitStock)
}

def static "beautytap.ShopAction.VerifyOrderDetailsOnCheckout"(
    	JSONArray productsArray	
     , 	float subtotal	
     , 	JSONArray shippingArray	
     , 	float total	) {
    (new beautytap.ShopAction()).VerifyOrderDetailsOnCheckout(
        	productsArray
         , 	subtotal
         , 	shippingArray
         , 	total)
}

def static "beautytap.ShopAction.VerifyOrderDetailsOnCheckout"(
    	JSONArray products	
     , 	float subtotal	
     , 	float pointUsed	
     , 	float moneyDiscount	
     , 	String shippingType	
     , 	String shippingLable	
     , 	float shippingPrice	
     , 	float total	) {
    (new beautytap.ShopAction()).VerifyOrderDetailsOnCheckout(
        	products
         , 	subtotal
         , 	pointUsed
         , 	moneyDiscount
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

def static "beautytap.ShopAction.VerifyOrderReceivedDetails"(
    	JSONArray products	
     , 	float subtotal	
     , 	float pointUsed	
     , 	float moneyDiscount	
     , 	float shippingPrice	
     , 	String shippingLabel	
     , 	String paymentMethod	
     , 	float total	) {
    (new beautytap.ShopAction()).VerifyOrderReceivedDetails(
        	products
         , 	subtotal
         , 	pointUsed
         , 	moneyDiscount
         , 	shippingPrice
         , 	shippingLabel
         , 	paymentMethod
         , 	total)
}

def static "beautytap.ShopAction.getOrderInfoOnOrderReceived"() {
    (new beautytap.ShopAction()).getOrderInfoOnOrderReceived()
}

def static "beautytap.ShopAction.selectCategory"(
    	String menu	
     , 	String category	
     , 	String subCategory	) {
    (new beautytap.ShopAction()).selectCategory(
        	menu
         , 	category
         , 	subCategory)
}

def static "beautytap.ShopAction.findProductOnProductList"(
    	String productName	) {
    (new beautytap.ShopAction()).findProductOnProductList(
        	productName)
}

def static "beautytap.ShopAction.selectProductOnProductList"(
    	String productName	) {
    (new beautytap.ShopAction()).selectProductOnProductList(
        	productName)
}

def static "beautytap.ShopAction.VerifyProductOnProductList"(
    	String productName	
     , 	float regularPrice	
     , 	String regularPriceColor	
     , 	float salePrice	
     , 	String salePriceColor	) {
    (new beautytap.ShopAction()).VerifyProductOnProductList(
        	productName
         , 	regularPrice
         , 	regularPriceColor
         , 	salePrice
         , 	salePriceColor)
}

def static "beautytap.ShopAction.selectShipping"(
    	String shipping	) {
    (new beautytap.ShopAction()).selectShipping(
        	shipping)
}

def static "beautytap.ShopAction.getRewardDetails"() {
    (new beautytap.ShopAction()).getRewardDetails()
}

def static "beautytap.ShopAction.VerifyRewardPointsDetails"(
    	float lifetime	
     , 	float pending	
     , 	float redeemable	
     , 	float pointvalue	) {
    (new beautytap.ShopAction()).VerifyRewardPointsDetails(
        	lifetime
         , 	pending
         , 	redeemable
         , 	pointvalue)
}

def static "beautytap.ShopAction.getRewardHistory"(
    	String orderNumber	
     , 	String status	) {
    (new beautytap.ShopAction()).getRewardHistory(
        	orderNumber
         , 	status)
}

def static "beautytap.ShopAction.VerifyRewardHistory"(
    	String orderNumber	
     , 	String date	
     , 	String status	
     , 	float pointRedeemed	
     , 	float subtotal	
     , 	float multiplier	
     , 	float loyaltyPoint	
     , 	float totalPoint	) {
    (new beautytap.ShopAction()).VerifyRewardHistory(
        	orderNumber
         , 	date
         , 	status
         , 	pointRedeemed
         , 	subtotal
         , 	multiplier
         , 	loyaltyPoint
         , 	totalPoint)
}

def static "beautytap.ShopAction.VerifyRewardEarned"(
    	String level	
     , 	float subtotal	
     , 	float multiplier	
     , 	float rewardEarned	) {
    (new beautytap.ShopAction()).VerifyRewardEarned(
        	level
         , 	subtotal
         , 	multiplier
         , 	rewardEarned)
}

def static "beautytap.ShopAction.calculateLoyaltyPointValue"(
    	float redeemablePoint	) {
    (new beautytap.ShopAction()).calculateLoyaltyPointValue(
        	redeemablePoint)
}

def static "beautytap.ShopAction.applyLoyaltyRewardPoint"(
    	float pointUsed	) {
    (new beautytap.ShopAction()).applyLoyaltyRewardPoint(
        	pointUsed)
}

def static "beautytap.ShopAction.VerifyOrderCompleteEmail"(
    	JSONArray products	
     , 	float subtotal	
     , 	float discount	
     , 	float shippingPrice	
     , 	String shippingLabel	
     , 	String paymentMethod	
     , 	float total	
     , 	float redeemablePoint	) {
    (new beautytap.ShopAction()).VerifyOrderCompleteEmail(
        	products
         , 	subtotal
         , 	discount
         , 	shippingPrice
         , 	shippingLabel
         , 	paymentMethod
         , 	total
         , 	redeemablePoint)
}

def static "beautytap.ShopAction.generateScheduleDateTime"(
    	String timezone	
     , 	int delayMinute	
     , 	int durationMiniute	) {
    (new beautytap.ShopAction()).generateScheduleDateTime(
        	timezone
         , 	delayMinute
         , 	durationMiniute)
}

def static "beautytap.ShopAction.waitForSchedule"(
    	String timezone	
     , 	String scheduleDate	
     , 	int timeout	) {
    (new beautytap.ShopAction()).waitForSchedule(
        	timezone
         , 	scheduleDate
         , 	timeout)
}

def static "beautytap.AdminAction.deleteUser"(
    	String email	) {
    (new beautytap.AdminAction()).deleteUser(
        	email)
}

def static "beautytap.AdminAction.selectAdminMenu"(
    	String menu	
     , 	String submenu	) {
    (new beautytap.AdminAction()).selectAdminMenu(
        	menu
         , 	submenu)
}

def static "beautytap.AdminAction.changeOrderStatus"(
    	String orderNumber	
     , 	String status	) {
    (new beautytap.AdminAction()).changeOrderStatus(
        	orderNumber
         , 	status)
}

def static "beautytap.AdminAction.scheduleSaleProduct"(
    	String productName	
     , 	float price	
     , 	JSONArray variations	
     , 	String startDate	
     , 	String endDate	
     , 	String limitStock	) {
    (new beautytap.AdminAction()).scheduleSaleProduct(
        	productName
         , 	price
         , 	variations
         , 	startDate
         , 	endDate
         , 	limitStock)
}
