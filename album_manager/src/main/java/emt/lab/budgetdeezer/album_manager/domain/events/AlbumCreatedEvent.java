package emt.lab.budgetdeezer.album_manager.domain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.lab.budgetdeezer.album_manager.domain.model.AlbumId;
import emt.lab.budgetdeezer.album_manager.domain.model.ArtistId;
import emt.lab.budgetdeezer.shared_kernel.domain.base.iDomainEvent;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

public class AlbumCreatedEvent implements iDomainEvent {

    @JsonProperty("artistId")
    private final ArtistId artistId;

    @JsonProperty("albumId")
    private final AlbumId albumId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public AlbumCreatedEvent(@JsonProperty("artistId") @NonNull ArtistId artistId,
                             @JsonProperty("albumId") @NonNull AlbumId albumId,
                             @JsonProperty("occurredOn") @NonNull Instant occurredOn) {

        this.artistId = Objects.requireNonNull(artistId, "artistId cannot be null");
        this.albumId = Objects.requireNonNull(albumId, "albumId cannot be null");
        this.occurredOn = Objects.requireNonNull(occurredOn, "occurredOn cannot be null");
    }

    public ArtistId getArtistId() {
        return this.artistId;
    }

    public AlbumId getAlbumId() {
        return albumId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
