package emt.lab.budgetdeezer.artist_manager.domain.repository;

import emt.lab.budgetdeezer.artist_manager.domain.model.Artist;
import emt.lab.budgetdeezer.artist_manager.domain.model.ArtistId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, ArtistId> {
}
