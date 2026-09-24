package at.pavlov.cannons.hooks.windfarer.listener;

import net.countercraft.movecraft.events.CraftGatherAdditionalDetectionStepsEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class RegisterDetectionStepsListener implements Listener {

    // Low so this fires after windfarer => Windfarer's sign detection needs to run first
    @EventHandler(priority = EventPriority.LOW)
    public void onDetectAddSteps(final CraftGatherAdditionalDetectionStepsEvent event) {
        // Cannon detection
        // Can return null, if that step is not to be run for that craft!
    }

}
