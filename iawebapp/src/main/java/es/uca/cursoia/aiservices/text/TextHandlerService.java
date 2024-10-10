package es.uca.cursoia.aiservices.text;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Service;

@Service
public class TextHandlerService {

    ChatLanguageModel chatLanguageModel;
    TextHandler textHandler;

    public TextHandlerService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
        textHandler = AiServices.create(TextHandler.class, chatLanguageModel);

    }

    public String summarize(String userMessage, int numSentences) {
        return textHandler.summarize(userMessage, numSentences);
    }

    public String translate(String userMessage, String language) {
        // TODO: implementar
        return "";
    }


}
