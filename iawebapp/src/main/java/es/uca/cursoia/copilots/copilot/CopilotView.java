package es.uca.cursoia.copilots.copilot;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;
import es.uca.cursoia.chat.ConversationService;
import es.uca.cursoia.chat.ConversationView;

@PageTitle("Copiloto")
@Route(value = "copilots/copilot", layout = MainLayout.class)
public class CopilotView extends ConversationView {

    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy tu asistente virtual UCA especializado... ¿En qué puedo ayudarte?
            """;

    private final CopilotService copilotService;

    public CopilotView(CopilotService copilotService) {
        this.copilotService = copilotService;
    }

    @Override
    protected ConversationService getChatService() {
        return copilotService;
    }

    @Override
    protected String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
