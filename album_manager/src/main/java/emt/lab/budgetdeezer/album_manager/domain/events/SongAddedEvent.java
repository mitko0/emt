package emt.lab.budgetdeezer.album_manager.domain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.lab.budgetdeezer.album_manager.domain.model.AlbumId;
import emt.lab.budgetdeezer.album_manager.domain.model.SongId;
import emt.lab.budgetdeezer.shared_kernel.domain.base.iDomainEvent;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

public class SongAddedEvent implements iDomainEvent {

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonProperty("albumId")
    private final AlbumId albumId;

    @JsonProperty("songId")
    private final SongId songId;

    @JsonCreator
    public SongAddedEvent(@JsonProperty("occurredOn") @NonNull Instant occurredOn,
                          @JsonProperty("albumId") @NonNull AlbumId albumId,
                          @JsonProperty("songId") @NonNull SongId songId) {
        this.occurredOn = Objects.requireNonNull(occurredOn, "occurredOn cannot be null");
        this.albumId = Objects.requireNonNull(albumId, "albumId cannot be null");
        this.songId = Objects.requireNonNull(songId, "songId cannot be null");
    }

    @Override
    public Instant occurredOn() {
        return this.occurredOn;
    }

    public AlbumId getAlbumId() {
        return albumId;
    }

    public SongId getSongId() {
        return songId;
    }
}
