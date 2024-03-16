package edu.java.scrapper.clients;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import edu.java.ScrapperApplication;
import edu.java.clients.sites.GitHubClient;
import edu.java.dto.responses.GitHubResponseDTO;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {ScrapperApplication.class})
public class GitHubClientTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

    @Autowired
    private GitHubClient gitHubClient;

    @Test
    @DisplayName("Test for GitHub existing repos")
    void testThatExistingReposReturnedRightAnswer() {
        String repositoryPath = "lzbkln/java-course-2023-tink";
        OffsetDateTime updatedAt = OffsetDateTime.of(2023, 10, 3, 19, 25, 25, 0, ZoneOffset.of("Z"));
        OffsetDateTime createdAt = OffsetDateTime.of(2023, 10, 3, 19, 16, 51, 0, ZoneOffset.of("Z"));
        OffsetDateTime pushedAt = OffsetDateTime.of(2023, 12, 26, 11, 25, 34, 0, ZoneOffset.of("Z"));

        wireMockServer.stubFor(WireMock.get("/repos/" + repositoryPath)
            .willReturn(WireMock.ok()
                .withHeader("Content-type", MediaType.APPLICATION_JSON_VALUE)
                .withBody("""
                    {
                        "full_name": "%s",
                        "created_at": "%s",
                        "updated_at": "%s",
                        "pushed_at": "%s",
                    }
                    """.formatted(repositoryPath, createdAt, updatedAt, pushedAt))));

        GitHubResponseDTO response = gitHubClient.getUserRepository(repositoryPath).block();

        Objects.requireNonNull(response);
        assertEquals(repositoryPath, response.fullName());
        assertEquals(createdAt, response.createdAt());
        assertEquals(updatedAt, response.updatedAt());
        assertEquals(pushedAt, response.pushedAt());
    }

    @Test
    @DisplayName("Test for GitHub not existing repos")
    void testThatNonExistingReposReturnedRightAnswer() {
        String repositoryPath = "1";

        wireMockServer.stubFor(WireMock.get("/repos/" + repositoryPath)
            .willReturn(WireMock.ok()
                .withHeader("Content-type", "application/json")
                .withBody("""
                    {
                        "message": "Not Found",
                        "documentation_url": "https://docs.github.com/rest"
                    }
                    """)));

        assertThrows(WebClientResponseException.class, () -> {
            gitHubClient.getUserRepository(repositoryPath).block();
        });
    }
}
