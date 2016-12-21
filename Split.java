package a2;

public class Split implements Strategy {
	/** 
	 * Remove tags from file's name.
	 * 
	 * @param  s file's old name.
	 * @param  s2 tag that delete from name.
	 * @return s3 file's new name.
	 * 
	 */
	@Override
	public String SS(String s, String s2) {
		String dd = s.replace("@"+s2,"");
		return dd;
	}

}
