package es.uca.cursoia.assistants.advancedrag;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface AssistantWithAdvancedRag {
    TokenStream chat(@MemoryId String sessionId, @UserMessage String message);


    // router
    // metadata
    // compresiong
    // ExpandingQueryTransformer
    // COntent retriever: websearch, sqldatabase
    //herramientas

    // chain


}
