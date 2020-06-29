package emt.lab.budgetdeezer.shared_kernel.domain.base;

import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.MappedSuperclass;
import java.util.Objects;
import java.util.UUID;

@Getter
@MappedSuperclass
public class DomainObjectId implements iValueObject {

    private String id;

    protected DomainObjectId() {
    }

    public DomainObjectId(String id) {
        this.id = id;
    }

    /**
     * Creates a new, random instance of the given {@code idClass}.
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public static <ID extends DomainObjectId> ID generateId(@NonNull Class<ID> idClass) {
        Objects.requireNonNull(idClass, "idClass cannot be null");

        try {
            return (ID) idClass.getMethod("valueOf", String.class).invoke(null, UUID.randomUUID().toString());
            // return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new RuntimeException("Could not create new instance of " + idClass, ex);
        }
    }

}
