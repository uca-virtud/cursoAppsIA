package es.uca.cursoia.chat.memory;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;
import es.uca.cursoia.chat.ConversationService;
import es.uca.cursoia.chat.ConversationView;

@PageTitle("Chat con memoria")
@Route(value = "chat/memory", layout = MainLayout.class)
public class MemoryConversationView extends ConversationView {

    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy una IA parlante con memoria. ¿De qué quieres charlar?
            """;

    private final MemoryConversationService memoryChatService;

    public MemoryConversationView(MemoryConversationService memoryChatService) {
        this.memoryChatService = memoryChatService;
    }

    @Override
    protected ConversationService getChatService() {
        return memoryChatService;
    }

    @Override
    protected String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
