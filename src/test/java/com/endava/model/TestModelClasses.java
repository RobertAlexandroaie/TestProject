package com.endava.model;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.endava.entities.User;
import com.endava.helper.TestHelper;


public class TestModelClasses {

    public static List<Class<?>> modelClasses =
            Arrays.asList(new Class<?>[] {User.class});
    public static List<Class<?>> modelClassesHash =
    	 Arrays.asList(new Class<?>[] {});
    
    public static List<Class<?>> modelClassesEquals =
   	 Arrays.asList(new Class<?>[] {});
    
    static SecureRandom rand = new SecureRandom();

    @Test
    public void testAccessors() throws Exception {
        for (Class<?> modelClass : modelClasses ) {
             Object modelObject = Whitebox.newInstance(modelClass);
             TestHelper.testAccessors(modelObject);
        }
    }
    
    @Test
    public void testToString() throws Exception {
        for (Class<?> modelClass : modelClasses) {
            Object modelInstance = TestHelper.getPopulatedBean(modelClass);
            Assert.assertNotNull(modelInstance.toString());
        }
    }
    
    /**
     * Tests the hashCode() contract for each of the model classes.
     * 
     * @throws Exception
     */
    @Test
    public void testHashCodeContract() throws Exception {
    	TestHelper.testHashCodeContract(modelClassesHash);
    }

    /**
     * Tests equals() as an equivalence relation.
     * 
     * @throws Exception
     */
    @Test
    public void testEquals() throws Exception {
    	TestHelper.testEquals(modelClassesEquals);
    }
}
