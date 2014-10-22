/**
 * 
 */
package com.zhu.kaoqin.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-10 下午5:26:15
 */
public class Spring implements BeanFactoryAware {

	private static BeanFactory beanFactory = null;

	@Override
	public void setBeanFactory(BeanFactory bf) throws BeansException {
		Spring.beanFactory = bf;
	}

	public static Object getBean(String beanName) {
		return beanFactory.getBean(beanName);
	}

}
