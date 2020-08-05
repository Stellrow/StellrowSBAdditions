package Economy;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import main.StellrowSBAdditionsMain;

public class EconomyEvents implements Listener{
	private StellrowSBAdditionsMain pl= StellrowSBAdditionsMain.getInstance();
	
	public void savePlayers() throws IOException {
		pl.savePlayers();
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws IOException {
		if(pl.getPlayers().contains("Players")) {
			if(pl.getPlayers().contains("Players."+e.getPlayer().getPlayerListName())) {
				return;
			}else {
				pl.getPlayers().set("Players."+e.getPlayer().getPlayerListName()+".Money", 0);
				savePlayers();
			}
		}
	}
	@EventHandler
	public void saveChest(PlayerInteractEvent e) throws IOException {
		if(e.getAction()==Action.RIGHT_CLICK_BLOCK && e.getPlayer().getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) {
			pl.shopInv.openInventory(e.getPlayer());
		}
	}

}
