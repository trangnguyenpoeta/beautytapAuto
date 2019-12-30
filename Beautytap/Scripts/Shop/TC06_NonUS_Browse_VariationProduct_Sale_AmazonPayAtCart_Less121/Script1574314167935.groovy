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
String productName = GlobalVariable.VARIATION_SALE_PRODUCT
float saleprice = GlobalVariable.VARIATION_SALE_PRICE1
float redularprice = GlobalVariable.VARIATION_SALE_REGULAR_PRICE1
String variation = GlobalVariable.VARIATION_SALE_NAME1
String r_string = new Math().random().toString().substring(2, 8)
String email = 'beauty' + r_string + '@mailinator.com'
int currentNumberItemInCart
int quantity = 5
String shippingLabel = GlobalVariable.SHIPPING_LABEL
float shippingPrice = GlobalVariable.SHIPPING_PRICE
float subtotal = CustomKeywords.'beautytap.ShopAction.calculateTotal'(quantity, saleprice)
float total=subtotal+shippingPrice
total= CustomKeywords.'beautytap.ShopAction.calculateTotal'(1, total)
JSONArray products = new JSONArray('[{"productname":"'+ productName +'","variation":"'+variation+'","quantity":"'+ quantity +'","price":"'+ saleprice +'"}]')
JSONArray shippingArray = new JSONArray('[{"shippingType":"normal","shippingMethod":"Shipping from Korea","shippingPrice":"'+ GlobalVariable.SHIPPING_PRICE +'","shippingLabel":"'+GlobalVariable.SHIPPING_LABEL+'"}]')
String shippingType = 'normal'
String paymentMethod ='Amazon Pay'
String category = "Brands"
String subcategory = "Innisfree"
String amazonAddress = 'Trang N. 444 cmt8, HCM, 70000, Viet Nam'
//---------------------------------------------------------
CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
CustomKeywords.'beautytap.ShopAction.selectCategory'("Shop",category, subcategory)
CustomKeywords.'beautytap.ShopAction.findProductOnProductList'(productName)
'VP1: Verify product display in Product List'
CustomKeywords.'beautytap.ShopAction.VerifyProductOnProductList'(productName, saleprice, "pink", redularprice, "pink")
CustomKeywords.'beautytap.ShopAction.selectProductOnProductList'(productName)
CustomKeywords.'beautytap.ShopAction.selectProductVariation'(variation)
'VP2: Verify product detail page display'
CustomKeywords.'beautytap.ShopAction.VerifyProductDetails'(productName, variation, redularprice, "grey", saleprice, "pink")
currentNumberItemInCart = CustomKeywords.'beautytap.ShopAction.getNumberItemInCart'()
CustomKeywords.'beautytap.ShopAction.addProductToCart'(quantity)
'VP3:Verify product is added to card: cart number increase and message display “PRODUCT_NAME” has been added to your cart.'
CustomKeywords.'beautytap.ShopAction.VerifyNumberItemInCart'(currentNumberItemInCart+quantity)
CustomKeywords.'beautytap.ShopAction.VerifyProductIsAddedToCart'(productName)
CustomKeywords.'beautytap.ShopAction.goToCart'()
'VP4:Verify product is added to cart with correct price and quantity'
CustomKeywords.'beautytap.ShopAction.VerifyProductInCart'(productName,variation, saleprice, quantity, subtotal)
'Login amazonPay'
CustomKeywords.'beautytap.ShopAction.loginAmazonPay'('cart', GlobalVariable.AMAZONPAY_EMAIL, GlobalVariable.AMAZONPAY_PASSWORD)
CustomKeywords.'beautytap.ShopAction.selectAmazonPayAddress'(amazonAddress)
'VP5: Verify order details on checkout page'
CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, subtotal, shippingArray, total)
'Checkout via amazonPay'
CustomKeywords.'beautytap.ShopAction.checkoutViaAmazonPay'()
WebUI.closeBrowser()