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
import com.kms.katalon.core.testobject.TestObject

import org.json.JSONArray
import org.json.JSONObject
import com.kms.katalon.core.testobject.ConditionType
import org.apache.commons.lang.StringUtils



String productName = GlobalVariable.SIMPLE_PRODUCT
float price = GlobalVariable.SIMPLE_PRODUCT_PRICE
String r_string = new Math().random().toString().substring(2, 8)
String email = 'auto' + r_string + '@mailinator.com'
String orderNote = 'auto' + r_string + 'Order'
int currentNumberItemInCart
int quantity = 1
float subtotal = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, price)
float total=subtotal+GlobalVariable.SHIPPING_PRICE
total= CustomKeywords.'beautytap.ShopAction.calculateTotal'(1, total)
JSONArray products = new JSONArray('[{"productname":"'+ GlobalVariable.SIMPLE_PRODUCT +'","variation":"","quantity":"'+ quantity +'","price":"'+ GlobalVariable.SIMPLE_PRODUCT_PRICE +'"}]')
JSONObject billingInformation =new JSONObject('{"firstname":"Test","lastname":"Automation","country":"United States (US)","address":"123 Testing","city":"New York","state":"New York","zip":"90012","email":"'+email+'"}')
String shippingLabel = GlobalVariable.SHIPPING_LABEL
float shippingPrice = GlobalVariable.SHIPPING_PRICE
String shippingType = 'normal'
String paymentMethod ='Credit Card Payment'
String category ="Makeup"
String subcategory ='Blush'
//---------------------------------------------------------

//CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.findProductOnProductList'('Etude House All Caring Eraser Pad 60p')
//CustomKeywords.'beautytap.ShopAction.VerifyOrderReceivedDetails'(products, subtotal, shippingPrice, shippingLabel, paymentMethod, total)