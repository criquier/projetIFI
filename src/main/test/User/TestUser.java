package User;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ifi.model.User;

public class TestUser {
	private User Usernull,UserAll,Userbis,Userother;
	
	@Before
	public void setUp() throws Exception {
		Usernull=new User("","");
		UserAll=new User("toto","titi");
		Userbis=new User("toto","titi");
		Userother=new User("tutu","tutu");
	}
	@Test
	public void NotEmptymdp()
	{
		assertFalse(Usernull.equals(null));
	}
	public void NotEmptylogin()
	{
		assertFalse(Usernull.equals(null));
	}
	public void NotEmptymdpAndlogin()
	{
		assertTrue(Userother.equals(null)||Userother.equals(null));
	}
	
	
	@Test
	public void testSamemdp()
	{
		assertTrue(UserAll.getPassword().equals(Userbis.getPassword()));
	}
	
	@Test
	public void testSamelogin()
	{
		assertTrue(UserAll.getLoggin().equals(Userbis.getLoggin()));
	}
	@Test
	public void testDifferentmdp()
	{
		assertFalse(UserAll.equals(Userother));
	}
	@Test
	public void testDifferentlogin()
	{
		assertFalse(UserAll.equals(Userother));
	}
	@Test
	public void testSameUser()
	{
		assertTrue(UserAll.getLoggin().equals(Userbis.getLoggin())&& UserAll.getPassword().equals(Userbis.getPassword()));
	}

}
