package a2;

public class Combine implements Strategy {
	/** 
	 * Includes new tag to file's name.
	 * 
	 * @param  s file's old name.
	 * @param  s2 tag that add on name.
	 * @return s3 file's new name.
	 */
	@Override
	public String SS(String s, String s2) {
		// s1 is a vector contains old name and its directory.
		String[] s1 = s.split("/");
		int i=0;
		//s3 is its new name.
	    String s3 = "";
		while(i < s1.length -1){
			s3 = s3 + s1[i]+"/";
			i++;
		}
		//s4 is its old name.(only contains name)
	   String s4 = s1[s1.length -1];
	   String[] parts = s4.split("[.]");
	   String part1 = parts[0]; 
	   String part2 = parts[1];
	   //s4 is its new name.(only contains name)
	   String s5 = parts[0]+s2+"."+parts[1];
	   s3 = s3+s5;

		return s3;
	}


}
