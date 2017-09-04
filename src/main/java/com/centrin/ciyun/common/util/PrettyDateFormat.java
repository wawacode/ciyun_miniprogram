package com.centrin.ciyun.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


/**
 * 时间日期格式化类
 * 
 * @author quanli
 * 
 */
public class PrettyDateFormat extends SimpleDateFormat {

	public final static String sdf = new String("yyyy-MM-dd");
	public final static String YEAR = new String("yyyy");
	public final static String sdff = new String("MM-dd HH:mm");
	public final static String sdy = new String("yy-MM-dd HH:mm");
	public final static String sf = new String("yyyy-MM-dd HH:mm");
	public final static String format = new String("yyyy-MM-dd HH:mm:ss");
	public final static String ymd = new String("yyyyMMddHHmmss");
	
	private static final Long MINUTES_MILLISECONDS = 60 * 1000L;
	private static final Long HOUR_MILLISECONDS = MINUTES_MILLISECONDS * 60;
	private static final Long DAY_MILLISECONDS = HOUR_MILLISECONDS * 24;
	private static final Long MONTH_MILLISECONDS = DAY_MILLISECONDS * 30;
	
	
	private static final long serialVersionUID = 1L;
	private Pattern pattern = Pattern.compile("('*)(#{1,2}|@)");
	private FormatType formatType = FormatType.DEAFULT;
	private SimpleDateFormat simpleDateFormat;

	private enum FormatType {
		DEAFULT, TIME, DAY
	};

	public static void main(String[] args) throws Exception {
//		Calendar calendar = Calendar.getInstance();
//		System.out.println(formatTime(calendar.getTime()));
//		calendar.add(Calendar.HOUR, -1);
//		System.out.println(formatTime(calendar.getTime()));
//		calendar.add(Calendar.DAY_OF_YEAR, -1);
//		System.out.println(formatTime(calendar.getTime()));
//		calendar.add(Calendar.MONTH, -1);
//		System.out.println(formatTime(calendar.getTime()));
//		calendar.add(Calendar.YEAR, -1);
//		System.out.println(formatTime(calendar.getTime()));
		//System.out.println(PrettyDateFormat.getWeekBeginTime(new Date()));
		//System.out.println(PrettyDateFormat.getWeekEndTime(new Date()));
		//System.out.println(PrettyDateFormat.differDayNum(new Date(), PrettyDateFormat.getWeekEndTime(new Date()))+1);
		System.out.println(stringToDate("10/12/2015 12:12:12","yyyy-MM-dd"));
	}

	
	
	
	public static String formatTime(long time) {
		Calendar calendar = Calendar.getInstance();
		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTimeInMillis(time);
		if (calendar.getTimeInMillis() - time < 3600 * 1000L * 2) {
			return new PrettyDateFormat("@", "yyyy-MM-dd HH:mm")
					.format(timeCalendar.getTime());
		} else if (calendar.get(Calendar.YEAR) == timeCalendar
				.get(Calendar.YEAR)) {
			return new PrettyDateFormat("# HH:mm", "MM-dd HH:mm")
					.format(timeCalendar.getTime());
		} else {
			return new PrettyDateFormat("# HH:mm", "yyyy-MM-dd HH:mm")
					.format(timeCalendar.getTime());
		}
	}
	
	
	public static String formatTimeShort(Date time) {
		Calendar calendar = Calendar.getInstance();
		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(time);
		if (calendar.get(Calendar.YEAR) == timeCalendar
				.get(Calendar.YEAR)) {
			return new PrettyDateFormat("# HH:mm", "MM-dd HH:mm").format(time);
		} else {
			return new PrettyDateFormat("# HH:mm", "yyyy-MM-dd HH:mm")
					.format(time);
		}
	}
	
	

	public static String formatTime(Date time) {
		Calendar calendar = Calendar.getInstance();
		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(time);
		if (calendar.getTimeInMillis() - time.getTime() < 3600 * 1000L * 2) {
			return new PrettyDateFormat("@", "yyyy-MM-dd HH:mm").format(time);
		} else if (calendar.get(Calendar.YEAR) == timeCalendar
				.get(Calendar.YEAR)) {
			return new PrettyDateFormat("# HH:mm", "MM-dd HH:mm").format(time);
		} else {
			return new PrettyDateFormat("# HH:mm", "yyyy-MM-dd HH:mm")
					.format(time);
		}
	}
	
	
	public static String formatTimeIce(Date time) {
		Calendar calendar = Calendar.getInstance();
		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(time);
		StringBuilder sBuilder = new StringBuilder("");
		Long lns = calendar.getTimeInMillis() - time.getTime();
		if (lns < MINUTES_MILLISECONDS) {
			sBuilder.append("刚刚");
		} else if (lns < HOUR_MILLISECONDS) {
			sBuilder.append((long)(lns/(MINUTES_MILLISECONDS))).append("分钟前");
		} else if (lns < DAY_MILLISECONDS) {
			sBuilder.append((long)(lns/(HOUR_MILLISECONDS))).append("小时前");
		} else if (lns < MONTH_MILLISECONDS){
			sBuilder.append((long)(lns/(DAY_MILLISECONDS))).append("天前");
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sBuilder.append(sdf.format(time));
		}
		return sBuilder.toString();
	}	

	/**
	 * 构造器
	 * <p>
	 * format中的@表示[XXX秒前,XXX分钟前,XXX小时前(最多是23小时前)]
	 * <p>
	 * format中的#表示[空字串(表示今天),昨天,前天]
	 * <p>
	 * format中的##表示[今天,昨天,前天]
	 * 
	 * @param format
	 *            和SimpleDateFormat中的格式设置基本上是一样的,只是多的@格式 #格式和##格式
	 * @param fullFormat
	 *            和SimpleDateFormat中的格式设置是一样的
	 */
	public PrettyDateFormat(String format, String fullFormat) {
		super(fullFormat);
		Matcher m = pattern.matcher(format);
		while (m.find()) {
			if (m.group(1).length() % 2 == 0) {
				if ("@".equals(m.group(2))) {
					if (formatType == FormatType.DAY) {
						throw new IllegalArgumentException("#和@模式字符不能同时使用.");
					}
					formatType = FormatType.TIME;
				} else {
					if (formatType == FormatType.TIME) {
						throw new IllegalArgumentException("#和@模式字符不能同时使用.");
					}
					formatType = FormatType.DAY;
				}
			}
		}
		this.simpleDateFormat = new SimpleDateFormat(format.replace("'", "''"));
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		throw new UnsupportedOperationException("暂时还不支持的操作");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.text.SimpleDateFormat#format(java.util.Date,
	 * java.lang.StringBuffer, java.text.FieldPosition)
	 */
	public StringBuffer format(Date date, StringBuffer toAppendTo,
			FieldPosition pos) {
		if (formatType == FormatType.DEAFULT) {
			return super.format(date, toAppendTo, pos);
		}

		long curTime = System.currentTimeMillis();

		long diffDay = 0L;
		long diffSecond = 0L;
		if (formatType == FormatType.TIME) {
			diffSecond = (curTime - date.getTime()) / 1000L;
			if (diffSecond < 0 || diffSecond >= 86400) {
				return super.format(date, toAppendTo, pos);
			}
		}
		if (formatType == FormatType.DAY) {
			Calendar curDate = new GregorianCalendar();
			curDate.setTime(new Date(curTime));
			curDate.set(Calendar.HOUR_OF_DAY, 23);
			curDate.set(Calendar.MINUTE, 59);
			curDate.set(Calendar.SECOND, 59);
			curDate.set(Calendar.MILLISECOND, 999);
			diffDay = (curDate.getTimeInMillis() - date.getTime()) / 86400000L;
			if (diffDay < 0 || diffDay > 2) {
				return super.format(date, toAppendTo, pos);
			}
		}
		StringBuffer sb = new StringBuffer();
		Matcher m = pattern.matcher(simpleDateFormat.format(date));
		if (m.find()) {
			String group2 = m.group(2);
			String replacement = "";
			while (true) {
				if ("@".equals(group2)) {
					if (diffSecond < 60) {
						replacement = diffSecond == 0 ? "1秒前" : diffSecond
								+ "秒前";
					} else if (diffSecond < 3600) {
						replacement = diffSecond / 60 + "分钟前";
					} else if (diffSecond < 86400) {
						replacement = diffSecond / 3600 + "小时前";
					}
				} else {
					if (diffDay == 0) {
						replacement = group2.length() == 2 ? "今天" : "";
					} else if (diffDay == 1) {
						replacement = "昨天";
					} else {
						replacement = "前天";
					}
				}
				m.appendReplacement(sb, replacement);
				if (!m.find()) {
					break;
				}
			}
			m.appendTail(sb);
		}
		return toAppendTo.append(sb.toString());
	}

	/**
	 * stirng转成Date
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String time, String format) {
		if (StringUtils.isEmpty(time)) {
			return null;
		}
		DateFormat df = getDateFormat(format);
		synchronized (df) {
			try {
				return df.parse(time);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	/**
	 * date转string
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		if (null == date) {
			return "";
		}
		DateFormat df = getDateFormat(format);
		synchronized (df) {
			return df.format(date);
		}
	}

	/**
	 * Date格式化
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date dateFormat(Date date, String format) {
		if (null == date) {
			return null;
		}
		DateFormat df = getDateFormat(format);
		synchronized (df) {
			try {
				String dateTime = df.format(date);
				return df.parse(dateTime);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	private static Map<String, DateFormat> formatMap = new HashMap<String, DateFormat>();

	private static DateFormat getDateFormat(String format) {
		DateFormat fm = formatMap.get(format);
		if (fm == null) {
			fm = new SimpleDateFormat(format);
			formatMap.put(format, fm);
		}
		return fm;
	}

	/**
	 * 判断时间是否为今天
	 * @param date
	 * @return
	 */
    public static boolean isToday(Date date){
    	return dateToString(date, sdf).equals(dateToString(Calendar.getInstance().getTime(), sdf));
    }
    
    /**
     * 判断是不是在今天之前的时间（历史）
     * @param date
     * @return
     */
    public static boolean isBeforeToday(Date date){
    	Timestamp todayBegin = getDayBeginTime();
    	if(date.getTime()<todayBegin.getTime())
    		return true;
    	return false;
    }
    
    /**
     * 获取日期为星期几
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date... date){
    	Calendar calendar =Calendar.getInstance();
    	if(date!=null && date.length==1 && date[0]!=null){
    		calendar.setTime(date[0]);
    	}
    	int day= (calendar.get(Calendar.DAY_OF_WEEK)-1)%7;
    	return day==0?7:day;
    }
    
    /**
     * 判断时间是否为当前周
     * @param date
     * @return
     */
    public static boolean isCurrentWeek(Date date){
    	Calendar calendar = Calendar.getInstance();
    	int day= (calendar.get(Calendar.DAY_OF_WEEK)-1)%7;
    	day = day==0?7:day;//当前周几
    	//这周结束的时间
    	calendar.add(Calendar.DATE,7-day);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59,  59);
        calendar.set(Calendar.MILLISECOND, 999);
        Long maxTime = calendar.getTimeInMillis();
    	//这周开始的时间
    	calendar.add(Calendar.DATE,-6);
    	calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0,0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	long minTime = calendar.getTimeInMillis();
    	//判断是否在本周时间范围内
    	if(minTime<=date.getTime() && date.getTime()<=maxTime){
    		return true;
    	}
    	return false;
    }
    
    /**
     * 获取一天的开始时间点（不传日期为今天）
     * @param date
     * @return
     */
    public static Timestamp getDayBeginTime(Date... date){
    	Calendar calendar = Calendar.getInstance();
    	if(date!=null && date.length==1 && date[0]!=null){
    		calendar.setTime(date[0]);
    	}
    	calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0,0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	return new Timestamp(calendar.getTimeInMillis());
    }
    
    /**
     * 获取一天的结束时间点（不传日期为今天）
     * @param date
     * @return
     */
    public static Timestamp getDayEndTime(Date... date){
    	Calendar calendar = Calendar.getInstance();
    	if(date!=null && date.length==1 && date[0]!=null){
    		calendar.setTime(date[0]);
    	}
    	calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59,  59);
        calendar.set(Calendar.MILLISECOND, 999);
    	return new Timestamp(calendar.getTimeInMillis());
    }
    
    /**
     * 获取一周的开始时间点（不传日期为当前周）
     * @param date
     * @return
     */
    public static Timestamp getWeekBeginTime(Date... date){
    	Calendar calendar = Calendar.getInstance();
    	if(date!=null && date.length==1 && date[0]!=null){
    		calendar.setTime(date[0]);
    	}
    	int day= (calendar.get(Calendar.DAY_OF_WEEK)-1)%7;
    	day = day==0?7:day;//当前周几
    	calendar.add(Calendar.DATE,1-day);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,0,0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
        
    }
    
    /**
     * 获取一周的结束时间点（不传日期为当前周）
     * @param date
     * @return
     */
    public static Timestamp getWeekEndTime(Date... date){
    	Calendar calendar = Calendar.getInstance();
    	if(date!=null && date.length==1 && date[0]!=null){
    		calendar.setTime(date[0]);
    	}
    	int day= (calendar.get(Calendar.DAY_OF_WEEK)-1)%7;
    	day = day==0?7:day;//当前周几
    	calendar.add(Calendar.DATE,7-day);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59,  59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }
    
    /**
     * 获取2个时间相差几周（逻辑周），如：本周内为0，如前一周和后一周相差1，即使相关不够7天
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int differWeekNum(Date beginTime,Date endTime){
    	Timestamp timestamp =PrettyDateFormat.getWeekBeginTime(beginTime);
    	Timestamp endTimestamp = PrettyDateFormat.getWeekBeginTime(endTime);
    	long time = Math.abs(endTimestamp.getTime()-timestamp.getTime());
    	Long num = time/86400000/7;
    	return num.intValue();
    }
    
    public static int differDayNum(Date beginTime,Date endTime){
    	Timestamp timestamp =PrettyDateFormat.getDayBeginTime(beginTime);
    	Timestamp endTimestamp = PrettyDateFormat.getDayBeginTime(endTime);
    	long time = Math.abs(endTimestamp.getTime()-timestamp.getTime());
    	Long num = time/86400000;
    	return num.intValue();
    }
    
    
    public static Date getYesterday(Date... date){
    	Calendar calendar = Calendar.getInstance();
    	if(date!=null && date.length==1 && date[0]!=null){
    		calendar.setTime(date[0]);
    	}
    	calendar.add(Calendar.DATE, -1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,0,0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    public static final long oneDayTimeLong = 86400000L;
}
