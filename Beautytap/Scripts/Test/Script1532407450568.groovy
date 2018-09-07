import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.Date

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
import com.kms.katalon.core.testobject.ConditionType
import org.apache.commons.lang.StringUtils


//JSONArray products = new JSONArray('[{"productname":"Simple product Not Sale","variation":"","quantity":"1","price":"30.99"}]')
//SIGNUP
//CustomKeywords.'beautytap.GeneralAction.openBeautytap'(GlobalVariable.SITE_URL)
//WebUI.delay(60)
//CustomKeywords.'beautytap.GeneralAction.clickNavigationMenu'("login")
///CustomKeywords.'beautytap.GeneralAction.login'('email', 'mrwhite', 'kgySM$Im')
//CustomKeywords.'beautytap.GeneralAction.selectProfileMenu'('My Rewards')
//CustomKeywords.'beautytap.ShopAction.getRewardHistory'('136120', 'completed')
//CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'("136127", "August 24, 2018","pending", "", "35.00", "1.3", "0.00", "84.50")
//CustomKeywords.'beautytap.ShopAction.VerifyOrderDetailsOnCheckout'(products, 30.99, 60, 2, "normal",'($10.00) - Pantos (est. 10-20 working days delivery including processing)', 10, 38.99)
//TestObject obj=new TestObject();
//obj.addProperty("xpath",ConditionType.EQUALS,"//strong[starts-with(text(),'Status')]/parent::span")
//println WebUI.getText(obj)
//CustomKeywords.'beautytap.ShopAction.VerifyRewardEarned'("BB Boss", 14.50, 1.2, 17.40)
//String t ="September 04, 2018"
//SimpleDateFormat datetime = new SimpleDateFormat("MMMM d, yyyy")
//Date date = datetime.parse(t);
//System.out.println(date);
//System.out.println(datetime.format(date));
float point=60
CustomKeywords.'beautytap.ShopAction.VerifyRewardHistory'("136449",'', "pending", 60, 93.40, 1, (float)(-1*point), 795.60)
//println CustomKeywords.'beautytap.ShopAction.calculateLoyaltyPointValue'(667.8)
//CustomKeywords.'beautytap.ShopAction.applyLoyaltyRewardPoint'(60)

