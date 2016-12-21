package a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NameHistory extends JButton implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TreeMap tree = TreeMap.getInstance();

	private NameHistory(){};
	
	NameHistory(String label)
	{
		super(label);
		this.addActionListener((ActionListener) this);
	}
	/** 
	 * Sees all tags that once inclueded in names's history. 
	 * 
	 * @param e Action Event
	 * @see A new frame that we can see name's history.
	 */
	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame("See Name History");
		String picPath = GUI.pressedPic;
		//System.out.println(picPath);
		//System.out.println(GUI.treeMap.keySet());
		//System.out.println(picPath);
		
		String tags = tree.treeMap.get(picPath).nameHistory();
		JOptionPane.showMessageDialog(null, tags);
	}

}
