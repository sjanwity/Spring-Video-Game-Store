package videogamestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VideoGameStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoGameStoreApplication.class, args);

    }
}