package a2;

import java.io.Serializable; 
/**
 *Constructs a binary tree to store information of 
 *every picture
 */
public class BinaryTree implements Serializable {
	/**
	* Determines if a de-serialized file is compatible with this class.
	*
	*/

	private static final long serialVersionUID = 1L;
	BinaryNode root;
	public int i;

	public BinaryTree(){
		
	}
    
	/**
	 *Creates a new tag in Binary tree. The name argument is 
	 *a specifier that specify the name of tag.It will automatically
	 *generates key of tree.
	 *This method will check the duplication to make sure 
	 *every duplicated won't be added in the Binary Tree
	 *
	 *@param name the name of tag.
	 */
    
    public void addNewTag(String name){
    	int t=1;
    	if(name == null || name == ""){
    		return;
    	}else{
    	
    	while(t <= i){
    		if(findNode(t).name.equals("@"+name)){
    			System.out.println("Sorry we have already have this tag");
    			return;
    		}else{t++;}
    	}
		System.out.println("Tag add successfully");
    	i++;
    	name = "@"+name;
    	BinaryNode newNode = new BinaryNode(i, name);
    	if(root == null){
    		root = newNode;
    	}else{
    
    		BinaryNode focusNode = root;
    		BinaryNode parent;
    	    while(true){
    	    	parent = focusNode;
    	    	if(i < focusNode.Key){
    	    		focusNode = focusNode.leftChild;
    	    		if(focusNode == null){
    	    			parent.leftChild = newNode;
    	    			return;
    	    			
    	    		}
    	    	} else{
    	    		focusNode = focusNode.rightChild;
    	    		if(focusNode == null){
    	    			parent.rightChild = newNode;
    	    			return;
    	    		}
    	    	}
    	    }	
    	}
    	}
    }
    public static boolean compare(String name, String name2){
    	if(name2.compareTo(name) == 0){
    		return true;
    	}
    	return false;
    }
    /*Returns the BinaryNode with specific position.
	 *It will return null if that position does not 
	 *exit.
	 *
	 *@param key the position of key.
	 *@return the BinaryNode with specifc position
	 */
    public BinaryNode findNode(int key){
    	BinaryNode focusNode = root;
		if(focusNode ==null){
			return null;
		}else{
    	while(focusNode.Key!=key){
    		if(key < focusNode.Key){
    			focusNode = focusNode.leftChild;
    			
    		}else{
    			focusNode = focusNode.rightChild;
    		}
    		if(focusNode == null){
    			return null;
    		}
    	}
    	
    	return focusNode;
		}
    }
    /**
	 *Chooses a specific tag in the history 
	 *that doe not exit in the name and puts
	 * it back to the name.
	 *
	 *@param h the name of tag
	 */
    
    public BinaryNode chooseName(String h){
    	if(i == 0){
    		return null;
    		 }else{
    	int t = 1;
    	BinaryNode focusNode = findNode(t);
    	while(!h.equals(focusNode.name.replace("@", ""))!=false){
    		t++;
    		//System.out.println(t);
    		focusNode = findNode(t);
    		if(focusNode == null){
    			System.out.println("No tag matches your input");
    			return null;
    			
    		}
    		//System.out.println(focusNode.name);       		
    	}
    	if(focusNode.name.indexOf('@')>=0){
    		System.out.println("It is in the tag");
    		return focusNode;
    	}
    	focusNode.name = "@"+focusNode.name;
    	return focusNode;
    }
    } 
    /**
	 *Deletes the tag. If that tags does not exit
	 *It mentions this tag does not exit.
	 *
	 *@param name the name of the tag.
	 */
    public String deleteTag(String name){
    	if(i == 0){
    		return null;
    	}else{
    	//String result = "Successfully deleted the choosen tag:)";
    	int t = 1;
    	BinaryNode focusNode = findNode(t);
    	while(!focusNode.name.replace("@","").equals(name)){
    		t++;
    		//System.out.println(t);
    		focusNode = findNode(t); 
    		if(focusNode == null){
    			System.out.println("No tag matches your input");
    			return null;
    		}
    	}
    	focusNode.name = focusNode.name.replace("@", "");
    	return null;
    }
    }
    /**
	 *Views all tags in the name of picture.
	 *If this picture does not have name. It returns no 
	 *name to view.
	 *
	 *@return all the tag that picture has
	 */
    public String viewTag(){
    	if(i == 0){
    	String other= "No tags to view!";
    	return null;}
    	else{
    	String str = "";
		int h = i;
		BinaryNode hh = findNode(h);
		System.out.println(hh.name);
		while(h > 0){

			if(hh.name.indexOf('@') != -1){
				str = str + hh.name;
			}
			h--;
			hh = findNode(h);
		}
		//return str;

    		return str;
    	}
    }
    /**
	 *Views all the tag exits in the history of that picture.
	 *Views all tags in the name of picture.
	 *If this picture does not have name in its history.
	 * It returns no name to view.
	 *
	 *@return all the names that picture has in its history
	 */
    public String nameHistory(){
    	String str = "";
    	String other = "No names to view";
    	int k=i;
    	BinaryNode focusnode = findNode(k);
    	while(k >0){
    		k--;
    		str = str + focusnode.toString() + " ";
    		focusnode = findNode(k);

    		
    	}
    	str = str.replace("@", "");
    	if(str.isEmpty()){
    		return other;
    	}
    	return str;
    }
    public String toString(){//overriding the toString() method  
    	  return String.valueOf(i);  
    	 }  
}

