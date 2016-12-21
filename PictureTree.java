package a2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

public class PictureTree {
	
	public List<String> picChildren;
	//public FileNode node;
	public File f;
	public static List<String> c  = new ArrayList<String>();
	
	public PictureTree(File file){
		picChildren = new ArrayList<String>();
		f = file;
	}
	
	public void buildTree(){
		File[] files = f.listFiles();
		if(files == null);
		for(int i=0; i< files.length; i++){
			File path = files[i];		
			if(path.isFile()){
				MimetypesFileTypeMap mtftp = new MimetypesFileTypeMap();
				mtftp.addMimeTypes("image png tif jpg jpeg bmp gif");
				String mimetype = mtftp.getContentType(path);
				String type = mimetype.split("/")[0];
				if (type.equals("image")){ 
					//System.out.println(path.getAbsolutePath().toString());
					c.add(path.getAbsolutePath().toString());

				}
			}else{
				PictureTree s = new PictureTree(path);
				s.buildTree();
			}
		}
	}
	
	public static void main(String[] args){
		File f = new File("/Users/jafarlielvin/Desktop/Java");
		PictureTree t = new PictureTree(f);
		t.buildTree();
		for(String s: c){
			System.out.println(s);
		}
	}
}