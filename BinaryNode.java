package a2;

import java.io.Serializable;
/**
 * Returns a BinaryNode that can be used to store 
 * a single tag.
 */

public class BinaryNode implements Serializable{
	
	/**
	* Determines if a de-serialized file is compatible with this class.
	*
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * tag's position 
	 */
	int Key;
	/**
	*Tag's name
	*
	*/
	public String name;
	
	BinaryNode leftChild;
	BinaryNode rightChild;
	/**
	 * Constructor of BinaryTree
	 * 
	 * @param key the position of tag
	 * @param name the name of tag
	 */
	
	BinaryNode(int key, String name){
		this.Key = key;
		this.name = name;
	}
	BinaryNode(int key){
		this.Key = key;

	}
	/**
	 * Returns tag's name
	 * 
	 * @return the name of tag
	 */
	public String toString(){
		//name = name.replace("@", "");
		return name;
	}

}
