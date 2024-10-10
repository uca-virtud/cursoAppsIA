package es.uca.cursoia.generators.multimodal;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MultimodalGeneratorService {

    private static final String UPLOAD_DIR = "uploads/";
    ChatLanguageModel chatLanguageModel;
    @Autowired
    private HttpServletRequest request;


    public MultimodalGeneratorService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    private String getServerUrl() {
        String serverUrl = request.getRequestURL().toString();
        return serverUrl;
    }

    public String generate(String prompt, File file) {

        // Ensure the upload directory exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Path destinationPath = Paths.get(UPLOAD_DIR, file.getName());
        try {
            Files.copy(file.toPath(), destinationPath);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String imageUrl = "/images/" + file.getName();

        UserMessage userMessage = UserMessage.from(
                ImageContent.from(getServerUrl() + destinationPath.toString()),
                TextContent.from(prompt)
        );

        return chatLanguageModel.generate(userMessage).content().text();
    }
}
