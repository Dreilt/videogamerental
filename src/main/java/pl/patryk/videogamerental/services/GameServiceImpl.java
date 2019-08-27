package pl.patryk.videogamerental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.patryk.videogamerental.model.Copy;
import pl.patryk.videogamerental.model.Game;
import pl.patryk.videogamerental.repositories.CopyRepository;
import pl.patryk.videogamerental.repositories.GameRepository;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CopyRepository copyRepository;

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
}
