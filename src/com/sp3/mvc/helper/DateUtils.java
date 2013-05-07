package com.sp3.mvc.helper;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtils {
	
	public static String getFormattedDateStr(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(d);
		return dateStr;
	}
	
	public static String getFormattedExpDateStr(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String dateStr = sdf.format(d);
		return dateStr;
	}
	
	public static Date getFormattedDate(Date d) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(d);
		Date dDate = sdf.parse(dateStr);
		return dDate;
	}
	
	public static Date getFormattedDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dDate = sdf.parse(dateStr);
		return dDate;
	}
	
	public static XMLGregorianCalendar getXMLGregorianCalendar(Date d) throws ParseException, DatatypeConfigurationException {
		Date formattedDate = getFormattedDate(d);
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(formattedDate);
		XMLGregorianCalendar xgc = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gregory);
		return xgc;
	}
	
	public static BigInteger convertLongToBigInt(Long longVar ) {
		String stringVar =longVar.toString();
		BigInteger bigIntVar = new BigInteger( stringVar );
		return bigIntVar;
	}
	
	public static void main(String[] args) {
		BigInteger val = convertLongToBigInt(1234123412341234L);
		System.out.println("BigInteger = "+val);
	}

}
