package videogamestore.controller;

import videogamestore.entity.Game;
import videogamestore.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public String listGames(@RequestParam(required = false) String search,
                            @RequestParam(required = false) String genre,
                            @RequestParam(required = false) String platform,
                            Model model) {

        model.addAttribute("genres", gameService.getAllGenres());
        model.addAttribute("platforms", gameService.getAllPlatforms());

        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("games", gameService.searchByTitle(search));
            model.addAttribute("searchTerm", search);
        } else if (genre != null && !genre.trim().isEmpty()) {
            model.addAttribute("games", gameService.getGamesByGenre(genre));
            model.addAttribute("selectedGenre", genre);
        } else if (platform != null && !platform.trim().isEmpty()) {
            model.addAttribute("games", gameService.getGamesByPlatform(platform));
            model.addAttribute("selectedPlatform", platform);
        } else {
            model.addAttribute("games", gameService.getAllGames());
        }

        return "games/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("game", new Game());
        return "games/form";
    }

    @PostMapping
    public String saveGame(@Valid @ModelAttribute Game game,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "games/form";
        }

        gameService.saveGame(game);
        redirectAttributes.addFlashAttribute("successMessage",
                "Game '" + game.getTitle() + "' has been saved successfully!");

        return "redirect:/games";
    }

    @GetMapping("/{id}")
    public String viewGame(@PathVariable Long id, Model model) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            model.addAttribute("game", game.get());
            return "games/detail";
        }
        return "redirect:/games";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            model.addAttribute("game", game.get());
            return "games/form";
        }
        return "redirect:/games";
    }

    @PostMapping("/{id}/delete")
    public String deleteGame(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            gameService.deleteGame(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Game '" + game.get().getTitle() + "' has been deleted successfully!");
        }
        return "redirect:/games";
    }
}
