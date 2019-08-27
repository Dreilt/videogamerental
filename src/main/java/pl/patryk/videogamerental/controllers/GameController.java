package pl.patryk.videogamerental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.patryk.videogamerental.forms.GameForm;
import pl.patryk.videogamerental.model.Copy;
import pl.patryk.videogamerental.model.Game;
import pl.patryk.videogamerental.model.User;
import pl.patryk.videogamerental.services.CopyService;
import pl.patryk.videogamerental.services.GameService;
import pl.patryk.videogamerental.services.UserService;
import pl.patryk.videogamerental.utilities.UserUtilities;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/addgame")
    public String showGameAddingForm(Model model) {
        model.addAttribute("game", new GameForm());
        model.addAttribute("copy", new Copy());
        return "addgame";
    }

    @PostMapping(value = "/addgame")
    public String createGame(@Valid GameForm form, @Valid Copy copy, Model model) throws IOException {
        Game game = new Game(form.getName(), form.getDescription(), form.getDeveloper(), form.getPublisher(), form.getGameLanguage(), form.getSubtitleLanguage(), form.getRating(), form.getCover().getBytes());

        Game gameExists = gameService.findGameByName(game.getName());
        if (gameExists != null) {
            model.addAttribute("msgFailure", "Taka gra już istnieje!");
            return "addgame";
        } else {
            gameService.saveGame(game, copy);
            model.addAttribute("msgSuccess", "Gra została dodana.");
            model.addAttribute("game", new Game());
            return "addgame";
        }
    }

    @GetMapping(value = "/games")
    public String showAllGames(Model model) {
        model.addAttribute("gameList", gameService.findAllGames());
        return "games";
    }

    @GetMapping(value = "/game/{gameId}")
    public String showOneGame(@PathVariable(value = "gameId") long gameId, Model model) {
        model.addAttribute("game", gameService.findOneGameById(gameId));
        model.addAttribute("copyList", copyService.findAllCopiesByGameId(gameId));

        String userEmail = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(userEmail);
        model.addAttribute("currentUser", user);

        return "game";
    }

    @GetMapping(value = "/game/{gameId}=edit")
    public String editGame(@PathVariable(value = "gameId") long gameId, Model model) {
        model.addAttribute("game", gameService.findOneGameById(gameId));
        return "gameedit";
    }

    @PostMapping(value = "/game/{gameId}=edit")
    public String updateGame(@PathVariable(value = "gameId") long gameId, Game game) {
        gameService.updateGame(game, gameId);
        return "redirect:/game/{gameId}";
    }

    @GetMapping(value = "/game/{gameId}=delete")
    public String deleteGame(@PathVariable(value = "gameId") long gameId) {
        gameService.deleteGame(gameId);
        return "redirect:/games";
    }
}
