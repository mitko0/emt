package emt.lab.budgetdeezer.album_manager.application;

import emt.lab.budgetdeezer.album_manager.domain.events.AlbumCreatedEvent;
import emt.lab.budgetdeezer.album_manager.domain.events.SongAddedEvent;
import emt.lab.budgetdeezer.album_manager.domain.model.Album;
import emt.lab.budgetdeezer.album_manager.domain.model.AlbumId;
import emt.lab.budgetdeezer.album_manager.domain.model.ArtistId;
import emt.lab.budgetdeezer.album_manager.domain.repository.AlbumRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;


@Transactional
@Service
public class AlbumAppService {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final AlbumRepository albumRepository;

    public AlbumAppService(ApplicationEventPublisher applicationEventPublisher, AlbumRepository albumRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.albumRepository = albumRepository;
    }

    public Album createAlbum(@NonNull Album album) {
        Objects.requireNonNull(album,"Album cannot be null");
        var newAlbum = this.albumRepository.saveAndFlush(album);

        ArtistId artistId = newAlbum.getArtistId();
        AlbumId albumId = newAlbum.getId();
        this.applicationEventPublisher
                .publishEvent(new AlbumCreatedEvent(artistId, albumId, Instant.now()));

        /*newAlbum
                .getSongs()
                .forEach(song -> applicationEventPublisher
                        .publishEvent(new SongAddedEvent(Instant.now(), albumId, song.getId())));*/

        return album;
    }
}
