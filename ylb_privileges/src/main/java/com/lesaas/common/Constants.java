/**
 * Copyright 1996-2006 The Letech Software Tech Ltd.
 */

package com.lesaas.common;

import org.springframework.context.ApplicationContext;


public class Constants{
	public static ApplicationContext Context = null; //该值会在web容器启动时由WebAppContextListener初始化
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
}