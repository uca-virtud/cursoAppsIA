package es.uca.cursoia.conversational.assistants.basic;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;
import es.uca.cursoia.conversational.ConversationalService;
import es.uca.cursoia.conversational.ConversationalView;

@PageTitle("Asistente básico")
@Route(value = "assistants/basic", layout = MainLayout.class)
public class BasicAssistantView extends ConversationalView {

    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy tu asistente virtual de la UCA. ¿En qué puedo ayudarte?
            """;

    private final BasicAssistantService basicAssistantService;

    public BasicAssistantView(BasicAssistantService basicAssistantService) {
        this.basicAssistantService = basicAssistantService;
    }

    @Override
    protected ConversationalService getChatService() {
        return basicAssistantService;
    }

    @Override
    protected String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
