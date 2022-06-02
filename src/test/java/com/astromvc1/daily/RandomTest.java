package com.astromvc1.daily;

import com.astromvc1.cache.RedisRepository;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import org.testcontainers.utility.DockerImageName;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@ActiveProfiles("testcontainers")
public class RandomTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Container
    static GenericContainer redisContainer = new GenericContainer(DockerImageName.parse("redis:6.2-alpine"))
            .withExposedPorts(6379);
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:11")
            .withDatabaseName("prop")
            .withUsername("postgres")
            .withPassword("pass")
            .withExposedPorts(5432);

    @BeforeAll
    public static void beforeTests() throws IOException{
        File f = new File("target/test-classes/application-testcontainers.properties");
        f.delete();
        FileUtils.writeLines(f,
                List.of(
                        "spring.redis.port=" + redisContainer.getFirstMappedPort(),
                        "spring.redis.host=" + redisContainer.getHost()

                ));
    }


    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void genericContainersMethodsTest() {

        System.out.println(redisContainer.getFirstMappedPort());
        System.out.println(redisContainer.getContainerId());
        System.out.println(redisContainer.getContainerInfo());
        System.out.println(redisContainer.getCommandParts());
        System.out.println(redisContainer.dependsOn());
        System.out.println(redisContainer.getCreateContainerCmdModifiers());
        System.out.println(redisContainer.getEnv());
        System.out.println(redisContainer.getLivenessCheckPortNumbers());
        System.out.println(redisContainer.getNetwork());
        System.out.println(redisContainer.getNetworkMode());

    }

    @Test
    void contextLoads() {

    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("app.datasource.main.jdbc-url",
                () -> String.format("jdbc:postgresql://localhost:%d/prop", postgres.getFirstMappedPort()));
        registry.add("app.datasource.main.username", () -> "postgres");
        registry.add("app.datasource.main.password", () -> "pass");
    }


    @Test
    public void dailynosign() throws Exception {
        this.mockMvc.perform(get("/daily")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[\"ARIES\",\"TAURUS\",\"GEMINI\",\"CANCER\",\"LEO\",\"VIRGO\",\"LIBRA\",\"SCORPIO\",\"SAGITTARIUS\",\"CAPRICORN\",\"AQUARIUS\",\"PISCES\",\"UNKNOWN\"]"))

        ;
    }
    @Test
    public void dailysignpiscesget() throws Exception {
        this.mockMvc.perform(get("/daily/pisces")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }
    @Test
    public void dailysignleoget() throws Exception {
        this.mockMvc.perform(get("/daily/leo")).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }
    @Test
    public void dailysignscorpioget() throws Exception {
        this.mockMvc.perform(get("/daily/scorpio")).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }
    @Test
    public void dailysignscorpiodelete() throws Exception {
        this.mockMvc.perform(delete("/daily/scorpio")).andDo(print())
                .andExpect(status().is2xxSuccessful());


    }

}
//    app:
//    datasource:
//    main:
//    driver-class-name: org.postgresql.Driver
//    jdbc-url: jdbc:postgresql://localhost:5432/meownation
//    username: meownation
//    password: password123
//    pool-size: 30

//    spring.redis.database=0
//    spring.redis.host=localhost
//    spring.redis.port=6379
//    spring.redis.password=eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81
//    spring.redis.timeout=60000

//    @Container
//    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
//            .withDatabaseName("integration-tests-db")
//            .withUsername("sa")
//            .withPassword("sa");