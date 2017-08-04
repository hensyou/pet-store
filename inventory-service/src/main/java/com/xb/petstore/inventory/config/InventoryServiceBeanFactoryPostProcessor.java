package com.xb.petstore.inventory.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.Order;
@Order(100)
public class InventoryServiceBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
 
	private static final Logger log = LoggerFactory.getLogger(InventoryServiceBeanFactoryPostProcessor.class);

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.info("My Own BeanFactoryPostProcessor is called");
	}

}
