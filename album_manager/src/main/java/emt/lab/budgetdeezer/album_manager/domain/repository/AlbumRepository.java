package emt.lab.budgetdeezer.album_manager.domain.repository;

import emt.lab.budgetdeezer.album_manager.domain.model.Album;
import emt.lab.budgetdeezer.album_manager.domain.model.AlbumId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, AlbumId> {
}
