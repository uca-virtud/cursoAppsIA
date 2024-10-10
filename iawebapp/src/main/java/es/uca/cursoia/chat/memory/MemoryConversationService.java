package es.uca.cursoia.chat.memory;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import es.uca.cursoia.chat.ConversationService;
import org.springframework.stereotype.Service;

@Service
public class MemoryConversationService implements ConversationService {

    private final MemoryChat memoryChat;
    StreamingChatLanguageModel streamingChatLanguageModel;

    public MemoryConversationService(StreamingChatLanguageModel streamingChatLanguageModel) {
        this.streamingChatLanguageModel = streamingChatLanguageModel;

        memoryChat = AiServices.builder(MemoryChat.class)
                .streamingChatLanguageModel(streamingChatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();

    }

    public TokenStream chat(String userMessage) {
        return memoryChat.chat(userMessage);
    }

}
