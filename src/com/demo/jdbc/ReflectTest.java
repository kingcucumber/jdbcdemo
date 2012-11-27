package com.demo.jdbc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.demo.jdbc.domain.User;

public class ReflectTest {

	public static void main(String[] args) throws Exception {
		User user = (User) create(User.class);
		//invoke1(user, "showMessage");
		// System.out.println(user);
		// Class clazz = Bean.class;
		// Object obj = create(clazz);
		// System.out.println(obj);
		//
		Class clz = user.getClass();
		annon(clz);
	}

	static Object create(Class clazz) throws Exception {
		Constructor con = clazz.getConstructor(String.class);

		Object obj = con.newInstance("test");
		return obj;
	}

	static void invoke1(Object obj, String methodName)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {

		Method[] method = obj.getClass().getDeclaredMethods();
		for (Method m : method) {
			if (m.equals(methodName))
				m.invoke(obj, null);
			// System.out.println(m.getName());
		}
	}
	
	static void annon(Class clazz) throws Exception{
		
		Annotation[] as = clazz.getAnnotations();
		System.out.println(as.toString());
	}
}
