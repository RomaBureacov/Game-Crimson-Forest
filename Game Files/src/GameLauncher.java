/* Game by Roman Bureacov, Kali Abdula, and Stuart Belvin
 * 
 */

/*
 * Implementation notes:
 * this class will be the master class, and it will be in charge of throwing the player into the various scenes.
 * This class will store the character data, passing it to scenes.
 */

public class GameLauncher {
	public static void main(String[] args) {
		ScenesAndUI.PlayerUI ui = new ScenesAndUI.PlayerUI(new ItemsAndCharacters.CharacterEntity("player", 100, 100));
		// exampleOfEntities();
	}
	
	// just an example method to showcase the ItemsAndCharacters package
	private static void exampleOfEntities() {

		final ItemsAndCharacters.CharacterEntity character1 = new ItemsAndCharacters.CharacterEntity("John Barosa", 100, 100);
		final ItemsAndCharacters.CharacterEntity character2 = new ItemsAndCharacters.CharacterEntity("John Warosa", 100, 100);
		character1.giveArmor(new ItemsAndCharacters.Armor("Basic Steel Plate", 5));
		
		System.out.println(character1);
		System.out.println(character2);
		System.out.println();
		
		System.out.println("Scene 1");
		System.out.println(character1.name() + " and " + character2.name() + " enter the scene");
		System.out.println(character1.name() + ": \"you fool! you think you can challenge me?\"");
		System.out.println(character2.name() + ": \"Of course! why would I not? you are a mere squabbler!\"");
		System.out.println(character1.name() + ": \"Then so be it! take a sword and we shall see who survives...\"");
		
		character1.giveWeapon(new ItemsAndCharacters.Weapon("Steel sword", 20, 500));
		System.out.println(character1.name() + " picks up a " + character1.weapon().name());
		character2.giveWeapon(new ItemsAndCharacters.Weapon("Steel sword", 20, 500));
		System.out.println(character2.name() + " picks up a " + character2.weapon().name());
		System.out.println();
		
		System.out.println("The fighting begins");
		
		System.out.println(character1.name() + " lands the first blow!");
		character1.attack(character2);
		System.out.println(character2.name() + ": " + character2.health());
		
		System.out.println("But here comes along " + character2.name() + " with two swift blows!");
		character2.attack(character1);
		System.out.println(character1.name() + ": " + character1.health());
		character2.attack(character1);
		System.out.println(character1.name() + ": " + character1.health());
		
		System.out.println(character1.name() + " lands another blow!");
		character1.attack(character2);
		System.out.println(character2.name() + ": " + character2.health());
		
		
		System.out.println("What's this? " + character2.name() + " enters a state of frenzy!");
		
		while (character1.health() > 0) {
			System.out.println(character2.name() + " strikes " + character1.name() + "!");
			character2.attack(character1);
			System.out.println(character1.name() + ": " + character1.health());
		}
		
		System.out.println(character2.name() + ": \"and there you go, " + character1.name() + "... a fool as you always were\"");
	
	}
}
