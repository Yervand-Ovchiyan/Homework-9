public class Character {
    private final char value;
    private final CharacterProperties properties;
 
    public Character(char value, CharacterProperties properties) {
        this.value = value;
        this.properties = properties;
    }
 
    public char getValue() { return value; }
    public CharacterProperties getProperties() { return properties; }
}