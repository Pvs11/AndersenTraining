package lesson3;

import lesson2.MemorizeService;
import lesson2.MemorizeServiceImpl;

public class ServletConfiguration {
	private static final MemorizeService service = new MemorizeServiceImpl();
	public static MemorizeService getService() {
		return service;
	}
}