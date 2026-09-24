package at.pavlov.cannons.hooks.windfarer;

import at.pavlov.cannons.Cannons;
import at.pavlov.cannons.hooks.windfarer.datatag.CraftCannonsData;
import net.countercraft.movecraft.craft.datatag.CraftDataTagKey;
import net.countercraft.movecraft.craft.datatag.CraftDataTagRegistry;
import org.bukkit.NamespacedKey;

import javax.naming.Name;

public class DataTagKeys {

    public static final CraftDataTagKey<CraftCannonsData> CANNONS = CraftDataTagRegistry.INSTANCE.registerTagKey(new NamespacedKey(Cannons.getPlugin(), "cannons/cannons_data"), c -> new CraftCannonsData());

}
