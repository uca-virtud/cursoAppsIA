package es.uca.cursoia.assistants.advancedrag;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;
import es.uca.cursoia.assistants.easyrag.AssistantWithEasyRagService;
import es.uca.cursoia.chat.ConversationService;
import es.uca.cursoia.chat.ConversationView;

@PageTitle("Asistente con RAG avanzado")
@Route(value = "assistant/advancedrag", layout = MainLayout.class)
public class AssistantWithAdvancedRagView extends ConversationView {

    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy tu asistente virtual UCA especializado... ¿En qué puedo ayudarte?
            """;

    private final AssistantWithEasyRagService assistantWithEasyRAGService;

    public AssistantWithAdvancedRagView(AssistantWithEasyRagService assistantWithEasyRAGService) {
        this.assistantWithEasyRAGService = assistantWithEasyRAGService;
    }

    @Override
    protected ConversationService getChatService() {
        return assistantWithEasyRAGService;
    }

    @Override
    protected String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
