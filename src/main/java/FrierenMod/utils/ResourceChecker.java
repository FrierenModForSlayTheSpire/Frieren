package FrierenMod.utils;

public class ResourceChecker {
    public static boolean exist(String imgPath){
        ClassLoader classLoader = ResourceChecker.class.getClassLoader();
        return classLoader.getResource(imgPath) != null;
    }
}
