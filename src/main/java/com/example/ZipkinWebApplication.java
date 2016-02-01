package com.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;


public class ZipkinWebApplication {

	private static final String ZIPKIN_WEB = "zipkin.web";
	private static final String SERVER_PORT = "server.port";
	private static final String ZIPKIN_WEB_PORT = "zipkin.web.port";

	public static void main(String[] args) {
		args = getArgs(args);
		com.twitter.zipkin.web.Main.main(args);
	}

	private static String[] getArgs(String[] args) {
		Properties properties = getApplicationProperties();
		Set<String> result = new LinkedHashSet<>(Arrays.<String>asList(args));
		for (Object key : properties.keySet()) {
			String name = key.toString();
			if (name.equals(SERVER_PORT)) {
				name = ZIPKIN_WEB_PORT;
			}
			if (name.startsWith(ZIPKIN_WEB)) {
				String value = properties.getProperty(name);
				if (ZIPKIN_WEB_PORT.equals(name) && !value.startsWith(":")) {
					value = ":" + value;
				}
				result.add("-" + name + "=" + value);
			}
		}
		return result.toArray(new String[0]);
	}

	private static Properties getApplicationProperties() {
		Properties properties = new Properties();
		try {
			properties.load(ZipkinWebApplication.class.getClassLoader().getResourceAsStream("application.properties"));
		}
		catch (IOException e) {
		}
		return properties;
	}
}
