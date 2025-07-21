package videogamestore.repository;

import videogamestore.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    // Custom query methods using Spring Data JPA method naming
    List<Game> findByGenreIgnoreCase(String genre);
    List<Game> findByPlatformIgnoreCase(String platform);
    List<Game> findByTitleContainingIgnoreCase(String title);
    List<Game> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Game> findByStockGreaterThan(Integer stock);
    List<Game> findByDeveloperIgnoreCase(String developer);

    // Custom JPQL queries
    @Query("SELECT g FROM Game g WHERE g.rating >= :minRating ORDER BY g.rating DESC")
    List<Game> findHighRatedGames(@Param("minRating") BigDecimal minRating);

    @Query("SELECT g FROM Game g WHERE g.stock > 0 ORDER BY g.releaseDate DESC")
    List<Game> findAvailableGamesOrderByNewest();

    @Query("SELECT DISTINCT g.genre FROM Game g ORDER BY g.genre")
    List<String> findAllGenres();

    @Query("SELECT DISTINCT g.platform FROM Game g ORDER BY g.platform")
    List<String> findAllPlatforms();
}
