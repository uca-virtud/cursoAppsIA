package es.uca.cursoia.aiservices.person;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Service;

@Service
public class PersonExtractorService {

    ChatLanguageModel chatLanguageModel;
    PersonExtractor personExtractor;

    public PersonExtractorService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
        personExtractor = AiServices.create(PersonExtractor.class, chatLanguageModel);

    }

    public Person extractPersonFrom(String userMessage) {
        return personExtractor.extractPersonFrom(userMessage);
    }


}
