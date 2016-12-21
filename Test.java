package a2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
//import java.util.Arrays;
//import java.util.Deque;
//import java.util.LinkedList;

//import a2.BinaryTree.BinaryNode;

class Test
{
		public static void main(String[] args) {
			HashMap<String, Integer> s = new HashMap<String, Integer>();
			s.put("S", 1);
			s.put("SS", 3);
			String newStr = "SS@";
			s.put(newStr, s.remove("SS"));
			System.out.println(s.keySet());
			try{
			BinaryTree theTree = new BinaryTree();
			theTree.addNewTag("Boss");
			theTree.addNewTag("Vice");
			theTree.addNewTag("Office");
			theTree.addNewTag("Secre");
			theTree.addNewTag("Sales");
			System.out.println(theTree.viewTag());
			//System.out.println(theTree.findNode(1));
			theTree.deleteTag("Vice");
			System.out.println(theTree.nameHistory());
            System.out.println(theTree.viewTag());	
			System.out.println(theTree.chooseName("Vice"));			
            System.out.println(theTree.viewTag());			
			System.out.println(theTree.nameHistory());
		    OutputStream file = new FileOutputStream("/Users/jafarlielvin/Desktop/quarks.csv");
		    OutputStream buffer = new BufferedOutputStream(file);
		    ObjectOutput output = new ObjectOutputStream(buffer);
			try{
				output.writeObject(theTree);
			}
			finally{
			output.close();
		
			}
			
			
		}
			catch (Exception e){
			e.printStackTrace();	
			}
			
		BinaryTree theTree = null;
			
		try{
		      //use buffering
		      InputStream file = new FileInputStream("/Users/jafarlielvin/Desktop/quarks.csv");
		      InputStream buffer = new BufferedInputStream(file);
		      ObjectInput input = new ObjectInputStream (buffer);
		      try{
		        //deserialize the List
		        theTree = (BinaryTree) input.readObject();
		      }
		      finally{
		        input.close();
		      }
		    }catch(IOException i) {
		         i.printStackTrace();
		         return;
		      }catch(ClassNotFoundException c) {
		         System.out.println("Some problem occured while reading...");
		         c.printStackTrace();
		         return;
		      }
		     System.out.println(theTree.nameHistory());
		     theTree.addNewTag("Sale");
		     System.out.println(theTree.nameHistory());
		}
}

