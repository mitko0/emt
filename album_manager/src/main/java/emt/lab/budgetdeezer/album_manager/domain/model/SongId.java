package emt.lab.budgetdeezer.album_manager.domain.model;

import emt.lab.budgetdeezer.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class SongId extends DomainObjectId {
    protected SongId() {
        super(DomainObjectId.generateId(SongId.class).toString());
    }

    private SongId(String id) {
        super(id);
    }

    public static SongId valueOf(String id) {
        return new SongId(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !this.getClass().equals(obj.getClass())) return false;

        SongId id = (SongId) obj;
        return Objects.equals(this.getId(), id.getId());
    }

    @Override
    public String toString() {
        return this.getId();
    }
}
