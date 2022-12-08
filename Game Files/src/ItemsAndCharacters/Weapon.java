package ItemsAndCharacters;

public class Weapon extends Item {
	/* variables */
	private int damage;
	private int swingSpeed;
	
	/* constructors */
	public Weapon(String name, int damage, int swingSpeed) {
		super(name);
		this.damage = damage;
		this.swingSpeed = swingSpeed;
	}
	public Weapon() { //default weapon
		super("No Weapon");
		this.damage = 1;
		this.swingSpeed = 500;
	}
	
	/* methods */
	public int damage() {
		return damage;
	}
	
	// swing speed in ms
	public int swingSpeed() {
		return swingSpeed;
	}
	
	@Override
	public String toString() {
		return name() + ": " + damage + " damage, " + swingSpeed + " ms swing speed";
	}
}
