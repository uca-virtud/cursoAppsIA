package es.uca.cursoia.assistants.naiverag;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface AssistantWithNaiveRAG {
    TokenStream chat(@MemoryId String sessionId, @UserMessage String message);


    // pdf extraer o directorio UCA, o scrapping html
    // dividir en parrafos
    // limpiar
    // embedding
    // devovler fuentes
    // buscar resriveer

}
