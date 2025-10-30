package thesis.Graduation.thesis.config.flyway;

import org.flywaydb.core.api.FlywayException;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayRepairConfig {


    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            try {
                System.out.println("🚀 Running Flyway migrations...");
                flyway.migrate();
            } catch (FlywayException e) {
                if (e.getMessage() != null && e.getMessage().contains("Validate failed")) {
                    System.out.println("⚠️ Flyway validation failed. Trying to repair...");
                    try {
                        flyway.repair();
                        System.out.println("✅ Flyway repaired successfully. Retrying migration...");
                        flyway.migrate();
                    } catch (Exception ex) {
                        System.out.println("❌ Flyway repair failed: {} " + ex.getMessage());
                        throw ex;
                    }
                } else {
                    System.out.println("❌ Unexpected Flyway error: {} " + e.getMessage());
                    throw e;
                }
            }
        };
    }
}
