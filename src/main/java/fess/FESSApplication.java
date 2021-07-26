package fess;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableScheduling
public class FESSApplication {

    static {
        ApiContextInitializer.init();
    }

    @SneakyThrows
    public static void main(String[] args) { SpringApplication.run(FESSApplication.class, args); }
}
