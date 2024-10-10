package es.uca.cursoia.copilots.copilot;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import es.uca.cursoia.chat.ConversationService;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CopilotService implements ConversationService {

    private final Copilot copilot;
    StreamingChatLanguageModel streamingChatLanguageModel;

    public CopilotService(StreamingChatLanguageModel streamingChatLanguageModel) {
        this.streamingChatLanguageModel = streamingChatLanguageModel;

        copilot = AiServices.builder(Copilot.class)
                .systemMessageProvider(chatSessionId -> generateSystemMessage((String) chatSessionId))
                .streamingChatLanguageModel(streamingChatLanguageModel)
                .chatMemoryProvider(chatSessionId -> MessageWindowChatMemory.withMaxMessages(10))
                .contentRetriever(createContentRetriever()) // it should have access to our documents

                .build();

    }

    public static PathMatcher glob(String glob) {
        return FileSystems.getDefault().getPathMatcher("glob:" + glob);
    }

    private Path toPath(String relativePath) {
        try {
            URL fileUrl = CopilotService.class.getClassLoader().getResource(relativePath);
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    private ContentRetriever createContentRetriever() {

        List<Document> documents = FileSystemDocumentLoader.loadDocuments(toPath("documents/"), glob("*.pdf"));

        // Here, we create and empty in-memory store for our documents and their embeddings.
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        // Here, we are ingesting our documents into the store.
        // Under the hood, a lot of "magic" is happening, but we can ignore it for now.
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);

        // Lastly, let's create a content retriever from an embedding store.
        return EmbeddingStoreContentRetriever.from(embeddingStore);
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
                    2. Analiza la información de contexto proporcionada.
                    3. Responde a la consulta incluyendo la URL o el nombre del documento de donde obtienes la información.                    
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
        return copilot.chat(chatSessionId, userMessage);
    }


}
