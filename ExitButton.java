package a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ExitButton extends JButton implements ActionListener{
	
	TreeMap tree = TreeMap.getInstance();
	// Interesting topic about how hide / prevent default constructors
	private ExitButton()
	{};
	
	// The constructor we want to use
	ExitButton(String label)
	{
		super(label); 
		this.addActionListener(this);
	}
	/** 
	 * Exits the program. Puts all information we change to logger.
	 * Puts new hashmap we have to a file.
	 * 
	 * @param e Action Event
	 * @see Program exits.
	 */
    public void actionPerformed(ActionEvent e) {
    	Set se = tree.treeMap.entrySet();
	    Iterator iterato = se.iterator();
	    while(iterato.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterato.next();
	         System.out.print("key: "+ mentry.getKey() + " & Value: ");
	         System.out.println(mentry.getValue());
	      }
	   // System.out.println(ken);
	    OutputStream fil = null;
		try {
			System.out.println(System.getProperty("user.home")+ "/Desktop/Hashmap.ser");
			fil = new FileOutputStream(System.getProperty("user.home")+ "/Desktop/Hashmap.ser");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    OutputStream buffe = new BufferedOutputStream(fil);
	    ObjectOutput output = null;
		try {
			output = new ObjectOutputStream(buffe);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			output.writeObject(tree.treeMap);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
		try {
			output.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		}
		OutputStream file = null;
		try {
			JOptionPane.showMessageDialog(null, "The following is the change you made this time"+"\n"+GUI.kenza);
			String userHomeFolder = System.getProperty("user.home");
			file = new FileOutputStream(userHomeFolder+"/Desktop/logger.csv");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    OutputStream buffer = new BufferedOutputStream(file);
	    ObjectOutput outputt = null;
		try {
			outputt = new ObjectOutputStream(buffer);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			outputt.writeObject(GUI.ken);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
		try {
			outputt.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		}
    	System.exit(0);
    }
	
} 