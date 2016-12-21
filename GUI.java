package a2;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.awt.*;
/**
 * 
 * @author Jafarli Elvin
 *@author Weije Xu
 *
 *GUI class, that delivers to user User Graphical Interface experience.
 *This class is responsible for all graphical events and back-end update
 *of certain data.
 */

public class GUI extends JFrame implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	JTextPane tp;
	JButton click;
	JButton exit;
	JButton addTag;
	JButton deleteTag;
	JButton viewTags;
	JFileChooser fc;
	StyledDocument doc;
	SimpleAttributeSet attr;
	public static HashMap<String, BinaryTree> treeMap;
	public static String pressedPic = "";
	public static BinaryTree tree;
	TreeMap treeMapClass = TreeMap.getInstance();
    public static String ken;
    public static String kenza;
	@SuppressWarnings("unchecked")
	/**
	 * GUI method responsible for setting final location for certain
	 * data-tracker files. Serializes data to given path and fetches 
	 * data back when program is reopened.
	 * @throws SecurityException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public GUI() throws SecurityException, IOException, ClassNotFoundException {
		String userHomeFolder = System.getProperty("user.home");
		String pathToFile = System.getProperty("user.home")+ "/Desktop/Hashmap.ser";
		System.out.println(System.getProperty("user.home")+ "/Desktop/Hashmap.ser");
		String loginfor = System.getProperty("user.home")+ "/Desktop/logger.csv";
		
		final Logger logger = Logger.getLogger(LoggingSystem.class.getName());
		final FileHandler consoleHandler = new FileHandler("log.txt", true);
		File fl = new File(pathToFile);
		File fk = new File(loginfor);
		String ken = "";
		if (fk.exists()) {
			InputStream fil = new FileInputStream(loginfor);
			InputStream buffe = new BufferedInputStream(fil);
			ObjectInput inpu = new ObjectInputStream(buffe);
			GUI.ken = (String) inpu.readObject();
			System.out.println(GUI.ken);
		} else {
			;
		}
		if (fl.exists()) {
			InputStream file = new FileInputStream(pathToFile);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);

				
			//treeMap = (HashMap<String, BinaryTree>) input.readObject();
			treeMapClass.treeMap = (HashMap<String, BinaryTree>) input.readObject();
			
			input.close();
			buffer.close();
			file.close();
		}else{
			;
		}
	}
	/**
	 * Returns HashMap.
	 * 
	 * @return HashMap<String, BinaryTree>
	 */

	public HashMap<String, BinaryTree> getTree() {
		return treeMap;
	}
	/**
	 * Void method that initializes and sets up appropriate Objects. 
	 */
	public void createAndShowGUI() {
		setTitle("PhotoRenamer");
		tp = new JTextPane();
		click = new JButton("Choose Directory");
		exit = new ExitButton("Exit");
		addTag = new JButton("Add Tag");
		deleteTag = new JButton("Delete Tag");
		doc = tp.getStyledDocument();
		attr = new SimpleAttributeSet();
		JOptionPane.showMessageDialog(null, "Please read "
				+ "our application guide in a3/src first");
		JScrollPane pane = new JScrollPane(tp);
		JPanel nPanel = new JPanel();
		
		nPanel.add(click);
		nPanel.add(exit);
		click.addActionListener(this);
		Container c = getContentPane();
		c.add(nPanel, BorderLayout.NORTH);
		c.add(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	/**
	 * Returns the String version of absolute path of clicked
	 * picture.
	 * 
	 * @return String
	 */
	public String getPic() {
		return this.pressedPic;
	}
	public void update1(Observable o, Object arg) {
		this.pressedPic = (String) arg;
		System.out.println(this.pressedPic);
	}

	/**
	 * Performs appropriate GUI actions. You can choose directory to get all
	 * pictures under that directory in a recursive way. Then by clicking to
	 * text paths on the screen the image is loaded to new window and you can 
	 * add tag, delete tag, view file name history, choose old name with the 
	 * given image file.
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		String txt = new String();
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (evt.getSource() == exit) {
		}
		if (evt.getSource() == click) {
			int returnVal = fc.showOpenDialog(GUI.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				PictureTree picTree = new PictureTree(file);
				System.out.println(picTree);
				picTree.buildTree();
//				for(String s: PictureTree.c){
//					System.out.println(s);
//				}
				for (String f : PictureTree.c) {
					MimetypesFileTypeMap mtftp = new MimetypesFileTypeMap();
					mtftp.addMimeTypes("image png tif jpg jpeg bmp");
					tree = new BinaryTree();
					if (!treeMapClass.treeMap.containsKey(f)) {
						treeMapClass.treeMap.put(f, tree);
						System.out.println(f);
					}
						JLabel label = new JLabel(f);
						label.setOpaque(true);
						label.setBackground(Color.gray);
						label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
						tp.setCaretPosition(tp.getDocument().getLength());
						tp.insertComponent(label);
						label.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								String path = ((JLabel) evt.getSource()).getText();
								pressedPic = path;

								System.out.println(path);
								BufferedImage img = null;
								try {
									img = ImageIO.read(new File(path));
								} catch (IOException e) {
								}
								JFrame jf = new JFrame("PhotoRenamer");
								JButton exitButton = new ExitButton("Exit");
								JButton addTagButton = new AddTagButton("Add Tag");
								JButton deleteTagButton = new DeleteTagButton("Delete Tag");
								JButton viewTags = new ViewTagsButton("View Tags");
								JButton chooseFromOldName = new ChooseFromOldName("Choose Old Name");
								JButton NameHistory = new NameHistory("NameHistory");
								JPanel otherPanel = new JPanel();

								otherPanel.add(exitButton);
								otherPanel.add(addTagButton);
								otherPanel.add(deleteTagButton);
								otherPanel.add(viewTags);
								otherPanel.add(chooseFromOldName);
								otherPanel.add(NameHistory);

								jf.add(otherPanel, BorderLayout.SOUTH);

								JPanel imagePanel = new JPanel();
								ImageIcon icon = new ImageIcon(img);
								JLabel imageLabel = new JLabel(null, icon, JLabel.CENTER);
								imagePanel.add(imageLabel);
								jf.add(imagePanel, BorderLayout.CENTER);
								jf.pack();
								jf.setVisible(true);

							}
						});
						try {
							doc.insertString(doc.getLength(), " ", attr);
						} catch (BadLocationException ex) {
							ex.printStackTrace();

						}
					}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}