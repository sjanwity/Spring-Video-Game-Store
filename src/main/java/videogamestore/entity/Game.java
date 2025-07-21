package videogamestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Platform is required")
    private String platform;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 4, fraction = 2, message = "Price format is invalid")
    private BigDecimal price;

    @NotNull(message = "Release date is required")
    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock = 0;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Developer is required")
    private String developer;

    @DecimalMin(value = "0.0", message = "Rating must be non-negative")
    @DecimalMax(value = "10.0", message = "Rating cannot exceed 10.0")
    private BigDecimal rating;

    // Constructors
    public Game() {}

    public Game(String title, String genre, String platform, BigDecimal price,
                LocalDate releaseDate, Integer stock, String description,
                String developer, BigDecimal rating) {
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.price = price;
        this.releaseDate = releaseDate;
        this.stock = stock;
        this.description = description;
        this.developer = developer;
        this.rating = rating;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDeveloper() { return developer; }
    public void setDeveloper(String developer) { this.developer = developer; }

    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }
}
