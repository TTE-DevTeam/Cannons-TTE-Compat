package at.pavlov.cannons.hooks.windfarer;

import net.countercraft.movecraft.craft.Craft;
import net.countercraft.movecraft.craft.type.CraftProperties;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

// Due to Windfarer and Movecraft sharing partly the same classes, some ugly hacks are sadly necessary
public class WindfarerUtils {

    private static final MethodHandle m_getCraftProperties;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(
                    Craft.class,
                    MethodHandles.lookup()
            );

            m_getCraftProperties = lookup.findVirtual(
                    Craft.class,
                    "getCraftProperties",
                    MethodType.methodType(CraftProperties.class)
            );
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static @Nullable CraftProperties getCraftProperties(final Craft craft) {
        try {
            return (CraftProperties) m_getCraftProperties.invoke(craft);
        } catch (Throwable throwable) {
            return null;
        }
    }

}
