package es.uca.cursoia.aiservices.person;

import dev.langchain4j.service.UserMessage;

interface PersonExtractor {
    @UserMessage("Extract information about a person from {{it}}")
    Person extractPersonFrom(String text);
}