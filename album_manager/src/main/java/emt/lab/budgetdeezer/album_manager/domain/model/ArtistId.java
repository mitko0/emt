package emt.lab.budgetdeezer.album_manager.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import emt.lab.budgetdeezer.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ArtistId extends DomainObjectId {

    protected ArtistId() {
        super(DomainObjectId.generateId(ArtistId.class).toString());
    }

    @JsonCreator
    private ArtistId(String id) {
        super(id);
    }

    public static ArtistId valueOf(String id) {
        return new ArtistId(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !this.getClass().equals(obj.getClass())) return false;

        ArtistId id = (ArtistId) obj;
        return Objects.equals(this.getId(), id.getId());
    }

    @Override
    public String toString() {
        return this.getId();
    }
}
