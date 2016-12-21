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

public class AddTagButton extends JButton implements ActionListener{
	
	TreeMap tree = TreeMap.getInstance();
	
	private AddTagButton(){};
	
	AddTagButton(String label)
	{
		super(label);
		this.addActionListener((ActionListener) this);
	}

	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame("AddTagBox");
		String tag = JOptionPane.showInputDialog(frame, "Write what you want to be tagged to this picture:");
		System.out.println(tag);
		
		String picPath = GUI.pressedPic;
		//System.out.println(picPath);
		tree.treeMap.get(picPath).addNewTag(tag);
		for(String key:tree.treeMap.keySet()){
			//System.out.println(key);
			
			if(tree.treeMap.get(key).equals(tree.treeMap.get(picPath))){
				String dd = key;
				String ddd=tree.treeMap.get(picPath).viewTag();
				S ssss = new S(new Combine());
				Object obj = tree.treeMap.remove(key);
				tree.treeMap.put(ssss.execute(dd, ddd), (BinaryTree) obj);
				
				String mmd = ssss.execute(dd, ddd);
				System.out.println(ssss.execute(dd, ddd));
				System.out.println(mmd);
				//
				GUI.pressedPic=mmd;
				//System.out.println(ddd);
				//System.out.println(mmd);
				//System.out.println(picPath);
				Path source = Paths.get(dd);
		        Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		        //System.out.println(GUI.ken);
		        GUI.ken = GUI.ken +" "+  dd +" "+ mmd + " "+ sdf.format(cal.getTime())+"\n";
		        GUI.kenza = GUI.kenza +" "+  dd +" "+ mmd + " "+ sdf.format(cal.getTime())+"\n";
		        //System.out.println(GUI.ken);
		        try {
					Files.move(source,source.resolveSibling(mmd));
				} catch (IOException e1) {					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        break;						
			}
			
		}
	}

}
