package features;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import main.StellrowSBAdditionsMain;

public class Recipes {
	private StellrowSBAdditionsMain pl= StellrowSBAdditionsMain.getInstance();
	

	public void registerRecipes() {
		registerPebble();
		registerHammer();
		registerCobbleGen();
		registerSitaString();
		registerPortelan();
		registerCruciblu();
		registerButoiLemn();
		registerSitaLemn();
		registerSiever();
	}
	
	@SuppressWarnings("static-access")
	private void registerSiever() {
		ItemStack sieve = pl.customItems.siever;
		ShapedRecipe sr = new ShapedRecipe(pl.ciNK.sieverKey,sieve);
		ItemStack sitaString = pl.customItems.sitaString;
		sr.shape("WSW","WRW","WIW");
		sr.setIngredient('S', sitaString);
		sr.setIngredient('W', Material.OAK_LOG);
		sr.setIngredient('R', Material.REDSTONE);
		sr.setIngredient('I', Material.IRON_INGOT);
		pl.getServer().addRecipe(sr);
		
	}
	@SuppressWarnings({ "static-access", "unused" })
	private void registerDebug() {
		ItemStack item = new ItemStack(Material.CARROT_ON_A_STICK);
		ItemMeta itemm = item.getItemMeta();
		itemm.setCustomModelData(1);
		item.setItemMeta(itemm);
		ShapelessRecipe sr = new ShapelessRecipe(pl.ciNK.debugKey,item);
		sr.addIngredient(2,Material.OAK_LOG);
		pl.getServer().addRecipe(sr);
	}
	private void registerPebble() {
		@SuppressWarnings("static-access")
		ItemStack pebble = pl.customItems.pebble;
		ItemStack cobble = new ItemStack(Material.COBBLESTONE);
		@SuppressWarnings("static-access")
		ShapelessRecipe cobbleSR = new ShapelessRecipe(pl.ciNK.pebbleKey,cobble);
		cobbleSR.addIngredient(4, pebble);
		pl.getServer().addRecipe(cobbleSR);
	}
	private void registerHammer() {
		@SuppressWarnings("static-access")
		ItemStack hammer = pl.customItems.hammer;
		@SuppressWarnings("static-access")
		ShapedRecipe hammerSR = new ShapedRecipe(pl.ciNK.hammerKey,hammer);
		hammerSR.shape(" W "," SW","S  ");
		hammerSR.setIngredient('W', Material.OAK_PLANKS);
		hammerSR.setIngredient('S', Material.STICK);
		pl.getServer().addRecipe(hammerSR);
	}
	@SuppressWarnings("static-access")
	private void registerSitaLemn() {
		ItemStack sl = pl.customItems.sitaLemn;
		
		ShapelessRecipe slSR = new ShapelessRecipe(pl.ciNK.sitaLemnKey,sl);
		slSR.addIngredient(9, Material.STICK);
		pl.getServer().addRecipe(slSR);
	}
	private void registerCobbleGen() {
		@SuppressWarnings("static-access")
		ItemStack cgen = pl.customItems.genCobble;
		@SuppressWarnings("static-access")
		ShapedRecipe cgenSR = new ShapedRecipe(pl.ciNK.cgenkey,cgen);
		cgenSR.shape("CCC","WGL","CCC");
		cgenSR.setIngredient('C', Material.COBBLESTONE);
		cgenSR.setIngredient('W', Material.WATER_BUCKET);
		cgenSR.setIngredient('G', Material.GLASS);
		cgenSR.setIngredient('L', Material.LAVA_BUCKET);
		pl.getServer().addRecipe(cgenSR);
	}
	@SuppressWarnings("static-access")
	private void registerSitaString() {
		ItemStack ss = pl.customItems.sitaString;
		
		ShapelessRecipe ssSR = new ShapelessRecipe(pl.ciNK.sitastringKey,ss);
		ssSR.addIngredient(9,Material.STRING);
		pl.getServer().addRecipe(ssSR);
		
	}
	@SuppressWarnings("static-access")
	private void registerCruciblu() {
		ItemStack cruciblu = pl.customItems.cruciblu;
		ItemStack portelan = pl.customItems.portelan;
		ShapedRecipe crSR = new ShapedRecipe(pl.ciNK.crucibluKey,cruciblu);
		crSR.shape("P P","P P","PPP");
		crSR.setIngredient('P', portelan);
		pl.getServer().addRecipe(crSR);
	}
	@SuppressWarnings("static-access")
	private void registerPortelan() {
		ItemStack portelan = pl.customItems.portelan;
		ShapelessRecipe pSR = new ShapelessRecipe(pl.ciNK.portelanKey,portelan);
		pSR.addIngredient(1,Material.CLAY_BALL);
		pSR.addIngredient(1, Material.BONE_MEAL);
		pl.getServer().addRecipe(pSR);
	}
	@SuppressWarnings("static-access")
	private void registerButoiLemn() {
		ItemStack butoiLemn = pl.customItems.butoiLemn;
		ShapedRecipe blSR = new ShapedRecipe(pl.ciNK.butoiLemnKey,butoiLemn);
		blSR.shape("W W","W W","WSW");
		blSR.setIngredient('W', Material.OAK_PLANKS);
		blSR.setIngredient('S', Material.OAK_SLAB);
		pl.getServer().addRecipe(blSR);
	}
}
