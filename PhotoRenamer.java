package photo_renamer;

import java.io.IOException;

import javax.swing.SwingUtilities;

import a2.BinaryTree;
import a2.GUI;

public class PhotoRenamer extends BinaryTree{
	
	
	/**Main class that runs the whole application as a one entity.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Main method, when the program runs and calls appropriate classes.
	 * @param args
	 */

	public static void main(String[] args) 
    {
        SwingUtilities.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                GUI lta = null;
				try {
					lta = new GUI();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                lta.createAndShowGUI();
            }
        });
    }
	

}