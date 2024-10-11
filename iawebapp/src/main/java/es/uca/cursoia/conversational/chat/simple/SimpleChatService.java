package es.uca.cursoia.conversational.chat.simple;

import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import es.uca.cursoia.conversational.ConversationalService;
import org.springframework.stereotype.Service;

@Service
public class SimpleChatService implements ConversationalService {

    private final SimpleChat simpleChat;
    private StreamingChatLanguageModel streamingChatLanguageModel;

    public SimpleChatService(StreamingChatLanguageModel streamingChatLanguageModel) {
        this.streamingChatLanguageModel = streamingChatLanguageModel;
        simpleChat = AiServices.create(SimpleChat.class, streamingChatLanguageModel);

    }

    public TokenStream chat(String chatSessionId, String userMessage) {
        return simpleChat.chat(userMessage);
    }


}
