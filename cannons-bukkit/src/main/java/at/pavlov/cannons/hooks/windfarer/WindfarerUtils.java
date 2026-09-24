package at.pavlov.cannons.hooks.windfarer;

import net.countercraft.movecraft.craft.Craft;
import net.countercraft.movecraft.craft.type.CraftProperties;

import javax.annotation.Nullable;
import java.lang.reflect.Method;

// Due to Windfarer and Movecraft sharing partly the same classes, some ugly hacks are sadly necessary
public class WindfarerUtils {

    private static final Method m_getCraftProperties;

    static {
        try {
            m_getCraftProperties = Craft.class.getDeclaredMethod("getCraftProperties");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static @Nullable CraftProperties getCraftProperties(final Craft craft) {
        try {
            Object result = m_getCraftProperties.invoke(craft);
            if (result instanceof CraftProperties craftProperties) {
                return craftProperties;
            } else {
                return null;
            }
        } catch(Exception exception) {
            return null;
        }
    }

}
