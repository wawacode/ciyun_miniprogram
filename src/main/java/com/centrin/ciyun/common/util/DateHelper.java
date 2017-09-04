package com.centrin.ciyun.common.util;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;



public class DateHelper {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat sdff = new SimpleDateFormat("MM-dd HH:mm");
	public static SimpleDateFormat sdy = new SimpleDateFormat("yy-MM-dd HH:mm");
	public static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat sdfd = new SimpleDateFormat("yyyy/MM/dd");
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMddHHmmss");

	// public static Log LOG = LogFactory.getLog(DateHelper.class);
	/**
	 * 字符串转日期类型
	 *
	 * @author HeCheng
	 * @time 2010-12-08 18:31:46
	 * @return
	 */
	public static Date convertStringToDate(String time, SimpleDateFormat simpleDateFormat ) {
		try {
			return simpleDateFormat.parse(time);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 日期类型转字符串
	 *
	 * @return
	 */
	public static String convertDateToString(Date time, SimpleDateFormat simpleDateFormat ) {
		try {
			return simpleDateFormat.format(time);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date convertStringToDate(String time, int type) {

		String format = "";
		if (type == 1) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(time);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取近一年
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 21:27:41
	 * @return
	 */
	public static String getLastOneYearDay() {
		return getLastNumberDayBeforeYesterDay(365);
	}

	/**
	 * 取近三个月
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 21:27:41
	 * @return
	 */
	public static String getLastThreeMonthDay() {
		return getLastNumberDayBeforeYesterDay(90);
	}

	/**
	 * 取近一个月
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 21:27:41
	 * @return
	 */
	public static String getLastMonthDay() {
		return getLastNumberDayBeforeYesterDay(30);
	}

	/**
	 * 取近一周
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 21:27:41
	 * @return
	 */
	public static String getLastWeekDay() {
		return getLastNumberDayBeforeYesterDay(7);
	}

	/**
	 * 取昨天的前XX天
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 21:06:08
	 * @param number
	 * @return
	 */
	public static String getLastNumberDayBeforeYesterDay(int number) {
		String yesterDay = getYesterdayOrTomorrow(getNowDate(), -1);
		return getLastNumberDay(yesterDay, number);
	}

	/**
	 * 取之前XX天
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 21:06:08
	 */
	@SuppressWarnings("deprecation")
	public static String getLastNumberDay(String day, int number) {
		String ntime = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(day);
			Date endDate = null;
			endDate = new Date(startDate.getTime()
					- (((long) number * (long) 24 * (long) 3600 * (long) 1000)));
			ntime = endDate.getYear() + 1900 + "-" + (endDate.getMonth() + 1)
					+ "-" + endDate.getDate();
		} catch (Exception e) {
			// System.out.println(e);
		}
		return ntime;
	}

	/**
	 * 取之前XX天
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 21:06:08
	 */
	@SuppressWarnings("deprecation")
	public static Date getLastBYNumberDay(Date startDate, int number) {
		Date endDate = null;
		try {
			endDate = new Date(startDate.getTime()
					+ (((long) number * (long) 24 * (long) 3600 * (long) 1000)));
			String ntime = endDate.getYear() + 1900 + "-"
					+ (endDate.getMonth() + 1) + "-" + endDate.getDate();
		} catch (Exception e) {
			// System.out.println(e);
		}
		return endDate;
	}

	/**
	 * 取上个月的昨天
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 18:41:14
	 * @param yesterday
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getYesterdayOnLastMonth(String yesterday) {
		String ntime = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(yesterday);
			Date endDate = null;
			endDate = new Date(startDate.getTime()
					- (((long) 30 * (long) 24 * (long) 3600 * (long) 1000)));
			ntime = endDate.getYear() + 1900 + "-" + (endDate.getMonth() + 1)
					+ "-" + endDate.getDate();
		} catch (Exception e) {
			// System.out.println(e);
		}
		return ntime;
	}

	/**
	 * 取今年第一天
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 18:44:12
	 * @param nowDate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getFirstDayInYear(String nowDate) {
		String ntime = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(nowDate);
			ntime = startDate.getYear() + 1900 + "-01-01";
		} catch (Exception e) {
			// System.out.println(e);
		}
		return ntime;
	}

	/**
	 * 取本季度第一天
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 18:46:33
	 * @param nowDate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getFirstDayInThisQuarter(String nowDate) {
		String ntime = "";
		int nowMonth;
		int nowYear;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(nowDate);
			nowMonth = startDate.getMonth() + 1;
			nowYear = startDate.getYear() + 1900;
			while (true) {
				if (nowMonth >= 10) {
					ntime = nowYear + "-10-01";
					break;
				}
				if (nowMonth >= 7) {
					ntime = nowYear + "-07-01";
					break;
				}
				if (nowMonth >= 4) {
					ntime = nowYear + "-04-01";
					break;
				}
				if (nowMonth >= 1) {
					ntime = nowYear + "-01-01";
					break;
				}
			}
		} catch (Exception e) {
			// System.out.println(e);
		}
		return ntime;
	}

	/**
	 * 取昨天
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 20:27:21
	 * @return
	 */
	public static String getYesterDay() {
		return getYesterdayOrTomorrow(getNowDate(), -1);
	}

	/**
	 * 取明天
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 20:27:21
	 * @return
	 */
	public static String getTomorrow() {
		return getYesterdayOrTomorrow(getNowDate(), 1);
	}

	/**
	 * 取今天
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getNowDate() {
		Date date = new Date();
		int nowMonth = date.getMonth() + 1;
		int nowYear = date.getYear() + 1900;
		int day = date.getDate();
		String startTime = nowYear + "-" + nowMonth + "-" + day;
		return startTime;
	}

	/**
	 * 取时间段
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 18:34:22
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<String> getTimes(String startTime, String endTime) {
		List<String> dayList = new ArrayList<String>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(startTime);
			Date endDate = df.parse(endTime);
			String now = "";
			for (long i = startDate.getTime(); i <= endDate.getTime(); i += (long) 24
					* (long) 3600 * (long) 1000) {
				Date date = new Date(i);
				now = date.getYear() + 1900 + "-" + (date.getMonth() + 1) + "-"
						+ date.getDate();
				dayList.add(now);
			}
		} catch (Exception e) {
			// System.out.println(e);
		}
		return dayList;
	}

	/**
	 * 取当前月第一天
	 * 
	 * @author HeCheng
	 * @time 2010-12-08 18:34:29
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getMonthFirstDay(Date date) {
		int nowMonth = date.getMonth() + 1;
		int nowYear = date.getYear() + 1900;
		String startTime = nowYear + "-" + nowMonth + "-1";
		return startTime;
	}

	/**
	 * 取明天或昨天
	 * 
	 * @param nowDate
	 * @param con
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getYesterdayOrTomorrow(String nowDate, int con) {
		String ntime = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(nowDate);
			Date endDate = null;
			if (con == -1) {
				endDate = new Date(startDate.getTime() - (long) 24
						* (long) 3600 * (long) 1000);
			} else {
				endDate = new Date(startDate.getTime() + (long) 24
						* (long) 3600 * (long) 1000);
			}
			ntime = endDate.getYear() + 1900 + "-" + (endDate.getMonth() + 1)
					+ "-" + endDate.getDate();
		} catch (Exception e) {
			// System.out.println(e);
		}
		return ntime;
	}

	/**
	 * 取明天或昨天
	 * 
	 * @param nowDate
	 * @param con
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getYesterdayOrTomorrow(Date startDate, int con) {
		String ntime = "";
		try {
			Date endDate = null;
			if (con == -1) {
				endDate = new Date(startDate.getTime() - (long) 24
						* (long) 3600 * (long) 1000);
			} else {
				endDate = new Date(startDate.getTime() + (long) 24
						* (long) 3600 * (long) 1000);
			}
			ntime = endDate.getYear() + 1900 + "-" + (endDate.getMonth() + 1)
					+ "-" + endDate.getDate();
		} catch (Exception e) {
			// System.out.println(e);
		}
		return ntime;
	}

	/**
	 * 取月的最后一天
	 * 
	 * @param time
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getMonthEndDay(String time) {
		String ntime = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(time);
			int nowMonth = startDate.getMonth() + 1;
			int nextMonth = nowMonth + 1;
			int nowYear = startDate.getYear() + 1900;
			String nextTime = nowYear + "-" + nextMonth + "-1";
			Date tmpDate = df.parse(nextTime);
			Date endDate = new Date(tmpDate.getTime() - 24 * 3600 * 1000);
			ntime = endDate.getYear() + 1900 + "-" + (endDate.getMonth() + 1)
					+ "-" + endDate.getDate();
		} catch (Exception e) {
			// System.out.println(e);
		}
		return ntime;
	}

	/**
	 * 取月的最后一天
	 * 
	 * @param time
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getMonthEndDay(Date startDate) {
		String ntime = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			int nowMonth = startDate.getMonth() + 1;
			int nextMonth = nowMonth + 1;
			int nowYear = startDate.getYear() + 1900;
			String nextTime = nowYear + "-" + nextMonth + "-1";
			Date tmpDate = df.parse(nextTime);
			Date endDate = new Date(tmpDate.getTime() - 24 * 3600 * 1000);
			ntime = endDate.getYear() + 1900 + "-" + (endDate.getMonth() + 1)
					+ "-" + endDate.getDate();
		} catch (Exception e) {
			// System.out.println(e);
		}
		return ntime;
	}

	/**
	 * 根据日期取周数
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		int week_of_year = cal.get(Calendar.WEEK_OF_YEAR);
		return week_of_year;
	}

	public static Map getWeekStartOfEnd(int week, Date date) {
		Map map = new HashMap();
		DateHelper.geWeekDate(week);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		/**
		 * week==0 表明是周日
		 */
		if (week == 0) {
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w == week) {
				map.put("end", cal.getTime());
				map.put("start", cal.getTime());
				cal.add(Calendar.DATE, -6);
				map.put("cur", cal.getTime());
			} else {
				int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
				if (1 == dayWeek) {
					cal.add(Calendar.DAY_OF_MONTH, -1);
				}
				cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
				int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
				cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
				map.put("start", cal.getTime());
				map.put("cur", cal.getTime());
				cal.add(Calendar.DATE, 6);
				map.put("end", cal.getTime());

			}
		} else {
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (dayOfWeek == 0)
				dayOfWeek = 7;
			cal.add(Calendar.DAY_OF_WEEK, week - dayOfWeek);
			map.put("cur", cal.getTime());
			map.put("start", cal.getTime());
			int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
			cal.add(Calendar.DATE, -day_of_week);
			cal.add(Calendar.DATE, 6);
			map.put("end", cal.getTime());
		}
		return map;
	}

	/**
	 * 根据日期计算所在周的周一和周日
	 * 
	 * @param time
	 *            指定的日期
	 */
	public static void convertWeekByDate(Date time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);

		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		// System.out.println(dayWeek);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		dayWeek = cal.get(Calendar.WEEK_OF_YEAR);
		// System.out.println(dayWeek);

		// System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		// System.out.println("所在周星期一的日期："+imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		// System.out.println("所在周星期日的日期："+imptimeEnd);

	}

	/**
	 * 返回当天是周几
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		// System.out.println("本周第一天: " +w );
		return weekDays[w];
	}

	/**
	 * 返回当天是周几
	 * 
	 * @param dt
	 * @return
	 */
	public static int getWeekOfDate() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		// System.out.println("本周为: " +w );
		if (w < 0)
			w = 0;
		// System.out.println("本周为: " +w );
		return w;
	}

	/**
	 * 返回两个日期之间的天数
	 * 
	 * @param endtime
	 * @param strtime
	 * @return
	 */
	public static long getQuot(String endtime, String strtime, String type) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat(type);
		try {
			Date date1 = ft.parse(endtime);
			Date date2 = ft.parse(strtime);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 返回两个日期之间的天数
	 * 
	 * @param endtime
	 * @param strtime
	 * @return
	 */
	public static long getQuot(Date endtime, Date strtime) {
		long quot = 0;
		quot = endtime.getTime() - strtime.getTime();
		quot = quot / 1000 / 60 / 60 / 24;
		return quot;
	}

	public static String geWeekDate(int week) {

		int mondayPlus = DateHelper.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);
		int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK) - 1;
		currentDate.add(Calendar.DAY_OF_WEEK, week - dayOfWeek);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		// 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	/**
	 * 字符串转日期
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date StringToDate(String dateStr, String formatStr) {
		if(StringUtils.isEmpty(dateStr))
			return null;
		DateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {//2015-12-12~2015-12-31
			date = dd.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}

	public static void printWeeks(Date dt, Map map) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.set(Calendar.DATE, 1);
		int month = calendar.get(Calendar.MONTH);
		while (calendar.get(Calendar.MONTH) == month) {
			int week_of_year = calendar.get(Calendar.WEEK_OF_YEAR);
			map.put(week_of_year + "", week_of_year + "");
			calendar.add(Calendar.DATE, 1);

		}
	}

	/**
	 * 判断给定日期是否为当天，
	 * 
	 * 距离当前时间七天之内的日期，和七天之外的日期
	 * 
	 * @param dt
	 * @param type
	 *            0--当天 1--7天之内的 2--7天之外的
	 * @return
	 */
	public static boolean getDayDiffFromToday(Date dt, int type) {
		Date today = new Date();
		today.setHours(23);
		today.setMinutes(59);
		today.setSeconds(59);

		long diff = today.getTime() - dt.getTime();
		if (diff < 0)
			diff = 0;
		long days = diff / (1000 * 60 * 60 * 24);

		if (type == 0 && days == 0)
			return true;
		if (type == 1 && days > 0 && days <= 7)
			return true;
		if (type == 2 && days > 7)
			return true;

		return false;
	}

	/**
	 * 根据开始时间和结束时间返回时间段内的时间集合
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public static List getDatesBetweenTwoDate(Date beginDate, Date endDate) {
		List lDate = new ArrayList();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}

	public static Map statisicWeekbyDate(Date starttime, Date endtime) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Calendar c_begin = new GregorianCalendar();
		Calendar c_end = new GregorianCalendar();
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] weeks = dfs.getWeekdays();
		c_begin.setTime(starttime);
		c_end.setTime(endtime);
		int count = 1;
		c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天
		int i = 0;

		while (c_begin.before(c_end)) {
			i++;
			// System.out.println("第" + count + "周  日期："
			// + new java.sql.Date(c_begin.getTime().getTime()) + ", "+
			// weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
			//
			map.put("week", count);
			if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				count++;
			}
			c_begin.add(Calendar.DAY_OF_YEAR, 1);
		}

		map.put("day", i);
		return map;
	}

	public static List getWeekBetweenTwoDate() {
		Calendar c_begin = new GregorianCalendar();
		Calendar c_end = new GregorianCalendar();
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] weeks = dfs.getWeekdays();
		c_begin.set(2014, 3, 1); // Calendar的月从0-11，所以4月是3.
		c_end.set(2014, 4, 20); // Calendar的月从0-11，所以5月是4.
		int count = 1;
		c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天
		while (c_begin.before(c_end)) {
			System.out.println("第" + count + "周  日期："
					+ new java.sql.Date(c_begin.getTime().getTime()) + ","
					+ weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
			if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				count++;
			}
			c_begin.add(Calendar.DAY_OF_YEAR, 1);
		}
		return null;
	}

	/**
	 * get Calendar of given year
	 * 
	 * @param year
	 * @return
	 */
	public static Calendar getCalendarFormYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.YEAR, year);
		return cal;
	}

	/**
	 * get start date of given week no of a year
	 * 
	 * @param year
	 * @param weekNo
	 * @return
	 */
	public static String getStartDayOfWeekNo(int year, int weekNo) {
		Calendar cal = getCalendarFormYear(year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
				+ "-" + cal.get(Calendar.DAY_OF_MONTH);

	}

	/**
	 * get the end day of given week no of a year.
	 * 
	 * @param year
	 * @param weekNo
	 * @return
	 */
	public static String getEndDayOfWeekNo(int year, int weekNo) {
		Calendar cal = getCalendarFormYear(year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
				+ "-" + cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 
	 * @Title: executionbyTime
	 * @Description: 根据年、周 、日， 计算也当天日期
	 * @param @param year
	 * @param @param weekNo
	 * @param @param week
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author xiaoyunliang
	 * @date 2014-10-24 上午4:10:39 创建时间
	 * @throws
	 */
	public static String executionbyTime(Date curTime, int weekNo, int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curTime);
		int year = calendar.get(Calendar.YEAR);
		Calendar curcal = getCalendarFormYear(year);
		curcal.set(Calendar.WEEK_OF_YEAR, weekNo);
		if (week == 0) {
			curcal.add(Calendar.DAY_OF_WEEK, 6);
		} else {
			curcal.add(Calendar.DAY_OF_WEEK, week - 1);
		}
		String zhixinTime = DateHelper.sdf.format(curcal.getTime())
				+ " 23:59:59";
		return zhixinTime;
	}

	/**
	 * 
	 * @param startTime
	 * @param weekNo
	 * @param week
	 * @param endTime
	 * @return
	 */
	public static Map DateByfPlan(Date curTime, int weekNo, int week,
			Date startTime, Date endTime) {
		Map map = new HashMap();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curTime);
		int year = calendar.get(Calendar.YEAR);
		Calendar cal = getCalendarFormYear(year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);
		int mm = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DAY_OF_MONTH);
		String startweek = cal.get(Calendar.YEAR) + "-"
				+ ((mm > 10) ? mm : ("0" + mm)) + "-"
				+ ((d > 10) ? d : ("0" + d));
		cal.add(Calendar.DAY_OF_WEEK, 6);
		mm = cal.get(Calendar.MONTH) + 1;
		d = cal.get(Calendar.DAY_OF_MONTH);
		String endweek = cal.get(Calendar.YEAR) + "-"
				+ ((mm > 10) ? mm : ("0" + mm)) + "-"
				+ ((d > 10) ? d : ("0" + d));
		map.put("cur", DateHelper.sdf.format(curTime));
		Calendar curcal = getCalendarFormYear(year);
		curcal.set(Calendar.WEEK_OF_YEAR, weekNo);

		if (week == 0) {
			curcal.add(Calendar.DAY_OF_WEEK, 6);
		} else {
			curcal.add(Calendar.DAY_OF_WEEK, week - 1);
		}

		String zhixinTime = DateHelper.sdf.format(curcal.getTime());

		// System.out.println("周开始时间=="+startweek);//周开始时间
		// System.out.println("周结束时间=="+endweek);//周结束时间
		// System.out.println("执行时间=="+zhixinTime);//执行时间
		// System.out.println("week=="+week);//周开始时间
		try {
			// 计划开始时间在本周
			if ((DateHelper.sdf.parse(DateHelper.sdf.format(startTime))
					.getTime() >= (DateHelper.sdf.parse(startweek)).getTime())
					&& DateHelper.sdf.parse(DateHelper.sdf.format(startTime))
							.getTime() <= (DateHelper.sdf.parse(endweek))
							.getTime()) {
				// 取周开始时间、执行时间，计划开始时间 三者最大的放到 start
				// System.out.println("计划开始时间在周内");
				List list = new ArrayList();
				list.add(startweek);
				list.add(zhixinTime);
				list.add(DateHelper.sdf.format(startTime));
				Date max = null;
				for (int i = 0; i < list.size(); i++) {
					Date dd = DateHelper.sdf.parse((String) list.get(i));
					if (max == null) {
						max = dd;
					} else {
						if (dd.after(max)) {
							max = dd;
						}
					}
				}
				map.put("start", DateHelper.sdf.format(max));

			} else {
				// 取周开始时间、执行时间，两者最大的放到 start
				// System.out.println("计划开始时间不在周内");
				if (DateHelper.sdf.parse(startweek).after(
						DateHelper.sdf.parse(zhixinTime))) {
					map.put("start", startweek);
				} else {
					map.put("start", zhixinTime);
				}
			}

			// System.out.println("endTime=="+endTime.getTime());//执行时间
			// System.out.println("startweek=="+DateHelper.sdf.parse(startweek).getTime());//周开始时间
			// System.out.println("endweek=="+DateHelper.sdf.parse(endweek).getTime());//周开始时间
			// 计划开始时间在本周
			if (endTime.getTime() >= DateHelper.sdf.parse(startweek).getTime()
					&& endTime.getTime() <= (DateHelper.sdf.parse(endweek))
							.getTime()) {
				// System.out.println("计划结束时间在周内");
				/**
				 * //取周开始时间、执行时间，计划开始时间 三者最大的放到 start List list=new ArrayList();
				 * list.add(endweek); 2014-7-13 list.add(zhixinTime);2014-07-07
				 * list.add(DateHelper.sdf.format(endTime));2014-07-08 Date
				 * min=null; for (int i = 0; i < list.size(); i++) { Date
				 * dd=DateHelper.sdf.parse((String)list.get(i)); if(min==null){
				 * min=dd; }else { if(dd.after(min)) { min=dd; } } }
				 **/
				map.put("end", DateHelper.sdf.format(endTime));

			} else {
				// 取周结束时间、执行时间，两者最小的的放到 end
				// System.out.println("计划结束时间不在周内");
				if (DateHelper.sdf.parse(endweek).before(
						DateHelper.sdf.parse(zhixinTime))) {
					map.put("end", zhixinTime);
				} else {
					map.put("end", endweek);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("start=="+map.get("start"));
		// System.out.println("cur=="+map.get("cur"));
		// System.out.println("end=="+map.get("end"));
		return map;
	}

	/**
	 * 比较两个字符串日期
	 * 
	 * @author wennan
	 * @param date1
	 *            字符串日期
	 * @param date2
	 *            字符串日期
	 * @param format
	 *            传入的日期格式，比如yyyy-MM-dd hh:mm
	 * 
	 * @return date1大于date2返回1，等于返回0，小于返回-1
	 */
	public static int compareStringDate(String date1, String date2) {

		Date dt1 = null;
		Date dt2 = null;
		try {
			dt1 = DateHelper.sf.parse(date1);
			dt2 = DateHelper.sf.parse(date2);
			return compareDate(dt1, dt2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 
	 * <p>
	 *   日期比较
	 * </p>
	 * @author YANXF
	 * @param date1
	 * @param date2
	 * @return
	 *
	 */
	public static int compareDate(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime()) {
			return 1;
		} else if (date1.getTime() < date2.getTime()) {
			return -1;
		} else {
			return 0;
		}
	}

	

	public static void main(String[] args) throws ParseException {
		Date dd = DateHelper.sdf.parse("2014-03-22");
		Date dd1 = DateHelper.sdf.parse("2014-07-11");

		Map<String, Integer> map = DateHelper.statisicWeekbyDate(dd, dd1);
		System.out.println(map.get("week"));
		System.out.println(map.get("day"));
		// statisicWeekbyDate
		// DateHelper.DateByfPlan(new Date(), 24, 1, dd1, dd);

		/**
		 * Calendar c_begin = new GregorianCalendar(); Calendar c_end = new
		 * GregorianCalendar(); DateFormatSymbols dfs = new DateFormatSymbols();
		 * String[] weeks = dfs.getWeekdays(); String aa = "2014-04-22"; try {
		 * c_begin.setTime(DateHelper.sdf.parse(aa)); } catch (ParseException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 * c_end.setTime(new Date());
		 * 
		 * int count = 1; c_end.add(Calendar.DAY_OF_YEAR, 1); //
		 * 结束日期下滚一天是为了包含最后一天
		 * 
		 * while (c_begin.before(c_end)) { System.out.println("第" + count +
		 * "周  日期：" + new java.sql.Date(c_begin.getTime().getTime()) + ", " +
		 * weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
		 * 
		 * if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) { count++;
		 * } c_begin.add(Calendar.DAY_OF_YEAR, 1); }
		 */
		/**
		 * get start date of given week no of a year
		 * 
		 * @param year
		 * @param weekNo
		 * @return
		 */

		 
	}

 
}