package videogamestore.service;

import videogamestore.entity.Game;
import videogamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Cacheable("games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Cacheable(value = "games", key = "#id")
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    @CacheEvict(value = "games", allEntries = true)
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @CacheEvict(value = "games", allEntries = true)
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public List<Game> searchByTitle(String title) {
        return gameRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Game> getGamesByGenre(String genre) {
        return gameRepository.findByGenreIgnoreCase(genre);
    }

    public List<Game> getGamesByPlatform(String platform) {
        return gameRepository.findByPlatformIgnoreCase(platform);
    }

    public List<Game> getGamesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return gameRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Game> getHighRatedGames(BigDecimal minRating) {
        return gameRepository.findHighRatedGames(minRating);
    }

    public List<Game> getAvailableGames() {
        return gameRepository.findAvailableGamesOrderByNewest();
    }

    public List<String> getAllGenres() {
        return gameRepository.findAllGenres();
    }

    public List<String> getAllPlatforms() {
        return gameRepository.findAllPlatforms();
    }

    public boolean isGameInStock(Long gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        return game.isPresent() && game.get().getStock() > 0;
    }
}
