package es.uca.cursoia.conversational.assistants.basic;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface BasicAssistant {
    TokenStream chat(@MemoryId String sessionId, @UserMessage String message);


}
