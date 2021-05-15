package incrementeur2morts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;


public class Reset implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object[] choices = {"Oui, je suis mauvais", "Non, je suis un giga boss"};
		Object defaultChoice = choices[1];
		
		int dialogResult = JOptionPane.showInternalOptionDialog (	Incrementeur2mort.contentPane, 
																	"Tu es sûr de vouloir remettre à 0 ton compteur ?",
																	"ATTENTION",
																	JOptionPane.YES_NO_OPTION,
																	JOptionPane.WARNING_MESSAGE,
													                null,
													                choices,
													                defaultChoice
																	);
		
		if(dialogResult == JOptionPane.YES_OPTION){
			Incrementeur2mort.nbMorts=0;
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

}
