import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.json.JSONArray as JSONArray
import org.json.JSONObject as JSONObject
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
String productName = GlobalVariable.VARIATION_PRODUCT

float price = GlobalVariable.VARIATION_PRICE1

String variation = GlobalVariable.VARIATION_NAME1

String r_string = new Math().random().toString().substring(2, 8)

String email = ('beauty' + r_string) + '@mailinator.com'

int currentNumberItemInCart

int quantity = 8

float subtotal = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, price)

float total = subtotal

total = CustomKeywords.'beautytap.ShopAction.calculateTotal'(1, total)

JSONArray products = new JSONArray(((((((('[{"productname":"' + productName) + '","variation":"') + variation) + '","quantity":"') + 
quantity) + '","price":"') + price) + '"}]')

JSONArray shippingArray = new JSONArray('[{"shippingType":"free","shippingMethod":"Shipping from Korea","shippingPrice":"0","shippingLabel":"Qualified for FREE international shipping from Korea."}]')

String paymentMethod = 'Amazon Pay'

String amazonAddress = 'Trang N. CMT8 Floor 9th, Hochiminh, 3, 700000, Vietnam'

//---------------------------------------------------------
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)

CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)

'VP1: Verify product display in search result panel with regular price'
CustomKeywords.'beautytap.ShopAction.VerifyProductOnSearchResult'(productName, price, 'pink', 0, null)

CustomKeywords.'beautytap.ShopAction.selectSearchResult'(productName)

CustomKeywords.'beautytap.ShopAction.selectProductVariation'(variation)

'VP2: Verify product detail page display with regular price'
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, variation, price, 'pink', 0, null)

currentNumberItemInCart = CustomKeywords.'beautytap.ShopAction.getNumberItemInCart'()

CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)

'VP3:Verify product is added to card: cart number increase and message display “PRODUCT_NAME” has been added to your cart.'
CustomKeywords.'beautytap.ShopAction.VerifyNumberItemInCart'(currentNumberItemInCart + quantity)

CustomKeywords.'beautytap.ShopAction.VerifyProductIsAddedToCart'(productName)

CustomKeywords.'beautytap.ShopAction.goToCart'()

'VP4:Verify product is added to cart with correct price and quantity'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, variation, price, quantity, subtotal)

CustomKeywords.'beautytap.ShopAction.processToCheckout'()

'Login amazonPay'
CustomKeywords.'beautytap.ShopAction.loginAmazonPay'('checkout', GlobalVariable.AMAZONPAY_EMAIL, GlobalVariable.AMAZONPAY_PASSWORD)

CustomKeywords.'beautytap.ShopAction.selectAmazonPayAddress'(amazonAddress)

'VP5: Verify order details on checkout page'
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, subtotal, shippingArray, total)

'Checkout via amazonPay'
CustomKeywords.'beautytap.ShopAction.checkoutViaAmazonPay'()

WebUI.closeBrowser()

