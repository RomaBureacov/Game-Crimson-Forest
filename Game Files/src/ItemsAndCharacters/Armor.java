package ItemsAndCharacters;

public class Armor extends Item {
	/* variables */
	private int armor;
	
	/* constructors */
	public Armor(String name, int armor) {
		super(name);
		this.armor = armor;
	}
	public Armor() { // default armor
		super("No armor");
		this.armor = 0;
	}
	/* methods */
	public int value() {
		return armor;
	}
	
	@Override
	public String toString() {
		return name() + ": " + armor + " armor";
	}
}