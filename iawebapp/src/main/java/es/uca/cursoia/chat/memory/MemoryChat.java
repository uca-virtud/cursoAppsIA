package es.uca.cursoia.chat.memory;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface MemoryChat {
    @SystemMessage("Eres un chatbot muy chistoso con el que puedes charlar y entretenerte")
    TokenStream chat(@UserMessage String userMessage);
}
