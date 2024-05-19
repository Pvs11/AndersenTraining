package config;

import service.MemorizeService;
import service.MemorizeServiceImpl;

public class ServletConfiguration {
	private static final MemorizeService service = new MemorizeServiceImpl();
	public static MemorizeService getService() {
		return service;
	}
}