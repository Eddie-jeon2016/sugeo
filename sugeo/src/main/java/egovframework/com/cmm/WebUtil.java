package egovframework.com.cmm;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.DateUtil;

import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.Globals;
import four.com.bbs.service.BbsManageVO;

/**
 * 교차접속 스크립트 공격 취약성 방지(파라미터 문자열 교체)
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    	--------    ---------------------------
 *   2011.10.10  한성곤          최초 생성
 *
 * </pre>
 */

public class WebUtil {
		
	public static String m_FileName = "log";     
    private static FileWriter objfile = null;
    
	public static String clearXSSMinimum(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}
		
		String returnValue = value;

		returnValue = returnValue.replaceAll("&", "&amp;");
		returnValue = returnValue.replaceAll("<", "&lt;");
		returnValue = returnValue.replaceAll(">", "&gt;");
		returnValue = returnValue.replaceAll("\"", "&#34;");
		returnValue = returnValue.replaceAll("\'", "&#39;");
		return returnValue;
	}

	public static String clearXSSMaximum(String value) {
		String returnValue = value;
		returnValue = clearXSSMinimum(returnValue);

		returnValue = returnValue.replaceAll("%00", null);

		returnValue = returnValue.replaceAll("%", "&#37;");

		// \\. => .

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\
		returnValue = returnValue.replaceAll("\\./", ""); // ./
		returnValue = returnValue.replaceAll("%2F", "");

		return returnValue;
	}

	public static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\

		return returnValue;
	}

	/**
	 * 행안부 보안취약점 점검 조치 방안.
	 *
	 * @param value
	 * @return
	 */
	public static String filePathReplaceAll(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("/", "");
		returnValue = returnValue.replaceAll("\\", "");
		returnValue = returnValue.replaceAll("\\.\\.", ""); // ..
		returnValue = returnValue.replaceAll("&", "");

		return returnValue;
	}

	public static String filePathWhiteList(String value) {
		return value; // TODO
	}
	
	 public static boolean isIPAddress(String str) {
		Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
		
		return ipPattern.matcher(str).matches();
    }
	 
	 public static String removeCRLF(String parameter) {
		 return parameter.replaceAll("\r", "").replaceAll("\n", "");
	 }
	 
	 public static String removeSQLInjectionRisk(String parameter) {
		 return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("%", "").replaceAll(";", "").replaceAll("-", "").replaceAll("\\+", "").replaceAll(",", "");
	 }
	 
	 public static String removeOSCmdRisk(String parameter) {
		 return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("|", "").replaceAll(";", "");
	 }
	 
	 public static String urlEncoding(String value) {
			if (value == null || value.trim().equals("")) {
				return "";
			}
			
			String returnValue = value;

			returnValue = returnValue.replace("&", "%26");
			returnValue = returnValue.replace("/", "%2F");
			returnValue = returnValue.replace(":", "%3A");
			returnValue = returnValue.replace("?", "%3F");
			returnValue = returnValue.replace("=", "%3D");
			return returnValue;
		}
		 
		public static String urlDecoding(String value) {
			if (value == null || value.trim().equals("")) {
				return "";
			}
			
			String returnValue = value;
			
			returnValue = returnValue.replace("%26", "&");
			returnValue = returnValue.replace("%2F", "/");
			returnValue = returnValue.replace("%3A", ":");
			returnValue = returnValue.replace("%3F", "?");
			returnValue = returnValue.replace("%3D", "=");
			return returnValue;
		}
		
		public static String urlArgumentMake(String value) {
			if (value == null || value.trim().equals("")) {
				return "";
			}
			
			String returnValue = value;
			
			if(returnValue.indexOf("?") < 0){
				returnValue = returnValue + "?";
			}else{
				returnValue = returnValue + "&";
			}
			System.out.println("urlArgumentMake_____returnValue = " + returnValue);
			return returnValue;
		}
	 
	/**
     * 전화번호 표시
     * @param regno    전화번호
     * @return
     */
    public static String fnTelNo(String telno) {
		String sValue    = "";
		String pattern     = "^(01\\d{1}|02|0505|0502|0506|0\\d{1,2})-?(\\d{3,4})-?(\\d{4})"; // 핸드폰/일반전화번호
		String pattern15xx = "^(1544|1566|1577|1588|1644|1688)-?([0-9]{4})";                  // 15XX 번호

		if ( telno != null ) {
			if ( "".equals(telno) ) {
				sValue = "";
			} else {
				Pattern telNoPattern = Pattern.compile(pattern); 

				Matcher matcher = telNoPattern.matcher(telno);

				if ( matcher.matches() ) {
					sValue = matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3); 
				} else {
					telNoPattern = Pattern.compile(pattern15xx); 
					matcher = telNoPattern.matcher(telno);

					if ( matcher.matches() ) {
						sValue = matcher.group(1) + "-" + matcher.group(2); 
					} else {
						sValue = telno;
					}
				}
			}
		} else {
			sValue = "";
		}

		return sValue;
	}
    
    /**
	 * 일자를 YYYYMMDD로 만들기
	 * @param day
	 * @return
	 */
	public static String getDateString(Calendar day) {
		String str = "" + day.get(Calendar.YEAR); // 년;

		int thisMonth = day.get(Calendar.MONTH) + 1; // 월 ( 0 ~ 11 )= 0;

		if ( thisMonth < 10 ) {
			str += "0";
		}

		str += "" + thisMonth ;

		int thisDate = day.get(Calendar.DATE) ; // 월 ( 0 ~ 11 )= 0;

		if ( thisDate < 10 ) {
			str += "0";
		}

		str += "" + thisDate ;

		return str;
	}
	
    /**
	 * 일자를 yyyy-mm-dd 로 만들기
	 * @param day
	 * @return
	 */
	public static String getMinusDateString(Calendar day) {
		String str = "" + day.get(Calendar.YEAR); // 년;
		
		str += "-";

		int thisMonth = day.get(Calendar.MONTH) + 1; // 월 ( 0 ~ 11 )= 0;

		if ( thisMonth < 10 ) {
			str += "0";
		}

		str += "" + thisMonth ;

		int thisDate = day.get(Calendar.DATE) ; // 월 ( 0 ~ 11 )= 0;

		str += "-";
				
		if ( thisDate < 10 ) {
			str += "0";
		}

		str += "" + thisDate ;

		return str;
	}
	
	
	/**
	 * 현재일 yyyy-mm-dd 얻기
	 * @return
	 */
	public static String getTodayDateString() {
		// 캘린더 인스턴스 생성
		Calendar day = Calendar.getInstance();
		
		String str = "" + day.get(Calendar.YEAR); // 년;

		int thisMonth = day.get(Calendar.MONTH) + 1; // 월 ( 0 ~ 11 )= 0;

		if ( thisMonth < 10 ) {
			str += "0";
		}

		str += "-" + thisMonth ;

		int thisDate = day.get(Calendar.DATE) ; // 월 ( 0 ~ 11 )= 0;

		if ( thisDate < 10 ) {
			str += "0";
		}

		str += "-" + thisDate ;

		return str;
	}
	
	/**
	 * 현재일 얻기
	 * @return
	 */
	public static String geTodayString() {
		// 캘린더 인스턴스 생성
		Calendar day = Calendar.getInstance();

		return getDateString(day);
	}
    
	/**
	 * 요일명 가져오기
	 * @param day
	 * @return
	 */
	public static String getDayName(int day) {
		String str = "";

		if ( day == 1 ) {
			str = "일";
		} else if ( day == 2 ) {
			str = "월";
		} else if ( day == 3 ) {
			str = "화";
		} else if ( day == 4 ) {
			str = "수";
		} else if ( day == 5 ) {
			str = "목";
		} else if ( day == 6 ) {
			str = "금";
		} else if ( day == 7 ) {
			str = "토";
		}

		return str;
	}
	
	/**
	 * 월
	 * @param month
	 * @return
	 */
	public static int getMonthNum(int month) {
		return month + 1 ;
	}
	
	/**
	 * 자리수 맞추기 함수
	 * @param src
	 * @param padChar
	 * @param padLength
	 * @return
	 */
    public static String fnLpad(String src, String padChar, int padLength) {
		String padString = "";
		String sValue    = "";
		
		int srcLength = src.length();
		
		sValue = src;
		
		if ( srcLength >= padLength ) {
			sValue = src;
		} else {
			for ( int i = 0; i < padLength - srcLength; i++ ) {
				padString += padChar;
			}
		}
		
		sValue = padString + sValue;
		
		return sValue;
	}	
    public static String getDate() {
  	   DecimalFormat df = new DecimalFormat("00");
  	   Calendar calendar = Calendar.getInstance();
  	 

  	   String year = Integer.toString(calendar.get(Calendar.YEAR)); //년도를 구한다
  	   String month = df.format(calendar.get(Calendar.MONTH) + 1); //달을 구한다
  	   String day = df.format(calendar.get(Calendar.DATE)); //날짜를 구한다
  	 
  	   String hour = ""; //시간을 구한다
  	   if( calendar.get(Calendar.AM_PM) == Calendar.PM){
  	      hour = df.format(calendar.get(Calendar.HOUR)+12); //Calendar.PM이면 12를 더한다
  	   } else {
  	      hour = df.format(calendar.get(Calendar.HOUR));
  	   }
  	 
  	   String minute = df.format(calendar.get(Calendar.MINUTE)); //분을 구한다
  	   String second = df.format(calendar.get(Calendar.SECOND)); //초를 구한다
  	   String date = year + month + day + hour + minute + second; 

  	   int iDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); //요일을 구한다
  	 
  	   String strDayOfWeek = "";
  	   switch(iDayOfWeek){
  	      case 1: 
  	         strDayOfWeek = "일요일";
  	         break;
  	      case 2: 
  	         strDayOfWeek = "월요일";
  	         break;
  	      case 3: 
  	         strDayOfWeek = "화요일";
  	         break;
  	      case 4: 
  	         strDayOfWeek = "수요일";
  	         break;
  	      case 5: 
  	         strDayOfWeek = "목요일";
  	         break;
  	      case 6: 
  	         strDayOfWeek = "금요일";
  	         break;
  	      case 7: 
  	         strDayOfWeek = "토요일";
  	         break;
  	 
  	      }
  	 //  date = date;// + strDayOfWeek;
  	   return date;
  	}
    
    /**
     * 시간차이 구하는 함수
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public static Integer diffOfDate_Time(String begin, String end) throws Exception {

	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date beginDate = formatter.parse(begin);
	    Date endDate = formatter.parse(end);

	    long diff = endDate.getTime() - beginDate.getTime();
	    long diffDays = diff / (60 * 60 * 1000);

	    return new Integer((int)(diffDays));
	}
    
    /**
     * 시간차이 구하는 함수
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public static String diffOf_Time(String begin, String end) throws Exception {

	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date beginDate = formatter.parse(begin);
	    Date endDate = formatter.parse(end);

	    long diff = endDate.getTime() - beginDate.getTime();
	    long diffTime = diff / (60 * 60 * 1000);
	    int diffDate = new Integer((int)(diff / (24 * 60 * 60 * 1000)));
	    int diffTimeMod = new Integer((int)(diffTime))%24;
	    String diffResult = "";
	    
	    if(diffDate < 1){
	    	diffResult = "1";
	    }else{
	    	if(diffTimeMod < 1){
	    		diffResult = String.valueOf(diffDate);
		    }else if(diffTimeMod < 10 && diffTimeMod > 0){
		    	diffResult = String.valueOf(diffDate) +"."+ String.valueOf(diffTimeMod);
		    }else{
		    	diffResult = String.valueOf(diffDate+1);
		    }
	    }
	    return diffResult;
	}

    
	/**
	 * 만나이구하기 로직
	 * @param idNum 8자리 생년월일
	 * @return
	 */
	public static int getMyAge(String idNum) {
		String year 	= "";
		String month 	= "";
		String day 		= "";
		String myYear 	= ""; // 생일(년)
		String myMonth 	= ""; // 생일(월)
		String myDay 	= ""; // 생일(일)
		int myAge 		= 0;  // 만 나이

		try{
			SimpleDateFormat formatY = new SimpleDateFormat("yyyy", Locale.KOREA);
			SimpleDateFormat formatM = new SimpleDateFormat("MM", Locale.KOREA);
			SimpleDateFormat formatD = new SimpleDateFormat("dd", Locale.KOREA);
			year	= formatY.format(new Date());
			month	= formatM.format(new Date());
			day		= formatD.format(new Date());
			if ( !isEmpty(idNum) ) {
				myYear  = idNum.substring(0, 4);
				myMonth = idNum.substring(4, 6);
				myDay   = idNum.substring(6, 8);

				if( Integer.parseInt(month) > Integer.parseInt(myMonth) ) {
					myAge = Integer.parseInt(year) - Integer.parseInt(myYear);
				} else if ( Integer.parseInt(month) == Integer.parseInt(myMonth) ) {
					if ( Integer.parseInt(day) > Integer.parseInt( myDay ) ) {
						myAge = Integer.parseInt(year) - Integer.parseInt(myYear);
					} else {
						myAge = Integer.parseInt(year) - (Integer.parseInt(myYear) + 1);
					}
				} else {
					myAge = Integer.parseInt(year) - (Integer.parseInt(myYear) + 1);
				}
			} else {
				myAge = 0;
			}

		} catch(Exception ex) {
			System.err.println("만 나이 계산 오류");
			System.err.println(ex.getMessage());
		} finally {
			//
		}

		return myAge;
	}
	
	/**
	 * 나이구하기 로직
	 * @param idNum 8자리 생년월일
	 * @return
	 */
	public static int getMyOld(String myYear) {
		int myAge 		= 0;  // 만 나이

		try{
			SimpleDateFormat formatY = new SimpleDateFormat("yyyy", Locale.KOREA);
			String year	= formatY.format(new Date());
			if ( !isEmpty(myYear) ) {
				myAge = Integer.parseInt(year) - (Integer.parseInt(myYear)) + 1;
			} else {
				myAge = 0;
			}

		} catch(Exception ex) {
			System.err.println("나이 계산 오류");
			System.err.println(ex.getMessage());
		} finally {
			//
		}

		return myAge;
	}
	
	public static boolean isEmpty(String strData) {
		boolean flag = false;
		
		if ( strData != null && !"".equals(strData) ) {
			flag = false;
		} else {
			flag = true;
		}
		
		return flag;		
	}

	/**
	 * 복호화 함수
	 * @param paramValue
	 * @param gubun
	 * @return
	 */
	public static String requestReplace (String paramValue, String gubun) {
        String result = "";
        
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}
        	
        	result = paramValue;
            
        }
        return result;
	}
	
	
	/**
	 * nullcheck
	 * @param str, Defaultvalue
	 * @return
	 */

	 public static String nullcheck(String str,  String Defaultvalue ) throws Exception 
	 {
	 	 String ReturnDefault = "" ;	 
	      if (str == null) 
	      {
	          ReturnDefault =  Defaultvalue ; 
	      }
	      else if (str == "" )
	     {
	          ReturnDefault =  Defaultvalue ; 
	      }      
	      else
	      {
	   	   		ReturnDefault = str ;     
	      }	   
		   return ReturnDefault ;
	 }

	/**
	 * BASE64 Encoder
	 * @param str
	 * @return
	 */
	public static String base64Encode(String str)  throws java.io.IOException {
	    sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	    byte[] strByte = str.getBytes();
	    String result = encoder.encode(strByte);
	    return result ;
	}

	/**
	 * BASE64 Decoder
	 * @param str
	 * @return
	 */
	public static String base64Decode(String str)  throws java.io.IOException {
	    sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	    byte[] strByte = decoder.decodeBuffer(str);
	    String result = new String(strByte);
	    return result ;
	}
	
	/**
	 * 자리수 맞추기 함수
	 * @param src
	 * @param padChar
	 * @param padLength
	 * @return
	 */
    public static boolean fnGetHoliday(String sdate, int thisYear, int thisMonth) {
		String padString = String.valueOf(thisYear) + WebUtil.fnLpad (String.valueOf(WebUtil.getMonthNum(thisMonth)), "0", 2);
		boolean sValue    = false;
		if( sdate.indexOf(padString) != -1){
			sValue = true;
		}
		
		return sValue;
	}
    /**
     * Null이거나 빈값(빈 문자열, 빈 컬렉션)인지 검사
     *
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }

        if (object instanceof String) {
            String str = (String) object;
            return str.length() == 0;
        }

        if (object instanceof Collection) {
            Collection collection = (Collection) object;
            return collection.size() == 0;
        }

        if (object.getClass().isArray()) {
            try {
                if (Array.getLength(object) == 0) {
                    return true;
                }
            } catch (Exception e) {
                //do nothing
            }
        }

        return false;
    }
    /**
     * 숫자인지 검사
     *
     * @param String
     * @return
     */
	public static boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/***************************
     * Logging Method          *  
     ***************************/
//    public static void TraceLog(String log)
//    {
//        int i                 = 0;
//        String stPath         = "";
//        String stFileName     = "";
//        
//        String m_PathName = log_path;  
////로그 파일이 저장 될 경로 (요것만 바꿔주면 된답니다.)
//
//        stPath     = m_PathName;
//        stFileName = m_FileName;
//        SimpleDateFormat formatter1 = new SimpleDateFormat ("yyyy-MM-dd");
//        SimpleDateFormat formatter2 = new SimpleDateFormat ("HH:mm:ss");
//        SimpleDateFormat formatter3 = new SimpleDateFormat ("yyyyMMdd");
//        
//        String stDate = formatter1.format(new Date());
//        String stTime = formatter2.format(new Date());
//        String stDatefileName = formatter3.format(new Date());
//        StringBuffer bufLogPath  = new StringBuffer();       
//        String tmp = "";
//		bufLogPath.append(stPath);
//		bufLogPath.append(stFileName);
//		bufLogPath.append("_");
//		bufLogPath.append(stDatefileName);
//		bufLogPath.append(".log") ;
//		
//        StringBuffer bufLogMsg = new StringBuffer(); 
//        String bufLogMsgTmp = new String();
//		bufLogMsg.append("[");
//		bufLogMsg.append(stDate +" "+ stTime);
//		bufLogMsg.append("]\r\n");             
//		bufLogMsg.append(log);
//		
//		bufLogMsgTmp = "\r\n["+stDate  +" "+  stTime+"]"+log;
//        try{
//            objfile = new FileWriter(bufLogPath.toString(), true);
//            objfile.write(bufLogMsgTmp.toString());
//            objfile.write("\r\n");
//        }catch(IOException e){
//            
//        } finally {
//            try{
//             objfile.close();
//            }catch(Exception e1){}
//        }
//    }
}