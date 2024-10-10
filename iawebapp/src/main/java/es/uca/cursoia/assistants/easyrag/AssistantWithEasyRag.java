package es.uca.cursoia.assistants.easyrag;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface AssistantWithEasyRag {
    TokenStream chat(@MemoryId String sessionId, @UserMessage String message);


}
