package util;
import java.text.DateFormatSymbols;

import com.Model.Power_Consumption;

public class CalcUtility {
	public static int[] calculateUsedUnits(int currentReading, String actno, String date) {
		
		// getReading[0] - carry the state of the database, getReading[1] - carry the previous reading value
		int[] getReading = Power_Consumption.readPreviousReading(date, actno);
		
		if(getReading[0]<0) {
			return getReading;
		}
		
		getReading[1] =  currentReading - getReading[1];
		
		return getReading;
	}
	
	
	public static String getMonthForInt(int num) {
	    String month = "wrong";
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (num >= 0 && num <= 12) {
	        month = months[num-1];
	    }
	    return month;
	}
	
}
