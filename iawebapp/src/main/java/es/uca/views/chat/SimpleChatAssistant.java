package es.uca.views.chat;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import reactor.core.publisher.Flux;

interface SimpleChatAssistant {

    @SystemMessage("Eres un asistente muy educado")
    Flux<String> chat(String userId, @UserMessage String userMessage);


}