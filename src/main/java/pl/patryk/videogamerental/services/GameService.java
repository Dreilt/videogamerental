package pl.patryk.videogamerental.services;

import pl.patryk.videogamerental.model.Copy;
import pl.patryk.videogamerental.model.Game;

import java.util.List;

public interface GameService {

    Game findGameByName(String name);

    void saveGame(Game game, Copy copy);

    List<Game> findAllGames();

    Game findOneGameById(long gameId);

    void updateGame(Game game, long gameId);

    void deleteGame(long gameId);
}
