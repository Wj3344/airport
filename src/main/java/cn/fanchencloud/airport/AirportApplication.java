package cn.fanchencloud.airport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author chen
 */
@SpringBootApplication
@EnableTransactionManagement
public class AirportApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportApplication.class, args);
    }

}