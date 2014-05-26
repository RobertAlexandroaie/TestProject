package com.endavafii.helper;

import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;
import org.powermock.reflect.Whitebox;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

public abstract class TestHelper {

	static SecureRandom rand = new SecureRandom();

	public static Collection<Class<?>> modelClasses = Arrays
			.asList(new Class<?>[] {});

	public static void testAccessors(Object modelObject, String... methods)
			throws Exception {
		PropertyDescriptor[] propertyDescriptors = PropertyUtils
				.getPropertyDescriptors(modelObject);
		List<String> methodsList = null;
		if (methods != null) {
			methodsList = Arrays.asList(methods);
		}
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (methodsList != null
					&& methodsList.contains(propertyDescriptor.getName())) {
				continue;
			}
			Method setter = propertyDescriptor.getWriteMethod();
			if (setter != null) {
				Assert.assertEquals(1, setter.getParameterTypes().length);
				Class<?> parameterType = setter.getParameterTypes()[0];
				if (parameterType.isPrimitive()) {
					testPrimitiveParameter(modelObject, propertyDescriptor,
							parameterType);
				} else if (parameterType.isEnum()) {
					@SuppressWarnings("unchecked")
					Class<? extends Enum<?>> enumType = (Class<Enum<?>>) parameterType;
					testEnumParameter(modelObject, propertyDescriptor, enumType);
				} else if (parameterType.isArray()) {
					testArrayParameter(modelObject, propertyDescriptor,
							parameterType);
				} else {
					testObjectParameter(modelObject, propertyDescriptor,
							parameterType);
				}
			}
		}
	}

	private static void testObjectParameter(Object targetObject,
			PropertyDescriptor propertyDescriptor, Class<?> parameterType)
			throws IllegalAccessException, InvocationTargetException {
		Method setter = propertyDescriptor.getWriteMethod();
		Method getter = propertyDescriptor.getReadMethod();
		Object value = generateRandomValue(parameterType);
		setter.invoke(targetObject, value);
		Object result;
		if (getter != null) {
			// use getter to retrieve the value of the property
			result = getter.invoke(targetObject);
		} else {
			// otherwise, retrieve the value directly from the internal field
			result = Whitebox.getInternalState(targetObject,
					propertyDescriptor.getName());
		}
		Assert.assertEquals(value, result);
	}

	private static void testArrayParameter(Object targetObject,
			PropertyDescriptor propertyDescriptor, Class<?> parameterType)
			throws IllegalAccessException, InvocationTargetException {
		Method setter = propertyDescriptor.getWriteMethod();
		Method getter = propertyDescriptor.getReadMethod();
		Object value = Array.newInstance(parameterType.getComponentType(), 1);
		setter.invoke(targetObject, value);
		Object result = getter.invoke(targetObject);
		Class<?> componentType = parameterType.getComponentType();
		if (!componentType.isPrimitive()) {
			Assert.assertArrayEquals((Object[]) value, (Object[]) result);
		} else {
			if (componentType.equals(byte.class)) {
				Assert.assertArrayEquals((byte[]) value, (byte[]) result);
			} else {
				throw new UnsupportedOperationException(
						"Primitive type not supported for array parameter: "
								+ parameterType.getName());
			}
		}
	}

	private static void testEnumParameter(Object targetObject,
			PropertyDescriptor propertyDescriptor,
			@SuppressWarnings("rawtypes") Class<? extends Enum> enumType)
			throws IllegalAccessException, InvocationTargetException {
		Method setter = propertyDescriptor.getWriteMethod();
		Method getter = propertyDescriptor.getReadMethod();
		Field field = enumType.getFields()[0];
		@SuppressWarnings("unchecked")
		Enum<?> value = Enum.valueOf(enumType, field.getName());
		setter.invoke(targetObject, value);
		Object result = getter.invoke(targetObject);
		Assert.assertSame(value, result);
	}

	private static void testPrimitiveParameter(Object targetObject,
			PropertyDescriptor propertyDescriptor, Class<?> parameterType)
			throws IllegalAccessException, InvocationTargetException {
		Method setter = propertyDescriptor.getWriteMethod();
		Method getter = propertyDescriptor.getReadMethod();
		Object value = generateRandomValue(parameterType);
		setter.invoke(targetObject, value);
		Object result = getter.invoke(targetObject);
		Assert.assertEquals(value, result);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object generateRandomValue(Class<?> type) {
		Assert.assertNotNull(type);
		Object randomValue = null;
		if (type.equals(boolean.class) || type.equals(Boolean.class)) {
			randomValue = rand.nextBoolean();
		} else if (type.equals(byte.class) || type.equals(Byte.class)) {
			randomValue = (byte) rand.nextInt(Byte.MAX_VALUE);
		} else if (type.equals(short.class) || type.equals(Short.class)) {
			randomValue = (short) rand.nextInt(Short.MAX_VALUE);
		} else if (type.equals(int.class) || type.equals(Integer.class)) {
			randomValue = rand.nextInt();
		} else if (type.equals(long.class) || type.equals(Long.class)) {
			randomValue = rand.nextLong();
		} else if (type.equals(float.class) || type.equals(Float.class)) {
			randomValue = rand.nextFloat();
		} else if (type.equals(double.class) || type.equals(Double.class)) {
			randomValue = rand.nextDouble();
		} else if (type.equals(String.class)) {
			randomValue = UUID.randomUUID().toString();
		} else if (type.equals(Date.class)) {
			randomValue = new Date(rand.nextLong());
		} else if (type.equals(BigDecimal.class)) {
			randomValue = BigDecimal.valueOf(rand.nextLong());
		} else if (type.equals(List.class)) {
			randomValue = PowerMock.createMock(List.class);
		} else if (type.isArray()) {
			randomValue = Array.newInstance(type.getComponentType(), 1);
		} else if (type.isEnum()) {
			Field field = type.getFields()[0];
			randomValue = Enum.valueOf((Class<? extends Enum>) type,
					field.getName());
		} else {
			randomValue = PowerMock.createMock(type);
			// throw new
			// UnsupportedOperationException("Please define random value generation for this type: "
			// + type);
		}
		return randomValue;
	}

	/**
	 * Tests the hashCode() contract for each of the model classes.
	 * 
	 * @throws Exception
	 */
	@Test
	public static void testHashCodeContract(List<Class<?>> classes)
			throws Exception {
		for (Class<?> classs : classes) {
			Object modelInstance = getPopulatedBean(classs);

			// hashCode() method must consistently return the same integer,
			// provided no information used in equals
			// comparisons on the object is modified
			Assert.assertTrue(modelInstance.hashCode() == modelInstance
					.hashCode());

			// if two objects are equal according to the equals(Object) method,
			// then calling the hashCode method on each
			// of the two objects must produce the same integer result.
			Object otherModelInstance = BeanUtils.cloneBean(modelInstance);
			Assert.assertTrue(modelInstance.equals(otherModelInstance));
			Assert.assertTrue(modelInstance.hashCode() == otherModelInstance
					.hashCode());
		}
	}

	/**
	 * Tests equals() as an equivalence relation.
	 * 
	 * @throws Exception
	 */
	@Test
	public static void testEquals(List<Class<?>> classes) throws Exception {
		for (Class<?> classs : classes) {
			Object modelInstance = getPopulatedBean(classs);
			Object equalModelInstance = BeanUtils.cloneBean(modelInstance);
			Object differentModelInstance = getPopulatedBean(classs);

			// reflexivity
			Assert.assertTrue(modelInstance.equals(modelInstance));

			// symmetry
			Assert.assertTrue(modelInstance.getClass().toString(),
					modelInstance.equals(equalModelInstance));
			Assert.assertTrue(equalModelInstance.equals(modelInstance));

			Assert.assertFalse(modelInstance.equals(differentModelInstance));
			Assert.assertFalse(differentModelInstance.equals(modelInstance));

			// skip transitivity

			// consistency
			Assert.assertTrue(modelInstance.equals(equalModelInstance));

			// null
			Assert.assertFalse(modelInstance.equals(null));

			// additional check (for code coverage)
			Assert.assertFalse(modelInstance.equals(new Object()));
		}
	}

	public static Object getPopulatedBean(Class<?> beanClass)
			throws IllegalAccessException, InvocationTargetException {
		Object beanInstance = Whitebox.newInstance(beanClass);
		PropertyDescriptor[] propertyDescriptors = PropertyUtils
				.getPropertyDescriptors(beanInstance);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			Method setter = propertyDescriptor.getWriteMethod();
			if (setter != null) {
				Assert.assertEquals(1, setter.getParameterTypes().length);
				Class<?> parameterType = setter.getParameterTypes()[0];
				Object attributeValue = null;
				if (modelClasses.contains(parameterType)) {
					attributeValue = getPopulatedBean(parameterType);
				} else {
					attributeValue = generateRandomValue(parameterType);
				}
				setter.invoke(beanInstance, attributeValue);
			}
		}
		return beanInstance;
	}

	/**
	 * 
	 * @param path
	 *            The path to the file
	 * @return The file content as a byte array
	 * @throws FileNotFoundException
	 *             In case the file was not found
	 * @throws IOException
	 *             In case error appeared upon reading
	 */
	public static byte[] readFile(String path) throws FileNotFoundException,
			IOException {
		byte[] fileContent;
		FileInputStream in = new FileInputStream(Thread.currentThread()
				.getContextClassLoader().getResource(path).getFile()
				.replaceAll("%20", " "));
		fileContent = new byte[in.available()];
		in.read(fileContent);
		in.close();
		return fileContent;
	}
	
	/**
	 * Helper method to set a mocked security context
	 * 
	 * @param userSid
	 *            The user SID
	 * @param userDetails
	 *            The object capturing user name and id
	 * @param roles
	 *            The list of roles the user has
	 */
	public static void setSecurityContext(String userSid, Object userDetails,
			String... roles) {
		SecurityContext context = new SecurityContextImpl();
		TestingAuthenticationToken authentication = new TestingAuthenticationToken(
				userSid, "N/A", roles);
		authentication.setDetails(userDetails);
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
	}
	
	public static void setSecurityContextUser(Object user, Object userDetails,
			String... roles) {
		SecurityContext context = new SecurityContextImpl();
		TestingAuthenticationToken authentication = new TestingAuthenticationToken(
				user, "N/A", roles);
		authentication.setDetails(userDetails);
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
	}
	
	public static void setSecurityContextUserNullAuth() {
		SecurityContext context = new SecurityContextImpl();
		SecurityContextHolder.setContext(context);
	}
}
