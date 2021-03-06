package pl.patryk.videogamerental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.patryk.videogamerental.model.Copy;
import pl.patryk.videogamerental.model.Game;
import pl.patryk.videogamerental.model.ReservationHistory;
import pl.patryk.videogamerental.repositories.CopyRepository;
import pl.patryk.videogamerental.repositories.GameRepository;
import pl.patryk.videogamerental.repositories.ReservationHistoryRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private ReservationHistoryRepository reservationHistoryRepository;

    @Override
    public Game findGameByName(String name) {
        return gameRepository.findGameByName(name);
    }

    @Override
    public void saveGame(Game game, Copy copy) {
        gameRepository.save(game);
        copy.setReserved(0);
        copy.setGame(game);
        copy.setUser(null);
        copyRepository.save(copy);
    }

    @Override
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game findOneGameById(long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }


    @Override
    public void updateGame(Game game, long gameId) {
        gameRepository.updateGame(game.getName(), game.getDescription(), game.getDeveloper(), game.getPublisher(), game.getGameLanguage(), game.getSubtitleLanguage(), game.getRating(), gameId);
    }

    @Override
    public void deleteGame(long gameId) {
        gameRepository.deleteById(gameId);
        copyRepository.deleteAllCopiesByGameId(gameId);
    }

    @Override
    public List<Game> findReservedGames(long userId) {
        List<ReservationHistory> historyList = reservationHistoryRepository.findReservationHistoryByUserId(userId);
        List<Game> gameList = new ArrayList<>();
        for (ReservationHistory reservationHistory : historyList) {
            gameList.add(reservationHistory.getGame());
        }

        return gameList;
    }

    @Override
    public List<Game> findGameBySearch(String searchString) {
        return gameRepository.findGameBySearch(searchString);
    }
}
