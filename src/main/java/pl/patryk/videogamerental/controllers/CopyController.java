package pl.patryk.videogamerental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.patryk.videogamerental.model.Copy;
import pl.patryk.videogamerental.model.Game;
import pl.patryk.videogamerental.services.CopyService;
import pl.patryk.videogamerental.services.GameService;

import javax.validation.Valid;

@Controller
public class CopyController {

    @Autowired
    private CopyService copyService;

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/addcopy")
    public String showCopyAddingForm(Model model) {
        model.addAttribute("gameList", gameService.findAllGames());
        model.addAttribute("copy", new Copy());
        return "addcopy";
    }

    @PostMapping(value = "/addcopy")
    public String createCopy(@Valid Game game, @Valid Copy copy, Model model) {
        copyService.saveCopy(game, copy);
        model.addAttribute("msgSuccess", "Egzemplarz został dodany.");
        model.addAttribute("gameList", gameService.findAllGames());
        model.addAttribute("copy", new Copy());
        return "addcopy";
    }
}