package pl.patryk.videogamerental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.patryk.videogamerental.model.Copy;
import pl.patryk.videogamerental.model.Game;
import pl.patryk.videogamerental.repositories.CopyRepository;

import java.util.List;

@Service
public class CopyServiceImpl implements CopyService {

    @Autowired
    private CopyRepository copyRepository;

    @Override
    public void saveCopy(Game game, Copy copy) {
        copy.setReserved(0);
        copy.setGame(game);
        copy.setUser(null);
        copyRepository.save(copy);
    }

    @Override
    public List<Copy> findAllCopiesByGameId(long gameId) {
        return copyRepository.findAllCopiesByGameId(gameId);
    }

    @Override
    public void deleteCopy(long gameId, long copyId) {
        copyRepository.deleteById(copyId);
    }
}
