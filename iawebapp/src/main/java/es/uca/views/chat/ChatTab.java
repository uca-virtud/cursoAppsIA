package es.uca.views.chat;

import com.vaadin.flow.component.tabs.Tab;

public class ChatTab extends Tab {
    private final ChatInfo chatInfo;

    public ChatTab(ChatInfo chatInfo) {
        this.chatInfo = chatInfo;
    }

    public ChatInfo getChatInfo() {
        return chatInfo;
    }
}
