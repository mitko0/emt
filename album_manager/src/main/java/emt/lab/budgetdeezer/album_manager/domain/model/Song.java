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

    private String name;
    private ReleaseInfo releaseInfo;

    private Duration duration;

    @ManyToOne
    private Album album;

    private int numberOfPlays;

    public Song(){}

    public Song(String name, ReleaseInfo releaseInfo, Duration duration){
        this.name = name;
        this.releaseInfo = releaseInfo;
        this.duration = duration;
        this.numberOfPlays = 0;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public void increaseNumberOfPlays(){
        this.numberOfPlays = this.numberOfPlays + 1;
    }

    public String getName(){
        return name;
    }

    public String setName(String name){
        return this.name = name;
    }

    public void setAlbum(Album album){
        this.album = album;
    }

    public ReleaseInfo getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(ReleaseInfo releaseInfo) {
        this.releaseInfo = releaseInfo;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public int getNumberOfPlays() {
        return numberOfPlays;
    }
}
