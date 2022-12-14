package ItemsAndCharacters;

public class Weapon extends Item {
	/* variables */
	private int damage;
	private int swingSpeed;
	
	/* constructors */
	public Weapon(String name, String relativeIconPath, int damage, int swingSpeed) {
		super(name, relativeIconPath);
		this.damage = damage;
		this.swingSpeed = swingSpeed;
	}
	public Weapon(String name, int damage, int swingSpeed) {
		this(name, null, damage, swingSpeed);
	}
	public Weapon() { //default weapon
		this("No Weapon", defaultIcon(), 1, 500);
	}
	
	/* methods */
	public int damage() {
		return damage;
	}
	
	// swing speed in ms
	public int swingSpeed() {
		return swingSpeed;
	}
	
	// override default icon static method
	// returns RELATIVE path only, item class joins with absolute path
	public static String defaultIcon() {
		return "NoItem/DefaultWeapon.png";
	}
	
	@Override
	public String toString() {
		return name() + ": " + damage + " damage, " + swingSpeed + " ms swing speed";
	}
}
