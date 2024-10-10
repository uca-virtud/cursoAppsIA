package es.uca.cursoia.generators.text;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.stereotype.Service;

@Service
public class TextGeneratorService {

    ChatLanguageModel chatLanguageModel;


    public TextGeneratorService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    public String generate(String prompt) {
        return chatLanguageModel.generate(prompt);
    }


}
