import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import org.json.JSONArray as JSONArray
import org.json.JSONObject as JSONObject

String username = GlobalVariable.USER_EMAIL

String password = GlobalVariable.USER_PASSWORD

String productName = GlobalVariable.SIMPLE_PRODUCT

float price = GlobalVariable.SIMPLE_PRODUCT_PRICE

int quantity = 4

String shippingLabel = GlobalVariable.FREE_SHIPPING_LABEL

float shippingPrice = 0

float pointUsed = 60

float pointValueUsed = 2

float subtotal = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, price)

float subtotalApplyReward = Float.parseFloat(String.format('%.2f', subtotal - pointValueUsed))

float total = Float.parseFloat(String.format('%.2f', (subtotal - pointValueUsed) + shippingPrice))

JSONArray products = new JSONArray(((((('[{"productname":"' + productName) + '","variation":"","quantity":"') + quantity) + 
'","price":"') + price) + '"}]')

JSONObject billingInformation = new JSONObject(('{"firstname":"Test","lastname":"Automation","country":"United States (US)","address":"123 Testing","city":"New York","state":"New York","zip":"90012","email":"' + 
username) + '","phone":"0123456789"}')

JSONArray shippingArray = new JSONArray('[{"shippingType":"free","shippingMethod":"Shipping from Korea","shippingPrice":"0","shippingLabel":"Qualified for FREE international shipping from Korea."}]')

String paymentMethod = 'PayPal'

//==========================================
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

float rewardEarned = Float.parseFloat(String.format('%.2f', multiplier * (subtotal - pointValueUsed)))

'Buy another product and use reward point'
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)

CustomKeywords.'beautytap.ShopAction.selectSearchResult'(productName)

CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)

CustomKeywords.'beautytap.ShopAction.goToCart'()

'Process to checkout'
CustomKeywords.'beautytap.ShopAction.processToCheckout'()

'Apply Reward point'
CustomKeywords.'beautytap.ShopAction.applyLoyaltyRewardPoint'(pointUsed)

'Fill customer information'
CustomKeywords.'beautytap.ShopAction.fillCustomerInformation'(billingInformation, 'no', null, null)

'VP1: Verify Order details on checkout page with reward discount'
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, subtotal, shippingArray, total)

'VP2: Verify reward on checkout page: Rewards earned = (subtotal-discount)*multiplier'
CustomKeywords.'beautytap.ShopAction.VerifyRewardEarned'(currentlevel, subtotalApplyReward, multiplier, rewardEarned)

'Checkout via creditcard'
CustomKeywords.'beautytap.ShopAction.checkoutViaPaypal'(GlobalVariable.PAYPAL_EMAIL, GlobalVariable.PAYPAL_PASSWORD)

'Get order ID, Order date'
JSONObject orderInfo = CustomKeywords.'beautytap.ShopAction.getOrderInfoOnOrderReceived'()

String orderNumber = orderInfo.get('ordernumber')

'Go to My Rewards page'
CustomKeywords.'beautytap.GeneralAction.goToRewardHistory'()

'VP3:lifetime=lifetime,pending=pending+reward, redeemable=redeemable-point used,point value=calculate base on new redeemable'
float newPending = Float.parseFloat(String.format('%.2f', pending + rewardEarned))

float newRedeemable = Float.parseFloat(String.format('%.2f', redeemable - pointUsed))

float newPointValue = CustomKeywords.'beautytap.ShopAction.calculateLoyaltyPointValue'(newRedeemable)

CustomKeywords.'beautytap.ShopAction.VerifyRewardPointsDetails'(lifetime, newPending, newRedeemable, newPointValue)

'VP4: Verify reward history: 1 row with status pending,1 row with status processing'
CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'(orderNumber, '', 'pending', pointUsed, subtotalApplyReward, multiplier, 
        ((-1 * pointUsed) as float), newRedeemable)

CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'(orderNumber, '', 'processing', pointUsed, subtotalApplyReward, 
    multiplier, 0, newRedeemable)

'Logout'
CustomKeywords.'beautytap.GeneralAction.logout'()

'Login As Admin'
WebUI.openBrowser(GlobalVariable.SITE_URL + '/admin')

WebUI.maximizeWindow(FailureHandling.OPTIONAL)

CustomKeywords.'beautytap.GeneralAction.login'('email', GlobalVariable.ADMIN_USERNAME, GlobalVariable.ADMIN_PASSWORD)

'Go to Woocommerce > Orders > Change order status to Completed'
CustomKeywords.'beautytap.AdminAction.changeOrderStatus'(orderNumber, 'Completed')

'Logout'
WebUI.closeBrowser()

'Login again'
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)

CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'('Login')

CustomKeywords.'beautytap.GeneralAction.login'('email', username, password)

'Go to My Rewards page'
CustomKeywords.'beautytap.GeneralAction.goToRewardHistory'()

'VP5: Verify reward detail: lifetime=lifetime+reward,pending=pending-reward, redeemable=redeemable+reward-point used,point value: associate with reward'
float newLifetime = Float.parseFloat(String.format('%.2f', lifetime + rewardEarned))

newRedeemable = Float.parseFloat(String.format('%.2f', newRedeemable + rewardEarned))

newPointValue = CustomKeywords.'beautytap.ShopAction.calculateLoyaltyPointValue'(newRedeemable)

CustomKeywords.'beautytap.ShopAction.VerifyRewardPointsDetails'(newLifetime, pending, newRedeemable, newPointValue)

'VP6: Verify reward history: 1 row with status complete'
CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'(orderNumber, '', 'completed', pointUsed, subtotalApplyReward, 
    multiplier, rewardEarned, newRedeemable)

CustomKeywords.'beautytap.GeneralAction.logout'('CloseBrowser')

