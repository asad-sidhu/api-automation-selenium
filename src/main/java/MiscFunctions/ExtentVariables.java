package MiscFunctions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentVariables {

	public static ExtentTest test;
	public static ExtentTest node;
	public static ExtentTest preconditions; 
	public static ExtentTest steps; 
	public static ExtentTest results; 
	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	
	public static String fileDownloadPath = System.getProperty("user.home")+"\\Downloads";
	public static String fileAbsolutePath = System.getProperty("user.dir")+"\\";
	
	public static String PreConditions = "Pre-Conditions";
	public static String Steps = "Steps";
	public static String Results = "Results";
	
}
