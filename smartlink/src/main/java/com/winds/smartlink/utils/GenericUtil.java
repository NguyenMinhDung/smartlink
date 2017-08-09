package com.winds.smartlink.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Windreams
 *
 */
public class GenericUtil {
	
	/**
	 * Find name of field.
	 * @param key
	 * @param fields
	 * @return name of field
	 */
	public static String hasInFields(Object key, Field[] fields) {
		for (Field field : fields) {
			if (field.getName().toUpperCase().equals(((String)key).toUpperCase()))
				return field.getName();
		}
		return null;
	}
	
	/**
	 * Set value to property.
	 * @param object
	 * @param proName
	 * @param value
	 */
	public static void applyValue2Property(Object object, String proName,
			String value) {
		try {
			Class<?> clz = object.getClass().getDeclaredField(proName).getType();
			String proNameUpperFirst = proName.substring(0, 1).toUpperCase() + proName.substring(1);
			Method setMethod = object.getClass().getMethod("set" + proNameUpperFirst, clz);
			if (StringUtils.isNullOrEmpty(value)) {
				setMethod.invoke(object, new Object[] {null});
			}
			else {
				if (Integer.class.equals(clz) || int.class.equals(clz)) {
					setMethod.invoke(object, Integer.parseInt(value));
				} else if (Long.class.equals(clz) || long.class.equals(clz)) {
					setMethod.invoke(object, Long.parseLong(value));
				} else if (String.class.equals(clz)) {
					setMethod.invoke(object, value);
				} else if (Double.class.equals(clz) || double.class.equals(clz)) {
					setMethod.invoke(object, Double.parseDouble(value));
				} else if (Float.class.equals(clz) || float.class.equals(clz)) {
					setMethod.invoke(object, Float.parseFloat(value));
				} else if (Boolean.class.equals(clz)
						|| boolean.class.equals(clz)) {
					setMethod.invoke(object, Boolean.parseBoolean(value));
				} else if (Date.class.equals(clz)) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = simpleDateFormat.parse(value);
					setMethod.invoke(object, date);
				} else if (BigDecimal.class.equals(clz)) {
					setMethod.invoke(object, new BigDecimal(value));
				}
			}

		} catch (Exception e) {
		}
	}
}
