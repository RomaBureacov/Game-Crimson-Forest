package ItemsAndCharacters;

import Configurations.Configurations;

public class Armor extends Item {
	/* variables */
	private int armor;
	
	/* constructors */
	public Armor(String name, String relativeFilePath, int armor) {
		super(name, relativeFilePath);
		this.armor = armor;
	}
	public Armor(String name, int armor) {
		this(name, null, armor);
	}
	public Armor(int armor) {
		this("NoNameArmor", armor);
	}
	public Armor() { // default armor
		this("No armor", defaultIcon(), 0);
		this.armor = 0;
	}
	
	/* methods */
	public int value() {
		return armor;
	}
	
	public static String defaultIcon() {
		return Configurations.path_ImagesAndIcons + "/NoItem/DefaultArmor.png";
	}
	
	@Override
	public String toString() {
		return name() + ": " + armor + " armor";
	}
}