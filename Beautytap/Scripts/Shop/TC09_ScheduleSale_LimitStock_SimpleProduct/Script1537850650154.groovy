import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.junit.Assert.*

import org.junit.Test

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
import org.json.JSONArray
import org.json.JSONObject

//Set variable
String productName = GlobalVariable.SCHEDULE_SIMPLE_PRODUCT
float regularPrice = GlobalVariable.SCHEDULE_REGULAR_PRICE
float salePrice = GlobalVariable.SCHEDULE_SALE_PRICE
int quantity = 6
float subtotalRegular = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, regularPrice)
float subtotalSale = CustomKeywords.'beautytap.ShopAction.calculateTotal'(1, salePrice)
String shippingType = 'free'
String shippingLabel = GlobalVariable.FREE_SHIPPING_LABEL
String shippingTypeSale = 'normal'
String shippingLabelSale = GlobalVariable.SHIPPING_LABEL
float shippingPriceSale = GlobalVariable.SHIPPING_PRICE
float totalSale = Float.parseFloat(String.format("%.2f",subtotalSale + shippingPriceSale))
int schedulTimeout = 300
String limitStockSchedule = 'yes'
JSONArray variation = new JSONArray()
int scheduleDelay = 3
int scheduleDuration = 3
String paymentMethod = 'Amazon Pay'
//Start Test
'Login as Admin'
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL+'/wp-admin')
//CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'("Login")
CustomKeywords.'beautytap.GeneralAction.login'("email", GlobalVariable.ADMIN_USERNAME, GlobalVariable.ADMIN_PASSWORD)
CustomKeywords.'beautytap.AdminAction.selectAdminMenu'("Products", "")
JSONObject datetime = CustomKeywords.'beautytap.ShopAction.generateScheduleDateTime'(GlobalVariable.TIMEZONE, scheduleDelay, scheduleDuration)
String startDate = datetime.get("startdate")
String endDate = datetime.get("enddate")
CustomKeywords.'beautytap.AdminAction.scheduleSaleProduct'(productName, salePrice, variation , startDate, endDate,limitStockSchedule)
WebUI.closeBrowser()
//Verify product BEFORE schedule
'VP1: Verify before schedule, regular price display on Search result'
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductOnSearchResult'(productName, regularPrice, "pink", 0, null)
'VP2: Verify before schedule, regular price display on Product Detail'
CustomKeywords.'beautytap.ShopAction.selectProductOnSearchResult'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, "", regularPrice, "pink", 0, null)
'VP3: Verify before schedule, regular price display on Product List'
CustomKeywords.'beautytap.ShopAction.selectCategory'("What's New", "")
CustomKeywords.'beautytap.ShopAction.findProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductOnProductList'(productName, regularPrice, "pink", 0, null)
'Add product to cart'
CustomKeywords.'beautytap.ShopAction.selectProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)
'Go to cart'
CustomKeywords.'beautytap.ShopAction.goToCart'()
'VP4: Verify before schedule, regular price display in cart'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, "", regularPrice, quantity, subtotalRegular)
'Checkout via amazon pay'
CustomKeywords.'beautytap.ShopAction.loginAmazonPay'("cart", GlobalVariable.AMAZONPAY_EMAIL, GlobalVariable.AMAZONPAY_PASSWORD)
'VP5: Verify before schedule, regular price display in Checkout'
String productArray = '[{"productname":"'+ productName +'","variation":"","quantity":"'+ quantity +'","price":"'+ regularPrice +'"}]'
JSONArray products = new JSONArray(productArray)
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, subtotalRegular, shippingType, shippingLabel, 0, subtotalRegular)
CustomKeywords.'beautytap.ShopAction.checkoutViaAmazonPay'()
'VP6: Verify before schedule, regular price display in Order Received'
CustomKeywords.'beautytap.ShopAction.VerifyOrderReceivedDetails'(products, subtotalRegular, 0, shippingLabel, paymentMethod, subtotalRegular)
'Wait for schedule'
CustomKeywords.'beautytap.ShopAction.waitForSchedule'(GlobalVariable.TIMEZONE, startDate, schedulTimeout)
//Verify product DURING schedule
'VP7: Verify during schedule, SALE price display on Search result'
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductOnSearchResult'(productName, regularPrice, "grey", salePrice, "pink")
'VP8: Verify during schedule, SALE price display on Product Detail'
CustomKeywords.'beautytap.ShopAction.selectProductOnSearchResult'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, "", regularPrice, "grey", salePrice , "pink")
'VP9: Verify during schedule, SALE price display on Product List'
CustomKeywords.'beautytap.ShopAction.selectCategory'("What's New", "")
CustomKeywords.'beautytap.ShopAction.findProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductOnProductList'(productName, regularPrice, "grey", salePrice, "pink")
'VP10: Verify during schedule,Quantity textbox is not displayed due to limit stock affect'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Page_Shop/txt_quantity'), GlobalVariable.SHORT_TIMEOUT, FailureHandling.OPTIONAL)
'Add product to cart'
CustomKeywords.'beautytap.ShopAction.selectProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.addProductToCart'(0)
'Go to cart'
CustomKeywords.'beautytap.ShopAction.goToCart'()
'VP11: Verify during schedule, SALE price display in cart'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, "", salePrice, 1, subtotalSale)
'Checkout via amazon pay'
CustomKeywords.'beautytap.ShopAction.processToCheckout'()
CustomKeywords.'beautytap.ShopAction.loginAmazonPay'("checkout", GlobalVariable.AMAZONPAY_EMAIL, GlobalVariable.AMAZONPAY_PASSWORD)
'VP12: Verify during schedule, SALE price display in Checkout'
String productArraySale = '[{"productname":"'+ productName +'","variation":"","quantity":"1","price":"'+ salePrice +'"}]'
JSONArray productSale = new JSONArray(productArraySale)
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(productSale, subtotalSale, shippingTypeSale, shippingLabelSale, shippingPriceSale, totalSale)
CustomKeywords.'beautytap.ShopAction.checkoutViaAmazonPay'()
'VP13: Verify during schedule, SALE price display in Order Received'
CustomKeywords.'beautytap.ShopAction.VerifyOrderReceivedDetails'(productSale, subtotalSale, shippingPriceSale, shippingLabelSale, paymentMethod, totalSale)
'Wait for schedule'
CustomKeywords.'beautytap.ShopAction.waitForSchedule'(GlobalVariable.TIMEZONE, endDate, schedulTimeout)
//Verify product AFTER schedule
'VP14: Verify AFTER schedule, regular price display on Search result'
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductOnSearchResult'(productName, regularPrice, "pink", 0, null)
'VP15: Verify AFTER schedule, regular price display on Product Detail'
CustomKeywords.'beautytap.ShopAction.selectProductOnSearchResult'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, "", regularPrice, "pink", 0, null)
'VP16: Verify AFTER schedule, regular price display on Product List'
CustomKeywords.'beautytap.ShopAction.selectCategory'("What's New", "")
CustomKeywords.'beautytap.ShopAction.findProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductOnProductList'(productName, regularPrice, "pink", 0, null)
'Add product to cart'
CustomKeywords.'beautytap.ShopAction.selectProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)
'Go to cart'
CustomKeywords.'beautytap.ShopAction.goToCart'()
'VP17: Verify AFTER schedule, regular price display in cart'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, "", regularPrice, quantity, subtotalRegular)
'Checkout via amazon pay'
CustomKeywords.'beautytap.ShopAction.loginAmazonPay'("cart", GlobalVariable.AMAZONPAY_EMAIL, GlobalVariable.AMAZONPAY_PASSWORD)
'VP18: Verify AFTER schedule, regular price display in Checkout'
String productArrayAfter = '[{"productname":"'+ productName +'","variation":"","quantity":"'+ quantity +'","price":"'+ regularPrice +'"}]'
JSONArray productsAfter = new JSONArray(productArrayAfter)
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(productsAfter, subtotalRegular, shippingType, shippingLabel, 0, subtotalRegular)
CustomKeywords.'beautytap.ShopAction.checkoutViaAmazonPay'()
'VP19: Verify AFTER schedule, regular price display in Order Received'
CustomKeywords.'beautytap.ShopAction.VerifyOrderReceivedDetails'(productsAfter, subtotalRegular, 0, shippingLabel, paymentMethod, subtotalRegular)
WebUI.closeBrowser()


