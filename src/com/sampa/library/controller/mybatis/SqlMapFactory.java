package com.sampa.library.controller.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SqlMapFactory {

	private final String resource = "com/sampa/library/controller/sql-map-config.xml";

	private static ThreadLocal<SqlMapFactory> THREAD_LOCAL = new ThreadLocal<SqlMapFactory>() {

		@Override
		protected SqlMapFactory initialValue() {
			return new SqlMapFactory();
		}
	};

	private SqlSessionManager sqlSessionManager;
	private SqlSessionFactory sqlSessionFactory;
	private SqlSessionFactory sqlSessionFactoryUnManaged;
	private SqlSession sqlSession;
	private SqlSession sqlSessionUnManaged;

	public static SqlMapFactory instance() {
		return SqlMapFactory.THREAD_LOCAL.get();
	}

	private SqlMapFactory() {
	}

	private SqlSessionManager getSqlSessionManager() {
		if (SqlMapFactory.instance().sqlSessionManager == null) {
			SqlMapFactory.instance().sqlSessionManager = SqlSessionManager.newInstance(SqlMapFactory.instance().getSqlSessionFactory());
		}

		return SqlMapFactory.instance().sqlSessionManager;
	}

	private SqlSessionFactory getSqlSessionFactory() {

		if (SqlMapFactory.instance().sqlSessionFactory == null) {
			try (Reader reader = Resources.getResourceAsReader(this.resource)) {
				SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
				SqlMapFactory.instance().sqlSessionFactory = builder.build(reader);
			} catch (Exception e) {
//				SqlMapFactory.log.error(e, e);
				throw new RuntimeException("Errore nell'inizializzazione della classe SqlSessionFactory. Causa: " + e);
			}
		}

		return SqlMapFactory.instance().sqlSessionFactory;
	}

	private SqlSessionFactory getSqlSessionFactoryUnManaged() {

		if (SqlMapFactory.instance().sqlSessionFactoryUnManaged == null) {
			try (Reader reader = Resources.getResourceAsReader(this.resource)) {
				SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
				SqlMapFactory.instance().sqlSessionFactoryUnManaged = builder.build(reader);
			} catch (Exception e) {
//				SqlMapFactory.log.error(e, e);
				throw new RuntimeException("Errore nell'inizializzazione della classe SqlSessionFactoryUnManaged. Causa: " + e);
			}
		}

		return SqlMapFactory.instance().sqlSessionFactoryUnManaged;
	}

	public SqlSession openSession() {
		if (SqlMapFactory.instance().sqlSession == null) {
			SqlMapFactory.instance().sqlSession = SqlMapFactory.instance().getSqlSessionFactory().openSession();
		}
		return SqlMapFactory.instance().sqlSession;
	}

	public SqlSession openSessionNoAutoCommit() {
		if (SqlMapFactory.instance().sqlSession == null) {
			SqlMapFactory.instance().sqlSession = SqlMapFactory.instance().getSqlSessionFactory().openSession(false);
		}
		return SqlMapFactory.instance().sqlSession;
	}

	public void commitSession() {
		if (SqlMapFactory.instance().sqlSession != null) {
			SqlMapFactory.instance().sqlSession.commit();
		}
	}

	public void closeSession() {
		if (SqlMapFactory.instance().sqlSession != null) {
			SqlMapFactory.instance().sqlSession.close();
			SqlMapFactory.instance().sqlSession = null;
		}
	}

	public void rollbackSession() {
		if (SqlMapFactory.instance().sqlSession != null) {
			SqlMapFactory.instance().sqlSession.rollback();
		}

	}

	public <T> T getMapper(Class<T> type) {
		if (SqlMapFactory.instance().sqlSession != null) {
			return SqlMapFactory.instance().sqlSession.getMapper(type);
		}
		return null;
	}

	public void openSessionLocalManaged() {
		if (SqlMapFactory.instance().sqlSessionManager == null) {
			SqlMapFactory.instance().getSqlSessionManager().startManagedSession(ExecutorType.SIMPLE, TransactionIsolationLevel.READ_COMMITTED);
		}
	}

	public void commitSessionLocalManaged() {
		if (SqlMapFactory.instance().sqlSessionManager != null) {
			SqlMapFactory.instance().sqlSessionManager.commit();
		}
	}

	public void closeSessionLocalManaged() {
		if (SqlMapFactory.instance().sqlSessionManager != null) {
			SqlMapFactory.instance().sqlSessionManager.close();
			SqlMapFactory.instance().sqlSessionManager = null;
		}
	}

	public void rollbackSessionLocalManaged() {
		if (SqlMapFactory.instance().sqlSessionManager != null) {
			SqlMapFactory.instance().sqlSessionManager.rollback();
		}

	}

	public <T> T getMapperLocalManaged(Class<T> type) {
		if (SqlMapFactory.instance().sqlSessionManager != null) {
			return SqlMapFactory.instance().sqlSessionManager.getMapper(type);
		}
		return null;
	}

	public SqlSession openSessionUnManaged() {
		if (SqlMapFactory.instance().sqlSessionUnManaged == null) {
			SqlMapFactory.instance().sqlSessionUnManaged = SqlMapFactory.instance().getSqlSessionFactoryUnManaged().openSession();
		}
		return SqlMapFactory.instance().sqlSessionUnManaged;
	}

	public SqlSession openSessionNoAutoCommitUnManaged() {
		if (SqlMapFactory.instance().sqlSessionUnManaged == null) {
			SqlMapFactory.instance().sqlSessionUnManaged = SqlMapFactory.instance().getSqlSessionFactoryUnManaged().openSession(false);
		}
		return SqlMapFactory.instance().sqlSessionUnManaged;
	}

	public void commitSessionUnManaged() {
		if (SqlMapFactory.instance().sqlSessionUnManaged != null) {
			SqlMapFactory.instance().sqlSessionUnManaged.commit();
		}
	}

	public void closeSessionUnManaged() {
		if (SqlMapFactory.instance().sqlSessionUnManaged != null) {
			SqlMapFactory.instance().sqlSessionUnManaged.close();
			SqlMapFactory.instance().sqlSessionUnManaged = null;
		}
	}

	public void rollbackSessionUnManaged() {
		if (SqlMapFactory.instance().sqlSessionUnManaged != null) {
			SqlMapFactory.instance().sqlSessionUnManaged.rollback();
		}

	}

	public <T> T getMapperUnManaged(Class<T> type) {
		if (SqlMapFactory.instance().sqlSessionUnManaged != null) {
			return SqlMapFactory.instance().sqlSessionUnManaged.getMapper(type);
		}
		return null;
	}

}
