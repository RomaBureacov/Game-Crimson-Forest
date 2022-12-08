package ItemsAndCharacters;
import java.util.ArrayList;

public class Inventory {
	/* variables */
	private ArrayList<Item> inventory = new ArrayList<>();
	private int maxSize;
	
	/* constructors */
	public Inventory(int maxSize) {
		this.maxSize = maxSize;
		inventory.ensureCapacity(maxSize);
	}
	
	/* methods */
	// accessors and mutators
	// allow for only eight items to ever be in an inventory, else throw exception
	public void add(Item item) throws IndexOutOfBoundsException {
		if (inventory.size() <= maxSize)
			inventory.add(item);
		else
			throw new IndexOutOfBoundsException("Too many items");
	}
	
	// remove methods
	public Item remove(String itemName) throws NoItemException {
		for (Item item: inventory)
			if (item.name() == itemName) {
				inventory.remove(item);
				return item;
			}
		
		// default
		throw new NoItemException("No such item: " + itemName);
	}
	
	public Item remove(Item item) throws NoItemException {
		return remove(item.name());
	}
	
	public Item getItem(String itemName) throws NoItemException {
		for (Item item: inventory)
			if (item.name() == itemName) {
				return item;
			}
		
		// default
		throw new NoItemException("No such item: " + itemName);
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
		return inventory.size() == maxSize;
	}
	
	public boolean isEmpty() {
		return inventory.size() == 0;
	}
	
	@Override
	public String toString() {
		return inventory.toString();
	}
}
