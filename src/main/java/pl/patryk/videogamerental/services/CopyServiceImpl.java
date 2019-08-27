package pl.patryk.videogamerental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.patryk.videogamerental.model.Copy;
import pl.patryk.videogamerental.model.Game;
import pl.patryk.videogamerental.model.ReservationHistory;
import pl.patryk.videogamerental.model.User;
import pl.patryk.videogamerental.repositories.CopyRepository;
import pl.patryk.videogamerental.repositories.ReservationHistoryRepository;
import pl.patryk.videogamerental.utilities.UserUtilities;

import java.util.List;

@Service
public class CopyServiceImpl implements CopyService {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationHistoryRepository reservationHistoryRepository;

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

    @Override
    public Copy findOneCopyById(long copyId) {
        return copyRepository.findById(copyId).orElse(null);
    }

    @Override
    public void doReservation(Game game, Copy copy) {
        String userEmail = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(userEmail);

        copy.setReserved(1);
        copy.setUser(user);
        copyRepository.save(copy);

        ReservationHistory reservationHistory = new ReservationHistory();
        reservationHistory.setGame(game);
        reservationHistory.setCopy(copy);
        reservationHistory.setUser(user);
        reservationHistoryRepository.save(reservationHistory);
    }

    @Override
    public void doRent(Game game, Copy copy) {
        copy.setReserved(2);
        copyRepository.save(copy);
    }

    @Override
    public void doAvailable(Game game, Copy copy) {
        copy.setReserved(0);
        copy.setUser(null);
        copyRepository.save(copy);
    }
}
