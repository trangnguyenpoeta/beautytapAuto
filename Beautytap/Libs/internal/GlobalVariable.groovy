package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object SITE_URL
     
    /**
     * <p></p>
     */
    public static Object FB_EMAIL
     
    /**
     * <p></p>
     */
    public static Object FB_PASSWORD
     
    /**
     * <p></p>
     */
    public static Object ADMIN_USERNAME
     
    /**
     * <p></p>
     */
    public static Object ADMIN_PASSWORD
     
    /**
     * <p>Profile default : 30s
Profile staging : 30s</p>
     */
    public static Object TIMEOUT
     
    /**
     * <p>Profile default : 60s
Profile staging : 60s</p>
     */
    public static Object LONG_TIMEOUT
     
    /**
     * <p>Profile default : 5s
Profile staging : 5s</p>
     */
    public static Object SHORT_TIMEOUT
     
    /**
     * <p></p>
     */
    public static Object SITE_TITLE
     
    /**
     * <p></p>
     */
    public static Object FB_PHONE
     
    /**
     * <p></p>
     */
    public static Object FB_PHONE_PASSWORD
     
    /**
     * <p></p>
     */
    public static Object SIMPLE_PRODUCT
     
    /**
     * <p></p>
     */
    public static Object SIMPLE_PRODUCT_PRICE
     
    /**
     * <p></p>
     */
    public static Object VARIATION_PRODUCT
     
    /**
     * <p></p>
     */
    public static Object VARIATION_NAME1
     
    /**
     * <p></p>
     */
    public static Object VARIATION_PRICE1
     
    /**
     * <p></p>
     */
    public static Object SIMPLE_SALE_PRODUCT
     
    /**
     * <p></p>
     */
    public static Object SIMPLE_SALE_REGULAR_PRICE
     
    /**
     * <p></p>
     */
    public static Object SIMPLE_SALE_PRICE
     
    /**
     * <p></p>
     */
    public static Object VARIATION_SALE_PRODUCT
     
    /**
     * <p></p>
     */
    public static Object VARIATION_SALE_NAME1
     
    /**
     * <p></p>
     */
    public static Object VARIATION_SALE_PRICE1
     
    /**
     * <p></p>
     */
    public static Object VARIATION_SALE_REGULAR_PRICE1
     
    /**
     * <p></p>
     */
    public static Object PAYPAL_EMAIL
     
    /**
     * <p></p>
     */
    public static Object PAYPAL_PASSWORD
     
    /**
     * <p></p>
     */
    public static Object AMAZONPAY_EMAIL
     
    /**
     * <p></p>
     */
    public static Object AMAZONPAY_PASSWORD
     
    /**
     * <p></p>
     */
    public static Object CREDITCARD_NUMBER
     
    /**
     * <p></p>
     */
    public static Object CARD_TYPE
     
    /**
     * <p></p>
     */
    public static Object CARD_EXPIRATION_MONTH
     
    /**
     * <p></p>
     */
    public static Object CARD_CVV
     
    /**
     * <p></p>
     */
    public static Object SHIPPING_LABEL
     
    /**
     * <p></p>
     */
    public static Object SHIPPING_PRICE
     
    /**
     * <p></p>
     */
    public static Object FREE_SHIPPING_LABEL
     
    /**
     * <p></p>
     */
    public static Object FREE_EMS_SHIPPING_LABEL
     
    /**
     * <p></p>
     */
    public static Object CARD_EXPIRATION_YEAR
     
    /**
     * <p></p>
     */
    public static Object EMS_SHIPPING_LABEL
     
    /**
     * <p></p>
     */
    public static Object EMS_SHIPPING_PRICE
     
    /**
     * <p></p>
     */
    public static Object USER_EMAIL
     
    /**
     * <p></p>
     */
    public static Object USER_PASSWORD
     

    static {
        def allVariables = [:]        
        allVariables.put('default', ['SITE_URL' : 'https://stage.beautytap.com', 'FB_EMAIL' : 'g5.posiba@gmail.com', 'FB_PASSWORD' : 'posiba123', 'ADMIN_USERNAME' : 'mrwhite', 'ADMIN_PASSWORD' : 'kgySM$Im', 'TIMEOUT' : 30, 'LONG_TIMEOUT' : 60, 'SHORT_TIMEOUT' : 5, 'SITE_TITLE' : '[Korean Beauty Products: Shop Korean Skin Care, Makeup + Cosmetics]', 'FB_PHONE' : '01649902126', 'FB_PHONE_PASSWORD' : 'vina12345', 'SIMPLE_PRODUCT' : 'La Muse Correct Care Complete Cushion SPF50+', 'SIMPLE_PRODUCT_PRICE' : 31.80, 'VARIATION_PRODUCT' : 'MeloMELI Unicorn Heart Lake Cushion — 2 Choices', 'VARIATION_NAME1' : 'Vanilla Beige', 'VARIATION_PRICE1' : 26.70, 'SIMPLE_SALE_PRODUCT' : 'Innisfree Perfect UV Protection Cream SPF50+/PA+++ (Long-Lasting For Oily Skin) 50ml', 'SIMPLE_SALE_REGULAR_PRICE' : 12.50, 'SIMPLE_SALE_PRICE' : 11.99, 'VARIATION_SALE_PRODUCT' : 'Innisfree Eye Contouring Stick Round Shape 0.7g', 'VARIATION_SALE_NAME1' : '5', 'VARIATION_SALE_PRICE1' : 4.95, 'VARIATION_SALE_REGULAR_PRICE1' : 6.50, 'PAYPAL_EMAIL' : 'g1.posiba@gmail.com', 'PAYPAL_PASSWORD' : 'Tr@ng99%', 'AMAZONPAY_EMAIL' : 'amazontest1503@yopmail.com', 'AMAZONPAY_PASSWORD' : '123456', 'CREDITCARD_NUMBER' : '4032036248443549', 'CARD_TYPE' : 'Visa', 'CARD_EXPIRATION_MONTH' : '07', 'CARD_CVV' : '123', 'SHIPPING_LABEL' : '($12.50) Pantos (est. 14-25 working days including processing)', 'SHIPPING_PRICE' : 12.50, 'FREE_SHIPPING_LABEL' : 'Free shipping for orders over $60', 'FREE_EMS_SHIPPING_LABEL' : 'Free EMS shipping (over $200)', 'CARD_EXPIRATION_YEAR' : '2023', 'EMS_SHIPPING_LABEL' : '($25) - EMS Express shipping (est. 10-14 working days delivery including processing)', 'EMS_SHIPPING_PRICE' : 25, 'USER_EMAIL' : 'trang2408@mailinator.com', 'USER_PASSWORD' : '123456'])
        allVariables.put('staging', allVariables['default'] + ['SITE_URL' : 'https://staging.beautytap.com', 'FB_EMAIL' : 'g5.posiba@gmail.com', 'FB_PASSWORD' : 'posiba123', 'ADMIN_USERNAME' : 'mrwhite', 'ADMIN_PASSWORD' : 'kgySM$Im', 'TIMEOUT' : 30, 'LONG_TIMEOUT' : 60, 'SHORT_TIMEOUT' : 5, 'SITE_TITLE' : '[Korean Beauty Products]', 'FB_PHONE' : '01649902126', 'FB_PHONE_PASSWORD' : 'vina12345', 'SIMPLE_PRODUCT' : 'La Muse Correct Care Complete Cushion SPF50+/PA+++', 'SIMPLE_PRODUCT_PRICE' : 31.80, 'VARIATION_PRODUCT' : 'MeloMELI Unicorn Heart Lake Cushion — 2 Choices', 'VARIATION_NAME1' : 'Vanilla Beige', 'VARIATION_PRICE1' : 26.70, 'SIMPLE_SALE_PRODUCT' : 'Innisfree Perfect UV Protection Cream SPF50+/PA+++ (Long-Lasting For Oily Skin) 50ml', 'SIMPLE_SALE_REGULAR_PRICE' : 12.5, 'SIMPLE_SALE_PRICE' : 11.99, 'VARIATION_SALE_PRODUCT' : 'Innisfree Eye Contouring Stick Round Shape 0.7g', 'VARIATION_SALE_NAME1' : '5', 'VARIATION_SALE_PRICE1' : 4.95, 'VARIATION_SALE_REGULAR_PRICE1' : 6.50, 'PAYPAL_EMAIL' : 'g1.posiba@gmail.com', 'PAYPAL_PASSWORD' : 'Tr@ng99%', 'AMAZONPAY_EMAIL' : 'amazontest1503@yopmail.com', 'AMAZONPAY_PASSWORD' : '123456', 'CREDITCARD_NUMBER' : '4032036248443549', 'CARD_TYPE' : 'Visa', 'CARD_EXPIRATION_MONTH' : '07', 'CARD_CVV' : '123', 'SHIPPING_LABEL' : '($12.50) Pantos (est. 14-25 working days including processing)', 'SHIPPING_PRICE' : 12.50, 'FREE_SHIPPING_LABEL' : 'Free shipping for orders over $60', 'FREE_EMS_SHIPPING_LABEL' : 'Free EMS shipping (over $200)', 'CARD_EXPIRATION_YEAR' : '2023', 'EMS_SHIPPING_LABEL' : '($25) - EMS Express shipping (est. 10-14 working days delivery including processing)', 'EMS_SHIPPING_PRICE' : 25, 'USER_EMAIL' : 'trang2408@mailinator.com', 'USER_PASSWORD' : '123456'])
        
        String profileName = RunConfiguration.getExecutionProfile()
        
        def selectedVariables = allVariables[profileName]
        SITE_URL = selectedVariables['SITE_URL']
        FB_EMAIL = selectedVariables['FB_EMAIL']
        FB_PASSWORD = selectedVariables['FB_PASSWORD']
        ADMIN_USERNAME = selectedVariables['ADMIN_USERNAME']
        ADMIN_PASSWORD = selectedVariables['ADMIN_PASSWORD']
        TIMEOUT = selectedVariables['TIMEOUT']
        LONG_TIMEOUT = selectedVariables['LONG_TIMEOUT']
        SHORT_TIMEOUT = selectedVariables['SHORT_TIMEOUT']
        SITE_TITLE = selectedVariables['SITE_TITLE']
        FB_PHONE = selectedVariables['FB_PHONE']
        FB_PHONE_PASSWORD = selectedVariables['FB_PHONE_PASSWORD']
        SIMPLE_PRODUCT = selectedVariables['SIMPLE_PRODUCT']
        SIMPLE_PRODUCT_PRICE = selectedVariables['SIMPLE_PRODUCT_PRICE']
        VARIATION_PRODUCT = selectedVariables['VARIATION_PRODUCT']
        VARIATION_NAME1 = selectedVariables['VARIATION_NAME1']
        VARIATION_PRICE1 = selectedVariables['VARIATION_PRICE1']
        SIMPLE_SALE_PRODUCT = selectedVariables['SIMPLE_SALE_PRODUCT']
        SIMPLE_SALE_REGULAR_PRICE = selectedVariables['SIMPLE_SALE_REGULAR_PRICE']
        SIMPLE_SALE_PRICE = selectedVariables['SIMPLE_SALE_PRICE']
        VARIATION_SALE_PRODUCT = selectedVariables['VARIATION_SALE_PRODUCT']
        VARIATION_SALE_NAME1 = selectedVariables['VARIATION_SALE_NAME1']
        VARIATION_SALE_PRICE1 = selectedVariables['VARIATION_SALE_PRICE1']
        VARIATION_SALE_REGULAR_PRICE1 = selectedVariables['VARIATION_SALE_REGULAR_PRICE1']
        PAYPAL_EMAIL = selectedVariables['PAYPAL_EMAIL']
        PAYPAL_PASSWORD = selectedVariables['PAYPAL_PASSWORD']
        AMAZONPAY_EMAIL = selectedVariables['AMAZONPAY_EMAIL']
        AMAZONPAY_PASSWORD = selectedVariables['AMAZONPAY_PASSWORD']
        CREDITCARD_NUMBER = selectedVariables['CREDITCARD_NUMBER']
        CARD_TYPE = selectedVariables['CARD_TYPE']
        CARD_EXPIRATION_MONTH = selectedVariables['CARD_EXPIRATION_MONTH']
        CARD_CVV = selectedVariables['CARD_CVV']
        SHIPPING_LABEL = selectedVariables['SHIPPING_LABEL']
        SHIPPING_PRICE = selectedVariables['SHIPPING_PRICE']
        FREE_SHIPPING_LABEL = selectedVariables['FREE_SHIPPING_LABEL']
        FREE_EMS_SHIPPING_LABEL = selectedVariables['FREE_EMS_SHIPPING_LABEL']
        CARD_EXPIRATION_YEAR = selectedVariables['CARD_EXPIRATION_YEAR']
        EMS_SHIPPING_LABEL = selectedVariables['EMS_SHIPPING_LABEL']
        EMS_SHIPPING_PRICE = selectedVariables['EMS_SHIPPING_PRICE']
        USER_EMAIL = selectedVariables['USER_EMAIL']
        USER_PASSWORD = selectedVariables['USER_PASSWORD']
        
    }
}
