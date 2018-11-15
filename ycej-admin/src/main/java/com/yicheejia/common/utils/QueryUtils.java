package com.yicheejia.common.utils;

/**
 * 查询工具类
 */
public class QueryUtils {

    /**
     * 转化为模糊查询字符串
     * @param str
     * @return
     */
    public static String toFuzzyQueryStr(String str){
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        if(chars.length > 0){
            sb.append("%");
        }

        for(int i=0;i<chars.length;i++){
            sb.append(chars[i]).append("%");
        }
        return sb.toString();
    }
}
