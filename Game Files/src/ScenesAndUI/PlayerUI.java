/* player UI
 * The main window the player will interact with.
 * All interactions and scenes are fed into the PlayerUI
 */

package ScenesAndUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ItemsAndCharacters.CharacterEntity;
import ItemsAndCharacters.Item;

public class PlayerUI {
	/* variables */
	// frame components
	private JFrame gameFrame = new JFrame();
	// game window implemented as a layered pane
	private JLayeredPane gameScreen; 
	public final int INTERFACE_SCENE = 0;
	private final int INTERFACE_USER = 1;
	private final int INTERFACE_COMBAT = 2;
	// background and foreground are handled by the scene
	private JComponent scene;
	// combat interface
	private JComponent combatInterface;
	// user interface
	private final CharacterEntity player;
	
	private JComponent UI_playerHealthBar; // UI health bar component
	private JComponent playerHealthBar; // shows player health, the bar itself
	Dimension healthBarDimensions = new Dimension(300, 50);
	
	private JComponent UI_playerInventory; // UI player inventory component
	private JComponent UI_playerInventoryEquipped; // UI player equipped inventory component
	
	// color palette
	final Color TRANSPARENT_BLACK = new Color(0, 0, 0, 150);
	
	// component margins
	final int MARGIN = 5;
	
	
	/* constructors */
	public PlayerUI(CharacterEntity player) {
		this.player = player;
		build();
		show();
	}
	
	/* methods */
	// show the window
	public void show() {
		gameFrame.setVisible(true);
	}
	
	// paints the scene onto the main window
	public void paintScene() {
		// TODO: method stub, first figure out how to implement making scenes into the game
	}
	
	// TODO
	// build the UI and its components
	private void build() {
		/*** BUILD USER INTERFACE ***/
		// build main
		gameFrame.setSize(1000, 750);
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameScreen = new JLayeredPane();
		
		//test button damaging the player
		JLabel test = new JLabel();
		test.setText("damage");
		test.setBackground(Color.BLACK);
		test.setBounds((int)((double)gameFrame.getWidth() / 2 - (double)test.getWidth() / 2), 100, 100, 20);
		// test.setSize(100, 100);
		test.setOpaque(true);
		gameScreen.add(test, INTERFACE_USER);
		test.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				player.damage((int) (Math.random() * (double)player.maxHealth() / 10));
				updateHealth();
			}
		});
		// end test button
		
		/* UI health bar */
		// player health bar
		playerHealthBar = new JLabel();
		playerHealthBar.setBackground(Color.RED);
		playerHealthBar.setOpaque(true);
		playerHealthBar.setBounds(MARGIN, MARGIN, (int) ((double)player.health() / player.maxHealth() * healthBarDimensions.getWidth()), (int)(healthBarDimensions.getHeight()));
		
		// compile components
		UI_playerHealthBar = new JLayeredPane();
		UI_playerHealthBar.setOpaque(true);
		UI_playerHealthBar.setBackground(TRANSPARENT_BLACK);
		UI_playerHealthBar.add(playerHealthBar, 1);
		UI_playerHealthBar.setBounds(10, (int)(gameFrame.getHeight() - healthBarDimensions.getHeight() - 10 - 50), healthBarDimensions.width + 10, healthBarDimensions.height + 10);
		gameScreen.add(UI_playerHealthBar, INTERFACE_USER);
		
		/* UI player inventory component */
		//UI_playerInventoryEquipped = new EquippedInventory().getModule();
		UI_playerInventoryEquipped = new Inventory(2).getModule();
		UI_playerInventoryEquipped.setLocation(gameFrame.getWidth() / 2 - UI_playerInventoryEquipped.getWidth() / 2
												, gameFrame.getHeight() - UI_playerInventoryEquipped.getHeight() - 50);
		gameScreen.add(UI_playerInventoryEquipped, INTERFACE_USER);
		
		/* UI player equipped inventory component */
		UI_playerInventory = new Inventory(6).getModule();
		UI_playerInventory.setLocation(gameFrame.getWidth() - UI_playerInventory.getWidth() - 26
										, gameFrame.getHeight() - UI_playerInventory.getHeight() - 50);
		gameScreen.add(UI_playerInventory, INTERFACE_USER);
		
		/*** BUILD FOREGROUND ***/
		
		/*** BUILD BACKGROUND ***/
		
		// make master layout
		gameFrame.add(gameScreen);
		
	}
	
	// update the health bar to match the player's health
	private void updateHealth() {
		playerHealthBar.setSize((int) ((double)player.health() / player.maxHealth() * healthBarDimensions.width), healthBarDimensions.height);
		gameScreen.repaint();
	}
	
	/* subclasses */
	// inventory class, to hold items
	private class Inventory {
		/* variables */
		private JComponent inventoryModule;
		private final int SLOT_SIZE = 50;
		private JPanel itemBackground;
		private JPanel itemForeground;
		private Dimension moduleSize;
		
		/* constructors */
		// build the module
		public Inventory(int numberSlots) {
			inventoryModule = new JLayeredPane();
			inventoryModule.setOpaque(true);
			inventoryModule.setBackground(TRANSPARENT_BLACK);
			moduleSize = new Dimension(SLOT_SIZE * numberSlots + MARGIN * (numberSlots + 1) * 2, SLOT_SIZE + MARGIN * 2);
			inventoryModule.setSize(moduleSize);
			
			GridBagConstraints constraints;
			
			// item backgrounds
			itemBackground = new JPanel(new GridBagLayout());
			constraints = new GridBagConstraints();
			itemBackground.setBackground(TRANSPARENT_BLACK);
			itemBackground.setOpaque(false);
			itemBackground.setSize(moduleSize);
			for (byte i = 0; i < numberSlots; i++) {
				JLabel label = new JLabel();
				label.setBackground(TRANSPARENT_BLACK);
				label.setOpaque(true);
				label.setSize(SLOT_SIZE, SLOT_SIZE);
				constraints.fill = GridBagConstraints.NONE;
				constraints.insets = new Insets(MARGIN, MARGIN, MARGIN, MARGIN);
				constraints.ipadx = SLOT_SIZE;
				constraints.ipady = SLOT_SIZE;
				constraints.gridx = i;
				constraints.gridy = 0;
				itemBackground.add(label, constraints);
			}
			inventoryModule.add(itemBackground, 0);

			// item foregrounds
			itemForeground = new JPanel(new GridBagLayout());
			constraints = new GridBagConstraints();
			itemForeground.setBackground(TRANSPARENT_BLACK);
			itemForeground.setOpaque(false);
			itemForeground.setSize(moduleSize);
			for (byte i = 0; i < numberSlots; i++) {
				ItemIcon label = new ItemIcon(null);
				label.setBackground(Color.WHITE);
				label.setOpaque(true);
				label.setSize(SLOT_SIZE, SLOT_SIZE);
				constraints.fill = GridBagConstraints.NONE;
				constraints.insets = new Insets(MARGIN + 5, MARGIN + 5, MARGIN + 5, MARGIN + 5);
				constraints.ipadx = SLOT_SIZE - 10;
				constraints.ipady = SLOT_SIZE - 10;
				constraints.gridx = i;
				constraints.gridy = 0;
				itemForeground.add(label, constraints);
			}
			inventoryModule.add(itemForeground, 0);
		}
		
		/* methods */
		// return the module
		public JComponent getModule() {
			return inventoryModule;
		}
		
		public ItemsAndCharacters.Item remove(String itemName) {
			//TODO method stub
			return new ItemsAndCharacters.Item(itemName);
		}
		
		public ItemsAndCharacters.Item remove(int itemIndex) {
			// TODO method stub
			return new ItemsAndCharacters.Item("");
		}
		
		public void add(String itemName) {
			//TODO method stub
			
		}
		
		/* subclasses */
		private class ItemIcon extends JLabel {
			//TODO make an acceptor for images and a default image for icons.
			/* variables */
			private ItemsAndCharacters.Item item;
			
			/* constructors */
			public ItemIcon(ItemsAndCharacters.Item item) {
				super();
				this.item = item;
			}
			
			/* methods */
			public String itemName() {
				return item.name();
			}
		}
		
	}
}
