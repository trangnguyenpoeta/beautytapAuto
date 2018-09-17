import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.json.JSONObject
import org.json.JSONArray
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

String username = GlobalVariable.USER_EMAIL
String password = GlobalVariable.USER_PASSWORD
String productName  = GlobalVariable.SIMPLE_PRODUCT
float price = GlobalVariable.SIMPLE_PRODUCT_PRICE
int quantity = 3
String shippingLabel = GlobalVariable.FREE_SHIPPING_LABEL
float shippingPrice =0
float subtotal = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, price)
float total = subtotal + shippingPrice
JSONArray products = new JSONArray('[{"productname":"'+ productName +'","variation":"","quantity":"'+ quantity +'","price":"'+ price +'"}]')
JSONObject billingInformation =new JSONObject('{"firstname":"Test","lastname":"Automation","country":"United States (US)","address":"123 Testing","city":"New York","state":"New York","zip":"90012","email":"'+username+'"}')
String paymentMethod ='Credit Card Payment'
//=======================================================================
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'("Login")
CustomKeywords.'beautytap.GeneralAction.login'("email", username, password)
CustomKeywords.'beautytap.GeneralAction.selectProfileMenu'("My Rewards")
'Get current multiplier, lifetime, pending, redeemable points, point value'
JSONObject objRewoard = CustomKeywords.'beautytap.ShopAction.getRewardDetails'()
float multiplier = Float.parseFloat(objRewoard.get("multiplier"))
String currentlevel = objRewoard.get("currentlevel")
float lifetime = Float.parseFloat(objRewoard.get("lifetime"))
float pending = Float.parseFloat(objRewoard.get("pending"))
float redeemable = Float.parseFloat(objRewoard.get("redeemable"))
float pointvalue = Float.parseFloat(objRewoard.get("pointvalue"))
float rewardEarned = Float.parseFloat(String.format("%.2f", multiplier*subtotal))
String notificationMessage = "You received "+ rewardEarned +" redeemable points"
'Buy a product'
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)
CustomKeywords.'beautytap.ShopAction.selectProductOnSearchResult'(productName)
CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)
CustomKeywords.'beautytap.ShopAction.goToCart'()
'Process to checkout'
CustomKeywords.'beautytap.ShopAction.processToCheckout'()
CustomKeywords.'beautytap.ShopAction.fillCustomerInformation'(billingInformation, "no", null, null, "TC07")
'VP1: Verify order details on checkout'
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, subtotal, "free", shippingLabel, shippingPrice, total)
'VP2: Verify reward on checkout page: Rewards earned = (subtotal-discount)*multiplier'
CustomKeywords.'beautytap.ShopAction.VerifyRewardEarned'(currentlevel, subtotal, multiplier, rewardEarned)
CustomKeywords.'beautytap.ShopAction.checkoutViaCreditCard'(GlobalVariable.CREDITCARD_NUMBER, GlobalVariable.CARD_TYPE, GlobalVariable.CARD_EXPIRATION_MONTH, GlobalVariable.CARD_EXPIRATION_YEAR, GlobalVariable.CARD_CVV)
'VP3: Verify order details on Order Recieved'
CustomKeywords.'beautytap.ShopAction.VerifyOrderReceivedDetails'(products, subtotal, shippingPrice, shippingLabel, paymentMethod, total)
'VP4: Verify reward on Order received page: Rewards earned = (subtotal-discount)*multiplier'
CustomKeywords.'beautytap.ShopAction.VerifyRewardEarned'(currentlevel, subtotal, multiplier, rewardEarned)
JSONObject orderInfo = CustomKeywords.'beautytap.ShopAction.getOrderInfoOnOrderReceived'()
String orderNumber = orderInfo.get("ordernumber")
String orderDate = orderInfo.get("date")
String completeOrderSubject = "Your "+ GlobalVariable.SITE_TITLE +" order from "+orderDate+" is complete"
completeOrderSubject = completeOrderSubject.replace("[", "").replace("]", "")
'Go to My Rewards page' 
CustomKeywords.'beautytap.GeneralAction.selectProfileMenu'("My Rewards")
'VP5: Verify reward detail: lifetime=no change,pending=current pending+reward, redeemable=no change,point value: no change'
float newPending = Float.parseFloat(String.format("%.2f", pending+rewardEarned))
CustomKeywords.'beautytap.ShopAction.VerifyRewardPointsDetails'(lifetime, newPending, redeemable, pointvalue)
'VP6: Verify reward history: 1 row with sataut pending,1 row with status processing'
CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'(orderNumber, '', "pending", 0, subtotal, multiplier, 0, redeemable)
CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'(orderNumber, '', "processing", 0, subtotal, multiplier, 0, redeemable)
'Logout'
CustomKeywords.'beautytap.GeneralAction.logout'()
'Login As Admin'
CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'("Login")
CustomKeywords.'beautytap.GeneralAction.login'("email", GlobalVariable.ADMIN_USERNAME, GlobalVariable.ADMIN_PASSWORD)
'Go to Woocommerce > Orders > Change order status to Completed'
WebUI.navigateToUrl(GlobalVariable.SITE_URL+"/admin")
CustomKeywords.'beautytap.AdminAction.changeOrderStatus'(orderNumber, "Completed")
'VP7: Verify user recieve notification email with redeemable point'
CustomKeywords.'beautytap.GeneralAction.openMailBox'("mailinator", username, password)
CustomKeywords.'beautytap.GeneralAction.verifyEmailExist'("mailinator", completeOrderSubject)
CustomKeywords.'beautytap.GeneralAction.openEmail'("mailinator", completeOrderSubject)
CustomKeywords.'beautytap.ShopAction.VerifyOrderCompleteEmail'(products, subtotal, 0, shippingPrice, shippingLabel, paymentMethod, subtotal, rewardEarned)
'Logout'
WebUI.closeBrowser()
'Login again'
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'("Login")
CustomKeywords.'beautytap.GeneralAction.login'("email", username, password)
'VP8: Verify notification: You received [#] redeemable points'
CustomKeywords.'beautytap.GeneralAction.VerifyNotificationText'(notificationMessage)
'Go to My Rewards page'
CustomKeywords.'beautytap.GeneralAction.selectProfileMenu'("My Rewards")
'VP9: Verify reward detail: lifetime=lifetime+reward,pending=pending-reward, redeemable=redeemable+reward,point value: associate with reward'
float newLifetime = Float.parseFloat(String.format("%.2f",lifetime + rewardEarned))
float newRedeemable = Float.parseFloat(String.format("%.2f", redeemable+rewardEarned))
float newPointValue = CustomKeywords.'beautytap.ShopAction.calculateLoyaltyPointValue'(newRedeemable)
CustomKeywords.'beautytap.ShopAction.VerifyRewardPointsDetails'(newLifetime, pending, newRedeemable, newPointValue)
'VP10: Verify reward history: 1 row with sataut complete'
CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'(orderNumber, '', "completed", 0, subtotal, multiplier, rewardEarned, newRedeemable)
CustomKeywords.'beautytap.GeneralAction.logout'()