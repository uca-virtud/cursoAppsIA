package es.uca.cursoia.aiservices.sentiment;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalyzerService {

    ChatLanguageModel chatLanguageModel;
    SentimentAnalyzer sentimentAnalyzer;

    public SentimentAnalyzerService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
        sentimentAnalyzer = AiServices.create(SentimentAnalyzer.class, chatLanguageModel);

    }

    // analizador de sentimientos
    public Sentiment analyzeSentimentOf(String userMessage) {
        return sentimentAnalyzer.analyzeSentimentOf(userMessage);
    }


}
