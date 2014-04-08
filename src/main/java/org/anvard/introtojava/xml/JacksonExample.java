package org.anvard.introtojava.xml;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonExample {

    public static class ConfigData {
	public String savePath;
	public Integer maxSize;
    }

    public static void main(String[] args) throws Exception {
	ObjectMapper mapper = new ObjectMapper();
	ConfigData config = mapper.readValue(
		JacksonExample.class.getResourceAsStream("config.json"),
		ConfigData.class);
	System.out.println("Save path: " + config.savePath);
	System.out.println("Max size: " + config.maxSize);
    }
}
