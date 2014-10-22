/**
 * 
 */
package com.zhu.kaoqin.service;

import java.io.File;

/**
 *
 * @作者： 朱伟亮
 * @创建时间：2013-4-12 下午4:16:13
 */
public interface FileReadServiceI {

	public abstract String readFileByChars(String filePath);

	public abstract String readFileByChars(File file);

	public abstract String fileReadByLines(String filePath, boolean isTrim);

	public abstract String fileReadByLines(File file, boolean isTrim);

}