/* abstract class, handles the scene and the scene elements
 * 
 */

package ScenesAndUI;

import javax.swing.*;
import java.awt.*;

public abstract class Scene {
	/* variables */
	protected JLayeredPane scenePane;
	protected JPanel background;
	protected JPanel foreground;
	
	/* methods */
	// give the scene
	public JLayeredPane getScene() {
		return scenePane;
	}
	// set the scene
	public void setScene() {
		setBackground();
		setForeground();
	}
	// set the background
	public abstract void setBackground();
	// set the foreground
	public abstract void setForeground();
	
}
