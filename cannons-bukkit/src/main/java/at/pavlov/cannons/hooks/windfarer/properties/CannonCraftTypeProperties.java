package at.pavlov.cannons.hooks.windfarer.properties;

import at.pavlov.cannons.Cannons;
import net.countercraft.movecraft.craft.type.PropertyKey;
import net.countercraft.movecraft.craft.type.PropertyKeyTypes;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

import java.util.List;

public class CannonCraftTypeProperties {

    // Simple types
    public static final PropertyKey<Integer> MIN_CANNON_MASS = PropertyKeyTypes.intPropertyKey(new NamespacedKey(Cannons.getPlugin(), "mass/min"), 0);
    public static final PropertyKey<Integer> MAX_CANNON_MASS = PropertyKeyTypes.intPropertyKey(new NamespacedKey(Cannons.getPlugin(), "mass/max"), 0);

    public static final PropertyKey<Boolean> USE_SHIP_ANGLES = PropertyKeyTypes.boolPropertyKey(new NamespacedKey(Cannons.getPlugin(), "use_ship_angles"), false);

    // Object types
    public static final PropertyKey<List<String>> EXCLUDE_FROM_MASS = PropertyKeyTypes.stringListPropertyKey(new NamespacedKey(Cannons.getPlugin(), "mass/excluded"), List.of());

    public static void register() {
        ConfigurationSerialization.registerClass(CannonTypeConstraint.class, "CannonsRevamped_CannonTypeConstraint");
    }
}
