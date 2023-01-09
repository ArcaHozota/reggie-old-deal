package jp.co.reggie.oldeal.utils;

import java.io.Serializable;
import java.util.Properties;

import jp.co.reggie.oldeal.config.Snowflake;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class SnowflakeIdGenerator implements IdentifierGenerator {

	@Override
	public void configure(final Type type, final Properties params, final ServiceRegistry serviceRegistry)
			throws MappingException {
	}

	@Override
	public Serializable generate(final SharedSessionContractImplementor session, final Object object)
			throws HibernateException {
		return SpringUtils.getBean(Snowflake.class).nextId();
	}
}
