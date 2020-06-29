package emt.lab.budgetdeezer.album_manager.domain.model;

import emt.lab.budgetdeezer.shared_kernel.domain.base.iValueObject;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class ObjectName implements iValueObject {

    private String name;

    protected ObjectName() {}

    private ObjectName(@NonNull String name) {
        this.name = name;
    }

    public static ObjectName valueOf(@NonNull String name) {
        return new ObjectName(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !this.getClass().equals(obj.getClass())) return false;

        ObjectName objectName = (ObjectName) obj;
        return Objects.equals(this.name, objectName.name);
    }

    @Override
    public String toString() {
        return name;
    }

}
