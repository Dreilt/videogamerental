package pl.patryk.videogamerental.services;

import pl.patryk.videogamerental.model.Copy;
import pl.patryk.videogamerental.model.Game;

public interface CopyService {

    void saveCopy(Game game, Copy copy);
}
