import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.json.JSONObject as JSONObject
import org.json.JSONArray as JSONArray
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
import java.text.DecimalFormat as DecimalFormat

String username = GlobalVariable.USER_EMAIL

String password = GlobalVariable.USER_PASSWORD

String productName = GlobalVariable.SIMPLE_PRODUCT

float price = GlobalVariable.SIMPLE_PRODUCT_PRICE

int quantity = 4

String shippingLabel = GlobalVariable.FREE_SHIPPING_LABEL

float shippingPrice = 0

float subtotal = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, price)

float total = subtotal + shippingPrice

JSONArray products = new JSONArray(((((('[{"productname":"' + productName) + '","variation":"","quantity":"') + quantity) + 
'","price":"') + price) + '"}]')

JSONObject billingInformation = new JSONObject(('{"firstname":"Test","lastname":"Automation","country":"United States (US)","address":"123 Testing","city":"New York","state":"New York","zip":"90012","email":"' + 
username) + '","phone":"0123456789"}')

JSONArray shippingArray = new JSONArray('[{"shippingType":"free","shippingMethod":"Shipping from Korea","shippingPrice":"0","shippingLabel":"Qualified for FREE international shipping from Korea."}]')

String paymentMethod = 'PayPal'

//=======================================================================
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)

CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('Login')

CustomKeywords.'beautytap.GeneralAction.login'('email', username, password)

CustomKeywords.'beautytap.GeneralAction.selectProfileMenu'('Beauty Wall')

CustomKeywords.'beautytap.GeneralAction.selectProfileSubMenu'('Bio')

'Get current multiplier, lifetime, pending, redeemable points, point value'
JSONObject objReward = CustomKeywords.'beautytap.ShopAction.getRewardDetails'()

float multiplier = Float.parseFloat(objReward.get('multiplier'))

String currentlevel = objReward.get('currentlevel')

float lifetime = Float.parseFloat(objReward.get('lifetime'))

float pending = Float.parseFloat(objReward.get('pending'))

float redeemable = Float.parseFloat(objReward.get('redeemable'))

float pointvalue = Float.parseFloat(objReward.get('pointvalue'))

DecimalFormat format = new DecimalFormat('0.##')

float rewardEarned = Float.parseFloat(format.format(multiplier * subtotal))

String notificationMessage = ('You received ' + rewardEarned) + ' redeemable points'

'Buy a product'
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)

CustomKeywords.'beautytap.ShopAction.selectSearchResult'(productName)

CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)

CustomKeywords.'beautytap.ShopAction.goToCart'()

'Process to checkout'
CustomKeywords.'beautytap.ShopAction.processToCheckout'()

CustomKeywords.'beautytap.ShopAction.fillCustomerInformation'(billingInformation, 'no', null, null)

'VP1: Verify order details on checkout'
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, subtotal, shippingArray, total)

'VP2: Verify reward on checkout page: Rewards earned = (subtotal-discount)*multiplier'
CustomKeywords.'beautytap.ShopAction.VerifyRewardEarned'(currentlevel, subtotal, multiplier, rewardEarned)

CustomKeywords.'beautytap.ShopAction.checkoutViaPaypal'(GlobalVariable.PAYPAL_EMAIL, GlobalVariable.PAYPAL_PASSWORD)

'Get Order ID, Order date'
JSONObject orderInfo = CustomKeywords.'beautytap.ShopAction.getOrderInfoOnOrderReceived'()

String orderNumber = orderInfo.get('ordernumber')

String orderDate = orderInfo.get('date')

String completeOrderSubject = ('Your Beautytap Order #' + orderNumber) + ' Is Complete'

'Go to Rewards History page'
CustomKeywords.'beautytap.GeneralAction.goToRewardHistory'()

'VP3: Verify reward detail: lifetime=no change,pending=current pending+reward, redeemable=no change,point value: no change'
float newPending = Float.parseFloat(String.format('%.2f', pending + rewardEarned))

CustomKeywords.'beautytap.ShopAction.VerifyRewardPointsDetails'(lifetime, newPending, redeemable, pointvalue)

'VP4: Verify reward history: 1 row with sataut pending,1 row with status processing'
CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'(orderNumber, orderDate, 'pending', 0, subtotal, multiplier, 0, 
    redeemable)

CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'(orderNumber, orderDate, 'processing', 0, subtotal, multiplier, 
    0, redeemable)

'Logout'
CustomKeywords.'beautytap.GeneralAction.logout'()

'Login As Admin'
WebUI.openBrowser(GlobalVariable.SITE_URL + '/admin')

WebUI.maximizeWindow(FailureHandling.OPTIONAL)

CustomKeywords.'beautytap.GeneralAction.login'('email', GlobalVariable.ADMIN_USERNAME, GlobalVariable.ADMIN_PASSWORD)

'Go to Woocommerce > Orders > Change order status to Completed'
CustomKeywords.'beautytap.AdminAction.changeOrderStatus'(orderNumber, 'Completed')

'VP5: Verify user recieve notification email with redeemable point'
CustomKeywords.'beautytap.GeneralAction.openMailBox'('mailinator', username, password)

CustomKeywords.'beautytap.GeneralAction.verifyEmailExist'('mailinator', completeOrderSubject)

CustomKeywords.'beautytap.GeneralAction.openEmail'('mailinator', completeOrderSubject)

CustomKeywords.'beautytap.ShopAction.VerifyOrderCompleteEmail'(products, subtotal, 0, shippingPrice, shippingLabel, paymentMethod, 
    subtotal, rewardEarned)

'Logout'
WebUI.closeBrowser()

'Login again'
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)

CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('Login')

CustomKeywords.'beautytap.GeneralAction.login'('email', username, password)

'VP6: Verify notification: You received [#] redeemable points'
CustomKeywords.'beautytap.GeneralAction.VerifyNotificationText'(notificationMessage)

'Go to My Rewards page'

'Go to My Rewards page'
CustomKeywords.'beautytap.GeneralAction.goToRewardHistory'()

'VP7: Verify reward detail: lifetime=lifetime+reward,pending=pending-reward, redeemable=redeemable+reward,point value: associate with reward'
float newLifetime = Float.parseFloat(String.format('%.2f', lifetime + rewardEarned))

float newRedeemable = Float.parseFloat(String.format('%.2f', redeemable + rewardEarned))

float newPointValue = CustomKeywords.'beautytap.ShopAction.calculateLoyaltyPointValue'(newRedeemable)

CustomKeywords.'beautytap.ShopAction.VerifyRewardPointsDetails'(newLifetime, pending, newRedeemable, newPointValue)

'VP8: Verify reward history: 1 row with status complete'
CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'(orderNumber, '', 'completed', 0, subtotal, multiplier, rewardEarned, 
    newRedeemable)

CustomKeywords.'beautytap.GeneralAction.logout'('CloseBrowser')

