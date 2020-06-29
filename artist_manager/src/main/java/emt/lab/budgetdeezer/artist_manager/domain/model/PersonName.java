package emt.lab.budgetdeezer.artist_manager.domain.model;

import emt.lab.budgetdeezer.shared_kernel.domain.base.iValueObject;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class PersonName implements iValueObject {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    protected PersonName() {
    }

    private PersonName(@NonNull String firstName, @NonNull String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static PersonName valueOf(@NonNull String firstName, @NonNull String lastName) {
        return new PersonName(firstName, lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !this.getClass().equals(obj.getClass())) return false;

        PersonName personName = (PersonName) obj;
        return Objects.equals(this.firstName, personName.firstName)
                && Objects.equals(this.lastName, personName.lastName);
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}
