package pl.patryk.videogamerental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.patryk.videogamerental.forms.GameForm;
import pl.patryk.videogamerental.model.Copy;
import pl.patryk.videogamerental.model.Game;
import pl.patryk.videogamerental.services.GameService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

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
}
