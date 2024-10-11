package es.uca.cursoia.conversational.chat.simple;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;
import es.uca.cursoia.conversational.ConversationalService;
import es.uca.cursoia.conversational.ConversationalView;

@PageTitle("Chat simple")
@Route(value = "chats/simple", layout = MainLayout.class)
public class SimpleChatView extends ConversationalView {

    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy una simpática IA parlante. ¿De qué quieres charlar?
            """;

    private final SimpleChatService simpleChatService;

    public SimpleChatView(SimpleChatService simpleChatService) {
        this.simpleChatService = simpleChatService;
    }

    @Override
    protected ConversationalService getChatService() {
        return simpleChatService;
    }

    protected String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
