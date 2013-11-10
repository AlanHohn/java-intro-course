package org.anvard.introtojava.spring;

import static org.anvard.introtojava.spring.StaticContext.create;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class StaticContextTest {

	@Test
	public void basicBean() {
		StaticContext sc = create();
		sc.bean("basic", InnerBean.class).prop("prop1", "abc").prop("prop2", "def").build();
		ApplicationContext ctx = sc.build();
		assertNotNull(ctx);
		assertFalse(ctx.containsBean("inner"));
		assertFalse(ctx.containsBean("outer"));
		InnerBean bean = (InnerBean) ctx.getBean("basic");
		assertNotNull(bean);
		assertEquals("abc", bean.getProp1());
		assertEquals("def", bean.getProp2());
	}
	
	@Test
	public void innerBean() {
		StaticContext sc = create();
		sc.bean("outer", OuterBean.class).prop("prop1", "xyz").ref("inner", "inner").build();
		sc.bean("inner", InnerBean.class).prop("prop1", "ghi").prop("prop2", "jkl").build();
		ApplicationContext ctx = sc.build();
		assertNotNull(ctx);
		assertFalse(ctx.containsBean("basic"));
		InnerBean inner = (InnerBean) ctx.getBean("inner");
		assertNotNull(inner);
		assertEquals("ghi", inner.getProp1());
		assertEquals("jkl", inner.getProp2());
		OuterBean outer = (OuterBean) ctx.getBean(OuterBean.class);
		assertNotNull(outer);
		assertEquals("xyz", outer.getProp1());
		assertEquals(inner, outer.getInner());
	}
}
