package es.uca.cursoia.multimodal.imagedescriptor;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Service
public class ImageDescriptorService {

    ChatLanguageModel chatLanguageModel;

    public ImageDescriptorService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    public String generate(File file) {

        UserMessage userMessage;
        try {
            String base64 = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));

            userMessage = UserMessage.from(
                    ImageContent.from(base64, "image/png"),
                    TextContent.from("Describe esta imagen:")
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return chatLanguageModel.generate(userMessage).content().text();
    }
}
