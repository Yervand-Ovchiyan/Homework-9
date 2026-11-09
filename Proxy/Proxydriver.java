import java.util.List;

public class ProxyDriver {
    public static void main(String[] args) {
        RealSongService realService = new RealSongService();
        realService.addSong(new Song(1, "Bohemian Rhapsody", "Queen", "A Night at the Opera", 354));
        realService.addSong(new Song(2, "Love of My Life", "Queen", "A Night at the Opera", 219));
        realService.addSong(new Song(3, "Imagine", "John Lennon", "Imagine", 183));
        realService.addSong(new Song(4, "Jealous Guy", "John Lennon", "Imagine", 261));
        realService.addSong(new Song(5, "Billie Jean", "Michael Jackson", "Thriller", 294));
        realService.addSong(new Song(6, "Thriller", "Michael Jackson", "Thriller", 358));
        realService.addSong(new Song(7, "Hotel California", "Eagles", "Hotel California", 391));

        SongServiceProxy proxy = new SongServiceProxy(realService);

        System.out.println("=== searchById(5) ===");
        timedById(proxy, 5);
        System.out.println();
        System.out.println("=== searchById(5) again ===");
        timedById(proxy, 5);

        System.out.println();
        System.out.println("=== searchByTitle(\"Thriller\") ===");
        timedByTitle(proxy, "Thriller");
        System.out.println();
        System.out.println("=== searchByTitle(\"Thriller\") again ===");
        timedByTitle(proxy, "Thriller");

        System.out.println();
        System.out.println("=== searchByAlbum(\"Imagine\") ===");
        timedByAlbum(proxy, "Imagine");
        System.out.println();
        System.out.println("=== searchByAlbum(\"Imagine\") again ===");
        timedByAlbum(proxy, "Imagine");
    }

    private static void timedById(SongService service, Integer id) {
        long start = System.currentTimeMillis();
        Song song = service.searchById(id);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Result: " + song);
        System.out.println("Time: " + elapsed + " ms");
    }

    private static void timedByTitle(SongService service, String title) {
        long start = System.currentTimeMillis();
        List<Song> songs = service.searchByTitle(title);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Result: " + songs);
        System.out.println("Time: " + elapsed + " ms");
    }

    private static void timedByAlbum(SongService service, String album) {
        long start = System.currentTimeMillis();
        List<Song> songs = service.searchByAlbum(album);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Result: " + songs);
        System.out.println("Time: " + elapsed + " ms");
    }
}