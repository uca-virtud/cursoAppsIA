package es.uca.views.chat;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageInputI18n;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.views.MainLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.firitin.components.messagelist.MarkdownMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@PageTitle("Chat")
@Route(value = "chat/:assistantId", layout = MainLayout.class)

public class ChatView extends VerticalLayout implements BeforeEnterObserver {

    public static final String ANALIZANDO_CONSULTA = "(analizando consulta...)";
    public static final String USER_AVATAR = "https://api.dicebear.com/8.x/personas/svg?seed=Socks";
    public static final String AI_AVATAR = "/icons/icon.png";
    protected static final String WELCOME_MESSAGE = """
            Hola %s, soy tu asistente virtual. ¿En qué puedo ayudarte?
            """;
    private final Button newChatButton;
    private final VerticalLayout chat;
    private final MessageInput messageInput;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UI ui;
    private final ChatAssistantManager chatAssistantManager;
    private String chatId;
    private MessageListItem aiMessage;
    private ChatAssistant assistant;

    public ChatView(ChatAssistantManager chatAssistantManager) {
        this.chatAssistantManager = chatAssistantManager;

        // Creamos interfaz
        this.setPadding(true); // Leave some white space
        this.setHeightFull(); // We maximize to window

        newChatButton = new Button("Nuevo chat");
        add(newChatButton);

        chat = new VerticalLayout();
        addAndExpand(new Scroller(chat));

        messageInput = new MessageInput();
        MessageInputI18n i18n = new MessageInputI18n();
        i18n.setMessage("Escribe un mensaje...");
        i18n.setSend("Enviar");
        messageInput.setI18n(i18n);
        messageInput.addSubmitListener(this::onSubmit);
        messageInput.setWidthFull();
        add(messageInput);

        newChatButton.addClickListener(e -> {
            chat.removeAll();
            startAssistant();
        });


        ui = UI.getCurrent();

        chatId = UUID.randomUUID().toString();

    }


    private void startAssistant() {
        chatId = UUID.randomUUID().toString();

        MarkdownMessage answer = new MarkdownMessage("Andy");
        answer.setAvatarColor(new MarkdownMessage.Color("orange"));
        answer.getElement().setProperty("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd-MM-YYYY")));
        chat.add(answer);
        answer.appendMarkdown(WELCOME_MESSAGE.formatted("Usuario"));


    }


    private void onSubmit(MessageInput.SubmitEvent submitEvent) {


        messageInput.setEnabled(false);

        String input = submitEvent.getValue();

        System.out.println("\n\n>>> User Message: \n" + input);

        MarkdownMessage userMessage = new MarkdownMessage(input, "Tú");
        userMessage.setAvatarColor(new MarkdownMessage.Color("blue"));
        userMessage.getElement().setProperty("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd-MM-YYYY")));
        chat.add(userMessage);


        askAssistant(input);


    }

    private void askAssistant(String input) {


        MarkdownMessage answer = new MarkdownMessage("IA");
        answer.setAvatarColor(new MarkdownMessage.Color("orange"));
        answer.getElement().setProperty("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd-MM-YYYY")));

        chat.add(answer);

        assistant.chat("123", input).doOnNext(token -> {
            ui.access(() -> {
                answer.appendMarkdownAsync(token);
            });
        }).doOnComplete(() -> {
            ui.access(() -> {
                messageInput.setEnabled(true);
                messageInput.getElement().callJsFunction("focus");
            });
        }).doOnError(er -> {
            er.printStackTrace();
        }).subscribe();

    }

    private String replaceLinks(String markdownText) {

        // Patrón para encontrar enlaces en formato Markdown
        String markdownLinkPattern = "\\[(.+?)\\]\\((http.+?)\\)";

        // Expresión regular para reemplazar
        String replacement = "<a href=\"$2\" target=\"_blank\">$1</a>";

        // Reemplazar todas las ocurrencias de enlaces Markdown con enlaces HTML
        return markdownText.replaceAll(markdownLinkPattern, replacement);


    }

    private String getAvatar(String role) {
        if ("chat".equals(role)) {
            return AI_AVATAR;
        } else if ("user".equals(role)) {
            return USER_AVATAR;
        } else {
            return "";
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        String assistantId = beforeEnterEvent.getRouteParameters().get("assistantId").get();
        

    }


    private static class ChatTextBuffer {
        boolean value;

        StringBuffer sb;

        ChatTextBuffer(boolean value) {
            this.value = value;
        }

    }

}
