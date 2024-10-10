package es.uca.cursoia.chat.simple;

import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import es.uca.cursoia.chat.ConversationService;
import org.springframework.stereotype.Service;

@Service
public class SimpleConversationService implements ConversationService {

    private final SimpleChat simpleChat;
    StreamingChatLanguageModel streamingChatLanguageModel;

    public SimpleConversationService(StreamingChatLanguageModel streamingChatLanguageModel) {
        this.streamingChatLanguageModel = streamingChatLanguageModel;
        simpleChat = AiServices.create(SimpleChat.class, streamingChatLanguageModel);

    }

    public TokenStream chat(String userMessage) {
        return simpleChat.chat(userMessage);
    }


}
