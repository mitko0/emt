package emt.lab.budgetdeezer.album_manager;

import emt.lab.budgetdeezer.shared_kernel.SharedConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Import(SharedConfiguration.class)
@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class AlbumManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlbumManagerApplication.class, args);
    }

}
