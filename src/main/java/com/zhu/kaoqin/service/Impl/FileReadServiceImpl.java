/**
 * 
 */
package com.zhu.kaoqin.service.Impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhu.kaoqin.service.FileReadServiceI;
import com.zhu.kaoqin.utils.StringUtil;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-12 下午2:06:21
 */
@Service("fileReadService")
public class FileReadServiceImpl implements FileReadServiceI{

	private Logger log = Logger.getLogger(FileReadServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.zhu.kaoqin.service.Impl.FileReadServiceI#readFileByChars(java.lang.String)
	 */
	@Override
	public String readFileByChars(String filePath) {
		File file = new File(filePath);
		return readFileByChars(file);
	}

	/* (non-Javadoc)
	 * @see com.zhu.kaoqin.service.Impl.FileReadServiceI#readFileByChars(java.io.File)
	 */
	@Override
	@SuppressWarnings("resource")
	public String readFileByChars(File file) {
		Reader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(file));
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉\r不显示
				if ((charread == tempchars.length)
						&& (tempchars[tempchars.length - 1] != '\r')) {
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == '\r') {
							continue;
						} else {
							sb.append(tempchars[i]);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.zhu.kaoqin.service.Impl.FileReadServiceI#fileReadByLines(java.lang.String, boolean)
	 */
	@Override
	public String fileReadByLines(String filePath, boolean isTrim) {
		File file = new File(filePath);
		return fileReadByLines(file, isTrim);
	}

	/* (non-Javadoc)
	 * @see com.zhu.kaoqin.service.Impl.FileReadServiceI#fileReadByLines(java.io.File, boolean)
	 */
	@Override
	public String fileReadByLines(File file, boolean isTrim) {
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 以行为单位读取文件内容，一次读一整行
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 去除空格，压缩数据
				if (isTrim) {
					tempString = StringUtil.trimExpert(tempString);
				}
				sb.append(tempString);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}
}
