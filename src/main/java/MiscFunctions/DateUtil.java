package MiscFunctions;

import Config.BaseTest;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtil extends BaseTest {

    public static String getCurrentDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        String todayStr = Integer.toString(todayInt);
        return todayStr;
    }
    
    
    public static String getCurrentDayPlus(int days) {
        LocalDate currentDate = LocalDate.now();
        int dayOfWeekPlus = currentDate.getDayOfWeek().plus(days).getValue();
        return Integer.toString(dayOfWeekPlus);
    }
    
    
    public static String getFirstDay() {
        String todayStr = "01";
        return todayStr;
    }

    
    
    public static String getDay(String day) {
        return day;
    }

    public static void clickGivenDay(List<WebElement> elementList, String day) {
        elementList.stream()
            .filter(element -> element.getText().contains(day))
            .findFirst()
            .ifPresent(WebElement::click);
    }

	public static String getTomorrowDate() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String tomorrowDate = tomorrow.format(formatter);
		return tomorrowDate;
	}

	public static String getDayAfterTomorrowDate() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(2);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String tomorrowDate = tomorrow.format(formatter);
		return tomorrowDate;
	}


	public static String getDateInCustomFormat(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		String getDate = dateFormat.format(date);

		return getDate;
	}


	public static String dateFormatConvertor(SimpleDateFormat inputFormat, SimpleDateFormat outputFormat, String dateStr) throws ParseException {
		Date date = inputFormat.parse(dateStr);

		String outputDateStr = outputFormat.format(date);
		System.out.println("Converted Date: " + outputDateStr);
		return outputDateStr;
	}

		public static String TimestampConverter(long timestamp ) {

			long milliseconds = timestamp * 1000L;

			Date date = new Date(milliseconds);

			SimpleDateFormat sdfUtc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdfUtc.setTimeZone(TimeZone.getTimeZone("UTC"));
			String formattedDateUtc = sdfUtc.format(date);

			SimpleDateFormat sdfLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formattedDateLocal = sdfLocal.format(date);

			return formattedDateLocal;
		}

    
	static DateFormat dateFormat = new SimpleDateFormat("MM_dd_HH_mm");
	static Date date1 = new Date();
	public static String date = dateFormat.format(date1);

	static DateFormat dateFormat0 = new SimpleDateFormat("mmss");
	static Date date10 = new Date();
	public static String date0 = dateFormat0.format(date10);
	
	static DateFormat dateFormat5 = new SimpleDateFormat("ss");
	static Date date1000 = new Date();
	public static String date1001 = dateFormat5.format(date1000);
	
	static DateFormat dateFormat50 = new SimpleDateFormat("MM-dd-yyyy");
	static Date date5000 = new Date();
	public static String dateMMDDYYYY = dateFormat50.format(date5000);
	
	static DateFormat dateFormat60 = new SimpleDateFormat("yyyyMMdd");
	static Date date6000 = new Date();
	public static String dateYYYYMMDD = dateFormat60.format(date6000);
	
	static DateFormat dateFormat501 = new SimpleDateFormat("MM/dd/yyyy");
	static Date date50001 = new Date();
	public static String dateMMDDYYYY1 = dateFormat501.format(date50001);
	
	static DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static Date date11 = new Date();
	public static String date100 = dateFormat1.format(date11);
	
	static DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	static Date dateR = new Date();
	public static String dateYMD = dateFormat2.format(dateR);
	
	static DateFormat dateFormat3 = new SimpleDateFormat("HH:mm:ss");
	static Date dateRI = new Date();
	public static String dateRIT = dateFormat3.format(dateRI);
	
	static DateFormat dateFormatM = new SimpleDateFormat("MM");
	static Date dateM = new Date();
	public static String dateMM = dateFormatM.format(dateM);
	
	static DateFormat dateFormatY = new SimpleDateFormat("yyyy");
	static Date dateY = new Date();
	public static String dateYYYY = dateFormatY.format(dateY);


    
}