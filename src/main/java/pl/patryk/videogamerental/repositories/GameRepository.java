package pl.patryk.videogamerental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.patryk.videogamerental.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findGameByName(String name);

    @Modifying
    @Query(value = "UPDATE games g SET g.name = :newName, g.description = :newDescription, g.developer = :newDeveloper, g.publisher = :newPublisher, g.game_language = :newGameLanguage, g.subtitle_language = :newSubtitleLanguage, g.rating = :newRating WHERE g.id= :gameId", nativeQuery = true)
    void updateGame(@Param("newName") String newName, @Param("newDescription") String newDescription, @Param("newDeveloper") String newDeveloper, @Param("newPublisher") String newPublisher, @Param("newGameLanguage") String newGameLanguage, @Param("newSubtitleLanguage") String newSubtitleLanguage, @Param("newRating") String newRating, @Param("gameId") long gameId);
}
