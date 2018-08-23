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


RunConfiguration.setExecutionSettingFile('C:\\Users\\TRANGN~1\\AppData\\Local\\Temp\\Katalon\\Test Cases\\Shop\\TC05_Browse_SimpleProduct_NotSale_PayPal_GreaterThan60LessThan200\\20180823_141220\\execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCase('Test Cases/Shop/TC05_Browse_SimpleProduct_NotSale_PayPal_GreaterThan60LessThan200', new TestCaseBinding('Test Cases/Shop/TC05_Browse_SimpleProduct_NotSale_PayPal_GreaterThan60LessThan200',[:]), FailureHandling.STOP_ON_FAILURE , false)
    
