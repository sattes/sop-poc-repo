package com.sp3.mvc.converter;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class MyConverter {
	private static DatatypeFactory dtf;
	static {
		try {
			dtf = DatatypeFactory.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("bad");
		}
	}

	public static Date parseDateTime(String s) {
		XMLGregorianCalendar xgc = dtf.newXMLGregorianCalendar(s);
		return xgc.toGregorianCalendar().getTime();
	}

	public static String printDateTime(Date cal) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(cal);
		XMLGregorianCalendar xgc = dtf.newXMLGregorianCalendar(gc);
		return xgc.toString();
	}
}
