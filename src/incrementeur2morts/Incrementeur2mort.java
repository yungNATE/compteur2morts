package incrementeur2morts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Scanner;
import org.jnativehook.*;
import org.jnativehook.dispatcher.SwingDispatchService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Incrementeur2mort extends JFrame implements NativeKeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String 	TEXTE_BOUTON_INCR="Je souhaite incrémenter les morts", 
			TEXTE_BOUTON_DECR="Je souhaite décrémenter les morts",
			TEXTE_BOUTON_RST="Remettre à 0"; 
	
	static String lienFichierSauvegarde="nbMorts.ser", police="Arial";
	static int nbMorts;
	
	JLabel titre;
	static JLabel morts; JTextField inputLienFichierSauvegarde;
	JButton incrementeurMorts, decrementeurMorts, resetMorts;
	Color 	couleurPoliceDecr = new Color(170,18,18), couleurPoliceIncr = new Color(0,153,0),
			GRIS_CLAIR = new Color(237,238,247);
	
	static Container contentPane;
    public Incrementeur2mort() {
    	
	   setTitle("incrémenteur2morts");
	   setLocationRelativeTo(null);
	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	   
	   
	   // panel total
	   contentPane = getContentPane();
	   getContentPane().setLayout(new BorderLayout(0, 0));   
	   getContentPane().setBackground(GRIS_CLAIR);
	   
	   
	   // panel0 : titre 
	   JPanel panel0 = new JPanel();   
	   getContentPane().add(panel0, BorderLayout.NORTH);
	   panel0.setOpaque(false);
	   // titre
	   titre = new JLabel("Je suis votre assistant de morts. Comment puis-je vous aider ?");
	   titre.setFont(new Font(police, Font.HANGING_BASELINE, 14));
	   panel0.add(titre);

	   
	   // panel1 : btn decr morts + btn resert morts
	   JPanel panel1 = new JPanel();   
	   getContentPane().add(panel1, BorderLayout.SOUTH);
	   panel1.setOpaque(false);
	   // btn decr morts
	   decrementeurMorts = new JButton(TEXTE_BOUTON_DECR);
	   decrementeurMorts.setFont(new Font(police, Font.PLAIN, 12));
	   decrementeurMorts.setForeground(couleurPoliceDecr);
	   decrementeurMorts.addActionListener(new Decrementation());
	   panel1.add(decrementeurMorts);
	   // btn reset morts
	   resetMorts = new JButton(TEXTE_BOUTON_RST);
	   resetMorts.setFont(new Font(police, Font.PLAIN, 12));
	   resetMorts.setForeground(Color.RED); 
	   resetMorts.setBackground(Color.WHITE);
	   resetMorts.addActionListener(new Reset());
	   panel1.add(resetMorts);
	   
	   // panel2 : btn incr morts + nb morts
	   JPanel panel2 = new JPanel();   
	   getContentPane().add(panel2, BorderLayout.CENTER);
	   panel2.setOpaque(false);
	   // btn incr morts
	   incrementeurMorts = new JButton(TEXTE_BOUTON_INCR);
	   incrementeurMorts.setFont(new Font(police, Font. BOLD, 18));
	   incrementeurMorts.setForeground(couleurPoliceIncr);
	   incrementeurMorts.addActionListener(new Incrementation());
	   panel2.add(incrementeurMorts);
	   // nb morts
	   morts = new JLabel();
	   panel2.add(morts);
	   
	   nbMorts=lireValSauvegardee(); updateMorts();
	   setSize(new Dimension(450,175)); setResizable(false);
	   
	   GlobalScreen.setEventDispatcher(new SwingDispatchService());
	   
	   setVisible(true);

    }
    
    private static int lireValSauvegardee() {
		int valeurLue=0;
		File fic = new File(lienFichierSauvegarde);
	
    	if(!fic.exists()) return 0;
    	
    	try {
    	      Scanner myReader = new Scanner(fic);
    	      while (myReader.hasNextLine()) {
    	        String data = myReader.nextLine();
    	        valeurLue=Integer.parseInt(data); //updateMorts();
    	        System.out.println(data);
    	      }
    	      myReader.close();
    	    } catch (FileNotFoundException e) {
    	      System.out.println("An error occurred.");
    	      //zzzzzezeze.printStackTrace();
    	    }
    	
		return valeurLue;
    }
	public static void updateMorts() {
    	morts.setText("Vous avez été sqweecked "+nbMorts+" fois");
    }
    
    
    public static void main(String[] args) {
    	final Incrementeur2mort I2M = new Incrementeur2mort();
    	try {GlobalScreen.registerNativeHook();} 
    		catch (NativeHookException e) {e.printStackTrace();}
    	GlobalScreen.addNativeKeyListener(I2M);
    }

	@Override //88888888888
	public void nativeKeyPressed(NativeKeyEvent key) {
		String actualKey = NativeKeyEvent.getKeyText(key.getKeyCode());
		System.out.println(actualKey);
		switch (actualKey){
			case "8": 
				Incrementation.action();
				break;
			case "7":
				Decrementation.action();
				break;
			case "0":
				Reset.action();
				break;
			default:
				// NULL
		}
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}