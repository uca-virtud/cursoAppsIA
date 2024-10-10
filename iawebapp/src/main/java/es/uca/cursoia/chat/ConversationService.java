package es.uca.cursoia.chat;

import dev.langchain4j.service.TokenStream;

public interface ConversationService {

    public TokenStream chat(String userMessage);

    default public TokenStream chat(String chatSessionId, String userMessage) {
        return chat(userMessage);
    }

}
