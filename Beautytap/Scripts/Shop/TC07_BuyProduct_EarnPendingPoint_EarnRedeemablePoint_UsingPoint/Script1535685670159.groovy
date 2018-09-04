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
'Go to My Rewards page'
CustomKeywords.'beautytap.GeneralAction.selectProfileMenu'("My Rewards")
'VP5: Verify reward detail: lifetime=no change,pending=current pending+reward, redeemable=no change,point value: no change'
float newPending = Float.parseFloat(String.format("%.2f", pending+rewardEarned))
CustomKeywords.'beautytap.ShopAction.VerifyRewardPointsDetails'(lifetime, newPending, redeemable, pointvalue)
'VP6: Verify reward history: 1 row with sataut pending,1 row with status processing'
CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'(orderNumber, orderDate, "pending", 0, subtotal, multiplier, 0, lifetime)
