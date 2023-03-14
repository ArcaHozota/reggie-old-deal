package jp.co.reggie.oldeal.handler;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import jp.co.reggie.oldeal.common.BaseContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	/**
	 * 插入操作，自動填充
	 *
	 * @param metaObject 任意填充類
	 */
	@Override
	public void insertFill(final MetaObject metaObject) {
		log.info("公共字段自動填充[insert]...");
		final Long currentId = BaseContext.getCurrentId();
		metaObject.setValue("createTime", LocalDateTime.now());
		metaObject.setValue("updateTime", LocalDateTime.now());
		metaObject.setValue("createUser", currentId);
		metaObject.setValue("updateUser", currentId);
	}

	/**
	 * 更新操作，自動填充
	 *
	 * @param metaObject 任意填充類
	 */
	@Override
	public void updateFill(final MetaObject metaObject) {
		log.info("公共字段自動填充[update]...");
		final Long currentId = BaseContext.getCurrentId();
		metaObject.setValue("updateTime", LocalDateTime.now());
		metaObject.setValue("updateUser", currentId);
	}
}