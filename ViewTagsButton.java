package a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ViewTagsButton extends JButton implements ActionListener{
	
	TreeMap tree = TreeMap.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ViewTagsButton(){};
	
	ViewTagsButton(String label)
	{
		super(label);
		this.addActionListener((ActionListener) this);
	}
	/** 
	 * View all tags that name have now. 
	 * 
	 * @param e Action Event
	 * @see A new frame that we can see name's history.
	 */
	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame("View Tags Box");
		String picPath = GUI.pressedPic;
		//System.out.println(picPath);
		//System.out.println(GUI.treeMap.keySet());
		System.out.println(picPath);
		
		String tags = tree.treeMap.get(picPath).viewTag();
		JOptionPane.showMessageDialog(null, tags);
	}

}
