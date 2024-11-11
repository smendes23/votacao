package br.com.sicredi.votacao.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource("jdbc:postgresql://localhost:5432/cooperativismo", "cdelivery_dev", "cdelivery_dev")
                .load();
    }
}
