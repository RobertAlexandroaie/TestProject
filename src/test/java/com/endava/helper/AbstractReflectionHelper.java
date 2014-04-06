/**
 * 
 */
package com.endava.helper;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

/**
 * 
 * Utility class used to inject dependencies in tested components.
 * 
 * @author mcristea
 * 
 */
public class AbstractReflectionHelper {

	protected final Logger LOG = Logger
			.getLogger(AbstractReflectionHelper.class);

	protected void injectDependency(final Object target, final String fieldName,
			final Object fieldValue) {

		try {
			final Class<?> clazz = target.getClass();
			final Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(target, fieldValue);
		} catch (final Exception e) {
			LOG.error("Inject dependency error.", e);
		}
	}

	protected void injectParentDependency(final Object target, final String fieldName,
			final Object fieldValue, int parentLevel) {

		if (0 >= parentLevel) {
			throw new IllegalArgumentException(
					"parentLevel parameter must be positive");
		}
		try {
			Class<?> clazz = target.getClass();
			while (parentLevel-- != 0 && null != clazz) {
				clazz = clazz.getSuperclass();
			}
			if (null == clazz || Object.class.equals(clazz)) {
				LOG.error("No parent available");
				return;
			}
			if (clazz.equals(target.getClass())) {
				LOG.error("Same object. No parent available");
				return;
			}
			final Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(target, fieldValue);
		} catch (final Exception e) {
			LOG.error("Inject dependency error.", e);
		}
	}
}
