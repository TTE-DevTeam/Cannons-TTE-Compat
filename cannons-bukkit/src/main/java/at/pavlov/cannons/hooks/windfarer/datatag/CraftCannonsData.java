package at.pavlov.cannons.hooks.windfarer.datatag;

import at.pavlov.cannons.cannon.Cannon;
import at.pavlov.cannons.cannon.CannonManager;
import at.pavlov.cannons.hooks.windfarer.DataTagKeys;
import net.countercraft.movecraft.MovecraftLocation;
import net.countercraft.movecraft.craft.Craft;
import net.countercraft.movecraft.craft.PlayerCraft;
import net.countercraft.movecraft.processing.MovecraftWorld;
import net.countercraft.movecraft.processing.functions.Result;
import net.countercraft.movecraft.util.MathUtils;
import net.countercraft.movecraft.util.hitboxes.BitmapHitBox;
import net.countercraft.movecraft.util.hitboxes.MutableHitBox;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CraftCannonsData {

    // Search happens multithreaded, thus the concurrent set
    protected Set<Cannon> cannons = ConcurrentHashMap.newKeySet();
    protected MutableHitBox locationBitMap = new BitmapHitBox();

    public static @NotNull CraftCannonsData of(Craft craft) {
        return craft.getDataTag(DataTagKeys.CANNONS);
    }

    // Not the prettiest way, but saves memory
    public void postDetection() {
        this.locationBitMap = null;
    }

    // Attempts to register a cannon at that location, if there is one
    public @NotNull Result checkAndAddCannon(@NotNull MovecraftLocation movecraftLocation, @NotNull MovecraftWorld movecraftWorld, @NotNull Craft craft) {
        if (!(craft instanceof PlayerCraft playerCraft)) {
            return Result.fail();
        }

        // Avoid checking blocks of known cannons
        if (!cannons.isEmpty() && locationBitMap.inBounds(movecraftLocation) && locationBitMap.contains(movecraftLocation)) {
            // We already know a cannon there!
            return Result.fail();
        }

        // Check for cannon, if there is one, add it to ourselves
        final Cannon atLocation = CannonManager.getInstance().getCannon(movecraftLocation.toBukkit(craft.getWorld()), playerCraft.getPilot().getUniqueId());
        if (atLocation != null) {
            if (cannons.add(atLocation)) {
                for (Location cannonBlock : atLocation.getCannonDesign().getAllCannonBlocks(atLocation)) {
                    locationBitMap.add(MathUtils.bukkit2MovecraftLoc(cannonBlock));
                }
                return Result.succeed();
            }
        }
        return Result.fail();
    }
}
