import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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
float total = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, price)
String json = '{"firstname":"Test","lastname":"Automation","country":"United States (US)","address":"123 Testing","city":"New York","state":"New York","zip":"90012","email":"'+email+'"}'
JSONObject billingInformation =new JSONObject(json)
//---------------------------------------------------------
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)
'VP1: Verify product display in search result panel with regular price'
CustomKeywords.'beautytap.ShopAction.VerifyProductOnSearchResult'(productName, price, "pink", 0, null)
CustomKeywords.'beautytap.ShopAction.selectProductOnSearchResult'(productName)
'VP2: Verify product detail page display with regular price'
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, "", price, "pink", 0, null)
'VP3:Verify message display: “PRODUCT_NAME” has been added to your cart.'
currentNumberItemInCart = CustomKeywords.'beautytap.ShopAction.getNumberItemInCart'()
CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)
CustomKeywords.'beautytap.ShopAction.VerifyNumberItemInCart'(currentNumberItemInCart+quantity)
CustomKeywords.'beautytap.ShopAction.goToCart'()
'VP4:Verify product is added to cart with the correct price and quantity'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName,'', price, quantity, total)
CustomKeywords.'beautytap.ShopAction.processToCheckout'()
CustomKeywords.'beautytap.ShopAction.fillCustomerInformation'(billingInformation, 'no', '', '', orderNote)
'VP5: Verify order details: product, quantity,subtotal,shipping,total'
CustomKeywords.'beautytap.ShopAction.checkoutViaCreditCard'(GlobalVariable.CREDITCARD_NUMBER, GlobalVariable.CARD_TYPE, GlobalVariable.CARD_EXPIRATION_MONTH, GlobalVariable.CARD_EXPIRATION_YEAR, GlobalVariable.CARD_CVV)
'VP6: Verify order recieive with correct information: Date,Total,Payment method'

