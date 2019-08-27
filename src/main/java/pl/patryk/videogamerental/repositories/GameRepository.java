package pl.patryk.videogamerental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryk.videogamerental.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findGameByName(String name);
}
