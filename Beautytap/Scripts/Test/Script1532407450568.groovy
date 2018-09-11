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


//JSONArray products = new JSONArray('[{"productname":"107 Oneoseven Core Flex Cream Essence 50ml","variation":"","quantity":"1","price":"73"},{"productname":"The Face Shop Coca Cola Oil Control Moisture Cushion 2 Choices","variation":"","quantity":"1","price":"35"},{"productname":"The Face Shop Coca Cola Lip Tint 5 Choices","variation":"01 Enjoy Sunshine","quantity":"1","price":"10"}]')
//float subtotal=118
//float discount=5
//float shippingPrice
//String shippingLabel='Free shipping for orders over $60'
//String paymentMethod='Amazon Pay'
//float total =113
//float redeemablePoint= 124.3

//Date date = new Date();
//DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

// Use Madrid's time zone to format the date in
//df.setTimeZone(TimeZone.getTimeZone("UTC"))
//String currentdate =df.format(date)
//Date d=df.parse(currentdate)

//println currentdate
//print d
//date = DateUtils.addMinutes(d, 5) 
//currentdate =df.format(date)
//rintln currentdate 
CustomKeywords.'beautytap.ShopAction.generateScheduleDateTime'("America/Los_Angeles", 0, 5)

