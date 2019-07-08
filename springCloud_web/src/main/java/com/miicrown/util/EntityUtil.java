package com.miicrown.util;

import java.lang.reflect.Constructor;

public class EntityUtil {

	public static <T> T caseDto(Object[] objectArray, Class[] objectClassArray, Class<T> dtoClass) throws Exception {
	    Constructor<T> constructor = dtoClass.getConstructor(objectClassArray);
	    return constructor.newInstance(objectArray);
	}	
	
}
