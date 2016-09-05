package org.anvard.introtojava.jdbc;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.anvard.introtojava.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class ComplexPersonDaoTest {

	@Mock
	private DataSource ds;
	
	@Mock
	private Connection c;
	
	@Mock
	private PreparedStatement stmt;
	
	@Mock
	private PreparedStatement retrieveStmt;
	
	@Mock
	private ResultSet rs;
	
	@Mock
	private ResultSet nullRs;

	private Person p;
	private int lastRetrieve;
	
	@Before
	public void setUp() throws Exception {
		assertNotNull(ds);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(c.prepareStatement(startsWith("SELECT"))).thenReturn(retrieveStmt);
		when(ds.getConnection()).thenReturn(c);
		
		p = new Person();
		p.setId(1);
		p.setFirstName("Johannes");
		p.setLastName("Smythe");

		when(rs.first()).thenReturn(true);
		when(rs.getInt(1)).thenReturn(1);
		when(rs.getString(2)).thenReturn(p.getFirstName());
		when(rs.getString(3)).thenReturn(p.getLastName());
		
		when(nullRs.first()).thenReturn(false);

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				lastRetrieve = (int)invocation.getArguments()[1];
				return null;
			}
			
		}).when(retrieveStmt).setInt(eq(1), anyInt());
		when(retrieveStmt.executeQuery()).thenAnswer(new Answer<ResultSet>() {
			@Override
			public ResultSet answer(InvocationOnMock invocation) throws Throwable {
				return lastRetrieve == 1 ? rs : nullRs;
			}
		});
	}

	@Test
	public void createAndRetrievePerson() throws Exception {
		PersonDao dao = new PersonDao(ds);
		dao.create(p);
		assertEquals(p, dao.retrieve(1));
	}
	
	@Test
	public void wrongIdReturnsNull() throws Exception {
		PersonDao dao = new PersonDao(ds);
		dao.create(p);
		assertEquals(p, dao.retrieve(1));
		for (int i = 2; i < 10; i++) {
			assertNull(dao.retrieve(i));
		}
		assertEquals(p, dao.retrieve(1));
	}
}
