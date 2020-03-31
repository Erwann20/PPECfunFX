package emt.formatter;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class HTMLFormatter extends Formatter{

	@Override
	public String format(LogRecord record) {
		StringBuffer s = new StringBuffer(1000);
		Date d = new Date(record.getMillis());
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM, Locale.FRANCE);
		
		
		s.append("<tr><td>"+df.format(d) + "</td>");
		s.append("<td><span style=\"font-family" + "Courrier New,Courier,monospace; color:rgb(204,0,0);\">"+
	    "<b>"+ record.getLevel().getLocalizedName()+" " +formatMessage(record) + "</b> </span> </td> </tr>\n");
		
		return s.toString();
		
	}
	
	@Override
	public String getHead(Handler h) {
		return  "<html>\n "
				+ "<head>\n <meta charset=\"UTF-8\">\r\n" + " </head>"
				+ "<body> \n <table>\n";
	}
	
	@Override
	public String getTail(Handler h) {
		return "</table>\n </body> \n </html>\n";
	}

}
