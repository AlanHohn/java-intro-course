package org.anvard.introtojava.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

public class JaxbExample {

    @XmlRootElement(name="config")
    public static class ConfigData {
	public String savePath;
	public Integer maxSize;
    }
    
    public static void main(String[] args) throws Exception {
	JAXBContext jc = JAXBContext.newInstance(ConfigData.class);
	Unmarshaller u = jc.createUnmarshaller();
	ConfigData config = (ConfigData) u.unmarshal(JaxbExample.class.getResourceAsStream("config.xml"));
	System.out.println("Save path: " + config.savePath);
	System.out.println("Max size: " + config.maxSize);
    }
}
