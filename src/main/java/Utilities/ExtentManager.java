package Utilities;


import com.aventstack.extentreports.ExtentReports;
public class ExtentManager {
	
	private static ExtentReports extent;
	
	
	
	public static ExtentReports getInstance(){
		
		if(extent==null){
			
			
	//		extent = new ExtentReports(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\extent.html",true, "");
	//		extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
			
		}
		
		return extent;
		
	}

}
