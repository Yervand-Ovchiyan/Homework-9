import java.util.ArrayList;
import java.util.List;

public class RealSongService implements SongService {
    private final List<Song> library = new ArrayList<>();

    public void addSong(Song song) {
        library.add(song);
    }

    private void simulateDelay() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    @Override
    public Song searchById(Integer songID) {
        simulateDelay();
        for (Song s : library) {
            if (s.getSongID().equals(songID)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        simulateDelay();
        List<Song> results = new ArrayList<>();
        for (Song s : library) {
            if (s.getTitle().equalsIgnoreCase(title)) {
                results.add(s);
            }
        }
        return results;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        simulateDelay();
        List<Song> results = new ArrayList<>();
        for (Song s : library) {
            if (s.getAlbum().equalsIgnoreCase(album)) {
                results.add(s);
            }
        }
        return results;
    }
}