package org.anvard.introtojava.spring;

public class OuterBean {

	private String prop1;
	private InnerBean inner;
	public String getProp1() {
		return prop1;
	}
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}
	public InnerBean getInner() {
		return inner;
	}
	public void setInner(InnerBean inner) {
		this.inner = inner;
	}
	
}
