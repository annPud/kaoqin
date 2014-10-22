/**
 * 
 */
package com.zhu.kaoqin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 *
 * @作者： 朱伟亮
 * @创建时间：2013-4-12 下午3:23:44
 */
public class StringUtil {

	/**
	 * 去除空格回车tab，压缩字符串长度
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-4-12 下午3:25:38
	 * @param data
	 * @return
	 */
	public static String trimExpert(String data){
		String result = data;
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(result);
		result = m.replaceAll("");
		return result;
	}
}
