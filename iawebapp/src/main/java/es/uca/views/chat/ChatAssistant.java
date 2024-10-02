package es.uca.views.chat;

import reactor.core.publisher.Flux;

public interface ChatAssistant {
    Flux<String> chat(String userId, String userMessage);
}
