package ItemsAndCharacters;

public class Item {
	/* variables */
	private String name;
	private boolean isConsumable = false;
	
	/* constructors */
	public Item(String name) {
		this.name = name;
	}
	public Item(String name, boolean isConsumable) {
		this.name = name;
		this.isConsumable = isConsumable;
	}
	
	/* methods */
	public String name() {
		return name;
	}
	
	public void effects(CharacterEntity characterEntity) {
		
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public boolean isConsumable() {
		return isConsumable;
	}
}
