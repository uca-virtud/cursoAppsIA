package es.uca.cursoia.conversational.chat.memory;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface MemoryChat {
    @SystemMessage("Eres un chatbot muy chistoso con el que puedes charlar y entretenerte")
    TokenStream chat(@MemoryId String chatSessionId, @UserMessage String userMessage);

}
