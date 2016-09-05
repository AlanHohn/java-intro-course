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
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonDaoTest {

	@Mock
	private DataSource ds;
	
	@Mock
	private Connection c;
	
	@Mock
	private PreparedStatement stmt;
	
	@Mock
	private ResultSet rs;
	
	private Person p;
	
	@Before
	public void setUp() throws Exception {
		assertNotNull(ds);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(ds.getConnection()).thenReturn(c);
		
		p = new Person();
		p.setId(1);
		p.setFirstName("Johannes");
		p.setLastName("Smythe");

		when(rs.first()).thenReturn(true);
		when(rs.getInt(1)).thenReturn(1);
		when(rs.getString(2)).thenReturn(p.getFirstName());
		when(rs.getString(3)).thenReturn(p.getLastName());
		when(stmt.executeQuery()).thenReturn(rs);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullCreateThrowsException() {
		new PersonDao(ds).create(null);
	}
			
	@Test
	public void createPerson() {
		new PersonDao(ds).create(p);
	}
	
	@Test
	public void createAndRetrievePerson() throws Exception {
		PersonDao dao = new PersonDao(ds);
		dao.create(p);
		Person r = dao.retrieve(1);
		assertEquals(p, r);
	}
		
}
