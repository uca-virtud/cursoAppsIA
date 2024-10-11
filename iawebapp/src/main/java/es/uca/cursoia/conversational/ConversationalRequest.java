package es.uca.cursoia.conversational;

import jakarta.validation.constraints.NotBlank;

public class ConversationalRequest {
    @NotBlank(message = "Id de conversación-usuario es obligatorio")
    private String chatSessionId;

    @NotBlank(message = "Mensaje es obligatorio")
    private String message;

    public @NotBlank(message = "Id de conversación-usuario es obligatorio") String getChatSessionId() {
        return chatSessionId;
    }

    public void setChatSessionId(@NotBlank(message = "Id de conversación-usuario es obligatorio") String chatSessionId) {
        this.chatSessionId = chatSessionId;
    }

    public @NotBlank(message = "Mensaje es obligatorio") String getMessage() {
        return message;
    }

    public void setMessage(@NotBlank(message = "Mensaje es obligatorio") String message) {
        this.message = message;
    }
}
