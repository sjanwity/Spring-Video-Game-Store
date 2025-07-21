package videogamestore.controller;

import videogamestore.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.math.BigDecimal;

@Controller
public class HomeController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalGames", gameService.getAllGames().size());
        model.addAttribute("availableGames", gameService.getAvailableGames().size());
        model.addAttribute("featuredGames", gameService.getHighRatedGames(new BigDecimal("8.0")));
        model.addAttribute("newReleases", gameService.getAvailableGames());
        return "index";
    }
}
