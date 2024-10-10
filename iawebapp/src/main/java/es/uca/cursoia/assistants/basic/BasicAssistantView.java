package es.uca.cursoia.assistants.basic;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;
import es.uca.cursoia.chat.ConversationService;
import es.uca.cursoia.chat.ConversationView;

@PageTitle("Asistente básico")
@Route(value = "assistant/basic", layout = MainLayout.class)
public class BasicAssistantView extends ConversationView {

    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy tu asistente virtual de la UCA. ¿En qué puedo ayudarte?
            """;

    private final BasicAssistantService basicAssistantService;

    public BasicAssistantView(BasicAssistantService basicAssistantService) {
        this.basicAssistantService = basicAssistantService;
    }

    @Override
    protected ConversationService getChatService() {
        return basicAssistantService;
    }

    @Override
    protected String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
