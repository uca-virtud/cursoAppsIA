package es.uca.cursoia.aiservices.sentiment;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

enum Sentiment {
    POSITIVE, NEUTRAL, NEGATIVE
}

interface SentimentAnalyzer {
    @UserMessage("Analyze sentiment of the following: {{text}}")
    Sentiment analyzeSentimentOf(@V("text") String text);

}
