package at.pavlov.cannons.hooks.windfarer.task;

import at.pavlov.cannons.Cannons;
import at.pavlov.cannons.hooks.windfarer.datatag.CraftCannonsData;
import net.countercraft.movecraft.MovecraftLocation;
import net.countercraft.movecraft.craft.Craft;
import net.countercraft.movecraft.processing.effects.Effect;
import net.countercraft.movecraft.util.BlockCollectionUtil;

import java.util.Set;
import java.util.function.Supplier;

public class CannonDetectionTask implements Supplier<Effect> {

    protected final Craft craft;

    public CannonDetectionTask(Craft craft) {
        this.craft = craft;
    }

    @Override
    public Effect get() {
        // Iterate over all positions aboard the craft multithreaded
        // Attempt to check if there is a cannon there
        // Access the cannons object
        // Add the cannons to it

        final long startTime = System.currentTimeMillis();
        final CraftCannonsData cannonsData = CraftCannonsData.of(this.craft);
        final Set<MovecraftLocation> cannonRootLocations = BlockCollectionUtil.getLocations(
                this.craft,
                cannonsData::checkAndAddCannon
        );
        cannonsData.postDetection();

        // Print out, how many cannons we have detected
        final long timeTaken = System.currentTimeMillis() - startTime;
        Cannons.getPlugin().getLogger().info("Successfully detected <" + cannonRootLocations.size() + "> aboard Craft <" + craft.getUUID().toString() + "> in " + timeTaken + "ms !");

        // We dont have a effect that we will run, so return null!
        return null;
    }

}
