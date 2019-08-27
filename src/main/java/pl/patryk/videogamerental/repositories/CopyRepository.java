package pl.patryk.videogamerental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryk.videogamerental.model.Copy;

import java.util.List;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {

    List<Copy> findAllCopiesByGameId(long gameId);
}
