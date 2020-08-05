package Economy;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import main.StellrowSBAdditionsMain;
import net.md_5.bungee.api.ChatColor;

public class ShopInventory {
	private StellrowSBAdditionsMain pl= StellrowSBAdditionsMain.getInstance();
	private static Inventory shopInvMain = Bukkit.createInventory(null, 27, ChatColor.GREEN+"Shop");
	private ItemStack invFill = retItem(Material.GREEN_STAINED_GLASS_PANE);
	
	
	
	
	
	public void setupInventorys() {
		for(int i = 0;i<=53;i++) {
			shopInvMain.setItem(i, invFill);
		}
	}
	
	private ItemStack retItem(Material mat) {
		ItemStack item = new ItemStack(mat);
		return item;
	}
	public void openInventory(Player p) {
		puneIteme();
		p.openInventory(shopInvMain);
	}
	private void puneIteme() {
		HashMap<Integer,ItemStack> map = pl.EconUtils.buildConfig("MainPage");
		for(Object key : map.keySet()) {
			shopInvMain.setItem((int) key, map.get(key));
		}
	}

}
