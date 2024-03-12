package FrierenMod.gameHelpers;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ObjectCloner {
    private static final Objenesis objenesis = new ObjenesisStd();

    public static <T> T copyObject(T source) {
        if (source == null) {
            throw new IllegalArgumentException("source object must not be null");
        }

        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) source.getClass();
        T copy = objenesis.newInstance(clazz);

        // Perform shallow copy
        // (You may need to implement a deep copy method depending on your requirements)
        copyFields(source, copy);

        return copy;
    }

    private static <T> void copyFields(T source, T target) {
        Class<?> clazz = source.getClass();
        while (clazz != null) {
            for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    field.set(target, field.get(source));
                } catch (IllegalAccessException e) {
                    // Handle exception as needed
                    e.printStackTrace();
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}
