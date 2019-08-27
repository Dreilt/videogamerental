package pl.patryk.videogamerental.services;

import pl.patryk.videogamerental.model.Copy;
import pl.patryk.videogamerental.model.Game;

import java.util.List;

public interface CopyService {

    void saveCopy(Game game, Copy copy);

    List<Copy> findAllCopiesByGameId(long gameId);

    void deleteCopy(long gameId, long copyId);
}
