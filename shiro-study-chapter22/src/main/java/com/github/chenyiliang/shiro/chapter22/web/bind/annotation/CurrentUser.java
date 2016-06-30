package com.github.chenyiliang.shiro.chapter22.web.bind.annotation;

import java.lang.annotation.*;

import com.github.chenyiliang.shiro.chapter22.web.Constants;

/**
 * <p>
 * 绑定当前登录的用户
 * </p>
 * <p>
 * 不同于@ModelAttribute
 * </p>
 *
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

	/**
	 * 当前用户在request中的名字
	 *
	 * @return
	 */
	String value() default Constants.CURRENT_USER;

}
