package es.uca.cursoia.copilots.copilot;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RedmineTool {


    public static final String REDMINE_API_KEY = "3442d342c190ee3f121557e33da351801ce461d3";
    private final RestTemplate restTemplate;

    public RedmineTool(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Create a new issue in the Redmine project
     *
     * @param subject     Issue subject
     * @param description Issue description
     */
    public void createIssue(String subject, String description) {

        String url = "https://des-sinf.uca.es/redmine/issues.json";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-Redmine-API-Key", REDMINE_API_KEY);

        String issueJson = """
                {
                               "project_id": "proyecto-de-pruebas-copilot",
                               "subject": "{{subject}}",
                               "description": "{{description}}. Nota: Este issue ha sido creado automáticamente por una IA",
                           }                                          
                """;

        HttpEntity<String> request = new HttpEntity<>(issueJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Issue created successfully");
        } else {
            System.out.println("Failed to create issue: " + response.getStatusCode());
        }

    }
}
