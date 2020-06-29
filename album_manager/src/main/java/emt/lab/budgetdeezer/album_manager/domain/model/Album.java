package emt.lab.budgetdeezer.album_manager.domain.model;

import emt.lab.budgetdeezer.shared_kernel.domain.base.AbstractEntity;
import emt.lab.budgetdeezer.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Table(name = "albums")
@Entity
public class Album extends AbstractEntity<AlbumId> {

    @Transient
    private static final LocalDate RELEASE_DATE_MIN_VALUE = LocalDate.of(1900, 1, 1);

    @Version
    private Long version;

    @Embedded
    private ObjectName albumName;

    private boolean active = false;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "artist_id"))
    })
    private ArtistId artistId;

    @OneToMany(mappedBy = "album",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Song> songs = new LinkedHashSet<>();

    protected Album() {}

    public Album(ObjectName albumName, LocalDate releaseDate, ArtistId artistId) {
        super(DomainObjectId.generateId(AlbumId.class));
        this.setAlbumName(albumName);
        this.setReleaseDate(releaseDate);
        this.setArtistId(artistId);
    }

    public ArtistId getArtistId() { return artistId; }

    public void setArtistId(ArtistId id) {
        this.artistId = id;
    }

    public ObjectName getAlbumName() {
        return albumName;
    }

    public void setAlbumName(ObjectName albumName) {
        this.albumName = albumName;
    }

    public boolean isAlbumActive() {
        return active;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate.isBefore(RELEASE_DATE_MIN_VALUE)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.releaseDate = releaseDate;
    }

    public Set<Song> getSongs() {
        return this.songs;
    }

    public Song addSong(Song song) {
        Objects.requireNonNull(song);

        this.songs.add(song);
        this.active = true;
        return song;
    }

    public Song removeSong(SongId songId) {
        Objects.requireNonNull(songId);

        List<Song> wantedSongs = this.songs
                .stream()
                .filter(song -> song.getId().equals(songId))
                .collect(Collectors.toList());

        if (wantedSongs.isEmpty()) {
            throw new IllegalArgumentException("Invalid song id");
        }

        Song wantedSong = wantedSongs.get(0);
        this.songs.remove(wantedSong);
        this.active = !this.songs.isEmpty();
        return wantedSong;
    }

    public Duration getTotalAlbumDuration() {
        return this.songs
                .stream()
                .map(Song::getDuration)
                .reduce(Duration.ofSeconds(0), Duration::plus);
    }

    public int getSongCount() {
        return this.songs != null
                ? this.songs.size()
                : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || !this.getClass().equals(obj.getClass())) return false;

        var other = (Album) obj;
        return this.getId() != null && this.getId().equals(other.getId());
    }
}
