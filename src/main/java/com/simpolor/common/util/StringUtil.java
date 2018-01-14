package com.simpolor.common.util;

public class StringUtil {
	
	 /**
     * 문자열 변환 : ", ', <, >, \n, ' '
     * @param str
     * @param brStr
     * @return
     */
    public static String replaceHtmlY(String str)
    {
        StringBuffer replaceStr = new StringBuffer();
        
        for(int i = 0 ; i < str.length() ; i ++)
        {
            switch(str.charAt(i))
            {
                case '\"':
                    replaceStr.append("&quot;");
                    break;
                case '\'':
                    replaceStr.append("&#39;");
                    break;
                case '<':
                    replaceStr.append("&lt;");
                    break;
                case '>':
                    replaceStr.append("&gt;");
                    break;
                case '\n':
            		replaceStr.append("<br/>");
                    break;
                case ' ':
                    replaceStr.append("&nbsp;");
                    break;
                default:
                    replaceStr.append(str.charAt(i));
                    break;
            }
        }
        return replaceStr.toString(); 
    }
    
    /**
     * 문자열 변환 :  <br/> -> \n
     * @param str
     * @return
     */
    public static String replaceHtmlN(String str)
    {        
        if(str == null) return "";
    	String reStr = str.toString().replaceAll("<([Bb][Rr])/>", "\n");
    	reStr = reStr.replaceAll("<([Bb][Rr])>", "\n");
    	
        return reStr;
    }
    
}
