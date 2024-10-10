package es.uca.cursoia.assistants.easyrag;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;
import es.uca.cursoia.chat.ConversationService;
import es.uca.cursoia.chat.ConversationView;

@PageTitle("Asistente con RAG fácil")
@Route(value = "assistant/easyrag", layout = MainLayout.class)
public class AssistantWithEasyRagView extends ConversationView {

    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy tu asistente virtual UCA especializado en la cartera estratégica de proyectos TI. ¿En qué puedo ayudarte?
            """;

    private final AssistantWithEasyRagService assistantWithEasyRAGService;

    public AssistantWithEasyRagView(AssistantWithEasyRagService assistantWithEasyRAGService) {
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
