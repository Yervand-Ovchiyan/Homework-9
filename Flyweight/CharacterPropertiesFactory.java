import java.util.HashMap;
import java.util.Map;
public class CharacterPropertiesFactory {
    private final Map<String, CharacterProperties> pool = new HashMap<>();

    public CharacterProperties getProperties(String font, String color, int size) {
        String key = font + "," + color + "," + size;
        CharacterProperties props = pool.get(key);
        if (props == null) {
            props = new CharacterProperties(font, color, size);
            pool.put(key, props);
            System.out.println("Factory: created new properties -> " + key);
        } else {
            System.out.println("Factory: reused existing properties -> " + key);
        }
        return props;
    }

    public int getPoolSize() {
        return pool.size();
    }
}
