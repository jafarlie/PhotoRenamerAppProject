package a2;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Unittest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	@Test
	public void testAddTagValue(){
		BinaryTree t = new BinaryTree();
		t.addNewTag("Hello");
		String resultTag = t.viewTag();
		String real = "@Hello";
		assertEquals(real, resultTag);
	}
	@Test
	public void testremoveValue(){
		BinaryTree t = new BinaryTree();
		t.addNewTag("Hello");
		t.deleteTag("Hello");
		String resultTag = t.viewTag();
		String real = "";
		assertEquals(real, resultTag);
	}
	@Test
	public void testnamehistory(){
		BinaryTree t = new BinaryTree();
		t.addNewTag("He");
		t.deleteTag("He");
		String resultTag = t.nameHistory();
		String real = "He ";
		System.out.println(resultTag);
		System.out.println(real);
		assertEquals(real, resultTag);
	}
	@Test
	public void testchoosefromold(){
		BinaryTree t = new BinaryTree();
		t.addNewTag("Hello");
		t.deleteTag("Hello");
		t.chooseName("Hello");
		String resultTag = t.viewTag();
		String real = "@Hello";
		assertEquals(real, resultTag);
	}
	@Test
	public void testviewname(){
		BinaryTree t = new BinaryTree();
		t.addNewTag("Hello");
		String resultTag = t.viewTag();
		String real = "@Hello";
		assertEquals(real, resultTag);
	}
	@Test
	public void testviename(){
		BinaryTree t = new BinaryTree();
		String resultTag = t.viewTag();
		String real = "No tags to view!";
		assertEquals(real, resultTag);
	}

}