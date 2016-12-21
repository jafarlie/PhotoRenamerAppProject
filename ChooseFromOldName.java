package a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ChooseFromOldName extends JButton implements ActionListener{
	TreeMap tree = TreeMap.getInstance();
	
	private ChooseFromOldName(){};
	
	ChooseFromOldName(String label)
	{
		super(label);
		this.addActionListener((ActionListener) this);
	}
	/** 
	 * Provides all old tags that once inclueded in names's history. 
	 * We can choose from these old names.
	 * 
	 * @param e Action Event
	 * @see A new frame that we can add old name back.
	 */
	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame("All tags ever exit in the name's history");
		String picPath = GUI.pressedPic;
		String tags = tree.treeMap.get(picPath).nameHistory();
		JOptionPane.showMessageDialog(null, "Tags we have in history\n"+tags);
		JFrame frame1 = new JFrame("View Tags Box");
		//String picPath = GUI.pressedPic;
		String tagss = tree.treeMap.get(picPath).viewTag();
		JOptionPane.showMessageDialog(null, "Tags we have now\n"+tagss);
		JFrame frame2 = new JFrame("Choose From Old Name Box");
		String tag = JOptionPane.showInputDialog(frame, "The followings are all possible tags to choose from");
		System.out.println(tag);
		if(tag == ""){
			;
		}else{
		BinaryTree s = tree.treeMap.get(GUI.pressedPic);
		System.out.println(s.viewTag());
		BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
		s.chooseName(tag);
		//This loop chanegs our hashmap according to the name we choose. 
		//Write down change to logger
		for(String key:tree.treeMap.keySet()){
			if(tree.treeMap.get(key).equals(tree.treeMap.get(GUI.pressedPic))){
				String dd = key;
				System.out.println(dd);
				String ddd=tree.treeMap.get(GUI.pressedPic).viewTag();
				S ssss = new S(new Combine());
				Object obj = tree.treeMap.remove(key);
				tree.treeMap.put(ssss.execute(dd, ddd), (BinaryTree) obj);
				String mmd = ssss.execute(dd, ddd);
				System.out.println(mmd);
				GUI.pressedPic = mmd;
				Path source = Paths.get(dd);
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		        GUI.ken = GUI.ken + " " + dd + " " + mmd +" "+ sdf.format(cal.getTime())+"\n";
		        GUI.kenza = GUI.kenza +" "+  dd +" "+ mmd + " "+ sdf.format(cal.getTime())+"\n";
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
}

