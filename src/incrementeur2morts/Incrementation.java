package incrementeur2morts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Incrementation implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		action();
	}
	
	static void action() {
		Incrementeur2mort.nbMorts+=1;
		Incrementeur2mort.updateMorts();
		
		File fic = new File(Incrementeur2mort.lienFichierSauvegarde);
		
    	if(!fic.exists()) try {fic.createNewFile();} 
    						catch (IOException e) {e.printStackTrace();}
		
    	try {
    	      FileWriter myWriter = new FileWriter(fic);
    	      myWriter.write(""+Incrementeur2mort.nbMorts);
    	      myWriter.close();
    	      System.out.println("Successfully wrote to the file.");
    	    } catch (IOException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }
	}

}
