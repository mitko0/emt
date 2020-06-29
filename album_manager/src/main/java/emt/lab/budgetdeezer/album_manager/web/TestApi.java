package emt.lab.budgetdeezer.album_manager.web;

import emt.lab.budgetdeezer.album_manager.application.AlbumAppService;
import emt.lab.budgetdeezer.album_manager.domain.model.Album;
import emt.lab.budgetdeezer.album_manager.domain.model.ArtistId;
import emt.lab.budgetdeezer.album_manager.domain.model.ObjectName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequestMapping("/api/album")
@RestController
public class TestApi {

    private final AlbumAppService albumAppService;

    public TestApi(AlbumAppService albumAppService) {
        this.albumAppService = albumAppService;
    }

    @GetMapping("/create")
    public ResponseEntity<Album> createAlbum() {
        Album album = new Album(ObjectName.valueOf("album 1"),
                LocalDate.now(), ArtistId.valueOf("cf95fdc3-201a-476d-ad8b-c29f17d619fc"));

        return ResponseEntity.ok().body(albumAppService.createAlbum(album));
    }
}
