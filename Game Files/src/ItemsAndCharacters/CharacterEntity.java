/* General class for creating items and characters
 * 
 */

package ItemsAndCharacters;

import java.rmi.AccessException;
import java.util.ArrayList;

public class CharacterEntity {
	/* variables */
	private String name;
	private int health;
	private final int MAX_HEALTH;
	private Weapon weapon = new Weapon("No weapon", 1, 500);
	private Armor armor = new Armor("No Armor", 0); // default to no armor
	private Inventory inventory = new Inventory(); 

	/*  begin inventory class */
	public class Inventory {
		/* variables */
		private ArrayList<Item> inventory = new ArrayList<>();
		
		/* constructors */
		public Inventory() {
			inventory.ensureCapacity(8);
		}
		
		/* methods */
		// accessors and mutators
		// allow for only eight items to ever be in an inventory, else throw exception
		public void add(Item item) throws IndexOutOfBoundsException {
			if (inventory.size() <= 8)
				inventory.add(item);
			else
				throw new IndexOutOfBoundsException("Too many items");
		}
		
		// remove methods
		public Item remove(String itemName) throws AccessException {
			for (Item item: inventory)
				if (item.name() == itemName) {
					inventory.remove(item);
					return item;
				}
			
			// deafult
			throw new AccessException("No such item");
		}
		
		public Item remove(Item item) throws AccessException {
			return remove(item.name());
		}
		
		public Item getItem(String itemName) throws AccessException {
			for (Item item: inventory)
				if (item.name() == itemName) {
					return item;
				}
			
			// default
			throw new AccessException("No such item");
		}
		
		public void clear() {
			inventory.clear();
		}
		
		// has item methods
		public boolean has(String itemName) {
			for (Item item: inventory)
				if (item.name() == itemName)
					return true;
			
			// default
			return false;
		}
		public boolean has(Item item) {
			return has(item.name());
		}
		
		public boolean isFull() {
			return inventory.size() == 8;
		}
		
		public boolean isEmpty() {
			return inventory.size() == 0;
		}
		
		@Override
		public String toString() {
			return inventory.toString();
		}
	}
	/* end inventory class */
	
	/* constructors */
	public CharacterEntity(String name, int health, int MAX_HEALTH) {
		this.name = name;
		this.health = health;
		this.MAX_HEALTH = MAX_HEALTH;
	}
	
	/* methods */
	
	// getters
	public int health() {
		return health;
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
		this.weapon = (weapon == null) ? new Weapon("No weapon", 1, 500) : weapon; // give "no weapon" for a null argument
		return exchangedWeapon;
	}
	
	// give armor to the character entity and return the armor they had previously
	public Armor giveArmor(Armor armor) {
		Armor exchangedArmor = this.armor;
		this.armor = (armor == null) ? new Armor("No armor", 0) : armor; // give "no armor" for a null argument
		return exchangedArmor;
	}
	
	// TODO: implement ways to do damage
	public void attack(CharacterEntity entity) {
		entity.damage(weapon.damage());
	}
	
	// use items
	// TODO: what about item usage? should all items have a use case?
	public Item useItem(String itemName) throws AccessException {
		Item item = inventory.getItem(itemName); // note: this method will throw exceptions
		if (item.isConsumable()) {
			item.effects(this);
			return inventory.remove(item);
		}
		
		// default
		throw new AccessException("Item " + itemName + " is not consumable");
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
	
	@Override
	public String toString() {
		return name + ": " + health + "/" + MAX_HEALTH
				+ "\nHas equipped weapon: " + weapon
				+ "\nHas equipped armor: " + armor
				+ "\nHas items: " + inventory;
	}
}
