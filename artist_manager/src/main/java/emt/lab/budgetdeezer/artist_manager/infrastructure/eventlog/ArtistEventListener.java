package emt.lab.budgetdeezer.artist_manager.infrastructure.eventlog;

import emt.lab.budgetdeezer.artist_manager.domain.events.AlbumCreatedEvent;
import emt.lab.budgetdeezer.artist_manager.domain.model.Artist;
import emt.lab.budgetdeezer.artist_manager.domain.model.ArtistId;
import emt.lab.budgetdeezer.artist_manager.domain.repository.ArtistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


@Transactional
@Service
public class ArtistEventListener {

    private final ArtistRepository artistRepository;

    public ArtistEventListener(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onAlbumCreated(AlbumCreatedEvent event) {
        ArtistId artistId = event.getArtistId();
        Artist artist = this.artistRepository.findById(artistId).orElseThrow(RuntimeException::new);

        artist.registerNewAlbums(1);
        this.artistRepository.save(artist);
    }
}
