package customitems;

import org.bukkit.NamespacedKey;

import main.StellrowSBAdditionsMain;

public class CustomItemsNamespacedKey {
	private StellrowSBAdditionsMain pl= StellrowSBAdditionsMain.getInstance();
	public static NamespacedKey pebbleKey;
	public static NamespacedKey hammerKey;
	public static NamespacedKey cgenkey;
	public static NamespacedKey sitastringKey;
	public static NamespacedKey crucibluKey;
	public static NamespacedKey portelanKey;
	public static NamespacedKey paperInvKey;
	public static NamespacedKey paperFluidLavaKey;
	public static NamespacedKey butoiLemnKey;
	public static NamespacedKey debugKey;
	public static NamespacedKey sitaLemnKey;
	public static NamespacedKey sieverKey;
	
	//Econ
	public static NamespacedKey econShopGate;
	public void registerKeys() {
		sitaLemnKey = new NamespacedKey(pl,"ssba.sitalemn");
		debugKey = new NamespacedKey(pl,"ssba.debugKey");
		pebbleKey = new NamespacedKey(pl,"ssba.pebble");
		hammerKey = new NamespacedKey(pl,"ssba.whammer");
		cgenkey = new NamespacedKey(pl,"ssba.cgen");
		sitastringKey = new NamespacedKey(pl,"ssba.sitastring");
		crucibluKey = new NamespacedKey(pl,"ssba.cruciblu");
		portelanKey = new NamespacedKey(pl,"ssba.portelan");
		paperInvKey = new NamespacedKey(pl,"ssba.paperInv");
		paperFluidLavaKey = new NamespacedKey(pl,"ssba.paperFluidLavaKey");
		butoiLemnKey = new NamespacedKey(pl,"ssba.butoiLemn");
		sieverKey = new NamespacedKey(pl,"ssba.siever");
		econShopGate = new NamespacedKey(pl,"ssba.econ.shopGate");
		
	}

}
