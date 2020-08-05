package customitems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import main.StellrowSBAdditionsMain;
import net.md_5.bungee.api.ChatColor;

public class CustomItems {
	private StellrowSBAdditionsMain pl= StellrowSBAdditionsMain.getInstance();
	
	public void registerItems() {
		registerPebble();
		registerHammer();
		registergenCobble();
		registerSitaString();
		registerCruciblu();
		registerPortelan();
		registerButoiLemn();
		registerSitaLemn();
		registerSiever();
	}
	public static ItemStack pebble = new ItemStack(Material.SUNFLOWER);
	public static ItemStack hammer = new ItemStack(Material.WOODEN_PICKAXE);
	public static ItemStack genCobble = new ItemStack(Material.NETHER_QUARTZ_ORE);
	public static ItemStack sitaString = new ItemStack(Material.STONE_SWORD);
	public static ItemStack cruciblu = new ItemStack(Material.CAULDRON);
	public static ItemStack portelan = new ItemStack(Material.CLAY);
	public static ItemStack butoiLemn = new ItemStack(Material.COMPOSTER);
	public static ItemStack sitaLemn = new ItemStack(Material.WOODEN_SWORD);
	public static ItemStack siever = new ItemStack(Material.CRACKED_STONE_BRICKS);
	
	@SuppressWarnings("static-access")
	private void registerSiever() {
		ItemMeta cm = siever.getItemMeta();
		cm.setDisplayName(ChatColor.RED+"Sita Automata");
		List<String>lore=new ArrayList<String>();
		lore.add(ChatColor.RED+"Siever Tier 1");
		lore.add(ChatColor.GRAY+"Sparge automat blocurile");
		lore.add(ChatColor.GRAY+"nisipoase(Dirt/Gravel/Sand)");
		lore.add(ChatColor.GRAY+"Daca un chest este deasupra");
		lore.add(ChatColor.GRAY+"Itemele vor intra automat in el");
		lore.add(ChatColor.GRAY+"");
		lore.add(ChatColor.GRAY+"Itemele apar deasupra acestuia");
		lore.add(ChatColor.RED+"Tier 1");
		cm.getPersistentDataContainer().set(pl.ciNK.sieverKey, PersistentDataType.STRING, "ssba.siever");
		cm.setLore(lore);
		siever.setItemMeta(cm);
	}
	private void registerButoiLemn() {
		ItemMeta blm = butoiLemn.getItemMeta();
		blm.setDisplayName(ChatColor.RED + "Butoi de lemn");
		List<String>blmlore=new ArrayList<String>();
		blmlore.add(ChatColor.GRAY+"Un butoi de lemn");
		blmlore.add(ChatColor.GRAY+"Converteste saplinguri in");
		blmlore.add(ChatColor.GRAY+"apa care poate fi extrasa");
		blmlore.add(ChatColor.GRAY+"cu o galeata");
		blmlore.add(ChatColor.GRAY+"Citeste instructiunile inauntru");
		blmlore.add(ChatColor.GRAY+"");
		blmlore.add(ChatColor.GRAY+"");
		blmlore.add(ChatColor.GRAY+"Adaugat de StellrowSBAdditions");
		blm.setLore(blmlore);
		butoiLemn.setItemMeta(blm);
	}
	@SuppressWarnings("static-access")
	private void registerSitaLemn() {
		ItemMeta slm = sitaLemn.getItemMeta();
		List<String> lore = new ArrayList<String>();
		slm.setDisplayName(ChatColor.GRAY+"Sita Lemn");
		lore.add(ChatColor.GRAY + "O sita din lemn");
		lore.add(ChatColor.GRAY + "sparge dirt pentru");
		lore.add(ChatColor.GRAY + "a obtine string");
		lore.add(ChatColor.GRAY + "Foloseste string-ul pentru a crea");
		lore.add(ChatColor.GRAY + "o sita din string!");
		slm.setLore(lore);
		slm.getPersistentDataContainer().set(pl.ciNK.sitaLemnKey, PersistentDataType.STRING, "ssba.sitaLemn");
		slm.setCustomModelData(1);
		sitaLemn.setItemMeta(slm);
		
	}
	@SuppressWarnings("static-access")
	private void registerPebble() {
		pebble = new ItemStack(Material.SUNFLOWER);
		ItemMeta pebbleM = pebble.getItemMeta();
		pebbleM.setDisplayName("Pietricica");
		List<String> pebbleLore = new ArrayList<String>();
		pebbleLore.add(ChatColor.GRAY + "O pietricica mica");
		pebbleLore.add(ChatColor.GRAY + "combino cu inca 3 pentru");
		pebbleLore.add(ChatColor.GRAY + "a crea un cobblestone!");
		pebbleLore.add("");
		pebbleLore.add("");
		pebbleLore.add(ChatColor.GRAY + "Adaugat de StellrowSBAdditions");
		pebbleM.setLore(pebbleLore);
		pebbleM.getPersistentDataContainer().set(pl.ciNK.pebbleKey, PersistentDataType.STRING, "Pietricica");
		pebbleM.setCustomModelData(1);
		pebble.setItemMeta(pebbleM);
	}
	@SuppressWarnings("static-access")
	private void registerHammer() {
		ItemMeta hammerM = hammer.getItemMeta();
		hammerM.setDisplayName(ChatColor.GRAY + "Ciocan de lemn");
		List<String> hammerLore = new ArrayList<String>();
		hammerLore.add(ChatColor.GRAY + "Un ciocan de lemn");
		hammerLore.add(ChatColor.GRAY + "folosestel pentru a sfarma");
		hammerLore.add(ChatColor.GRAY + "Piatra>Cobble>Gravel>Dirt>Sand");
		hammerLore.add(ChatColor.GRAY + "");
		hammerLore.add(ChatColor.GRAY + "");
		hammerLore.add(ChatColor.GRAY + "Adaugat de StellrowSBAdditions");
		hammerM.setLore(hammerLore);
		hammerM.getPersistentDataContainer().set(pl.ciNK.hammerKey, PersistentDataType.STRING, "ssba.whammer");
		hammerM.setCustomModelData(1);
		hammer.setItemMeta(hammerM);
	}
	@SuppressWarnings("static-access")
	private void registergenCobble() {
		ItemMeta cgenm = genCobble.getItemMeta();
		cgenm.setDisplayName(ChatColor.RED + "Generator Cobblestone");
		List<String> genLore = new ArrayList<String>();
		genLore.add(ChatColor.GRAY + "Un generator de cobblestone");
		genLore.add(ChatColor.GRAY + "punel pe jos si va incepe");
		genLore.add(ChatColor.GRAY + "sa spawneze cobblestone");
		genLore.add(ChatColor.GRAY + "Pune un chest deasupra pentru");
		genLore.add(ChatColor.GRAY + "a introduce cobblestonu in acesta");
		genLore.add(ChatColor.GRAY + "");
		genLore.add(ChatColor.GRAY + "");
		genLore.add(ChatColor.GRAY + "Adaugat de StellrowSBAdditions");
		cgenm.getPersistentDataContainer().set(pl.ciNK.cgenkey, PersistentDataType.STRING,"ssba.cgen");
		cgenm.setLore(genLore);
		genCobble.setItemMeta(cgenm);
		
	}
	@SuppressWarnings("static-access")
	private void registerSitaString() {
		ItemMeta ssm = sitaString.getItemMeta();
		ssm.setDisplayName(ChatColor.WHITE + "Sita din ata");
		List<String> sslore = new ArrayList<String>();
		sslore.add(ChatColor.GRAY + "O sita facuta din ata");
		sslore.add(ChatColor.GRAY + "daca spargi un bloc nisipos");
		sslore.add(ChatColor.GRAY + "ai o sansa sa primesti minereu");
		sslore.add(ChatColor.GRAY + "in functie de tipul blocului");
		sslore.add(ChatColor.GRAY + "");
		sslore.add(ChatColor.GRAY + "");
		sslore.add(ChatColor.GRAY + "Adaugat de StellrowSBAdditions");
		ssm.getPersistentDataContainer().set(pl.ciNK.sitastringKey, PersistentDataType.STRING, "ssba.sitaString");
		ssm.setLore(sslore);
		ssm.setCustomModelData(1);
		sitaString.setItemMeta(ssm);
	}
	@SuppressWarnings("static-access")
	private void registerCruciblu() {
		ItemMeta cm = cruciblu.getItemMeta();
		cm.setDisplayName(ChatColor.RED + "Cruciblu de portelan");
		List<String> cmlore = new ArrayList<String>();
		cmlore.add(ChatColor.GRAY + "Un cruciblu din portelan");
		cmlore.add(ChatColor.GRAY + "pune o sursa de caldura sub");
		cmlore.add(ChatColor.GRAY + "pentru a topi cobblestone");
		cmlore.add(ChatColor.GRAY + "in lava");
		cmlore.add(ChatColor.GRAY + "");
		cmlore.add(ChatColor.GRAY + "Surse de incalzire(torte x1/lava x3)");
		cmlore.add(ChatColor.GRAY + "");
		cmlore.add(ChatColor.GRAY + "");
		cmlore.add(ChatColor.GRAY + "Adaugat de StellrowSBAdditions");
		cm.getPersistentDataContainer().set(pl.ciNK.crucibluKey, PersistentDataType.STRING, "ssba.cruciblu");
		cm.setLore(cmlore);
		cruciblu.setItemMeta(cm);
	}
	@SuppressWarnings("static-access")
	private void registerPortelan() {
		ItemMeta pm = portelan.getItemMeta();
		pm.setDisplayName(ChatColor.GREEN + "O bucata de portelan");
		List<String> pmlore = new ArrayList<String>();
		pmlore.add(ChatColor.GRAY + "Pune in forma de cazan pentru a crea");
		pmlore.add(ChatColor.GRAY + "un cruciblu folosit");
		pmlore.add(ChatColor.GRAY + "pentru a crea lava");
		pmlore.add(ChatColor.GRAY + "");
		pmlore.add(ChatColor.GRAY + "");
		pmlore.add(ChatColor.GRAY + "Adaugat de StellrowSBAdditions");
		pm.setLore(pmlore);
		pm.getPersistentDataContainer().set(pl.ciNK.portelanKey, PersistentDataType.STRING, "ssba.portelan");
		portelan.setItemMeta(pm);
		
		
	}

}
