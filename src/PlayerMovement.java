import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;


public class PlayerMovement implements Action {

	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {

	}

	
	public Object getValue(String key) {
		return null;
	}

	public boolean isEnabled() {
		return false;
	}

	public void putValue(String key, Object value) {

	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {

	}

	@Override
	public void setEnabled(boolean b) {

	}

}
