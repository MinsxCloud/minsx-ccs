package com.minsx.ccs.api;

import java.util.HashMap;
import java.util.Map;

import com.minsx.ccs.core.service.CCSClient;

public class ClientFactory {
	
	private final static Map<String, CCSClient> CCS_CLIENT_POOL = new HashMap<>();
	
	public static void addCCSClient(String name ,CCSClient ccsClient) {
		CCS_CLIENT_POOL.put(name, ccsClient);
	}
	
	public static void removeCCSClient(String name) {
		if (CCS_CLIENT_POOL.containsKey(name)) {
			if (CCS_CLIENT_POOL.get(name)!=null) {
				CCS_CLIENT_POOL.get(name).shutdown();
			}
			CCS_CLIENT_POOL.remove(name);
		}
	}
	
	public static CCSClient getCCSClient(String name) {
		return CCS_CLIENT_POOL.get(name);
	}

}
