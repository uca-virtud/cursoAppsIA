package es.uca.views.generation;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.stereotype.Service;

@Service
public class GenerationService {

    ChatLanguageModel chatLanguageModel;

    public GenerationService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    public String generateText(String userMessage) {
        return chatLanguageModel.generate(userMessage);
    }


}
