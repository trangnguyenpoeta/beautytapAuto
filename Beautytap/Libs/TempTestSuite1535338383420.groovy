import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import groovy.lang.MissingPropertyException
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import internal.GlobalVariable as GlobalVariable

Map<String, String> suiteProperties = new HashMap<String, String>();


suiteProperties.put('id', 'Test Suites/TS1')

suiteProperties.put('name', 'TS1')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())



RunConfiguration.setExecutionSettingFile("E:\\BeautytapAutomation\\beautytapAuto\\Beautytap\\Reports\\TS1\\20180827_095303\\execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/TS1', suiteProperties, [new TestCaseBinding('Test Cases/SignUp_Login_ResetPass/TC01_SignUp_Login_ResetPassword_Via_Email', 'Test Cases/SignUp_Login_ResetPass/TC01_SignUp_Login_ResetPassword_Via_Email',  null), new TestCaseBinding('Test Cases/SignUp_Login_ResetPass/TC02_SignUp_Login_ViaFacebookEmail_NewUser_OldUser', 'Test Cases/SignUp_Login_ResetPass/TC02_SignUp_Login_ViaFacebookEmail_NewUser_OldUser',  null), new TestCaseBinding('Test Cases/Shop/TC03_Search_SimpleProduct_Sale_Creditcard_LessThan60_Normal', 'Test Cases/Shop/TC03_Search_SimpleProduct_Sale_Creditcard_LessThan60_Normal',  null), new TestCaseBinding('Test Cases/Shop/TC04_Search_VariationProduct_NotSale_AmazonPay_GreaterThan200', 'Test Cases/Shop/TC04_Search_VariationProduct_NotSale_AmazonPay_GreaterThan200',  null), new TestCaseBinding('Test Cases/Shop/TC05_Browse_SimpleProduct_NotSale_PayPal_GreaterThan60LessThan200', 'Test Cases/Shop/TC05_Browse_SimpleProduct_NotSale_PayPal_GreaterThan60LessThan200',  null), new TestCaseBinding('Test Cases/Shop/TC06_Browse_VariationProduct_Sale_AmazonPayAtCart_LessThan60_EMS', 'Test Cases/Shop/TC06_Browse_VariationProduct_Sale_AmazonPayAtCart_LessThan60_EMS',  null)])
