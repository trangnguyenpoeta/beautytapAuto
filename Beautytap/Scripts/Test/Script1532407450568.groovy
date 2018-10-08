import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.Date
import java.util.Calendar
import java.text.SimpleDateFormat
import java.text.DateFormat
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
import com.sun.corba.se.impl.presentation.rmi.InvocationHandlerFactoryImpl.CustomCompositeInvocationHandlerImpl
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.TestObject

import org.json.JSONArray
import org.json.JSONObject
import org.junit.After

import com.kms.katalon.core.testobject.ConditionType
import org.apache.commons.lang.StringUtils
import org.apache.commons.lang3.time.DateUtils


/*//Set variable
String productName = GlobalVariable.VARIATION_PRODUCT
float price = GlobalVariable.VARIATION_PRICE1
String variation = GlobalVariable.VARIATION_NAME1
String r_string = new Math().random().toString().substring(2, 8)
String email = 'auto' + r_string + '@mailinator.com'
String orderNote = 'auto' + r_string + 'Order'
int currentNumberItemInCart
int quantity = 8
float subtotal = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, price)
float total=subtotal
total= CustomKeywords.'beautytap.ShopAction.calculateTotal'(1, total)
JSONArray products = new JSONArray('[{"productname":"'+ productName +'","variation":"'+variation+'","quantity":"'+ quantity +'","price":"'+ price +'"}]')
JSONObject billingInformation =new JSONObject('{"firstname":"Test","lastname":"Automation","country":"United States (US)","address":"123 Testing","city":"New York","state":"New York","zip":"90012","email":"'+email+'"}')
String shippingLabel = GlobalVariable.FREE_EMS_SHIPPING_LABEL
float shippingPrice = 0
String shippingType = 'freeEMS'
String paymentMethod ='Amazon Pay'
//---------------------------------------------------------
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)*/
//TestObject obj_product = new TestObject()
//obj_product.addProperty("xpath",ConditionType.EQUALS,"//div[starts-with(text(),'Results from Variation')]/following::h3/a[@class='asp_res_url']")
//println WebUI.getText(obj_product)
CustomKeywords.'beautytap.ShopAction.selectSearchResult'('MeloMELI Unicorn Heart Lake Cushion — 2 Choices')
