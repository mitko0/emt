package emt.lab.budgetdeezer.album_manager.domain.model;

import emt.lab.budgetdeezer.shared_kernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Duration;

@Table(name = "songs")
@Entity
public class Song extends AbstractEntity<SongId> {

    @Version
    private Long version;

    private Duration duration;

    @ManyToOne
    private Album album;

    public Duration getDuration() {
        return this.duration;
    }
}
