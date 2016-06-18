package com.github.chenyiliang.shiro.chapter10.session.scheduler;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.AbstractValidatingSessionManager;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ReflectionUtils;

import com.github.chenyiliang.shiro.chapter10.JdbcTemplateUtils;
import com.github.chenyiliang.shiro.chapter10.SerializableUtils;

public class MySessionValidationScheduler implements SessionValidationScheduler, Runnable {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

	private static final Logger LOG = LoggerFactory.getLogger(MySessionValidationScheduler.class);

	private ValidatingSessionManager sessionManager;
	private ScheduledExecutorService service;
	private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
	private boolean enabled = false;

	public ValidatingSessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(ValidatingSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Creates a single thread {@link ScheduledExecutorService} to validate
	 * sessions at fixed intervals and enables this scheduler. The executor is
	 * created as a daemon thread to allow JVM to shut down
	 */
	// TODO Implement an integration test to test for jvm exit as part of the
	// standalone example
	// (so we don't have to change the unit test execution model for the core
	// module)
	@Override
	public void enableSessionValidation() {
		if (this.interval > 0l) {
			this.service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
				@Override
				public Thread newThread(Runnable r) {
					Thread thread = new Thread(r);
					thread.setDaemon(true);
					return thread;
				}
			});
			this.service.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
			this.enabled = true;
		}
	}

	@Override
	public void run() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Executing session validation...");
		}
		long startTime = System.currentTimeMillis();

		// 分页获取会话并验证
		String sql = "SELECT session FROM sessions LIMIT ?,?";
		int start = 0;// 起始记录
		int size = 20;// 每页大小
		List<String> sessionList;
		do {
			sessionList = jdbcTemplate.queryForList(sql, String.class, start, size);
			for (String sessionStr : sessionList) {
				try {
					Session session = SerializableUtils.deserialize(sessionStr);
					Method validateMethod = ReflectionUtils.findMethod(AbstractValidatingSessionManager.class,
							"validate", Session.class, SessionKey.class);
					validateMethod.setAccessible(true);
					ReflectionUtils.invokeMethod(validateMethod, sessionManager, session,
							new DefaultSessionKey(session.getId()));
				} catch (Exception e) {
					// ignore
				}
			}
			start = start + size;
		} while (sessionList != null && sessionList.size() > 0);

		long stopTime = System.currentTimeMillis();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Session validation completed successfully in {} milliseconds.", stopTime - startTime);
		}
	}

	@Override
	public void disableSessionValidation() {
		this.service.shutdownNow();
		this.enabled = false;
	}

}
