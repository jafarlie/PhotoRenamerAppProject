package a2;

import java.util.HashMap;

public class TreeMap {
	private static TreeMap instance;
	public static HashMap<String, BinaryTree> treeMap;
	
	private TreeMap() {
	}
	public static TreeMap getInstance() {
		if(instance == null){
			instance = new TreeMap();
			treeMap = new HashMap<String, BinaryTree>();
		}
		return instance;
	}
	public void writeToTreeMap(String s, BinaryTree tree){
		treeMap.put(s, tree);
	}
	public HashMap<String, BinaryTree> getTreeHashMap(){
		return TreeMap.treeMap;
	}
}
