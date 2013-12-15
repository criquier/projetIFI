package Article;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ifi.model.Article;
import com.ifi.model.User;
public class TestArticle {
		private Article ArticleAll,Articlebis,Articleother;
		
		@Before
		public void setUp() throws Exception {
			ArticleAll=new Article((long) 1.0,"BD","BD");
			Articlebis=new Article((long) 1.0,"BD","BD");
			Articlebis.setAuteur(new User("toto","titi"));
			Articleother=new Article((long) 2.0,"Manga","Manga");
			Articleother.setDate("16/12/2000");
		}
		@Test
		public void NotEmptyTitle()
		{
			assertTrue(!ArticleAll.getTitre().equals(""));
		}
		@Test
		public void NotEmptyContent()
		{
			assertTrue(!ArticleAll.getContenu().equals(""));
		}	
		@Test
		public void NotEmptyDate()
		{
			assertTrue(!ArticleAll.getDate().equals(""));
		}
		@Test
		public void testSameID()
		{
			assertTrue(ArticleAll.getId()==Articlebis.getId());
		}	
		@Test
		public void testSameTitle()
		{
			assertTrue(ArticleAll.getTitre().equals(Articlebis.getTitre()));
		}
		@Test
		public void testSameContent()
		{
			assertTrue(ArticleAll.getContenu().equals(Articlebis.getContenu()));
		}
		@Test
		public void testSameDate()
		{
			assertTrue(ArticleAll.getDate().equals(Articlebis.getDate()));
		}
		@Test
		public void testDifferentID()
		{
			assertTrue(ArticleAll.getId()!=Articleother.getId());
		}	
		@Test
		public void testDifferentDate()
		{
			assertTrue(!ArticleAll.getDate().equals(Articleother.getDate()));
		}
		@Test
		public void testDifferentTitle()
		{
			assertFalse(ArticleAll.getTitre().equals(Articleother.getTitre()));
		}
		@Test
		public void testDifferentContent()
		{
			assertFalse(ArticleAll.getContenu().equals(Articleother.getContenu()));
		}
		@Test
		public void testSameArticle()
		{
			//assertTrue(ArticleAll.getTitre().equals(Articlebis.getTitre()) && ArticleAll.getContenu().equals(Articlebis.getContenu()) && ArticleAll.getAuteur().equals(Articlebis.getAuteur()));
			assertTrue(ArticleAll.getTitre().equals(Articlebis.getTitre()) && ArticleAll.getContenu().equals(Articlebis.getContenu()));
		}

	

}
