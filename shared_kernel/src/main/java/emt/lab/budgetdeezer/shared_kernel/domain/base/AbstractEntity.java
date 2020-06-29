package emt.lab.budgetdeezer.shared_kernel.domain.base;

import lombok.Getter;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class AbstractEntity<ID extends DomainObjectId>
        implements iIdentifiableDomainObject<ID> {

    @EmbeddedId
    private ID id;

    public AbstractEntity() {
    }

    public AbstractEntity(ID id) {
        this.id = id;
    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || !this.getClass().equals(obj.getClass())) return false;

        var other = (AbstractEntity<?>) obj;
        return this.id != null && this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return this.id == null ? super.hashCode() : this.id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", this.getClass().getSimpleName(), this.id);
    }
}
