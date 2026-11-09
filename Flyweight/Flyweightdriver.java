import java.io.IOException;

public class FlyweightDriver {
    public static void main(String[] args) throws IOException {
        CharacterPropertiesFactory factory = new CharacterPropertiesFactory();
        Document doc = new Document(factory);

        String text = "HelloWorldCS3560";

        String[][] variations = {
            {"Arial",   "Red",   "12"},
            {"Calibri", "Blue",  "14"},
            {"Verdana", "Black", "16"},
            {"Arial",   "Blue",  "12"}
        };

        for (int i = 0; i < text.length(); i++) {
            String[] v = variations[i % variations.length];
            doc.addCharacter(text.charAt(i), v[0], v[1], Integer.parseInt(v[2]));
        }

        System.out.println();
        System.out.println("Characters in document: " + doc.getCharacterCount());
        System.out.println("Unique property objects in factory: " + factory.getPoolSize());
        System.out.println("(16 characters share only " + factory.getPoolSize() + " property objects)");

        System.out.println();
        doc.save("HelloWorldCS3560.txt");

        System.out.println();
        System.out.println("--- Reloading from file ---");
        Document loaded = new Document(factory);
        loaded.load("HelloWorldCS3560.txt");
        System.out.println();
        loaded.print();
    }
}
