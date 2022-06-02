package com.astromvc1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
//@ContextConfiguration(initializers = {Astromvc1ApplicationTests.MyConfig.class})
class Astromvc1ApplicationTests {

//
//	@Container
//	public PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
//			.withDatabaseName("integration-tests-db")
//			.withUsername("sa")
//			.withPassword("sa");
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:11")
			.withDatabaseName("prop")
			.withUsername("postgres")
			.withPassword("pass")
			.withExposedPorts(5432);

	@DynamicPropertySource
	static void registerPgProperties(DynamicPropertyRegistry registry) {
		registry.add("app.datasource.main.jdbc-url",
				() -> String.format("jdbc:postgresql://localhost:%d/prop", postgres.getFirstMappedPort()));
		registry.add("app.datasource.main.username", () -> "postgres");
		registry.add("app.datasource.main.password", () -> "pass");
	}
	@Test
	void contextLoads() {
	}


}

