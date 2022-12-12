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
	}
	
	/* methods */
	public int value() {
		return armor;
	}
	
	// override default static icon method
	// returns RELATIVE path only, item class joins with absolute path
	public static String defaultIcon() {
		return "NoItem/DefaultArmor.png";
	}
	
	@Override
	public String toString() {
		return name() + ": " + armor + " armor";
	}
}