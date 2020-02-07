import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.json.JSONArray as JSONArray
import org.json.JSONObject as JSONObject
//Set variable
String productName = GlobalVariable.VARIATION_PRODUCT

float price = GlobalVariable.VARIATION_PRICE1

String variation = GlobalVariable.VARIATION_NAME1

String r_string = new Math().random().toString().substring(2, 8)

String email = 'beauty' + r_string + '@mailinator.com'

int currentNumberItemInCart

int quantity = 12

float subtotal = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, price)

float total = subtotal

total = CustomKeywords.'beautytap.ShopAction.calculateTotal'(1, total)

JSONArray products = new JSONArray('[{"productname":"' + productName + '","variation":"' + variation + '","quantity":"' + quantity + '","price":"' + price + '"}]')

JSONArray shippingArray = new JSONArray('[{"shippingType":"free","shippingMethod":"Shipping from Korea","shippingPrice":"0","shippingLabel":"'+ GlobalVariable.FREE_SHIPPING_LABEL +'"}]')

JSONObject billingInformation = new JSONObject(('{"firstname":"Beauty","lastname":"Automation","country":"Vietnam","address":"123 Testing","city":"HCM","state":"","zip":"90012","email":"' +
	email) + '","phone":"0123456789"}')
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

CustomKeywords.'beautytap.ShopAction.fillCustomerInformation'(billingInformation, 'no', null, null)

'VP5: Verify order details on checkout page'
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, subtotal, shippingArray, total)

WebUI.closeBrowser()

