package jp.co.reggie.mbpdeal.handler;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

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
		metaObject.setValue("createdTime", LocalDateTime.now());
		metaObject.setValue("updatedTime", LocalDateTime.now());
		metaObject.setValue("createdUser", BaseContext.getCurrentId());
		metaObject.setValue("updatedUser", BaseContext.getCurrentId());
	}

	/**
	 * 更新操作，自動填充
	 *
	 * @param metaObject 任意填充類
	 */
	@Override
	public void updateFill(final MetaObject metaObject) {
		log.info("公共字段自動填充[update]...");
		metaObject.setValue("updatedTime", LocalDateTime.now());
		metaObject.setValue("updatedUser", BaseContext.getCurrentId());
	}
}