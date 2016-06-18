package com.github.chenyiliang.shiro.chapter10.session.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import com.github.chenyiliang.shiro.chapter10.JdbcTemplateUtils;
import com.github.chenyiliang.shiro.chapter10.SerializableUtils;

public class MySessionDAO extends CachingSessionDAO {
	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		String sql = "INSERT INTO sessions (id, session) values (?,?)";
		jdbcTemplate.update(sql, sessionId, SerializableUtils.serialize(session));
		return session.getId();
	}

	@Override
	protected void doUpdate(Session session) {
		if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
			return;// 如果会话过期/停止 没必要再更新了
		}
		String sql = "UPDATE sessions SET session = ? WHERE id = ?";
		jdbcTemplate.update(sql, SerializableUtils.serialize(session), session.getId());
	}

	@Override
	protected void doDelete(Session session) {
		String sql = "DELETE FROM sessions WHERE id = ?";
		jdbcTemplate.update(sql, session.getId());
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		String sql = "SELECT session FROM sessions WHERE id = ?";
		List<String> sessionStrList = jdbcTemplate.queryForList(sql, String.class, sessionId);
		if (sessionStrList.size() == 0) {
			return null;
		}
		return SerializableUtils.deserialize(sessionStrList.get(0));
	}

}
