package a2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import a2.BinaryTree;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoggingSystem {
	/**
	 *Does the logging system , implements all methods needed, 
	 *, stores all information in hashmap, and serializes all
	 *names.
	 *
	 *@exception IOException
	 *@exception ClassNotFoundException
	 */

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//Deserializes photoApp.csv and builds HashMap if file contains information about HashMap
		//Otherwise creates new HashMap of String as key, and BinaryTree as value.

		String pathToFile = "/Users/jafarlielvin/Desktop/photoApp.csv";
		String pathToFil = "/Users/jafarlielvin/Desktop/photp.csv";
		final Logger logger =
	            Logger.getLogger(LoggingSystem.class.getName());
	    final FileHandler consoleHandler = new FileHandler("log.txt", true);
		File fl = new File(pathToFile);
		File fk = new File(pathToFil);
		String ken = "";
		if(fk.exists()){
			InputStream fil = new FileInputStream(pathToFil);
			InputStream buffe = new BufferedInputStream(fil);
			ObjectInput inpu = new ObjectInputStream(buffe);
			ken = (String) inpu.readObject();
		}else{ ken = "";}
		HashMap<String, BinaryTree> treeMap;
		if(fl.exists()){
			InputStream file = new FileInputStream(pathToFile);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			treeMap = (HashMap<String, BinaryTree>) input.readObject();
			input.close();
			buffer.close();
			file.close();
			
		}
			else{
			treeMap= new HashMap<String, BinaryTree>();
			
		}
		//Greets user and asks for command to choose
		System.out.println("Welcome to PhotoRenamer app, please choose options from below");
		System.out.println("1) Choose Directory");
		System.out.println("2) Exit");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BinaryTree tree = null;
		boolean flag = true;
		boolean flag2 = true;
		while (flag) {
			System.out.println("Select option: ");
			String s = br.readLine();
			/*If user inputed Exit, then app terminates(exits), 
			 * else it pops up a box where you choose directory
			 * and shows images under that directory.
			 */
			if (s.equals("Exit")) {
				System.out.println("Photo Renamer terminated");
				System.exit(0);
			} else {
				if (s.equals("Choose Directory")) {
					List<String> images = new ArrayList<String>();
					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int result = fc.showOpenDialog(fc);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fc.getSelectedFile();
						for (File f : selectedFile.listFiles()) {
							String mimetype = new MimetypesFileTypeMap().getContentType(f);
							String type = mimetype.split("/")[0];
							if (type.equals("image")) {
								images.add(f.getAbsolutePath());
								tree = new BinaryTree();
								if(!treeMap.containsKey(f.getAbsolutePath().toString())){
									treeMap.put(f.getAbsolutePath().toString(), tree);
								}else{
									;
								}
							}else{
								
							}
						}
						String imageResult = "";
						for (String image : images) {
							imageResult += image + ",";
						}
						imageResult = imageResult.substring(0, imageResult.length());
						imageResult += "";
						String indented = imageResult.replaceAll(",", "\n");
						System.out.println(
								"Here are the images under that directory:\n ---" + "-------------------------------");
						System.out.println(indented);
						flag = false;
					}
				}else{
					System.out.println("Please choose from above options");
					flag = true;
				}
			}
		}
		/*After having chosen directory and got images under it,
		 * gives operation options to do with images
		 */
		while(flag2){
			System.out.println("\nChoose from available options to do:");
			System.out.println("1)View Image, 2)Add Tag, 3)Delete Tag, 4)View Names History,"
					+ " 5)View Tags, 6)Exit, 7)Choose Directory, 8)Choose From Old Name");
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("\nSelect option:");
			String s1  = br1.readLine();
			/*
			 * If chosen option is View Image, it displays image in the background of
			 * Eclipse
			 */
			if(s1.equals("View Image")){
				BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Choose image to view: ");
				String path = br2.readLine();
				File imageFile = new File(path);
				if(imageFile.exists()){
					System.out.println("If image doesn't appear, check the background of Eclipse");
					BufferedImage newImage = ImageIO.read(new File(path));
					JFrame picFrame = new JFrame();
					JLabel picLabel = new JLabel(new ImageIcon(newImage));
					picFrame.add(picLabel);
					picFrame.pack();
					picFrame.setVisible(true);
				}else{
					System.out.println("Please select a valid path!");
				}
			}
			/*
			 * If  choosen option is Add Tag, it renames the image file to
			 * include the added tag as per requirements of assignment.
			 */
			if(s1.equals("Add Tag")){
				BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Write what you want to be tagged:");
				String tagWanted = br4.readLine();
				BufferedReader br5 = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Select to which image you want to add tag:");
				String picSelected = br5.readLine();
				if(treeMap.get(picSelected) == null){
					System.out.println("Sorry no such picture");
				}else{
					treeMap.get(picSelected).addNewTag(tagWanted);
					
					for(String key:treeMap.keySet()){
						System.out.println(key);
						
						if(treeMap.get(key).equals(treeMap.get(picSelected))){
							String dd = key;
							String ddd=treeMap.get(picSelected).viewTag();
							S ssss = new S(new Combine());
							Object obj = treeMap.remove(key);
							treeMap.put(ssss.execute(dd, ddd), (BinaryTree) obj);
							
							String mmd = ssss.execute(dd, ddd);
							Path source = Paths.get(dd);
					        Calendar cal = Calendar.getInstance();
					        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					        ken = ken +" "+  dd +" "+ mmd + " "+ sdf.format(cal.getTime());
					        Files.move(source,source.resolveSibling(mmd));
					        break;						
						}
						
					}
					
				}
			}
			/*
			 * If choosen option is Delete Tag, it deletes the wanted tag 
			 * to be deleted, if no tag exists, doesn't delete.
			 */
			if(s1.equals("Delete Tag")){
				BufferedReader br5 = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Choose Picture that you want delete tag:");
				String imageSelected = br5.readLine();
				File f = new File(imageSelected);
				if(f.exists()){
					BinaryTree s = treeMap.get(imageSelected);
					System.out.println(s.viewTag());
					BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
					System.out.print("Write what tag you want to delete:");
					String deleteWantedTag = br4.readLine();
					if(s.deleteTag(deleteWantedTag) == "Successfully deleted the choosen tag:)"){
						for(String key:treeMap.keySet()){
							if(treeMap.get(key).equals(treeMap.get(imageSelected))){
								String dd = key.replace("@"+deleteWantedTag,"");
								String ddddd = key;
								S ssss = new S(new Combine());
								Object obj = treeMap.remove(key);
								treeMap.put(dd, (BinaryTree) obj);
								Path source = Paths.get(ddddd);
								Files.move(source,source.resolveSibling(dd));
								Calendar cal = Calendar.getInstance();
						        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
								ken = ken +" "+ ddddd +" "+ dd +" "+ sdf.format(cal.getTime());
								break;
							}
						}
						System.out.println("Tag deleted successfully");
					}else{
						System.out.println("Choose valid tag to be deleted!");
					}
				}else{
					System.out.println("Please choose a valid picture or tag!");
				}
			}
			/*
			 * If chosen option is View Tags, it shows all tags of the picture selected.
			 */
			if(s1.equals("View Tags")){
				BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Selected the image whose's tags you want to view:");
				String image = br4.readLine();
				//treeMap.get(image).viewTag();
				File f = new File(image);
				if(f.exists()){
					System.out.println(treeMap.get(image).viewTag());
				}else{
					System.out.println("Choose valid path!");
				}
			}
			/*
			 * If chosen option is View Names History, it shows the naming history of 
			 * particular image file.
			 */
			if(s1.equals("View Names History")){
				BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Selected the image whose's names you want to view:");
				String image = br4.readLine();
				File f = new File(image);
				if(f.exists()){
					treeMap.get(image).nameHistory();
					System.out.println(treeMap.get(image).nameHistory());
				}else{
					System.out.println("Please choose valid path");
				}
			}
			/*
			 * If chosen option is Exit, it serializes HashMap treeMap to given file
			 * and exits the app
			 */
			if(s1.equals("Exit")){
				Set se = treeMap.entrySet();
			    Iterator iterato = se.iterator();
			    while(iterato.hasNext()) {
			         Map.Entry mentry = (Map.Entry)iterato.next();
			         System.out.print("key: "+ mentry.getKey() + " & Value: ");
			         System.out.println(mentry.getValue());
			      }
			    System.out.println(ken);
			    OutputStream fil = new FileOutputStream("/Users/jafarlielvin/Desktop/photoApp.csv");
			    OutputStream buffe = new BufferedOutputStream(fil);
			    ObjectOutput output = new ObjectOutputStream(buffe);
				try{
					output.writeObject(treeMap);
				}
				finally{
				output.close();
			
				}
				OutputStream file = new FileOutputStream("/Users/jafarlielvin/Desktop/photp.csv");
			    OutputStream buffer = new BufferedOutputStream(file);
			    ObjectOutput outputt = new ObjectOutputStream(buffer);
				try{
					outputt.writeObject(ken);
				}
				finally{
				outputt.close();
			
				}
				System.out.println("Photo Renamer terminated");
				System.exit(0);
			}
			/*
			 * If chosen option is Choose From Old Name, user can
			 * choose name from the history of names.
			 */
			if(s1.equals("Choose From Old Name")){
				BufferedReader br5 = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Choose Picture that you want get old tag:");
				String imageSelected = br5.readLine();
				File f = new File(imageSelected);
				if(f.exists()){
					BinaryTree s = treeMap.get(imageSelected);
					System.out.println(s.viewTag());
					System.out.println(s.nameHistory());
					BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
					System.out.print("The first line is tag you have; The second line is Tag you once have");
					System.out.print("Write what you want tag you want to add back:");
					String addTag = br4.readLine();
					s.chooseName(addTag);
					for(String key:treeMap.keySet()){
						if(treeMap.get(key).equals(treeMap.get(imageSelected))){
							String dd = key;
							String ddd=treeMap.get(imageSelected).viewTag();
							S ssss = new S(new Combine());
							Object obj = treeMap.remove(key);
							treeMap.put(ssss.execute(dd, ddd), (BinaryTree) obj);
							
							String mmd = ssss.execute(dd, ddd);
							Path source = Paths.get(dd);
							Files.move(source,source.resolveSibling(mmd));
							Calendar cal = Calendar.getInstance();
					        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					        ken = ken + " " + dd + " " + mmd +" "+ sdf.format(cal.getTime());
					        break;
						}
						
					}
					System.out.println("Tag added back successfully");
				}else{
					System.out.println("Please choose valid path!");
				}
			}
			/* it pops up a box where you choose directory
			 * and shows images under that directory.
			 * 
			 */
			if(s1.equals("Choose Directory")){
				List<String> images = new ArrayList<String>();
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fc.showOpenDialog(fc);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					for (File f : selectedFile.listFiles()) {
						String mimetype = new MimetypesFileTypeMap().getContentType(f);
						String type = mimetype.split("/")[0];
						if (type.equals("image")) {
							images.add(f.getAbsolutePath());
							tree = new BinaryTree();
							if(!treeMap.containsKey(f.getAbsolutePath().toString())){
								treeMap.put(f.getAbsolutePath().toString(), tree);
							}else{
								;
							}
						}
					}
					String imageResult = "";
					for (String image : images) {
						imageResult += image + ",";
					}
					imageResult = imageResult.substring(0, imageResult.length());
					imageResult += "";
					String indented = imageResult.replaceAll(",", "\n");
					System.out.println(
							"Here are the images under that directory:\n ---" + "-------------------------------");
					System.out.println(indented);
				}
			}
		}
		flag2 = false;
	}
}


