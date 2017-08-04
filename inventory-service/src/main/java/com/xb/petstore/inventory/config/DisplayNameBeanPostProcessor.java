package com.xb.petstore.inventory.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;

@Order(100)
public class DisplayNameBeanPostProcessor implements BeanPostProcessor {
	
	private static final Logger log = LoggerFactory.getLogger(DisplayNameBeanPostProcessor.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		log.info("postProcessBeforeInitialization:  "+beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		log.info("postProcessAfterInitialization: "+beanName);
		return bean;
	}

}
