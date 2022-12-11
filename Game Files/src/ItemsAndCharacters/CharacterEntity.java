/* General class for creating items and characters
 * 
 */

package ItemsAndCharacters;

public class CharacterEntity {
	/* variables */
	private String name;
	private int health;
	private final int MAX_HEALTH;
	private Weapon weapon = new Weapon();
	private Armor armor = new Armor();
	private Inventory inventory; 

	/* constructors */
	public CharacterEntity(String name, int health, int MAX_HEALTH, int inventorySize) throws IllegalArgumentException {
		name = name.trim();
		if (name.length() == 0)
			throw new IllegalArgumentException("Name cannot be empty");
		else if (health < 0 || MAX_HEALTH < 0)
			throw new IllegalArgumentException("Health cannot be less than 0");
		else if (health > MAX_HEALTH)
			throw new IllegalArgumentException("Health cannot be greater than maximum health");
		
		this.name = name;
		this.health = health;
		this.MAX_HEALTH = MAX_HEALTH;
		this.inventory = new Inventory(inventorySize);
	}
	
	/* methods */
	// getters
	public int health() {
		return health;
	}
	
	public int maxHealth() {
		return MAX_HEALTH;
	}
	
	public Armor armor() {
		return armor;
	}
	
	public Weapon weapon() {
		return weapon;
	}
	
	public String name() {
		return name;
	}
	
	// give a weapon to the character entity and return the weapon they had previously
	public Weapon giveWeapon(Weapon weapon) {
		Weapon exchangedWeapon = this.weapon;
		// TODO create a default no weapon icon and apply path
		this.weapon = (weapon == null) ? new Weapon() : weapon; // give "no weapon" for a null argument
		return exchangedWeapon;
	}
	public Weapon dropWeapon() {
		return giveWeapon(null);
	}
	
	// give armor to the character entity and return the armor they had previously
	public Armor giveArmor(Armor armor) {
		Armor exchangedArmor = this.armor;
		this.armor = (armor == null) ? new Armor() : armor; // give "no armor" for a null argument
		return exchangedArmor;
	}
	public Armor dropArmor() {
		return giveArmor(null);
	}
	
	// give and take items from the entity inventory
	public void giveItem(Item item) {
		inventory.add(item);
	}
	public Item dropItem(Item item) {
		return inventory.remove(item);
	}
	public Item dropItem(String itemName) {
		return inventory.remove(itemName);
	}
	
	// TODO: implement ways to do damage
	public void attack(CharacterEntity entity) {
		entity.damage(this.weapon.damage());
	}
	
	// use items
	// TODO: what about item usage? should all items have a use case?
	public Item useItem(String itemName) throws NoItemException {
		Item item = inventory.getItem(itemName); // note: this method will throw exceptions
		if (item.isConsumable()) {
			item.effects(this);
			return inventory.remove(item);
		}
		
		// default
		throw new NoItemException("Item " + itemName + " is not consumable");
	}
	
	public void damage(int damage) {
		this.health -= (damage - armor.value() >= 0) ? damage - armor.value() : 0; // damage depending on armor, damage - armor or 0 damage if armor >= damage
		if (this.health < 0) this.health = 0;
	}
	
	public Inventory inventory() {
		return inventory;
	}
	
	public void heal(int amount) {
		this.health += amount;
		if (health > MAX_HEALTH)
			health = MAX_HEALTH;
	}
	
	public void healToMax() {
		this.health = MAX_HEALTH;
	}
	
	public boolean isDead() {
		return this.health == 0;
	}
	
	@Override
	public String toString() {
		return name + ": " + health + "/" + MAX_HEALTH
				+ "\nHas equipped weapon: " + weapon
				+ "\nHas equipped armor: " + armor
				+ "\nHas items: " + inventory;
	}
}
