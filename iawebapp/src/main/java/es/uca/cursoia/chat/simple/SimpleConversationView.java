package es.uca.cursoia.chat.simple;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;
import es.uca.cursoia.chat.ConversationService;
import es.uca.cursoia.chat.ConversationView;

@PageTitle("Chat simple")
@Route(value = "chat/simple", layout = MainLayout.class)
public class SimpleConversationView extends ConversationView {

    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy una IA parlante. ¿De qué quieres charlar?
            """;

    private final SimpleConversationService simpleChatService;

    public SimpleConversationView(SimpleConversationService simpleChatService) {
        this.simpleChatService = simpleChatService;
    }

    @Override
    protected ConversationService getChatService() {
        return simpleChatService;
    }

    protected String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
