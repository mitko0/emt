package emt.lab.budgetdeezer.shared_kernel.domain.value.classification;

import emt.lab.budgetdeezer.shared_kernel.domain.base.iValueObject;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Getter
@Embeddable
public class Genre implements iValueObject {

    @Enumerated(EnumType.STRING)
    @Column(name = "genre_type")
    private GenreType genreType;

    protected Genre() {}

    private Genre(@NonNull GenreType genreType) {
        this.genreType = genreType;
    }

    public static Genre valueOf(@NonNull GenreType genreType) {
        return new Genre(genreType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.genreType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Genre genre = (Genre) obj;
        return Objects.equals(this.genreType, genre.genreType);
    }

    @Override
    public String toString() {
        return genreType.name();
    }
}
