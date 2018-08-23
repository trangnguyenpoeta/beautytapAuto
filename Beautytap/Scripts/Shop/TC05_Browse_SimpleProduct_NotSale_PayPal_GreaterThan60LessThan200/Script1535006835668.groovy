import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.json.JSONArray
import org.json.JSONObject
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

//Set variable
String productName = GlobalVariable.SIMPLE_PRODUCT
float price = GlobalVariable.SIMPLE_PRODUCT_PRICE
String r_string = new Math().random().toString().substring(2, 8)
String email = 'auto' + r_string + '@mailinator.com'
String orderNote = 'auto' + r_string + 'Order'
int currentNumberItemInCart
int quantity = 3
float subtotal = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, price)
float total=subtotal
total= CustomKeywords.'beautytap.ShopAction.calculateTotal'(1, total)
JSONArray products = new JSONArray('[{"productname":"'+ productName +'","variation":"","quantity":"'+ quantity +'","price":"'+ price +'"}]')
JSONObject billingInformation =new JSONObject('{"firstname":"Test","lastname":"Automation","country":"United States (US)","address":"123 Testing","city":"New York","state":"New York","zip":"90012","email":"'+email+'"}')
String shippingLabel = GlobalVariable.FREE_SHIPPING_LABEL
float shippingPrice = 0
String shippingType = 'free'
String paymentMethod ='PayPal'
String category = "What's New"
//---------------------------------------------------------
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.selectCategory'(category, null)
CustomKeywords.'beautytap.ShopAction.findProductOnProductList'(productName)
'VP1: Verify product display on product list'
CustomKeywords.'beautytap.ShopAction.VerifyProductOnProductList'(productName, price, "pink", 0, null)
CustomKeywords.'beautytap.ShopAction.selectProductOnProductList'(productName)
'VP2: Verify product detail page display with regular price'
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, "", price, "pink", 0, null)
currentNumberItemInCart = CustomKeywords.'beautytap.ShopAction.getNumberItemInCart'()
CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)
'VP3:Verify product is added to card: cart number increase and message display “PRODUCT_NAME” has been added to your cart.'
CustomKeywords.'beautytap.ShopAction.VerifyNumberItemInCart'(currentNumberItemInCart+quantity)
CustomKeywords.'beautytap.ShopAction.VerifyProductIsAddedToCart'(productName)
CustomKeywords.'beautytap.ShopAction.goToCart'()
'VP4:Verify product is added to cart with correct price and quantity'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName,'', price, quantity, subtotal)
CustomKeywords.'beautytap.ShopAction.processToCheckout'()
CustomKeywords.'beautytap.ShopAction.fillCustomerInformation'(billingInformation, 'no', '', '', orderNote)
'VP5: Verify order details on checkout page'
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, subtotal, shippingType, shippingLabel, shippingPrice, total)
'Checkout by credit card'
CustomKeywords.'beautytap.ShopAction.checkoutViaPaypal'(GlobalVariable.PAYPAL_EMAIL, GlobalVariable.PAYPAL_PASSWORD)
'VP6: Verify order details on order received Page'
CustomKeywords.'beautytap.ShopAction.VerifyOrderReceivedDetails'(products, subtotal, shippingPrice, shippingLabel, paymentMethod, total)
//WebUI.closeBrowser()