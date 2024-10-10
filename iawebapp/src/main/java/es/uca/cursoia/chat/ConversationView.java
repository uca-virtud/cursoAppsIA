package es.uca.cursoia.chat;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageInputI18n;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.firitin.components.messagelist.MarkdownMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public abstract class ConversationView extends VerticalLayout {

    private final Button newChatButton;
    private final VerticalLayout chat;
    private final MessageInput messageInput;
    private final UI ui;

    private String chatSessionId;

    public ConversationView() {

        // Get the current UI
        ui = UI.getCurrent();

        // Generate a random chat ID
        chatSessionId = UUID.randomUUID().toString();

        // Set the layout
        this.setPadding(true); // Leave some white space
        this.setHeightFull(); // We maximize to window

        // Build the chat
        newChatButton = new Button("Nuevo chat");
        newChatButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(newChatButton);

        chat = new VerticalLayout();
        addAndExpand(new Scroller(chat));

        messageInput = new MessageInput();
        MessageInputI18n i18n = new MessageInputI18n();
        i18n.setMessage("Escribe un mensaje...");
        i18n.setSend("Enviar");
        messageInput.setI18n(i18n);
        messageInput.setWidthFull();
        add(messageInput);

        // Add the button click listener
        messageInput.addSubmitListener(e -> {
            messageInput.setEnabled(false);

            String input = e.getValue();

            System.out.println("\n\n>>> User Message: \n" + input);

            MarkdownMessage userMessage = new MarkdownMessage(input, "Tú");
            userMessage.setAvatarColor(new MarkdownMessage.Color("blue"));
            userMessage.getElement().setProperty("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd-MM-YYYY")));
            chat.add(userMessage);

            askAssistant(input);

        });

        // Add the button click listener
        newChatButton.addClickListener(e -> {
            messageInput.setEnabled(true);
            chat.removeAll();
            startAssistant();
        });

        startAssistant();
    }

    protected abstract ConversationService getChatService();

    protected abstract String getWelcomeMessage();


    private void startAssistant() {
        chatSessionId = UUID.randomUUID().toString();

        MarkdownMessage answer = createMessage();
        chat.add(answer);
        answer.appendMarkdown(getWelcomeMessage().formatted("Usuario"));

    }


    private void askAssistant(String input) {

        MarkdownMessage answer = createMessage();
        chat.add(answer);

        getChatService().chat(input).onNext(token -> {
            ui.access(() -> {
                answer.appendMarkdownAsync(token);
            });
        }).onComplete(response -> {
            ui.access(() -> {
                messageInput.setEnabled(true);
                messageInput.getElement().callJsFunction("focus");
            });
        }).onError(er -> {
            er.printStackTrace();
        }).start();

    }


    private MarkdownMessage createMessage() {
        MarkdownMessage answer = new MarkdownMessage("Asistente");
        answer.setAvatarColor(new MarkdownMessage.Color("orange"));
        answer.getElement().setProperty("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd-MM-YYYY")));
        return answer;
    }


}
