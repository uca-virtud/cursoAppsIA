package es.uca.views.chat;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.stereotype.Service;

@Service
public class ChatAssistantManager {

    ChatLanguageModel chatLanguageModel;


    public String generateText(String userMessage) {
        return chatLanguageModel.generate(userMessage);
    }


}
