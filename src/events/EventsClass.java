package events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import main.StellrowSBAdditionsMain;
import net.md_5.bungee.api.ChatColor;

public class EventsClass implements Listener{
	private StellrowSBAdditionsMain pl= StellrowSBAdditionsMain.getInstance();
	private Inventory crucibleInv,sieverinv;
	private ItemStack invFill,cobblePaper,paperLava,NivelLava,paperInfoLava;
	
	private Inventory butoiLemnInv;
	//Siever
	private ItemStack bdirt,bsand,bgravel,gconfig;
	//Lore Siever
	
	private ItemStack saplingPaper,paperApa,nivelApa,paperInfoApa,butonSapling,butonGaleata,butonApa;
	//Butoane Cruciblu
	private ItemStack button1;//Cobble
	private ItemStack button2;//Buton Galeti
	private ItemStack button3;//Buton Lava
	//Strange pebble cu ShiftClick pe Dirt/Grass
	@EventHandler
	public void onPebbleCollect(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().isSneaking()) {
			if(e.getClickedBlock().getType() == Material.DIRT || e.getClickedBlock().getType() == Material.GRASS_BLOCK) {
				World world = e.getClickedBlock().getWorld();
				Random rnd = new Random();
				new BukkitRunnable() {

					@SuppressWarnings("static-access")
					@Override
					public void run() {
						int chance = rnd.nextInt(10);
						if(chance == 1) {
							world.dropItemNaturally(e.getClickedBlock().getLocation().add(0, 1, 0),pl.customItems.pebble);
						}
						
						
					}
					
				}.runTaskLater(pl, 5);
				
			}
	}
	}
	//Sparge un bloc cu un ciocan
	@SuppressWarnings({ "static-access" })
	@EventHandler
	public void spargeBlocCiocan(BlockBreakEvent e) {
		if(e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
		if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(pl.ciNK.hammerKey, PersistentDataType.STRING)=="ssba.whammer") {
			Material t = e.getBlock().getType();
			Material st = Material.STONE;
			Material cb = Material.COBBLESTONE;
			Material gr = Material.GRAVEL;
			Material dr = Material.DIRT;
			Location loc = e.getBlock().getLocation();
			World world = loc.getWorld();
			if(t==st) {
				e.setDropItems(false);
				world.dropItemNaturally(loc, new ItemStack(Material.COBBLESTONE));
			}
			if(t==cb) {
				e.setDropItems(false);
				dropI(world,loc,Material.GRAVEL);
			}
			if(t==gr) {
				e.setDropItems(false);
				dropI(world,loc,Material.DIRT);
			}
			if(t==dr) {
				e.setDropItems(false);
				dropI(world,loc,Material.SAND);
			}
			
		}
	}
	
}
	public void dropI(World world,Location loc,Material mat) {
		world.dropItemNaturally(loc, new ItemStack(mat));
	}
	//Sneak pt grow copaci
	public void twerkCopac(PlayerToggleSneakEvent e) {
		int count = 0;
		Location loc = e.getPlayer().getLocation();
		World world = loc.getWorld();
		Location min = new Location(world,loc.getX() - 3,loc.getY(),loc.getZ() -3);
		Location max = new Location(world,loc.getX() + 3,loc.getY(),loc.getZ() +3);
		e.getPlayer().sendMessage("Sneak");
		for(int x = (int)min.getX();x<=(int)max.getX();x++) {
			for(int z = (int)min.getZ();x<=max.getZ();z++) {
				Location cord = new Location(world,x,min.getY(),z) ;
				Block b = cord.getBlock();
				e.getPlayer().sendMessage(b.getType().toString());
				if(b.getType()==Material.OAK_SAPLING) {
					e.getPlayer().sendMessage("Gasit sapling");
					world.spawnParticle(Particle.VILLAGER_HAPPY, cord.getX(), cord.getY(), cord.getZ(), 1);
					BlockData data = b.getBlockData();
					if(data instanceof Sapling) {
						count++;
						if(count==4) {
						((Sapling) data).setStage(1);
						count=0;
						e.getPlayer().sendMessage("succes");
						}
					}
				}
						
						}
				}
				
	}
	@SuppressWarnings("static-access")
	@EventHandler
	public void spargeSita(BlockBreakEvent e) {
		Block b = e.getBlock();
		Material bm = b.getType();
			if(e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(pl.ciNK.sitastringKey, PersistentDataType.STRING)=="ssba.sitaString") {
						e.setDropItems(false);
						e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(retRItem(bm)));
				}
			}
		
	}
	@SuppressWarnings("static-access")
	@EventHandler
	public void spargeSitaLemn(BlockBreakEvent e) {
		if(e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(pl.ciNK.sitaLemnKey, PersistentDataType.STRING)=="ssba.sitaLemn") {
				if(e.getBlock().getType()==Material.DIRT) {
					Random rnd = new Random();
					int chance = rnd.nextInt(11);
					if(chance<5) {
					e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.STRING));
					}
					e.setDropItems(false);
					
				}
			}
		}
	}
	public Material retRItem(Material mat) {
		Random rnd = new Random();
		Material toReturn = mat;
		int chance = rnd.nextInt(11);
		//Gravel
		if(mat==Material.GRAVEL) {
			if(chance == 10) {
				toReturn =Material.DIAMOND;
			}
			if(chance==9) {
				toReturn=Material.EMERALD;
			}
			if(chance==8||chance==7) {
				toReturn=Material.GOLD_ORE;
			}
			if(chance==6||chance==5||chance==4) {
				toReturn=Material.IRON_ORE;
			}
			if(chance==3||chance==2||chance==1) {
				toReturn=Material.COAL;
			}
		}
		//Dirt
		if(mat==Material.DIRT) {
			//Saplings
			if(chance==10||chance==9||chance==8) {
				int saplch = rnd.nextInt(11);
				if(saplch==10||saplch==9) {
					toReturn=Material.OAK_SAPLING;
				}
				if(saplch==8||saplch==7) {
					toReturn=Material.JUNGLE_SAPLING;
				}
				if(saplch==6||saplch==5) {
					toReturn=Material.BIRCH_SAPLING;
				}
				if(saplch==4||saplch==3) {
					toReturn=Material.ACACIA_SAPLING;
				}
				if(saplch==2||saplch==1) {
					toReturn=Material.SPRUCE_SAPLING;
				}
			}
			//Seeds
			if(chance==7||chance==6||chance==5||chance==4) {
				int sc = rnd.nextInt(11);
				if(sc==10||sc==9) {
					toReturn=Material.WHEAT_SEEDS;
				}
				if(sc==8||sc==7) {
					toReturn=Material.CARROT;
				}
				if(sc==6||sc==5) {
					toReturn=Material.POTATO;
				}
				if(sc==4||sc==3) {
					toReturn=Material.SUGAR_CANE;
				}
				if(sc==2||sc==1) {
					toReturn=Material.MELON_SEEDS;
				}
			}
			if(chance==3||chance==2||chance==1) {
				toReturn=Material.STRING;
			}
		}
		if(mat==Material.SAND) {
			if(chance==10||chance==9) {
				toReturn=Material.REDSTONE;
			}
			if(chance==8||chance==7) {
				toReturn=Material.LAPIS_LAZULI;
			}
			if(chance==6||chance==5) {
				toReturn=Material.BONE_MEAL;
			}
			if(chance==4||chance==3) {
				toReturn=Material.GLOWSTONE_DUST;
			}
			if(chance==2||chance==1) {
				toReturn=Material.CACTUS;
			}
		}
		return toReturn;
	}
	@SuppressWarnings("static-access")
	@EventHandler
	public void puneCruciblu(BlockPlaceEvent e) {
		if(e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			if(e.getBlock().getType()==Material.CAULDRON) {
				Block block = e.getBlock();
			if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(pl.ciNK.crucibluKey, PersistentDataType.STRING)=="ssba.cruciblu") {
				int idUnic = returnIDUnic();
				int defaultWL = 0;
				int defaultCL = 0;
				int defaultCG = 0;
				pl.getCrucible().set("Cruciblu."+ idUnic + ".World", block.getWorld().getName());
				pl.getCrucible().set("Cruciblu."+ idUnic + ".X", block.getX());
				pl.getCrucible().set("Cruciblu."+ idUnic + ".Y", block.getY());
				pl.getCrucible().set("Cruciblu."+ idUnic + ".Z", block.getZ());
				pl.getCrucible().set("Cruciblu." + idUnic + ".NivelLichid", defaultWL);
				pl.getCrucible().set("Cruciblu." + idUnic + ".NivelLCobble", defaultCL);
				pl.getCrucible().set("Cruciblu."+idUnic+".CG", defaultCG);
				try {
					pl.saveCrucible();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			}
		}
		
	}
	@SuppressWarnings("static-access")
	@EventHandler
	public void spargeCruciblu(BlockBreakEvent e) {
		ItemStack cruciblu = pl.customItems.cruciblu;
		Block bl = e.getBlock();
		Location loc = bl.getLocation();
		if(pl.getCrucible().contains("Cruciblu")) {
			for(String key : pl.getCrucible().getConfigurationSection("Cruciblu").getKeys(false)) {
				Location loc2 = new Location(Bukkit.getWorld(pl.getCrucible().getString("Cruciblu." + key + ".World")), pl.getCrucible().getDouble("Cruciblu." + key + ".X"),pl.getCrucible().getDouble("Cruciblu." + key + ".Y"),pl.getCrucible().getDouble("Cruciblu." + key + ".Z"));
	        if(loc.equals(loc2)) {
	        	pl.getCrucible().getConfigurationSection("Cruciblu").set(key, null);
	        	try {
					pl.saveCrucible();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        	e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), cruciblu);
	        	e.setDropItems(false);
	        	return;
	        }
			}
		}else {
			return;
		}
	}
	public int returnIDUnic() {
		int inttoReturn=genereazaIDNou();
			if(pl.getCrucible().contains("Cruciblu")) {
				for(String key : pl.getCrucible().getConfigurationSection("Cruciblu").getKeys(false)) {
					if(pl.getCrucible().getInt("Cruciblu." + key)==inttoReturn) {
						returnIDUnic();
						inttoReturn=returnIDUnic();
					}
				}
			}
		return inttoReturn;
	}
	public int returnIDbL() {
		int inttoReturn=genereazaIDNou();
		if(pl.getbutoiLemn().contains("Butoi")) {
			for(String key : pl.getbutoiLemn().getConfigurationSection("Butoi").getKeys(false)) {
				if(pl.getbutoiLemn().getInt("Butoi."+key)==inttoReturn) {
					returnIDbL();
					inttoReturn=returnIDUnic();
					}
			}
		}
		return inttoReturn;
	}
	public int genereazaIDNou() {
		int toReturn =0;
		Random rnd = new Random();
		toReturn=rnd.nextInt(10000);
		return toReturn;
	}
	@SuppressWarnings("static-access")
	public void deschideInventar(Player p,Location loc) {
		
		invFill = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
		ItemMeta invFillM = invFill.getItemMeta();
		invFillM.setDisplayName(ChatColor.GREEN + "-");
		invFill.setItemMeta(invFillM);
		
		
		cobblePaper = new ItemStack(Material.PAPER);
		ItemMeta cobblePaperM = cobblePaper.getItemMeta();
		cobblePaperM.setDisplayName(ChatColor.RED + "^Slot Cobblestone^");
		List<String> cpmlore = new ArrayList<String>();cpmlore.add(ChatColor.GRAY + "Pune cobblestone in slotul deasupra");cpmlore.add(ChatColor.GREEN + "pentru a topi in lava");cpmlore.add(ChatColor.GREEN + "1 bloc de cobblestone este");cpmlore.add(ChatColor.GREEN + "echivalent a 250ml de lava");cpmlore.add(ChatColor.GREEN + "");cpmlore.add(ChatColor.GREEN + "");cpmlore.add(ChatColor.RED + "Incearca sa nu pui alte blocuri in");cpmlore.add(ChatColor.RED + "acest bloc,pentru siguranta");
		cobblePaperM.setLore(cpmlore);
		cobblePaperM.getPersistentDataContainer().set(pl.ciNK.paperInvKey, PersistentDataType.STRING,"ssba.paperInventory");
		cobblePaper.setItemMeta(cobblePaperM);
		
		
		List<String> fluidLore = new ArrayList<String>();fluidLore.add(ChatColor.GRAY + "Indicator nivel lichid>");fluidLore.add(ChatColor.GREEN + "Aceasta este o bare care indica nivelul lavei");fluidLore.add(ChatColor.GREEN + "Lava se genereaza in functie de sursa de incalzire");fluidLore.add(ChatColor.GREEN + "Torta genereaza 1x");fluidLore.add(ChatColor.GREEN + "Lava genereaza 3x");fluidLore.add(ChatColor.GREEN + "1x este echivalent cu 1 cobblestone topit");
		paperLava = retItemStack(Material.PAPER,ChatColor.RED + "Nivel lichid lava",fluidLore,pl.ciNK.paperFluidLavaKey,PersistentDataType.STRING,"ssba.paperFluidLava");
		List<String> ifl = new ArrayList<String>();ifl.add(ChatColor.GREEN+"In slotul deasupra pune o galeata goala");
		ifl.add(ChatColor.GREEN+"si apasa butonul pentru a salva galeata");
		ifl.add(ChatColor.GREEN+"Daca nivelul lavei este peste 1000mb");
		ifl.add(ChatColor.GREEN+"Poti apasa butonul pentru lava pentru a primi");
		ifl.add(ChatColor.GREEN+"o galeata cu lava");
		paperInfoLava = retItemStack(Material.BOOK,ChatColor.RED + "Instructiuni de folosire",ifl);
		if(pl.getCrucible().contains("Cruciblu")) {
			for(String key : pl.getCrucible().getConfigurationSection("Cruciblu").getKeys(false)) {
				Location loc2 = new Location(Bukkit.getWorld(pl.getCrucible().getString("Cruciblu." + key + ".World")), pl.getCrucible().getDouble("Cruciblu." + key + ".X"),pl.getCrucible().getDouble("Cruciblu." + key + ".Y"),pl.getCrucible().getDouble("Cruciblu." + key + ".Z"));
				if(loc.equals(loc2)) {
					crucibleInv= pl.getServer().createInventory(null, 27,ChatColor.RED + "Cruciblu");
					crucibleInv.setItem(9, cobblePaper);
					crucibleInv.setItem(15, paperLava);
					crucibleInv.setItem(17, paperInfoLava);
					
					p.openInventory(crucibleInv);
					for(int i =0;i<=26;i++) {
						ItemStack item = crucibleInv.getItem(i);
						if(item==null) {
							crucibleInv.setItem(i, invFill);
						}
					}
					//ButtonCobble
					List<String> button1lore = new ArrayList<String>();
					button1lore.add(ChatColor.GRAY + "Apasa pentru a salva cobblestone-ul");button1lore.add(ChatColor.GRAY + "odata pus nu il poti lua inapoi!");
					button1lore.add(ChatColor.GRAY + "");
					button1lore.add(ChatColor.GRAY + "Cantitate cobblestone: "+ChatColor.GREEN+pl.getCrucible().getInt("Cruciblu."+key + ".NivelLCobble"));
					button1 = retItemStack(Material.STONE_BUTTON,ChatColor.RED + "Buton Cobblestone",button1lore);
					crucibleInv.setItem(1, button1);
					//ButtonGaleata
					List<String> button2lore = new ArrayList<String>();
					button2lore.add(ChatColor.GRAY + "Apasa pentru a salva galetile");button2lore.add(ChatColor.GRAY + "odata salvate nu le poti lua inapoi!");
					button2 = retItemStack(Material.STONE_BUTTON,ChatColor.RED + "Buton Galeata",button2lore);
					crucibleInv.setItem(6, button2);
					//ButonLava
					List<String> button3lore = new ArrayList<String>();
					button3lore.add(ChatColor.GRAY + "Apasa pentru a primi lava");button3lore.add(ChatColor.GRAY + "Trebuie sa ai peste 1000mb de lava si o galeata");button3lore.add(ChatColor.GRAY + "pentru a putea umple o galeata cu lava!");
					button3 = retItemStack(Material.STONE_BUTTON,ChatColor.RED + "Buton Lava",button3lore);
					crucibleInv.setItem(24, button3);
					
					//NivelGaleti
					//NivelLava
					List<String> nll = new ArrayList<String>();nll.add(ChatColor.YELLOW+"Acesta este indicatorul de lava");nll.add(ChatColor.YELLOW+"Cantiatea maxima este de 8000mb");nll.add(ChatColor.YELLOW+"Ratia este de 1000 la 1");nll.add(ChatColor.YELLOW+"1000mb = 1 galeata");nll.add(ChatColor.RED +"Cantitate lava: " + pl.getCrucible().getInt("Cruciblu."+key + ".NivelLichid")+"/8000mb");
					nll.add(ChatColor.GRAY + "Numar galeti: "+ pl.getCrucible().getInt("Cruciblu."+key + ".CG"));
					NivelLava = retItemStack(Material.RED_STAINED_GLASS_PANE,ChatColor.RED + "Informatii",nll);
					crucibleInv.setItem(7, NivelLava);
					crucibleInv.setItem(16, NivelLava);
					crucibleInv.setItem(25, NivelLava);
					crucibleInv.setItem(0, null);
					crucibleInv.setItem(8, null);
					crucibleInv.setItem(26, null);
					
					
					
					p.updateInventory();
					
					new BukkitRunnable() {

						@Override
						public void run() {
							//ButtonCobble
							List<String> button1lore = new ArrayList<String>();
							button1lore.add(ChatColor.GRAY + "Apasa pentru a salva cobblestone-ul");button1lore.add(ChatColor.GRAY + "odata pus nu il poti lua inapoi!");
							button1lore.add(ChatColor.GRAY + "");
							button1lore.add(ChatColor.GRAY + "Cantitate cobblestone: "+ChatColor.GREEN+pl.getCrucible().getInt("Cruciblu."+key + ".NivelLCobble"));
							button1 = retItemStack(Material.STONE_BUTTON,ChatColor.RED + "Buton Cobblestone",button1lore);
							crucibleInv.setItem(1, button1);
							//ButtonGaleata
							List<String> button2lore = new ArrayList<String>();
							button2lore.add(ChatColor.GRAY + "Apasa pentru a salva galetile");button2lore.add(ChatColor.GRAY + "odata salvate nu le poti lua inapoi!");
							button2lore.add(ChatColor.GRAY + "Numar galeti: "+ ChatColor.GREEN+pl.getCrucible().getInt("Cruciblu."+key + ".CG"));
							button2 = retItemStack(Material.STONE_BUTTON,ChatColor.RED + "Buton Galeata",button2lore);
							crucibleInv.setItem(6, button2);
							//ButonLava
							List<String> button3lore = new ArrayList<String>();
							button3lore.add(ChatColor.GRAY + "Apasa pentru a primi lava");button3lore.add(ChatColor.GRAY + "Trebuie sa ai peste 1000mb de lava si o galeata");button3lore.add(ChatColor.GRAY + "pentru a putea umple o galeata cu lava!");
							button3 = retItemStack(Material.STONE_BUTTON,ChatColor.RED + "Buton Lava",button3lore);
							crucibleInv.setItem(24, button3);
							
							//Info Lava
							List<String> nll = new ArrayList<String>();nll.add(ChatColor.YELLOW+"Acesta este indicatorul de lava");nll.add(ChatColor.YELLOW+"Cantiatea maxima este de 8000mb");nll.add(ChatColor.YELLOW+"Ratia este de 1000 la 1");nll.add(ChatColor.YELLOW+"1000mb = 1 galeata");nll.add(ChatColor.RED +"Cantitate lava: " + pl.getCrucible().getInt("Cruciblu."+key + ".NivelLichid")+"/8000mb");
							nll.add(ChatColor.GRAY + "Numar galeti: "+ChatColor.GREEN+ pl.getCrucible().getInt("Cruciblu."+key + ".CG"));
							NivelLava = retItemStack(Material.RED_STAINED_GLASS_PANE,ChatColor.RED + "Informatii",nll);
							crucibleInv.setItem(7, NivelLava);
							crucibleInv.setItem(16, NivelLava);
							crucibleInv.setItem(25, NivelLava);
						}
						
					}.runTaskTimer(pl, 0, 60);
					
				}
			}
		}
		
	}
	@EventHandler
	public void deschideCruciblu(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block b = e.getClickedBlock();
			Location loc = b.getLocation();
			if(b.getType()==Material.CAULDRON) {
				if(pl.getCrucible().contains("Cruciblu")) {
					for(String key : pl.getCrucible().getConfigurationSection("Cruciblu").getKeys(false)) {
						Location loc2 = new Location(Bukkit.getWorld(pl.getCrucible().getString("Cruciblu." + key + ".World")), pl.getCrucible().getDouble("Cruciblu." + key + ".X"),pl.getCrucible().getDouble("Cruciblu." + key + ".Y"),pl.getCrucible().getDouble("Cruciblu." + key + ".Z"));
			        if(loc.equals(loc2)) {
			        	
			deschideInventar(e.getPlayer(),loc);
			        }
					}
					
				}
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemStack retItemStack(Material mat,String displayName,List<String>lore,NamespacedKey key,PersistentDataType type,String stringData) {
		ItemStack toReturn=new ItemStack(mat);
		ItemMeta trm=toReturn.getItemMeta();
		trm.setDisplayName(displayName);
		trm.setLore(lore);
		trm.getPersistentDataContainer().set(key, type, stringData);
		toReturn.setItemMeta(trm);
		return toReturn;
	}
	public ItemStack retItemStack(Material mat,String displayName,List<String>lore) {
		ItemStack toReturn=new ItemStack(mat);
		ItemMeta trm=toReturn.getItemMeta();
		trm.setDisplayName(displayName);
		trm.setLore(lore);
		toReturn.setItemMeta(trm);
		return toReturn;
	}
	@EventHandler
	public void clickInventarCruciblu(InventoryClickEvent e) {
		if(e.getClickedInventory()==null) {
			return;
		}
		if(e.getClickedInventory().equals(crucibleInv)) {
			if(e.getCurrentItem()==null) {
				return;
			}
			if(e.getCurrentItem().equals(paperInfoLava)) {
				e.setCancelled(true);
			}
			
			if(e.getCurrentItem().equals(cobblePaper)||e.getCurrentItem().equals(paperLava)||e.getCurrentItem().equals(NivelLava)||e.getCurrentItem().equals(invFill)) {
			e.setCancelled(true);
			}
			Block b = e.getWhoClicked().getTargetBlock(10);
			Location loc = b.getLocation();
			if(pl.getCrucible().contains("Cruciblu")) {
				for(String key : pl.getCrucible().getConfigurationSection("Cruciblu").getKeys(false)) {
					Location loc2 = new Location(Bukkit.getWorld(pl.getCrucible().getString("Cruciblu." + key + ".World")), pl.getCrucible().getDouble("Cruciblu." + key + ".X"),pl.getCrucible().getDouble("Cruciblu." + key + ".Y"),pl.getCrucible().getDouble("Cruciblu." + key + ".Z"));
					if(loc.equals(loc2)) {
						if(e.getCurrentItem().equals(button1)) {
							e.setCancelled(true);
							if(e.getInventory().getItem(0)!=null) {
								if(e.getInventory().getItem(0).getType()==Material.COBBLESTONE) {
								int amount = e.getInventory().getItem(0).getAmount();
								int cbl = pl.getCrucible().getInt("Cruciblu."+key+".NivelLCobble");
								pl.getCrucible().set("Cruciblu."+key+".NivelLCobble", amount+cbl);
								e.getInventory().setItem(0, null);
								sC();
								}
							}
						}
						if(e.getCurrentItem().equals(button2)) {
							e.setCancelled(true);
							if(e.getInventory().getItem(8)!=null) {
								if(e.getInventory().getItem(8).getType()==Material.BUCKET) {
									int amount = e.getInventory().getItem(8).getAmount();
									int gl = pl.getCrucible().getInt("Cruciblu."+key+".CG");
									pl.getCrucible().set("Cruciblu."+key+".CG", amount+gl);
									e.getInventory().setItem(8, null);
									sC();
								}
							}
						}
						if(e.getCurrentItem().equals(button3)) {
							e.setCancelled(true);
							if(e.getInventory().getItem(26)==null) {
								if(vCLG(key)) {
									e.getInventory().setItem(26, new ItemStack(Material.LAVA_BUCKET));
								}
							}
						}
					}
			
		}
			}
	}
	}
	public void sC() {
		try {
			pl.saveCrucible();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sbl() {
		try {
			pl.savebutoiLemn();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@EventHandler
	public void closeInventarCruciblu(InventoryCloseEvent e) {
		if(e.getInventory()==null) {
			return;
		}
		if(e.getInventory().equals(crucibleInv)) {
		Block b = e.getPlayer().getTargetBlock(10);
		Location loc = b.getLocation();
		if(pl.getCrucible().contains("Cruciblu")) {
			for(String key : pl.getCrucible().getConfigurationSection("Cruciblu").getKeys(false)) {
				Location loc2 = new Location(Bukkit.getWorld(pl.getCrucible().getString("Cruciblu." + key + ".World")), pl.getCrucible().getDouble("Cruciblu." + key + ".X"),pl.getCrucible().getDouble("Cruciblu." + key + ".Y"),pl.getCrucible().getDouble("Cruciblu." + key + ".Z"));
				if(loc.equals(loc2)) {
					if(e.getInventory().getItem(0)!=null) {
						ItemStack item = e.getInventory().getItem(0);
							loc.getWorld().dropItemNaturally(loc, item);
					}
					if(e.getInventory().getItem(8)!=null) {
						ItemStack item = e.getInventory().getItem(8);
							loc.getWorld().dropItemNaturally(loc, item);
					}
					if(e.getInventory().getItem(26)!=null) {
						ItemStack item = e.getInventory().getItem(26);
							loc.getWorld().dropItemNaturally(loc, item);
					}
					
					
					
				}
			}
		}
				
	}
	}
	public void convertesteLava() {
		if(pl.getCrucible()==null) {
			pl.getServer().getConsoleSender().sendMessage("Crucible.yml is null");
			return;
		}
		if(pl.getCrucible().contains("Cruciblu")) {
			new BukkitRunnable() {
				@Override
				public void run() {
			for(String key : pl.getCrucible().getConfigurationSection("Cruciblu").getKeys(false)) {
				Location loc2 = new Location(Bukkit.getWorld(pl.getCrucible().getString("Cruciblu." + key + ".World")), pl.getCrucible().getDouble("Cruciblu." + key + ".X"),pl.getCrucible().getDouble("Cruciblu." + key + ".Y"),pl.getCrucible().getDouble("Cruciblu." + key + ".Z"));
				if(pl.getCrucible().getInt("Cruciblu."+key+".NivelLCobble")>0) {
					Block b = loc2.getBlock();
					Location bbloc = b.getLocation().add(0,-1,0);
					Block bb = bbloc.getBlock();
					if(bb!=null) {
						if(bb.getType()==Material.TORCH) {
									int cobam = pl.getCrucible().getInt("Cruciblu."+key+".NivelLCobble");
									
									int lvlLava = pl.getCrucible().getInt("Cruciblu."+key+".NivelLichid");
									if(lvlLava>=8000) {
										return;
									}else {
										pl.getCrucible().set("Cruciblu."+key+".NivelLCobble", cobam-1);
										pl.getCrucible().set("Cruciblu."+key+".NivelLichid", lvlLava+250);
									}
									try {
										pl.saveCrucible();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
						if(bb.getType()==Material.LAVA) {
							int cobam = pl.getCrucible().getInt("Cruciblu."+key+".NivelLCobble");
							
							int lvlLava = pl.getCrucible().getInt("Cruciblu."+key+".NivelLichid");
							if(lvlLava>=8000) {
								return;
							}else {
								pl.getCrucible().set("Cruciblu."+key+".NivelLCobble", cobam-1);
								pl.getCrucible().set("Cruciblu."+key+".NivelLichid", lvlLava+750);
							}
							try {
								pl.saveCrucible();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
							}
						
					}.runTaskTimer(pl, 0, 100);
				}
	}
	public boolean vCLG(String key) {
		int lavamn = pl.getCrucible().getInt("Cruciblu."+key+".NivelLichid");
		int glam = pl.getCrucible().getInt("Cruciblu."+key+".CG");
		if(lavamn>=1000) {
			if(glam>=1) {
				pl.getCrucible().set("Cruciblu."+key+".NivelLichid", lavamn-1000);
				pl.getCrucible().set("Cruciblu."+key+".CG", glam-1);
				return true;
			}
		}
		return false;
	}
		
	//Pune butoi lemn
	@SuppressWarnings("static-access")
	@EventHandler
	public void punebutoiLemn(BlockPlaceEvent e) {
		if(e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			if(e.getBlock().getType()==Material.COMPOSTER) {
				Block block = e.getBlock();
			if(e.getPlayer().getInventory().getItemInMainHand().equals(pl.customItems.butoiLemn)) {
				int idUnic = returnIDbL();
				int defaultWL = 0;
				int defaultCL = 0;
				int defaultCG = 0;
				pl.getbutoiLemn().set("Butoi."+ idUnic + ".World", block.getWorld().getName());
				pl.getbutoiLemn().set("Butoi."+ idUnic + ".X", block.getX());
				pl.getbutoiLemn().set("Butoi."+ idUnic + ".Y", block.getY());
				pl.getbutoiLemn().set("Butoi."+ idUnic + ".Z", block.getZ());
				pl.getbutoiLemn().set("Butoi." + idUnic + ".NivelApa", defaultWL);
				pl.getbutoiLemn().set("Butoi." + idUnic + ".NivelSapling", defaultCL);
				pl.getbutoiLemn().set("Butoi."+idUnic+".CG", defaultCG);
				try {
					pl.savebutoiLemn();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			}
		}
		
	}
	//Sparge butoi lemn
	@SuppressWarnings("static-access")
	@EventHandler
	public void spargebutoiLemn(BlockBreakEvent e) {
		ItemStack butoi = pl.customItems.butoiLemn;
		Block bl = e.getBlock();
		Location loc = bl.getLocation();
		if(pl.getbutoiLemn().contains("Butoi")) {
			for(String key : pl.getbutoiLemn().getConfigurationSection("Butoi").getKeys(false)) {
				Location loc2 = new Location(Bukkit.getWorld(pl.getbutoiLemn().getString("Butoi." + key + ".World")), pl.getbutoiLemn().getDouble("Butoi." + key + ".X"),pl.getbutoiLemn().getDouble("Butoi." + key + ".Y"),pl.getbutoiLemn().getDouble("Butoi." + key + ".Z"));
	        if(loc.equals(loc2)) {
	        	pl.getbutoiLemn().getConfigurationSection("Butoi").set(key, null);
	        	try {
					pl.savebutoiLemn();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        	e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), butoi);
	        	e.setDropItems(false);
	        	return;
	        }
			}
		}else {
			return;
		}
	}
	
	//Click Dreapta butoi lemn
	@EventHandler
	public void deschidebutoiLemn(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block b = e.getClickedBlock();
			Location loc = b.getLocation();
			if(b.getType()==Material.COMPOSTER) {
				if(pl.getbutoiLemn().contains("Butoi")) {
					for(String key : pl.getbutoiLemn().getConfigurationSection("Butoi").getKeys(false)) {
						Location loc2 = new Location(Bukkit.getWorld(pl.getbutoiLemn().getString("Butoi." + key + ".World")), pl.getbutoiLemn().getDouble("Butoi." + key + ".X"),pl.getbutoiLemn().getDouble("Butoi." + key + ".Y"),pl.getbutoiLemn().getDouble("Butoi." + key + ".Z"));
			       if(loc.equals(loc2)) {
			deschidebutoiLemn(e.getPlayer(),loc);
			       }
			}
			}
		}
		}
	}
	public void deschidebutoiLemn(Player p,Location loc) {
		invFill = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
		ItemMeta invFillM = invFill.getItemMeta();
		invFillM.setDisplayName(ChatColor.GREEN + "-");
		invFill.setItemMeta(invFillM);
		
		List<String> saplingPaperLore = new ArrayList<>();
		saplingPaperLore.add(ChatColor.GRAY+"Pune saplingul deasupra");saplingPaperLore.add(ChatColor.GRAY+"Si apasa pe buton pentru a stoca saplingul");saplingPaperLore.add(ChatColor.GRAY+"Ratia este de 1 sapling = 250mb apa");saplingPaperLore.add(ChatColor.GRAY+"Incearca sa eviti punerea altor iteme in acel slot");
		saplingPaper= retItemStack(Material.PAPER,ChatColor.RED+"Slot Sapling",saplingPaperLore);
		
		List<String> paperApaLore = new ArrayList<>();
		paperApaLore.add(ChatColor.GRAY+"Aceasta este bara pentru nivelul apei");paperApaLore.add(ChatColor.GRAY+"Fiecare sapling este echivalent cu 250mb");paperApaLore.add(ChatColor.GRAY+"Fiecare galeata cu apa este echivalent a 1000mb");paperApaLore.add(ChatColor.GRAY+"Nu necesita nici o conditie de functionare!");
		paperApa = retItemStack(Material.PAPER,ChatColor.RED+"Nivel Apa",paperApaLore);
		
		List<String> paperInfoApaLore = new ArrayList<>();
		paperInfoApaLore.add(ChatColor.GRAY+"Pune saplingul in primul slot");paperInfoApaLore.add(ChatColor.GRAY+"Si apasa pentru butonul de sapling");paperInfoApaLore.add(ChatColor.GRAY+"Pune galeti in slotul deasupra");paperInfoApaLore.add(ChatColor.GRAY+"Si apasa butonul pentru a le salva");paperInfoApaLore.add(ChatColor.GRAY+"Odata ce ai destula apa si cel putin");paperInfoApaLore.add(ChatColor.GRAY+"o galeata,apasa pe butonul");paperInfoApaLore.add(ChatColor.GRAY+"de jos pentru a umple o galeata cu apa!");
		paperInfoApa = retItemStack(Material.BOOK,ChatColor.RED+"Instructiuni de folosire",paperInfoApaLore);
		
		List<String> nivelApaLore = new ArrayList<>();
		nivelApaLore.add(ChatColor.GRAY+"Acesta este indicatorul de apa");nivelApaLore.add(ChatColor.GRAY+"Cantitatea maxima este de 8000mb");nivelApaLore.add(ChatColor.GRAY+"In momentul de fata nivelul apei este");
		
		//Butoane
		
		List<String> butonGaleataLore = new ArrayList<>();
		butonGaleataLore.add(ChatColor.GRAY + "Apasa pentru a salva galetile");
		butonGaleataLore.add(ChatColor.RED + "Odata salvate nu le poti lua inapoi!");
		butonGaleata = retItemStack(Material.STONE_BUTTON,ChatColor.RED+"Buton Galeata",butonGaleataLore);
		
		List<String> butonApaLore = new ArrayList<>();
		butonApaLore.add(ChatColor.GRAY + "Apasa pentru a umple o galeata cu apa");
		butonApaLore.add(ChatColor.RED+"Trebuie sa ai cel putin 1000mb de apa");
		butonApaLore.add(ChatColor.RED+"si o galeata pentru a putea apasa");
		butonApa = retItemStack(Material.STONE_BUTTON,ChatColor.RED+"Buton Apa",butonApaLore);
		
		
		butoiLemnInv = pl.getServer().createInventory(null, 27,ChatColor.RED+"Butoi Lemn");
		
		
		if(pl.getbutoiLemn().contains("Butoi")) {
			for(String key : pl.getbutoiLemn().getConfigurationSection("Butoi").getKeys(false)) {
				Location loc2 = new Location(Bukkit.getWorld(pl.getbutoiLemn().getString("Butoi." + key + ".World")), pl.getbutoiLemn().getDouble("Butoi." + key + ".X"),pl.getbutoiLemn().getDouble("Butoi." + key + ".Y"),pl.getbutoiLemn().getDouble("Butoi." + key + ".Z"));
		        if(loc.equals(loc2)) {
		        	for(int i=0;i<=26;i++) {
		    			butoiLemnInv.setItem(i, invFill);
		    		}
		        	List<String> butonSaplingLore = new ArrayList<>();
    				
		        	butonSaplingLore.add(ChatColor.GRAY+"Pune saplinguri in slotul din stanga");butonSaplingLore.add(ChatColor.GRAY+"Si apasa pentru a le salva");butonSaplingLore.add(ChatColor.RED+"Odata salvate,nu le poti lua inapoi!");butonSaplingLore.add(ChatColor.GRAY+"Cantitate saplinguri: "+ChatColor.GREEN+pl.getbutoiLemn().getInt("Butoi."+key+".NivelSapling"));
		    		butonSapling = retItemStack(Material.STONE_BUTTON,ChatColor.RED+"Buton Sapling",butonSaplingLore);
		    		
		        	
		        	nivelApaLore.add(ChatColor.AQUA+""+pl.getbutoiLemn().getInt("Butoi."+key+".NivelApa")+"/8000mb");
		        	nivelApaLore.add(ChatColor.GRAY+"Cantitate galeti: "+ChatColor.GREEN+pl.getbutoiLemn().getInt("Butoi."+key+".CG"));
		        	nivelApa = retItemStack(Material.BLUE_STAINED_GLASS_PANE,ChatColor.AQUA+"Informatii",nivelApaLore);
		        	
		        	butoiLemnInv.setItem(0, null);
		    		butoiLemnInv.setItem(6, butonGaleata);
		    		butoiLemnInv.setItem(8, null);
		    		butoiLemnInv.setItem(9, saplingPaper);
		    		butoiLemnInv.setItem(15, paperApa);
		    		butoiLemnInv.setItem(16, nivelApa);
		    		butoiLemnInv.setItem(17, paperInfoApa);
		    		butoiLemnInv.setItem(24, butonApa);
		    		butoiLemnInv.setItem(26, null);
		    		p.openInventory(butoiLemnInv);
		    		p.updateInventory();
		        	new BukkitRunnable() {

		    			@Override
		    			public void run() {
		    				List<String> butonSaplingLore = new ArrayList<>();
		    				
		    				butonSaplingLore.add(ChatColor.GRAY+"Pune saplinguri in slotul din stanga");butonSaplingLore.add(ChatColor.GRAY+"Si apasa pentru a le salva");butonSaplingLore.add(ChatColor.RED+"Odata salvate,nu le poti lua inapoi!");butonSaplingLore.add(ChatColor.GRAY+"Cantitate saplinguri: "+ChatColor.GREEN+pl.getbutoiLemn().getInt("Butoi."+key+".NivelSapling"));
		    	    		butonSapling = retItemStack(Material.STONE_BUTTON,ChatColor.RED+"Buton Sapling",butonSaplingLore);
		    	    		
		    	    		List<String> nivelApaLore = new ArrayList<>();
		    	    		nivelApaLore.add(ChatColor.GRAY+"Acesta este indicatorul de apa");nivelApaLore.add(ChatColor.GRAY+"Cantitatea maxima este de 8000mb");nivelApaLore.add(ChatColor.GRAY+"In momentul de fata nivelul apei este");
		    	    		
		    	    		
		    	        	nivelApaLore.add(ChatColor.AQUA+""+pl.getbutoiLemn().getInt("Butoi."+key+".NivelApa")+"/8000mb");
		    	        	nivelApaLore.add(ChatColor.GRAY+"Cantitate galeti: "+ChatColor.GREEN+pl.getbutoiLemn().getInt("Butoi."+key+".CG"));
		    	        	nivelApa = retItemStack(Material.BLUE_STAINED_GLASS_PANE,ChatColor.AQUA+"Informatii",nivelApaLore);
		    	        
		    	        	butoiLemnInv.setItem(1, butonSapling);
		    	        	butoiLemnInv.setItem(7, nivelApa);
		    	        	butoiLemnInv.setItem(16, nivelApa);
		    	        	butoiLemnInv.setItem(25, nivelApa);
		    	        	
		    			}
		    			
		    		}.runTaskTimer(pl, 0, 60);
		        	
		        	
		        }
				}
				}
		
		
		
		
		
	}
	//Interact
	@EventHandler
	public void interactBL(InventoryClickEvent e) {
		if(e.getClickedInventory()==null) {
			return;
		}
		if(e.getClickedInventory().equals(butoiLemnInv)){
			Block b = e.getWhoClicked().getTargetBlock(10);
			Location loc = b.getLocation();
			if(pl.getbutoiLemn().contains("Butoi")) {
			for(String key : pl.getbutoiLemn().getConfigurationSection("Butoi").getKeys(false)) {
				Location loc2 = new Location(Bukkit.getWorld(pl.getbutoiLemn().getString("Butoi." + key + ".World")), pl.getbutoiLemn().getDouble("Butoi." + key + ".X"),pl.getbutoiLemn().getDouble("Butoi." + key + ".Y"),pl.getbutoiLemn().getDouble("Butoi." + key + ".Z"));
		        if(loc.equals(loc2)) {
			
		if(e.getCurrentItem()==null) {
			return;
		}
		if(e.getCurrentItem().equals(invFill)||e.getCurrentItem().equals(saplingPaper)||e.getCurrentItem().equals(paperApa)||e.getCurrentItem().equals(paperInfoApa)||e.getCurrentItem().equals(nivelApa)) {
			e.setCancelled(true);
		}
		if(e.getCurrentItem().equals(butonSapling)) {
			e.setCancelled(true);
			
				
			        	if(e.getInventory().getItem(0)!=null) {
			        		if(e.getInventory().getItem(0).getType()==Material.OAK_SAPLING) {
			        			int amnt = e.getInventory().getItem(0).getAmount();
			        			int sam = pl.getbutoiLemn().getInt("Butoi."+key+".NivelSapling");
			        			pl.getbutoiLemn().set("Butoi."+key+".NivelSapling", amnt+sam);
			        			e.getInventory().setItem(0, null);
			        			sbl();
			        		}
			        	}
			        	
			        }
		if(e.getCurrentItem().equals(butonGaleata)) {
			e.setCancelled(true);
			if(e.getInventory().getItem(8)!=null){
				if(e.getInventory().getItem(8).getType()==Material.BUCKET) {
					int amnt = e.getInventory().getItem(8).getAmount();
					int bam = pl.getbutoiLemn().getInt("Butoi."+key+".CG");
					pl.getbutoiLemn().set("Butoi."+key+".CG", amnt+bam);
					e.getInventory().setItem(8, null);
					sbl();
				}
			}
		}
		if(e.getCurrentItem().equals(butonApa)) {
			e.setCancelled(true);
			if(e.getInventory().getItem(26)==null) {
				if(vnag(key)) {
					e.getInventory().setItem(26, new ItemStack(Material.WATER_BUCKET));
				}
			}
		}
				}
			}
			        }
			
		}
	}
	public boolean vnag(String key) {
		int apaam = pl.getbutoiLemn().getInt("Butoi."+key+".NivelApa");
		int glam = pl.getbutoiLemn().getInt("Butoi."+key+".CG");
		if(apaam>=1000) {
			if(glam>=1) {
				pl.getbutoiLemn().set("Butoi."+key+".NivelApa", apaam-1000);
				pl.getbutoiLemn().set("Butoi."+key+".CG", glam-1);
				sbl();
				return true;
			}
		}
		return false;
	}
	//Inchide butoi
	@EventHandler
	public void inchidebutoiLemn(InventoryCloseEvent e) {
		if(e.getInventory().equals(butoiLemnInv)) {
			if(e.getInventory().getItem(0)!=null) {
				ItemStack item = e.getInventory().getItem(0);
				e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), item);
			}
			if(e.getInventory().getItem(8)!=null) {
				ItemStack item = e.getInventory().getItem(8);
				e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation(), item);
			}
			if(e.getInventory().getItem(26)!=null) {
				ItemStack item = e.getInventory().getItem(26);
				e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), item);
			}
		}
	}
	public void genereazabutoiLemn() {
		if(pl.getbutoiLemn()==null) {
			pl.getServer().getConsoleSender().sendMessage("butoiLemn is null");
			return;
		}
		if(pl.getbutoiLemn().contains("Butoi")) {
			new BukkitRunnable() {
				@Override
				public void run() {
					for(String key : pl.getbutoiLemn().getConfigurationSection("Butoi").getKeys(false)) {
			        	if(pl.getbutoiLemn().getInt("Butoi."+key+".NivelSapling")>0) {
									int sapling = pl.getbutoiLemn().getInt("Butoi."+key+".NivelSapling");
									pl.getbutoiLemn().set("Butoi."+key+".NivelSapling", sapling-1);
									int lvlapa = pl.getbutoiLemn().getInt("Butoi."+key+".NivelApa");
									if(lvlapa>=8000) {
										return;
									}else {
										pl.getbutoiLemn().set("Butoi."+key+".NivelApa", lvlapa+250);
									}
								}
			        	sbl();
					}
				
			
							
						
					
					
				
			
				
	}
}.runTaskTimer(pl, 0, 100);
		}
	}
	//CGEN
	public int genCobbleID() {
		Random rnd = new Random();
		int toReturn = rnd.nextInt(5000);
		if(pl.getCgen().contains("Gen")) {
			for(String key : pl.getCgen().getConfigurationSection("Gen").getKeys(false)) {
				if(pl.getCgen().getInt("Gen."+key)==toReturn) {
					toReturn = genCobbleID();
				}
			}
		}
		return toReturn;
	}
	@SuppressWarnings("static-access")
	//Pune
	@EventHandler
	public void puneCGEN(BlockPlaceEvent e) {
		if(e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			if(e.getBlock().getType()==Material.NETHER_QUARTZ_ORE) {
				Block block = e.getBlock();
			if(e.getPlayer().getInventory().getItemInMainHand().equals(pl.customItems.genCobble)) {
				
				int idUnic = genCobbleID();
				pl.getCgen().set("Gen."+ idUnic + ".World", block.getWorld().getName());
				pl.getCgen().set("Gen."+ idUnic + ".X", block.getX());
				pl.getCgen().set("Gen."+ idUnic + ".Y", block.getY());
				pl.getCgen().set("Gen."+ idUnic + ".Z", block.getZ());
				try {
					pl.saveCgen();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			}
		}
	}
	//Sparge
	@SuppressWarnings("static-access")
	@EventHandler
	public void spargeCGen(BlockBreakEvent e) {
		ItemStack cgen = pl.customItems.genCobble;
		Block bl = e.getBlock();
		Location loc = bl.getLocation();
		if(pl.getCgen().contains("Gen")) {
			for(String key : pl.getCgen().getConfigurationSection("Gen").getKeys(false)) {
				Location loc2 = new Location(Bukkit.getWorld(pl.getCgen().getString("Gen." + key + ".World")), pl.getCgen().getDouble("Gen." + key + ".X"),pl.getCgen().getDouble("Gen." + key + ".Y"),pl.getCgen().getDouble("Gen." + key + ".Z"));
	        if(loc.equals(loc2)) {
	        	pl.getCgen().getConfigurationSection("Gen").set(key, null);
	        	try {
					pl.saveCgen();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        	e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), cgen);
	        	e.setDropItems(false);
	        	e.setExpToDrop(0);
	        	return;
	        }
			}
		}else {
			return;
		}
	}
	//Genereaza Cobblestone
	public void genCobble() {
		if(pl.getCgen()==null){
			pl.getServer().getConsoleSender().sendMessage("cgen.yml is null");
			return;
		}
		if(pl.getCgen().contains("Gen")) {
			new BukkitRunnable() {

				@Override
				public void run() {
					for(String key : pl.getCgen().getConfigurationSection("Gen").getKeys(false)) {
						Location loc2 = new Location(Bukkit.getWorld(pl.getCgen().getString("Gen." + key + ".World")), pl.getCgen().getDouble("Gen." + key + ".X"),pl.getCgen().getDouble("Gen." + key + ".Y"),pl.getCgen().getDouble("Gen." + key + ".Z"));
				        Block b = loc2.getBlock();
				        if(b!=null) {
				        	//Chest
				        	Block ch = b.getWorld().getBlockAt(b.getX(), b.getY()+1, b.getZ());
				        	Chest chest = (Chest) ch.getState();
				        	if(ch.getType()==Material.CHEST) {
				        		chest.getInventory().addItem(new ItemStack(Material.COBBLESTONE));
				        	}
				        }
					}
					
				}
				
			}.runTaskTimer(pl, 0, 60);
		}
	}

	//Siever
	public int genIdSiva() {
		Random rnd = new Random();
		int intToReturn = 0;
		intToReturn = rnd.nextInt(10000);
		if(pl.getSiever().contains("Sieve")) {
		for(String key : pl.getSiever().getConfigurationSection("Sieve").getKeys(false)) {
			if(pl.getSiever().getInt("Sieve."+key)==intToReturn) {
				intToReturn = genIdSiva();
			}
		}
		}
		return intToReturn;
	}
	//Pune
	@SuppressWarnings("static-access")
	@EventHandler
	public void puneSita(BlockPlaceEvent e) {
		if(e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			if(e.getBlock().getType()==Material.CRACKED_STONE_BRICKS) {
				Block b = e.getBlock();
				if(e.getPlayer().getInventory().getItemInMainHand().equals(pl.customItems.siever)) {
					int idUnic = genIdSiva();
					pl.getSiever().set("Sieve."+idUnic+".World", b.getWorld().getName());
					pl.getSiever().set("Sieve."+idUnic+".X", b.getX());
					pl.getSiever().set("Sieve."+idUnic+".Y", b.getY());
					pl.getSiever().set("Sieve."+idUnic+".Z", b.getZ());
					pl.getSiever().set("Sieve."+idUnic+".Dirt", 0);
					pl.getSiever().set("Sieve."+idUnic+".Gravel", 0);
					pl.getSiever().set("Sieve."+idUnic+".Sand", 0);
					try {
						pl.saveSiever();
					}catch(IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	//Sparge
	@SuppressWarnings("static-access")
	@EventHandler
	public void spargeSieve(BlockBreakEvent e) {
		ItemStack toRet = pl.customItems.siever;
		Block bl = e.getBlock();
		Location loc = bl.getLocation();
		if(pl.getSiever().contains("Sieve")) {
			for(String key : pl.getSiever().getConfigurationSection("Sieve").getKeys(false)) {
				Location loc2 = new Location(Bukkit.getWorld(pl.getSiever().getString("Sieve."+key+".World")),pl.getSiever().getDouble("Sieve." + key + ".X"),pl.getSiever().getDouble("Sieve." + key + ".Y"),pl.getSiever().getDouble("Sieve." + key + ".Z"));
			if(loc.equals(loc2)) {
				pl.getSiever().getConfigurationSection("Sieve").set(key, null);
				try {
					
					pl.saveSiever();
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			}else{
				return;
			}
				
			loc.getWorld().dropItemNaturally(loc, toRet);
			e.setDropItems(false);
			
			}
			
		}

	}
	//Deschide Siever
	@EventHandler
	public void cdreaptaSiever(PlayerInteractEvent e) {
		
		if(e.getAction()==Action.RIGHT_CLICK_BLOCK) {
			Location loc = e.getClickedBlock().getLocation();
			if(e.getClickedBlock().getType()==Material.CRACKED_STONE_BRICKS) {
				if(pl.getSiever().contains("Sieve")) {
					for(String key : pl.getSiever().getConfigurationSection("Sieve").getKeys(false)) {
						Location loc2 = new Location(Bukkit.getWorld(pl.getSiever().getString("Sieve."+key+".World")),pl.getSiever().getDouble("Sieve." + key + ".X"),pl.getSiever().getDouble("Sieve." + key + ".Y"),pl.getSiever().getDouble("Sieve." + key + ".Z"));
					if(loc.equals(loc2)) {
						deschideSieve(e.getPlayer(),loc);
			}
		}
	}
			}
		}
	}
	public void deschideSieve(Player p,Location loc) {
		
		invFill = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
		ItemMeta invFillM = invFill.getItemMeta();
		invFillM.setDisplayName(ChatColor.GREEN + "-");
		invFill.setItemMeta(invFillM);
		
		
		sieverinv = pl.getServer().createInventory(null, 27, "Siever");
		for(int i =0;i<=26;i++) {
			sieverinv.setItem(i, invFill);
		}
		
		sieverinv.setItem(0, null);
		sieverinv.setItem(1, null);
		sieverinv.setItem(2, null);
		
		
		setItem(sieverinv,7,15,16,17,gconfig);
		setItem(sieverinv,25,gconfig);
		p.openInventory(sieverinv);
		p.updateInventory();
		if(pl.getSiever().contains("Sieve")) {
			for(String key : pl.getSiever().getConfigurationSection("Sieve").getKeys(false)) {
				Location loc2 = new Location(Bukkit.getWorld(pl.getSiever().getString("Sieve."+key+".World")),pl.getSiever().getDouble("Sieve." + key + ".X"),pl.getSiever().getDouble("Sieve." + key + ".Y"),pl.getSiever().getDouble("Sieve." + key + ".Z"));
			if(loc.equals(loc2)) {
				new BukkitRunnable() {

					@Override
					public void run() {
						List<String> dlore = new ArrayList<String>();
						List<String> slore = new ArrayList<String>();
						List<String> glore = new ArrayList<String>();
						dlore.add(ChatColor.GRAY+"Apasa pentru a salva dirtul");dlore.add(ChatColor.GRAY+"");dlore.add(ChatColor.RED+"Odata salvat nu il poti lua inapoi!");
						dlore.add(ChatColor.GRAY+"");dlore.add(ChatColor.GRAY+"Cantitate dirt: "+ChatColor.GREEN+""+pl.getSiever().getInt("Sieve."+key+".Dirt"));
						slore.add(ChatColor.GRAY+"Apasa pentru a salva sandul");slore.add(ChatColor.GRAY+"");slore.add(ChatColor.RED+"Odata salvat nu il poti lua inapoi!");
						slore.add(ChatColor.GRAY+"");slore.add(ChatColor.GRAY+"Cantitate sand: "+ChatColor.GREEN+""+pl.getSiever().getInt("Sieve."+key+".Sand"));
						glore.add(ChatColor.GRAY+"Apasa pentru a salva gravelul");glore.add(ChatColor.GRAY+"");glore.add(ChatColor.RED+"Odata salvat nu il poti lua inapoi!");
						glore.add(ChatColor.GRAY+"");glore.add(ChatColor.GRAY+"Cantitate gravel: "+ChatColor.GREEN+""+pl.getSiever().getInt("Sieve."+key+".Gravel"));
						bdirt = retItemStack(Material.STONE_BUTTON,ChatColor.RED+"Buton Dirt",dlore);
						bsand = retItemStack(Material.STONE_BUTTON,ChatColor.RED+"Buton Sand",slore);
						bgravel = retItemStack(Material.STONE_BUTTON,ChatColor.RED+"Buton Gravel",glore);
						gconfig = retItemStack(Material.BLUE_STAINED_GLASS_PANE,ChatColor.BLUE+"Config Iesiri/In lucru");
						sieverinv.setItem(9, bdirt);
						sieverinv.setItem(10, bsand);
						sieverinv.setItem(11, bgravel);
					}
					
				}.runTaskTimer(pl, 0, 60);
				
				
			}
			}
		}
	
		
		
		p.openInventory(sieverinv);
	}
	public ItemStack retItemStack(Material mat,String displayName) {
		ItemStack toRet = new ItemStack(mat);
		ItemMeta trm = toRet.getItemMeta();
		trm.setDisplayName(displayName);
		toRet.setItemMeta(trm);
		return toRet;
	}
	//Interact Siever
	@EventHandler
	public void clickSiever(InventoryClickEvent e) {
		if(e.getInventory().equals(sieverinv)) {
			Block b = e.getWhoClicked().getTargetBlock(10);
			Location loc = b.getLocation();
			if(e.getCurrentItem()==null) {
				return;
			}
			if(e.getCurrentItem().equals(invFill)||e.getCurrentItem().equals(gconfig)) {
				e.setCancelled(true);
			}
			if(pl.getSiever().contains("Sieve")) {
				for(String key : pl.getSiever().getConfigurationSection("Sieve").getKeys(false)) {
					Location loc2 = new Location(Bukkit.getWorld(pl.getSiever().getString("Sieve."+key+".World")),pl.getSiever().getDouble("Sieve." + key + ".X"),pl.getSiever().getDouble("Sieve." + key + ".Y"),pl.getSiever().getDouble("Sieve." + key + ".Z"));
				if(loc.equals(loc2)) {
					if(e.getCurrentItem().equals(bdirt)) {
						e.setCancelled(true);
						if(e.getInventory().getItem(0)!=null) {
							ItemStack slot0 = e.getInventory().getItem(0);
							if(slot0.getType()==Material.DIRT) {
								int amnt = slot0.getAmount();
								int cfgamnt = pl.getSiever().getInt("Sieve."+key+".Dirt");
								pl.getSiever().set("Sieve."+key+".Dirt", amnt+cfgamnt);
								e.getInventory().setItem(0, null);
								try {
									pl.saveSiever();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
								
							}
						}}
					
					if(e.getCurrentItem().equals(bsand)) {
						e.setCancelled(true);
						if(e.getInventory().getItem(1)!=null) {
							ItemStack item = e.getInventory().getItem(1);
							if(item.getType()==Material.SAND) {
								int amnt = item.getAmount();
								int cfgamnt = pl.getSiever().getInt("Sieve."+key+".Sand");
								pl.getSiever().set("Sieve."+key+".Sand", amnt+cfgamnt);
								e.getInventory().setItem(1, null);
								try {
									pl.saveSiever();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
					if(e.getCurrentItem().equals(bgravel)) {
						e.setCancelled(true);
						if(e.getInventory().getItem(2)!=null) {
							ItemStack slot2 = e.getInventory().getItem(2);
							if(slot2.getType()==Material.GRAVEL) {
								int amnt = slot2.getAmount();
								int cfgamnt = pl.getSiever().getInt("Sieve."+key+".Gravel");
								pl.getSiever().set("Sieve."+key+".Gravel", amnt+cfgamnt);
								e.getInventory().setItem(2, null);
								try {
									pl.saveSiever();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
					
					
				}
				}
			}
			
		}
	}
	//Inchide Siever
	@EventHandler
	public void inchideSieve(InventoryCloseEvent e) {
		if(e.getInventory().equals(sieverinv)) {
			Inventory i = e.getInventory();
			if(i.getItem(0)!=null) {
				ItemStack item = i.getItem(0);
				e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), item);
			}
			if(i.getItem(1)!=null) {
				ItemStack item = i.getItem(1);
				e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), item);
			}
			if(i.getItem(2)!=null) {
				ItemStack item = i.getItem(2);
				e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), item);
			}
		}
	}
	//GenSiva
	public void genSiva() {
		if(pl.getSiever()==null) {
			pl.getServer().getConsoleSender().sendMessage("siever.yml is null");
			return;
		}
		if(pl.getSiever().contains("Sieve")) {
			new BukkitRunnable() {

				@Override
				public void run() {
					for(String key : pl.getSiever().getConfigurationSection("Sieve").getKeys(false)) {
						Location loc2 = new Location(Bukkit.getWorld(pl.getSiever().getString("Sieve."+key+".World")),pl.getSiever().getDouble("Sieve." + key + ".X"),pl.getSiever().getDouble("Sieve." + key + ".Y"),pl.getSiever().getDouble("Sieve." + key + ".Z"));
						int dirtamnt=pl.getSiever().getInt("Sieve."+key+".Dirt");
						int sandamnt=pl.getSiever().getInt("Sieve."+key+".Sand");
						int gravelamnt=pl.getSiever().getInt("Sieve."+key+".Gravel");
						Block b = loc2.getBlock();
						Block ch = b.getWorld().getBlockAt(b.getX(),b.getY()+1,b.getZ());
						if(ch.getType()!=Material.CHEST) {
							return;
						}
						if(dirtamnt>=1) {
							Random rnd = new Random();
							Material mat = Material.DIRT;
							Material toReturn = Material.DIRT;
							int chance = rnd.nextInt(11);
							//Dirt
							if(mat==Material.DIRT) {
								//Saplings
								if(chance==10||chance==9||chance==8) {
									int saplch = rnd.nextInt(11);
									if(saplch==10||saplch==9) {
										toReturn=Material.OAK_SAPLING;
									}
									if(saplch==8||saplch==7) {
										toReturn=Material.JUNGLE_SAPLING;
									}
									if(saplch==6||saplch==5) {
										toReturn=Material.BIRCH_SAPLING;
									}
									if(saplch==4||saplch==3) {
										toReturn=Material.ACACIA_SAPLING;
									}
									if(saplch==2||saplch==1) {
										toReturn=Material.SPRUCE_SAPLING;
									}
									retItemChest(loc2,toReturn);
									pl.getSiever().set("Sieve."+key+".Dirt", dirtamnt-1);
									
								}
								//Seeds
								if(chance==7||chance==6||chance==5||chance==4) {
									int sc = rnd.nextInt(11);
									if(sc==10||sc==9) {
										toReturn=Material.WHEAT_SEEDS;
									}
									if(sc==8||sc==7) {
										toReturn=Material.CARROT;
									}
									if(sc==6||sc==5) {
										toReturn=Material.POTATO;
									}
									if(sc==4||sc==3) {
										toReturn=Material.SUGAR_CANE;
									}
									if(sc==2||sc==1) {
										toReturn=Material.MELON_SEEDS;
									}
									retItemChest(loc2,toReturn);
									pl.getSiever().set("Sieve."+key+".Dirt", dirtamnt-1);
									
								}
								if(chance==3||chance==2||chance==1) {
									toReturn=Material.STRING;
								}
								retItemChest(loc2,toReturn);
								pl.getSiever().set("Sieve."+key+".Dirt", dirtamnt-1);
								
							}
							
						}
						//Sand
						if(sandamnt>=1) {
							Random rnd = new Random();
							Material toReturn = Material.SAND;
							int chance=rnd.nextInt(11);
								if(chance==10||chance==9) {
									toReturn=Material.REDSTONE;
								}
								if(chance==8||chance==7) {
									toReturn=Material.LAPIS_LAZULI;
								}
								if(chance==6||chance==5) {
									toReturn=Material.BONE_MEAL;
								}
								if(chance==4||chance==3) {
									toReturn=Material.GLOWSTONE_DUST;
								}
								if(chance==2||chance==1) {
									toReturn=Material.CACTUS;
								}
								retItemChest(loc2,toReturn);
								pl.getSiever().set("Sieve."+key+".Sand", sandamnt-1);
								
							}
						if(gravelamnt>=1) {
							Random rnd = new Random();
							Material toReturn = Material.GRAVEL;
							int chance = rnd.nextInt(11);
							if(chance == 10) {
								toReturn =Material.DIAMOND;
							}
							if(chance==9) {
								toReturn=Material.EMERALD;
							}
							if(chance==8||chance==7) {
								toReturn=Material.GOLD_ORE;
							}
							if(chance==6||chance==5||chance==4) {
								toReturn=Material.IRON_ORE;
							}
							if(chance==3||chance==2||chance==1) {
								toReturn=Material.COAL;
							}
							retItemChest(loc2,toReturn);
							pl.getSiever().set("Sieve."+key+".Gravel", gravelamnt-1);
							
						}
						try {
							pl.saveSiever();
						} catch (IOException e) {
							e.printStackTrace();
						}
						}
						
						
					}
				
				
			
			
		}.runTaskTimer(pl, 0, 200);
		}
	}
	public void setItem(Inventory i,int slot,ItemStack item) {
		i.setItem(slot, item);
	}
	public void retItemChest(Location loc,Material mat) {
		ItemStack item = new ItemStack(mat);
		Block b1 = loc.getBlock();
		Block b = loc.getWorld().getBlockAt(b1.getX(),b1.getY()+1,b1.getZ());
		Chest chest = (Chest)b.getState();
		if(b.getType()==Material.CHEST) {
			chest.getInventory().addItem(item);
		}
	}
	public void setItem(Inventory i,int slot,int slot2,ItemStack item) {
		i.setItem(slot, item);
		i.setItem(slot2, item);
	}
	public void setItem(Inventory i,int slot,int slot2,int slot3,ItemStack item) {
		i.setItem(slot, item);
		i.setItem(slot2, item);
		i.setItem(slot3, item);
	}
	public void setItem(Inventory i,int slot,int slot2,int slot3,int slot4,ItemStack item) {
		i.setItem(slot, item);
		i.setItem(slot2, item);
		i.setItem(slot3, item);
		i.setItem(slot4, item);
	}
	
}
