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
import org.json.JSONArray
import org.json.JSONObject

//Set variable
String productName = GlobalVariable.SCHEDULE_VARIATION_PRODUCT
String jsonArrayVariation = GlobalVariable.SCHEDULE_VARIATION_PRICE
String shippingType = 'freeEMS'
String shippingLabel = GlobalVariable.FREE_EMS_SHIPPING_LABEL
String shippingTypeSale = 'free'
String shippingLabelSale = GlobalVariable.FREE_SHIPPING_LABEL
int schedulTimeout = 300
String limitStockSchedule = 'yes'
JSONArray variation = new JSONArray(jsonArrayVariation)
int quantity =2
JSONObject obj_variation1 = variation.get(0)
JSONObject obj_variation2 = variation.get(1)
String variationName1 = obj_variation1.get("variation")
float regularPrice1 = Float.parseFloat(obj_variation1.get("regularprice"))
float salePrice1 = Float.parseFloat(obj_variation1.get("saleprice"))
float subtotalRegular1 = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, regularPrice1)
float subtotalSale1 = CustomKeywords.'beautytap.ShopAction.calculateTotal'(1, salePrice1)
String variationName2 = obj_variation2.get("variation")
float regularPrice2 = Float.parseFloat(obj_variation2.get("regularprice"))
float salePrice2 = Float.parseFloat(obj_variation2.get("saleprice"))
float subtotalRegular2 = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, regularPrice2)
float subtotalSale2 = CustomKeywords.'beautytap.ShopAction.calculateTotal'(1, salePrice2)
float sumRegularSubTotal =Float.parseFloat(String.format("%.2f", subtotalRegular1+subtotalRegular2))
float sumSaleSubTotal =Float.parseFloat(String.format("%.2f", subtotalSale1+subtotalSale2))
int scheduleDelay = 4
int scheduleDuration = 4
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
CustomKeywords.'beautytap.AdminAction.scheduleSaleProduct'(productName, 0, variation , startDate, endDate,limitStockSchedule)
WebUI.closeBrowser()
//Verify product BEFORE schedule
'VP1: Verify before schedule, regular price display on Search result'
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)
'VP2: Verify before schedule, regular price display on Product Detail'
CustomKeywords.'beautytap.ShopAction.selectSearchResult'(productName)
CustomKeywords.'beautytap.ShopAction.selectProductVariation'(variationName1)
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, variationName1, regularPrice1, "pink", 0, null)
CustomKeywords.'beautytap.ShopAction.selectProductVariation'(variationName2)
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, variationName2, regularPrice2, "pink", 0, null)
'VP3: Verify before schedule, regular price display on Product List'
WebUI.navigateToUrl(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.selectCategory'("Skincare", "Sensitive Skin")
CustomKeywords.'beautytap.ShopAction.findProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductOnProductList'(productName, regularPrice2, "pink", 0, null)
'Add product to cart'
CustomKeywords.'beautytap.ShopAction.selectProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.selectProductVariation'(variationName1)
CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)
CustomKeywords.'beautytap.ShopAction.selectProductVariation'(variationName2)
CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)

'Go to cart'
CustomKeywords.'beautytap.ShopAction.goToCart'()
'VP4: Verify before schedule, regular price display in cart'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, variationName1, regularPrice1, quantity, subtotalRegular1)
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, variationName2, regularPrice2, quantity, subtotalRegular2)
'Checkout via amazon pay'
CustomKeywords.'beautytap.ShopAction.loginAmazonPay'("cart", GlobalVariable.AMAZONPAY_EMAIL, GlobalVariable.AMAZONPAY_PASSWORD)
'VP5: Verify before schedule, regular price display in Checkout'
String productArray = '[{"productname":"'+ productName +'","variation":"'+ variationName1 +'","quantity":"'+ quantity +'","price":"'+ regularPrice1 +'"},{"productname":"'+ productName +'","variation":"'+ variationName2 +'","quantity":"'+ quantity +'","price":"'+ regularPrice2 +'"}]'
JSONArray products = new JSONArray(productArray)
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, sumRegularSubTotal, shippingType, shippingLabel, 0, sumRegularSubTotal)
CustomKeywords.'beautytap.ShopAction.checkoutViaAmazonPay'()
'VP6: Verify before schedule, regular price display in Order Received'
CustomKeywords.'beautytap.ShopAction.VerifyOrderReceivedDetails'(products, sumRegularSubTotal, 0, shippingLabel, paymentMethod, sumRegularSubTotal)
'Wait for schedule'
CustomKeywords.'beautytap.ShopAction.waitForSchedule'(GlobalVariable.TIMEZONE, startDate, schedulTimeout)
//Verify product DURING schedule
'VP7: Verify during schedule, SALE price display on Search result'
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)

'VP8: Verify during schedule, SALE price display on Product Detail'
CustomKeywords.'beautytap.ShopAction.selectSearchResult'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, variationName1, regularPrice1, "grey", salePrice1 , "pink")
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, variationName2, regularPrice2, "grey", salePrice2 , "pink")
'VP9: Verify during schedule, SALE price display on Product List'
WebUI.navigateToUrl(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.selectCategory'("Skincare", "Sensitive Skin")
CustomKeywords.'beautytap.ShopAction.findProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductOnProductList'(productName, salePrice2, "pink", regularPrice1, "pink")
'VP10: Verify during schedule,Quantity textbox is not displayed due to limit stock affect'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Page_Shop/txt_quantity'), GlobalVariable.SHORT_TIMEOUT, FailureHandling.OPTIONAL)
'Add product to cart'
CustomKeywords.'beautytap.ShopAction.selectProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.selectProductVariation'(variationName1)
CustomKeywords.'beautytap.ShopAction.addProductToCart'(0)
CustomKeywords.'beautytap.ShopAction.selectProductVariation'(variationName2)
CustomKeywords.'beautytap.ShopAction.addProductToCart'(0)
CustomKeywords.'beautytap.ShopAction.VerifyProductIsAddedToCart'(productName)
'Go to cart'
CustomKeywords.'beautytap.ShopAction.goToCart'()
'VP11: Verify during schedule, SALE price display in cart'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, variationName1, salePrice1, quantity, subtotalSale1)
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, variationName2, salePrice2, quantity, subtotalSale2)
'Checkout via amazon pay'
CustomKeywords.'beautytap.ShopAction.processToCheckout'()
CustomKeywords.'beautytap.ShopAction.loginAmazonPay'("checkout", GlobalVariable.AMAZONPAY_EMAIL, GlobalVariable.AMAZONPAY_PASSWORD)
'VP12: Verify during schedule, SALE price display in Checkout'
String productArraySale = '[{"productname":"'+ productName +'","variation":"'+ variationName1 +'","quantity":"1","price":"'+ salePrice1 +'"},{"productname":"'+ productName +'","variation":"'+ variationName2 +'","quantity":"1","price":"'+ salePrice2 +'"}]'
JSONArray productSale = new JSONArray(productArraySale)
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(productSale, sumSaleSubTotal, shippingTypeSale, shippingLabelSale, 0, sumSaleSubTotal)
CustomKeywords.'beautytap.ShopAction.checkoutViaAmazonPay'()
'VP13: Verify during schedule, SALE price display in Order Received'
CustomKeywords.'beautytap.ShopAction.VerifyOrderReceivedDetails'(productSale, sumSaleSubTotal, 0, shippingLabelSale, paymentMethod, sumSaleSubTotal)
'Wait for schedule'
CustomKeywords.'beautytap.ShopAction.waitForSchedule'(GlobalVariable.TIMEZONE, endDate, schedulTimeout)
//Verify product AFTER schedule
'VP14: Verify AFTER schedule, regular price display on Search result'
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)
'VP15: Verify AFTER schedule, regular price display on Product Detail'
CustomKeywords.'beautytap.ShopAction.selectSearchResult'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, variationName1, regularPrice1, "pink", 0, null)
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, variationName2, regularPrice2, "pink", 0, null)
'VP16: Verify AFTER schedule, regular price display on Product List'
WebUI.navigateToUrl(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.selectCategory'("Skincare", "Sensitive Skin")
CustomKeywords.'beautytap.ShopAction.findProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.VerifyProductOnProductList'(productName, regularPrice1, "pink", 0, null)
'Add product to cart'
CustomKeywords.'beautytap.ShopAction.selectProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.selectProductVariation'(variationName1)
CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)
CustomKeywords.'beautytap.ShopAction.selectProductVariation'(variationName2)
CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)

'Go to cart'
CustomKeywords.'beautytap.ShopAction.goToCart'()
'VP17: Verify AFTER schedule, regular price display in cart'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, variationName1, regularPrice1, quantity, subtotalRegular1)
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, variationName2, regularPrice2, quantity, subtotalRegular2)
'Checkout via amazon pay'
CustomKeywords.'beautytap.ShopAction.loginAmazonPay'("cart", GlobalVariable.AMAZONPAY_EMAIL, GlobalVariable.AMAZONPAY_PASSWORD)
'VP18: Verify AFTER schedule, regular price display in Checkout'
String productArrayAfter = '[{"productname":"'+ productName +'","variation":"'+ variationName1 +'","quantity":"'+ quantity +'","price":"'+ regularPrice1 +'"},{"productname":"'+ productName +'","variation":"'+ variationName2 +'","quantity":"'+ quantity +'","price":"'+ regularPrice2 +'"}]'
JSONArray productsAfter = new JSONArray(productArrayAfter)
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(productsAfter, sumRegularSubTotal, shippingType, shippingLabel, 0, sumRegularSubTotal)
CustomKeywords.'beautytap.ShopAction.checkoutViaAmazonPay'()
'VP19: Verify AFTER schedule, regular price display in Order Received'
CustomKeywords.'beautytap.ShopAction.VerifyOrderReceivedDetails'(productsAfter, sumRegularSubTotal, 0, shippingLabel, paymentMethod, sumRegularSubTotal)
WebUI.closeBrowser()