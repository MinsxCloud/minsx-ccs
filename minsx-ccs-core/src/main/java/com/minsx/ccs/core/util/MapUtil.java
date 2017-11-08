package com.minsx.ccs.core.util;

import java.util.Map;

public class MapUtil {
	
	public static String mapToString(Map<? extends Object, ? extends Object> map){
		StringBuffer result = new StringBuffer();
		map.forEach((key,value)->{
			result.append(key.toString()).append(" : ").append(value.toString()).append(" , ");
		});
		result.delete(result.length()-1, result.length());
		return result.toString();
	}

}
