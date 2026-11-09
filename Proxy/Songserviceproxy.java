import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongServiceProxy implements SongService {
    private final RealSongService realService;
    private final Map<Integer, Song> idCache = new HashMap<>();
    private final Map<String, List<Song>> titleCache = new HashMap<>();
    private final Map<String, List<Song>> albumCache = new HashMap<>();

    public SongServiceProxy(RealSongService realService) {
        this.realService = realService;
    }

    @Override
    public Song searchById(Integer songID) {
        if (idCache.containsKey(songID)) {
            System.out.println("Proxy: cache hit for id " + songID);
            return idCache.get(songID);
        }
        System.out.println("Proxy: cache miss for id " + songID + " -> calling server");
        Song song = realService.searchById(songID);
        idCache.put(songID, song);
        return song;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        String key = title.toLowerCase();
        if (titleCache.containsKey(key)) {
            System.out.println("Proxy: cache hit for title '" + title + "'");
            return titleCache.get(key);
        }
        System.out.println("Proxy: cache miss for title '" + title + "' -> calling server");
        List<Song> results = realService.searchByTitle(title);
        titleCache.put(key, results);
        return results;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        String key = album.toLowerCase();
        if (albumCache.containsKey(key)) {
            System.out.println("Proxy: cache hit for album '" + album + "'");
            return albumCache.get(key);
        }
        System.out.println("Proxy: cache miss for album '" + album + "' -> calling server");
        List<Song> results = realService.searchByAlbum(album);
        albumCache.put(key, results);
        return results;
    }
}