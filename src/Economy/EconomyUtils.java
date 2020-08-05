package Economy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import main.StellrowSBAdditionsMain;

public class EconomyUtils {
	private StellrowSBAdditionsMain pl= StellrowSBAdditionsMain.getInstance();
	//Save Config
	public void save(){
		try {
			pl.savePlayers();
		} catch (IOException e) {
			e.printStackTrace();
		}}
	
	
	public boolean checkPlayer(String name) {
		boolean toRet = false;
		if(pl.getPlayers().contains("Players")) {
			if(pl.getPlayers().contains("Players."+name)) {
				toRet = true;
			}
		}
		return toRet;
	}
	public int getMoney(String name) {
		int money = 0;
		if(checkPlayer(name)) {
				money = pl.getPlayers().getInt("Players."+name+".Money");
			}
		return money;}
	public void setMoney(String name,int amount) {
		if(checkPlayer(name)) {
				pl.getPlayers().set("Players."+name+".Money", amount);
				save();
			}}
	public boolean checkAmount(String name,int amount) {
		boolean toRet = false;
	
		if(checkPlayer(name)) {
				int currAmount = getMoney(name);
				if(currAmount>=amount) {
					toRet=true;
				}}
		return toRet;
		}
	
	public void decMoney(String name,int amount) {
		if(checkPlayer(name)) {
			int currAmount = getMoney(name);
			setMoney(name,currAmount-amount);
			save();
		}
	}
	
	public void addMoney(String name,int amount) {
		if(checkPlayer(name)) {
			int currAmount = getMoney(name);
			setMoney(name,currAmount+amount);
			save();
		}
	}
	@SuppressWarnings("static-access")
	public HashMap<Integer,ItemStack> buildConfig(String sectionName) {
		HashMap<Integer,ItemStack> toRet = new HashMap<Integer,ItemStack>();
		if(pl.getShopItems().contains(sectionName)) {
			for(String key : pl.getShopItems().getConfigurationSection(sectionName).getKeys(false)) {
				int slot = pl.getShopItems().getInt(sectionName+"."+key+".Slot");
				ItemStack item = new ItemStack(Material.valueOf(pl.getShopItems().getString(sectionName+"."+key+".Material")));
				ItemMeta im = item.getItemMeta();
				String name = ChatColor.translateAlternateColorCodes('&', pl.getShopItems().getString(sectionName+"."+key+".Name"));
				im.setDisplayName(name);
				List<String> lore = new ArrayList<String>();
				List<String> loreFConf = pl.getShopItems().getStringList(sectionName+"."+key+".Lore");
				for(String string : loreFConf) {
					String trnslated = ChatColor.translateAlternateColorCodes('&', string);
					lore.add(trnslated);
				}
				if(pl.getShopItems().contains(sectionName+"."+key+".GateName")) {
				im.getPersistentDataContainer().set(pl.ciNK.econShopGate, PersistentDataType.STRING,pl.getShopItems().getString(sectionName+"."+key+".GateName"));
				}
				im.setLore(lore);
				item.setItemMeta(im);
				toRet.put(slot, item);
			}
		}
		return toRet;
	}
	
}
