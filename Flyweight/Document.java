import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Document {
    private final List<Character> characters = new ArrayList<>();
    private final CharacterPropertiesFactory factory;

    public Document(CharacterPropertiesFactory factory) {
        this.factory = factory;
    }

    public void addCharacter(char value, String font, String color, int size) {
        CharacterProperties props = factory.getProperties(font, color, size);
        characters.add(new Character(value, props));
    }

    public void save(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Character c : characters) {
                CharacterProperties p = c.getProperties();
                writer.write(c.getValue() + "," + p.getFont() + "," + p.getColor() + "," + p.getSize());
                writer.newLine();
            }
        }
        System.out.println("Saved document to " + fileName);
    }

    public void load(String fileName) throws IOException {
        characters.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                char value   = parts[0].charAt(0);
                String font  = parts[1];
                String color = parts[2];
                int size     = Integer.parseInt(parts[3]);
                addCharacter(value, font, color, size);
            }
        }
        System.out.println("Loaded document from " + fileName);
    }

    public void print() {
        for (Character c : characters) {
            CharacterProperties p = c.getProperties();
            System.out.println("'" + c.getValue() + "'  ["
                    + p.getFont() + ", " + p.getColor() + ", " + p.getSize() + "]");
        }
    }

    public int getCharacterCount() {
        return characters.size();
    }
}