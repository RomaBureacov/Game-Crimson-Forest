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
		for (int i = 0; i < maxSize; i++) {
			inventory.add(null);
		}
		defaultFill();
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
	public Item replace(int index, Item item) throws NoItemException {
		return inventory.set(index, item);
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
	// replace the old item with a default item and return the old item
	public Item remove(Item item) throws NoItemException {
		Item returnItem = remove(item.name());
		add(new Item());
		return returnItem;
	}
	
	public Item getItem(String itemName) throws NoItemException {
		for (Item item: inventory)
			if (item.name() == itemName) {
				return item;
			}
		
		// default
		throw new NoItemException("No such item: " + itemName);
	}
	public Item getItem(int index) {
		return inventory.get(index);
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
	
	public int maxSize() {
		return maxSize;
	}
	
	// fill with default items
	public void defaultFill() {
		for (int i = 0; i < maxSize; i++) {
			inventory.set(i, new Item());
		}
	}
	
	@Override
	public String toString() {
		return inventory.toString();
	}
}
