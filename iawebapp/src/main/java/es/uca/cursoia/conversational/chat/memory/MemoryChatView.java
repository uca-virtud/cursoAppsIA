package es.uca.cursoia.conversational.chat.memory;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;
import es.uca.cursoia.conversational.ConversationalService;
import es.uca.cursoia.conversational.ConversationalView;

@PageTitle("Chat con memoria")
@Route(value = "chats/memory", layout = MainLayout.class)
public class MemoryChatView extends ConversationalView {

    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy una simpática IA parlante con memoria. ¿De qué quieres charlar?
            """;

    private final MemoryChatService memoryChatService;

    public MemoryChatView(MemoryChatService memoryChatService) {
        this.memoryChatService = memoryChatService;
    }

    @Override
    protected ConversationalService getChatService() {
        return memoryChatService;
    }

    @Override
    protected String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
