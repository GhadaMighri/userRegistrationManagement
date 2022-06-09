package fr.airfrance.userregistrationmanagement.configuration;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("fr.airfrance.userregistrationmanagement.repository")
@EnableJpaAuditing
@EnableConfigurationProperties(LiquibaseProperties.class)
public class DatabaseConfiguration {

  private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

  /**
   *
   * @param dataSource
   * @param liquibaseProperties
   * @return
   */
  @Bean
  public SpringLiquibase liquibase(DataSource dataSource, LiquibaseProperties liquibaseProperties) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource);
    liquibase.setChangeLog("classpath:db/changelog/changelog-master.xml");
    liquibase.setContexts(liquibaseProperties.getContexts());
    liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
    liquibase.setDropFirst(liquibaseProperties.isDropFirst());
    liquibase.setShouldRun(liquibaseProperties.isEnabled());
    log.debug("Configuring Liquibase");
    return liquibase;
  }
}
