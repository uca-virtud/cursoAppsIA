package es.uca.cursoia.aiservices.text;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface TextHandler {

    @SystemMessage("You are a professional translator into {{language}}")
    @UserMessage("Translate the following text: {{text}}")
    String translate(@V("text") String text, @V("language") String language);

    @SystemMessage("Summarize every message from user in  no more than {{n}} lines.")
    String summarize(@UserMessage String text, @V("n") int numLines);
}
