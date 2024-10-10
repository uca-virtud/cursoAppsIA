package es.uca.cursoia.assistants.basic;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import es.uca.cursoia.chat.ConversationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasicAssistantService implements ConversationService {

    private final BasicAssistant basicAssistant;
    StreamingChatLanguageModel streamingChatLanguageModel;

    public BasicAssistantService(StreamingChatLanguageModel streamingChatLanguageModel) {
        this.streamingChatLanguageModel = streamingChatLanguageModel;

        basicAssistant = AiServices.builder(BasicAssistant.class)
                .systemMessageProvider(chatSessionId -> generateSystemMessage((String) chatSessionId))
                .streamingChatLanguageModel(streamingChatLanguageModel)
                .chatMemoryProvider(chatSessionId -> MessageWindowChatMemory.withMaxMessages(10))
                .build();

    }

    private String generateSystemMessage(String chatSessionId) {

        String promptTemplate = """
                Rol: 
                    Eres un asistente basado en IA creado por la Universidad de Cádiz (UCA) para ayudar a los miembros de la comunidad universitaria.
                    Tu objetivo es proporcionar información y asistencia únicamente sobre aspectos relacionados con la docencia, la investigación y la gestión dentro de la universidad.
                    Si un usuario realiza preguntas o solicita información fuera de estos ámbitos, responde amablemente indicando que tu función se limita a temas académicos y administrativos de la universidad, y sugiere dirigir la conversación hacia esos temas
                Información del sistema: 
                    La fecha y hora actual es {{fechaHora}}
                Información del usuario: 
                    Nombre y apellidos: {{nombre}} {{apellidos}}
                    Estamento: {{estamento}}
                    Departamento: {{departamento}}
                    Centro: {{centro}}
                    Última conexión: {{ultimaConexion}}
                Tareas:
                    1. Analiza la consulta planteada por el usuario.
                    2. Responde a la consulta incluyendo la URL de la fuente de donde obtienes la información.
                Contexto:
                Formato de la respuesta:
                    Deberás responder siempre en español.
                    Utiliza un tono educado y respetuoso.                                      
                """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("text", "hi");
        variables.put("fechaHora", LocalDateTime.now());
        variables.put("nombre", "Iván");
        variables.put("apellidos", "Ruiz Rube");
        variables.put("estamento", "Personal Docente e Investigador");
        variables.put("departamento", "Departamento de Ingeniería Informática");
        variables.put("centro", "Escuela Superior de Ingeniería");
        variables.put("ultimaConexion", LocalDateTime.now().minusYears(1));


        Prompt prompt = PromptTemplate.from(promptTemplate).apply(variables);

        return prompt.text();

    }

    @Override
    public TokenStream chat(String userMessage) {
        return chat("default", userMessage);
    }

    public TokenStream chat(String chatSessionId, String userMessage) {
        return basicAssistant.chat(chatSessionId, userMessage);
    }


}
