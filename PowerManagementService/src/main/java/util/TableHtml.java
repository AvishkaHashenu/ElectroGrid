package util;

public class TableHtml {
	public static String getHtml() {
		
		// buttons
		/*String output += "<td><input name='btnUpdate' " + " type='button' value='Update'></td>"
				+ "<td><form method='post' action='dbconcheck.jsp.jsp'>" + "<input name='btnRemove' "
				+ " type='submit' value='Remove'>" + "<input name='itemID' type='hidden' " + " value='" + itemID
				+ "'>" + "</form></td></tr>"; */
		
		return  "<table border='1'>"
				+ "<tr>"
					+ "<th>Record ID</th>" 
					+ "<th>Account Number</th>"
					+ "<th>Current Reading</th>"
					+ "<th>Used Units</th>"
					+ "<th>Date</th>"
					+ "<th>MM,YY</th>"
					+ "<th>Type</th>"
					+ "<th>ReaderID</th>"
					+ "<th>UserID</th></tr>";
		
	}
	public static String getHtmlCapacity() {
		
		
		return  "<table border='1'>"
				+ "<tr>"
					+ "<th>Capacity ID</th>" 
					+ "<th>Date</th>"
					+ "<th>Source Type</th>"
					+ "<th>No of Units</th>"
					+ "<th>Supplier ID</th>"
					+ "</tr>";
		
	}
	
	
	public static String getHtmlSummery() {
		
		
		return  "<table border='1'>"
				+ "<tr>"
					+"<th>Month</th>" 
					+ "<th>Total Capacity Units<br>(kWh)</th>" 
					+ "<th>Total Consume Units<br>(kWh)</th>"
					+ "<th>Remaining</th>"
					+ "<th>Extra units</th>"
					+ "</tr>";
		
	}
}
