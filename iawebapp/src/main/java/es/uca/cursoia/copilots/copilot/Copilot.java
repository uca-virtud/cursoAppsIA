package es.uca.cursoia.copilots.copilot;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface Copilot {
    TokenStream chat(@MemoryId String sessionId, @UserMessage String message);

    //herramientas

    // chain


}
