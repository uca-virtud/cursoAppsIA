package es.uca.cursoia.assistants.naiverag;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;
import es.uca.cursoia.chat.ConversationService;
import es.uca.cursoia.chat.ConversationView;

@PageTitle("Asistente con RAG ingenuo")
@Route(value = "assistant/naiverag", layout = MainLayout.class)
public class AssistantWithNaiveRagView extends ConversationView {

    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy tu asistente virtual UCA especializado... ¿En qué puedo ayudarte?
            """;

    private final AssistantWithNaiveRagService assistantWithNaiveRAGService;

    public AssistantWithNaiveRagView(AssistantWithNaiveRagService assistantWithNaiveRAGService) {
        this.assistantWithNaiveRAGService = assistantWithNaiveRAGService;
    }

    @Override
    protected ConversationService getChatService() {
        return assistantWithNaiveRAGService;
    }

    @Override
    protected String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
