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

public class DeleteTagButton extends JButton implements ActionListener{
	
	TreeMap tree = TreeMap.getInstance();
	
	private DeleteTagButton(){};
	
	DeleteTagButton(String label)
	{
		super(label);
		this.addActionListener((ActionListener) this);
	}
	/** 
	 * Deletes tag that once inclueded in names's history. 
	 * We can choose from these old names.
	 * 
	 * @param e Action Event
	 * @see A new frame that we can delete tags.
	 */
	public void actionPerformed(ActionEvent e) {
		//First we have to view all tags we have 
		JFrame frame = new JFrame("View Tags Box");
		String picPath = GUI.pressedPic;
		//System.out.println(picPath);
		//System.out.println(GUI.treeMap.keySet());
		//System.out.println(picPath);
		
		String tags = tree.treeMap.get(picPath).viewTag();
		JOptionPane.showMessageDialog(null, "Tags you have in this picture\n"+tags);
		JFrame frame1 = new JFrame("DeleteTagBox");
		//String picPath = GUI.pressedPic;
		//String tag = JOptionPane.showInputDialog(frame, GUI.treeMap.get(picPath).viewTag());
		String tag = JOptionPane.showInputDialog(frame, "Write which tag you want to delete from this picture");
        //System.out.println(tag);
		
		//String picPath = GUI.pressedPic;
		//System.out.println(picPath);
		tree.treeMap.get(picPath).deleteTag(tag);
		//This loop chanegs our hashmap according to the name we choose. 
		//Write down change to logger
		for(String key:tree.treeMap.keySet()){
			if(tree.treeMap.get(key).equals(tree.treeMap.get(picPath))){
				S ssss = new S(new Split());
				String dd = ssss.execute(key, tag);
				String ddddd = key;
				//S ssss = new S(new Split());
				Object obj = tree.treeMap.remove(key);
				tree.treeMap.put(dd, (BinaryTree) obj);
				GUI.pressedPic = dd;
				System.out.println(GUI.pressedPic);
				Path source = Paths.get(ddddd);
				try {
					Files.move(source,source.resolveSibling(dd));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		        GUI.ken = GUI.ken +" "+ ddddd +" "+ dd +" "+ sdf.format(cal.getTime())+"\n";
		        GUI.kenza = GUI.kenza +" "+  dd +" "+ dd + " "+ sdf.format(cal.getTime())+"\n";
				break;
			}
		}
		System.out.println("Tag deleted successfully");
	}
	
	}


