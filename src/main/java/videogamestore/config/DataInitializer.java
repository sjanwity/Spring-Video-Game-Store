package videogamestore.config;

import videogamestore.entity.Game;
import videogamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize sample data
        if (gameRepository.count() == 0) {
            gameRepository.save(new Game(
                    "The Legend of Zelda: Breath of the Wild",
                    "Adventure",
                    "Nintendo Switch",
                    new BigDecimal("59.99"),
                    LocalDate.of(2017, 3, 3),
                    25,
                    "Open-world adventure game set in Hyrule",
                    "Nintendo",
                    new BigDecimal("9.5")
            ));

            gameRepository.save(new Game(
                    "God of War",
                    "Action",
                    "PlayStation 4",
                    new BigDecimal("49.99"),
                    LocalDate.of(2018, 4, 20),
                    15,
                    "Norse mythology action-adventure game",
                    "Santa Monica Studio",
                    new BigDecimal("9.2")
            ));

            gameRepository.save(new Game(
                    "Cyberpunk 2077",
                    "RPG",
                    "PC",
                    new BigDecimal("39.99"),
                    LocalDate.of(2020, 12, 10),
                    30,
                    "Futuristic open-world RPG",
                    "CD Projekt Red",
                    new BigDecimal("7.8")
            ));

            gameRepository.save(new Game(
                    "Super Mario Odyssey",
                    "Platformer",
                    "Nintendo Switch",
                    new BigDecimal("54.99"),
                    LocalDate.of(2017, 10, 27),
                    20,
                    "3D platformer adventure with Mario",
                    "Nintendo",
                    new BigDecimal("9.0")
            ));

            gameRepository.save(new Game(
                    "Halo Infinite",
                    "FPS",
                    "Xbox Series X",
                    new BigDecimal("59.99"),
                    LocalDate.of(2021, 12, 8),
                    12,
                    "Sci-fi first-person shooter",
                    "343 Industries",
                    new BigDecimal("8.5")
            ));
        }
    }
}