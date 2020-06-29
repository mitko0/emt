package emt.lab.budgetdeezer.artist_manager.domain.model;

import emt.lab.budgetdeezer.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class AlbumId extends DomainObjectId {

    protected AlbumId() {
        super(DomainObjectId.generateId(AlbumId.class).toString());
    }

    private AlbumId(String id) {
        super(id);
    }

    public static AlbumId valueOf(String id) {
        return new AlbumId(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !this.getClass().equals(obj.getClass())) return false;

        AlbumId id = (AlbumId) obj;
        return Objects.equals(this.getId(), id.getId());
    }

    @Override
    public String toString() {
        return this.getId();
    }
}
