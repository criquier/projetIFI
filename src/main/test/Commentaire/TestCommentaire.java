package Commentaire;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;



import com.ifi.model.Commentaire;
import com.ifi.model.User;

public class TestCommentaire {
	private Commentaire CommentaireAll,Commentairebis,Commentaireother;
	
	@Before
	public void setUp() throws Exception {
		User author=new User("toto","toto");
		User authorbis=new User("titi","titi");
		CommentaireAll=new Commentaire((long) 1.0,"BD","16/12/2013",author);
		Commentairebis=new Commentaire((long) 1.0,"BD","16/12/2013",author);
		Commentaireother=new Commentaire((long) 2.0,"Manga","17/12/2013",authorbis);
	}
	@Test
	public void NotEmptyContent()
	{
		assertTrue(!CommentaireAll.getContenu().equals(""));
	}
	@Test
	public void NotEmptyAuthor()
	{
		assertTrue(!CommentaireAll.getAuteur().equals(""));
	}	
	@Test
	public void NotEmptyDate()
	{
		assertTrue(!CommentaireAll.getDate().equals(""));
	}
	@Test
	public void testSameID()
	{
		assertTrue(CommentaireAll.getIdCommentaire()==Commentairebis.getIdCommentaire());
	}	
	@Test
	public void testSameContent()
	{
		assertTrue(CommentaireAll.getContenu().equals(Commentairebis.getContenu()));
	}
	@Test
	public void testSameAuthor()
	{
		assertTrue(CommentaireAll.getAuteur().equals(Commentairebis.getAuteur()));
	}
	@Test
	public void testSameDate()
	{
		assertTrue(CommentaireAll.getDate().equals(Commentairebis.getDate()));
	}
	@Test
	public void testDifferentID()
	{
		assertTrue(CommentaireAll.getIdCommentaire()!=Commentaireother.getIdCommentaire());
	}	
	@Test
	public void testDifferentDate()
	{
		assertTrue(!CommentaireAll.getDate().equals(Commentaireother.getDate()));
	}
	@Test
	public void testDifferentContent()
	{
		assertFalse(CommentaireAll.getContenu().equals(Commentaireother.getContenu()));
	}
	@Test
	public void testDifferentAuthor()
	{
		assertFalse(CommentaireAll.getAuteur().equals(Commentaireother.getAuteur()));
	}
	@Test
	public void testSameCommentaire()
	{
		//assertTrue(CommentaireAll.getTitre().equals(Commentairebis.getTitre()) && CommentaireAll.getContenu().equals(Commentairebis.getContenu()) && CommentaireAll.getAuteur().equals(Commentairebis.getAuteur()));
		assertTrue(CommentaireAll.getContenu().equals(Commentairebis.getContenu()) && CommentaireAll.getDate().equals(Commentairebis.getDate()));
	}
}
