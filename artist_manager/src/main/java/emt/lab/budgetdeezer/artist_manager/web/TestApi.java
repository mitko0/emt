package emt.lab.budgetdeezer.artist_manager.web;

import emt.lab.budgetdeezer.artist_manager.domain.model.AccountState;
import emt.lab.budgetdeezer.artist_manager.domain.model.AccountType;
import emt.lab.budgetdeezer.artist_manager.domain.model.Artist;
import emt.lab.budgetdeezer.artist_manager.domain.model.PersonName;
import emt.lab.budgetdeezer.artist_manager.domain.repository.ArtistRepository;
import emt.lab.budgetdeezer.shared_kernel.domain.value.classification.Genre;
import emt.lab.budgetdeezer.shared_kernel.domain.value.classification.GenreType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequestMapping("/api/artist")
@RestController
public class TestApi {

    private final ArtistRepository artistRepository;

    public TestApi(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @GetMapping("/create")
    public ResponseEntity<Artist> createAlbum() {
        Artist artist = new Artist(PersonName.valueOf("mitko", "mil"),
                LocalDate.now(),
                LocalDate.now(),
                Genre.valueOf(GenreType.Classical),
                AccountState.valueOf(AccountType.Trial));

        artistRepository.save(artist);

        return ResponseEntity.ok().body(artist);
    }
}