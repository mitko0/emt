package emt.lab.budgetdeezer.artist_manager.domain.model;

import emt.lab.budgetdeezer.shared_kernel.domain.base.AbstractEntity;
import emt.lab.budgetdeezer.shared_kernel.domain.base.DomainObjectId;
import emt.lab.budgetdeezer.shared_kernel.domain.value.classification.Genre;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "artists")
@Entity
public class Artist extends AbstractEntity<ArtistId> {

    @Version
    private Long version;

    @Embedded
    private PersonName artistName;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(name = "account_creation_date")
    private LocalDate accountCreationDate;

    @Embedded
    private Genre preferredGenre;

    @Embedded
    private AccountState accountState;

    protected Artist() {}

    public Artist(PersonName artistName, LocalDate birthDay, LocalDate accountCreationDate, Genre preferredGenre, AccountState accountState) {
        super(DomainObjectId.generateId(ArtistId.class));
        this.artistName = artistName;
        this.birthDay = birthDay;
        this.accountCreationDate = accountCreationDate;
        this.preferredGenre = preferredGenre;
        this.accountState = accountState;
    }

    public Artist(PersonName artistName) {
        super(DomainObjectId.generateId(ArtistId.class));
        this.artistName = artistName;
    }

    public void changeArtistName(PersonName artistName) {
        this.artistName = artistName;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public void setPreferredGenre(Genre preferredGenre) {
        this.preferredGenre = preferredGenre;
    }

    public void registerNewAlbums(int count) {
        this.accountState = this.accountState.registerAlbums(count);
    }

    public void unregisterAlbums(int count) {
        this.accountState = this.accountState.unregisterAlbums(count);
    }

    public void changeAccountType(AccountType accountType) {
        this.accountState = this.accountState.changeAccountType(accountType);
    }

    public PersonName getArtistName() {
        return artistName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public Genre getPreferredGenre() {
        return preferredGenre;
    }

    public AccountState getAccountState() {
        return this.accountState;
    }
}
