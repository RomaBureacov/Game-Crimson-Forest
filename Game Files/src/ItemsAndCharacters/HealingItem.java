package ItemsAndCharacters;

public class HealingItem extends Item {
	/* variables */
	private int healAmount;
	
	/* constructors */
	public HealingItem(String name, int healAmount) {
		// super(name, true);
		this.healAmount = healAmount;
	}
	
	/* methods */
	@Override
	public void effects(CharacterEntity characterEntity) {
		characterEntity.heal(healAmount);
	}
	
	@Override
	public String toString() {
		return name() + ": heals for " + healAmount + " HP";
	}
}
