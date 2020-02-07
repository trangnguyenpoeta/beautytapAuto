package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


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
Profile production : 30s
Profile staging : 30s</p>
     */
    public static Object TIMEOUT
     
    /**
     * <p>Profile default : 60s
Profile production : 60s
Profile staging : 60s</p>
     */
    public static Object LONG_TIMEOUT
     
    /**
     * <p>Profile default : 5s
Profile production : 5s
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
     
    /**
     * <p></p>
     */
    public static Object USER_DISPLAY_NAME
     
    /**
     * <p></p>
     */
    public static Object USER_EMAIL2
     
    /**
     * <p></p>
     */
    public static Object USER_PASSWORD2
     
    /**
     * <p></p>
     */
    public static Object USER_DISPLAY_NAME2
     
    /**
     * <p></p>
     */
    public static Object SCHEDULE_SIMPLE_PRODUCT
     
    /**
     * <p></p>
     */
    public static Object SCHEDULE_SALE_PRICE
     
    /**
     * <p></p>
     */
    public static Object SCHEDULE_REGULAR_PRICE
     
    /**
     * <p>Profile default : [{&quot;variation&quot;:&quot;VARIATION&quot;,&quot;saleprice&quot;:&quot;SALEPRICE&quot;,&quot;regularprice&quot;:&quot;REGULARPRICE&quot;},{&quot;variation&quot;:&quot;VARIATION&quot;,&quot;saleprice&quot;:&quot;SALEPRICE&quot;,&quot;regularprice&quot;:&quot;REGULARPRICE&quot;}]
Profile production : [{&quot;variation&quot;:&quot;VARIATION&quot;,&quot;saleprice&quot;:&quot;SALEPRICE&quot;,&quot;regularprice&quot;:&quot;REGULARPRICE&quot;},{&quot;variation&quot;:&quot;VARIATION&quot;,&quot;saleprice&quot;:&quot;SALEPRICE&quot;,&quot;regularprice&quot;:&quot;REGULARPRICE&quot;}]</p>
     */
    public static Object SCHEDULE_VARIATION_PRICE
     
    /**
     * <p></p>
     */
    public static Object SCHEDULE_VARIATION_PRODUCT
     
    /**
     * <p>Profile default : PST: America&#47;Los_Angeles; UTC
Profile production : PST: America&#47;Los_Angeles; UTC
Profile staging : PST: America&#47;Los_Angeles; UTC</p>
     */
    public static Object TIMEZONE
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += RunConfiguration.getOverridingParameters()
    
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
            USER_DISPLAY_NAME = selectedVariables['USER_DISPLAY_NAME']
            USER_EMAIL2 = selectedVariables['USER_EMAIL2']
            USER_PASSWORD2 = selectedVariables['USER_PASSWORD2']
            USER_DISPLAY_NAME2 = selectedVariables['USER_DISPLAY_NAME2']
            SCHEDULE_SIMPLE_PRODUCT = selectedVariables['SCHEDULE_SIMPLE_PRODUCT']
            SCHEDULE_SALE_PRICE = selectedVariables['SCHEDULE_SALE_PRICE']
            SCHEDULE_REGULAR_PRICE = selectedVariables['SCHEDULE_REGULAR_PRICE']
            SCHEDULE_VARIATION_PRICE = selectedVariables['SCHEDULE_VARIATION_PRICE']
            SCHEDULE_VARIATION_PRODUCT = selectedVariables['SCHEDULE_VARIATION_PRODUCT']
            TIMEZONE = selectedVariables['TIMEZONE']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
