package ItemsAndCharacters;

import Configurations.Configurations;
import javax.swing.ImageIcon;

public class Item {
	/* variables */
	private String name;
	private boolean isConsumable = false;
	private ImageIcon icon;
	
	
	/* constructors */
	public Item(String name, String relativeIconPath, boolean isConsumable) {
		this.name = name;
		this.isConsumable = isConsumable;
		// create either the item, a default item, or a null item
		if (new java.io.File(Configurations.path_ImagesAndIcons + relativeIconPath).exists())
			this.icon = new ImageIcon(Configurations.path_ImagesAndIcons + relativeIconPath);
		else if (relativeIconPath == null)
			this.icon = new ImageIcon(Configurations.path_ImagesAndIcons + "/NoItem/defaultItem.png");
		else
			this.icon = new ImageIcon(Configurations.path_ImagesAndIcons + "NoItem/NoItem.png");
	}
	public Item(String name, String relativeIconPath) {
		this(name, relativeIconPath, false);
	}
	public Item(String name) {
		this(name, null);
	}
	public Item() {
		this("No Item");
	}
	
	/* methods */
	public String name() {
		return name;
	}
	
	public void effects(CharacterEntity characterEntity) {
		
	}
	
	public static String defaultIcon() {
		return Configurations.path_ImagesAndIcons + "/NoItem/DefaultItem.png";
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public boolean isConsumable() {
		return isConsumable;
	}
	
	public ImageIcon icon() {
		return icon;
	}
}
