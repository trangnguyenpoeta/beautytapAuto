import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.logging.KeywordLogger
import groovy.lang.MissingPropertyException
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.contribution.WebUiDriverCleaner
import com.kms.katalon.core.mobile.contribution.MobileDriverCleaner


DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())


RunConfiguration.setExecutionSettingFile('C:\\Users\\TRANGN~1\\AppData\\Local\\Temp\\Katalon\\Test Cases\\Shop\\TC03_Search_SimpleProduct_NotSale_Creditcard_LessThan60\\20180822_110952\\execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCaseRawScript(
'''import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
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
not_run: String productName = GlobalVariable.SIMPLE_PRODUCT

not_run: float price = GlobalVariable.SIMPLE_PRODUCT_PRICE

not_run: String r_string = new Math().random().toString().substring(2, 8)

not_run: String email = ('auto' + r_string) + '@mailinator.com'

not_run: String orderNote = ('auto' + r_string) + 'Order'

not_run: int currentNumberItemInCart

not_run: int quantity = 1

not_run: float subtotal = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, price)

not_run: float total = subtotal + GlobalVariable.SHIPPING_PRICE

not_run: total = CustomKeywords.'beautytap.ShopAction.calculateTotal'(1, total)

not_run: JSONArray products = new JSONArray(((((('[{"productname":"' + GlobalVariable.SIMPLE_PRODUCT) + '","variation":"","quantity":"') + 
quantity) + '","price":"') + GlobalVariable.SIMPLE_PRODUCT_PRICE) + '"}]')

not_run: JSONObject billingInformation = new JSONObject(('{"firstname":"Test","lastname":"Automation","country":"United States (US)","address":"123 Testing","city":"New York","state":"New York","zip":"90012","email":"' + 
email) + '"}')

not_run: String shippingLabel = GlobalVariable.SHIPPING_LABEL

not_run: float shippingPrice = GlobalVariable.SHIPPING_PRICE

String shippingType = 'normal'

String paymentMethod = 'Credit Card Payment'

//---------------------------------------------------------
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)

CustomKeywords.'beautytap.ShopAction.globalSearch'(productName)

'VP1: Verify product display in search result panel with regular price'
CustomKeywords.'beautytap.ShopAction.VerifyProductOnSearchResult'(productName, price, 'pink', 0, null)

CustomKeywords.'beautytap.ShopAction.selectProductOnSearchResult'(productName)

'VP2: Verify product detail page display with regular price'
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, '', price, 'pink', 0, null)

currentNumberItemInCart = CustomKeywords.'beautytap.ShopAction.getNumberItemInCart'()

CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)

'VP3:Verify message display: “PRODUCT_NAME” has been added to your cart.'
CustomKeywords.'beautytap.ShopAction.VerifyNumberItemInCart'(currentNumberItemInCart + quantity)

CustomKeywords.'beautytap.ShopAction.VerifyProductIsAddedToCart'(productName)

CustomKeywords.'beautytap.ShopAction.goToCart'()

'VP4:Verify product is added to cart with correct price and quantity'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName, '', price, quantity, subtotal)

CustomKeywords.'beautytap.ShopAction.processToCheckout'()

CustomKeywords.'beautytap.ShopAction.fillCustomerInformation'(billingInformation, 'no', '', '', orderNote)

'VP5: Verify order details on checkout page'
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, subtotal, shippingType, shippingLabel, shippingPrice, 
    total)

'Checkout by credit card'
CustomKeywords.'beautytap.ShopAction.checkoutViaCreditCard'(GlobalVariable.CREDITCARD_NUMBER, GlobalVariable.CARD_TYPE, 
    GlobalVariable.CARD_EXPIRATION_MONTH, GlobalVariable.CARD_EXPIRATION_YEAR, GlobalVariable.CARD_CVV)

'VP6: Verify order details on order received Page'
CustomKeywords.'beautytap.ShopAction.VerifyOrderReceivedDetails'(products, subtotal, shippingPrice, shippingLabel, paymentMethod, 
    total)

''', 'Test Cases/Shop/TC03_Search_SimpleProduct_NotSale_Creditcard_LessThan60', new TestCaseBinding('Test Cases/Shop/TC03_Search_SimpleProduct_NotSale_Creditcard_LessThan60',[:]), FailureHandling.STOP_ON_FAILURE , false)
    
