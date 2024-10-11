package es.uca.cursoia.conversational.chat.memory;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import es.uca.cursoia.conversational.ConversationalService;
import org.springframework.stereotype.Service;

@Service
public class MemoryChatService implements ConversationalService {

    private final MemoryChat memoryChat;
    private StreamingChatLanguageModel streamingChatLanguageModel;

    public MemoryChatService(StreamingChatLanguageModel streamingChatLanguageModel) {
        this.streamingChatLanguageModel = streamingChatLanguageModel;

        memoryChat = AiServices.builder(MemoryChat.class)
                .streamingChatLanguageModel(streamingChatLanguageModel)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .build();

    }

    public TokenStream chat(String chatSessionId, String userMessage) {
        return memoryChat.chat(chatSessionId, userMessage);
    }


}
