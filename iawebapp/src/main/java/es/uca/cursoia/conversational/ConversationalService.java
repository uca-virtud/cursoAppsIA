package es.uca.cursoia.conversational;

import dev.langchain4j.service.TokenStream;

public interface ConversationalService {

    public TokenStream chat(String chatSessionId, String userMessage);


}
