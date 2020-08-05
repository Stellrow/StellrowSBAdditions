package main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import Economy.EconomyEvents;
import Economy.EconomyUtils;
import Economy.ShopInventory;
import customitems.CustomItems;
import customitems.CustomItemsNamespacedKey;
import events.EventsClass;
import features.Recipes;

public class StellrowSBAdditionsMain extends JavaPlugin {
	private static StellrowSBAdditionsMain instance;
	//Config Files
	private File crucible,cgen,butoiLemn,siever,players,shopItems;
	private FileConfiguration cruciblecfg,cgencfg,butoiLemncfg,sievercfg,playerscfg,shopItemscfg;
	
	public Recipes recipes;
	public EventsClass eventsClass;
	public CustomItems customItems;
	public CustomItemsNamespacedKey ciNK;
	public EconomyEvents EconEvents;
	public EconomyUtils EconUtils;
	public ShopInventory shopInv;
	public void onEnable() {
		createConfigs();
		instance = this;
		registerInstances();
		registerItems();
		registerEvents();
		startRunnables();
		
		
	}
	public void onDisable() {
		
	}
	public void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	public void startRunnables() {
		this.eventsClass.convertesteLava();
		this.eventsClass.genereazabutoiLemn();
		this.eventsClass.genCobble();
		this.eventsClass.genSiva();
	}
	public static StellrowSBAdditionsMain getInstance() {
		return instance;
	}
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(eventsClass, this);
		this.getServer().getPluginManager().registerEvents(EconEvents, this);
	}
	public void registerItems() {
		this.ciNK.registerKeys();
		this.customItems.registerItems();
		this.recipes.registerRecipes();
	}
	public void registerInstances() {
		this.customItems = new CustomItems();
		this.recipes = new Recipes();
		this.eventsClass = new EventsClass();
		this.ciNK = new CustomItemsNamespacedKey();
		this.EconUtils = new EconomyUtils();
		this.EconEvents = new EconomyEvents();
		this.shopInv = new ShopInventory();
		
		
	}
	
	public void createConfigs() {
		crucible = retFile("crucible.yml");
		cruciblecfg = retCfg(crucible);
		cgen = retFile("cgen.yml");
		cgencfg = retCfg(cgen);
		butoiLemn = retFile("butoiLemn.yml");
		butoiLemncfg = retCfg(butoiLemn);
		siever = retFile("siever.yml");
		sievercfg = retCfg(siever);
		players = retFile("players.yml");
		playerscfg = retCfg(players);
		shopItems = retFile("shopItems.yml");
		shopItemscfg = retCfg(shopItems);
		getServer().getConsoleSender().sendMessage("Registered configs");
	}
	public FileConfiguration retCfg(File filetoLoad) {
		FileConfiguration cfg = new YamlConfiguration();
		try {
			cfg.load(filetoLoad);
		}catch(IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return cfg;
	}
	public File retFile(String name) {
		File file = new File(getDataFolder(),name);
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			saveResource(name,false);
		}
		return file;
	}
	public void saveCrucible() throws IOException {
    	cruciblecfg.save(crucible);
    }
	public void saveCgen() throws IOException {
    	cgencfg.save(cgen);
    }
	public void savebutoiLemn() throws IOException{
		butoiLemncfg.save(butoiLemn);
	}
	public FileConfiguration getCrucible() {
        return this.cruciblecfg;
    }
	public FileConfiguration getCgen() {
		return this.cgencfg;
	}
	public FileConfiguration getbutoiLemn() {
		return this.butoiLemncfg;
	}
	public void saveSiever() throws IOException{
		sievercfg.save(siever);
	}
	public FileConfiguration getSiever() {
		return this.sievercfg;
	}
	public void savePlayers() throws IOException {
		playerscfg.save(players);
	}
	public FileConfiguration getPlayers() {
		return this.playerscfg;
	}
	public void saveShopItems() throws IOException {
		shopItemscfg.save(shopItems);
	}
	public FileConfiguration getShopItems() {
		return this.shopItemscfg;
	}
	
}
