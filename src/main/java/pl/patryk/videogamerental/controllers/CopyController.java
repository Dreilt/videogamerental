package pl.patryk.videogamerental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/game/{gameId}/copy/{copyId}=delete")
    public String deleteCopy(@PathVariable(value = "gameId") long gameId, @PathVariable(value = "copyId") long copyId) {
        copyService.deleteCopy(gameId, copyId);
        return "redirect:/game/{gameId}";
    }

    @GetMapping(value = "/game/{gameId}/copy/{copyId}=reserved")
    public String copyReservation(@PathVariable(value = "gameId") long gameId, @PathVariable(value = "copyId") long copyId) {
        Game game = gameService.findOneGameById(gameId);
        Copy copy = copyService.findOneCopyById(copyId);
        copyService.doReservation(game, copy);
        return "redirect:/game/{gameId}";
    }

    @GetMapping(value = "/game/{gameId}/copy/{copyId}=rented")
    public String copyRent(@PathVariable(value = "gameId") long gameId, @PathVariable(value = "copyId") long copyId) {
        Game game = gameService.findOneGameById(gameId);
        Copy copy = copyService.findOneCopyById(copyId);
        copyService.doRent(game, copy);
        return "redirect:/game/{gameId}";
    }

    @GetMapping(value = "/game/{gameId}/copy/{copyId}=available")
    public String copyAvailable(@PathVariable(value = "gameId") long gameId, @PathVariable(value = "copyId") long copyId) {
        Game game = gameService.findOneGameById(gameId);
        Copy copy = copyService.findOneCopyById(copyId);
        copyService.doAvailable(game, copy);
        return "redirect:/game/{gameId}";
    }
}
