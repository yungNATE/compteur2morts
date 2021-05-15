package incrementeur2morts;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EcouteurDeCle implements KeyListener {

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		System.out.println(k.getKeyChar());

	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		System.out.println(k.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
