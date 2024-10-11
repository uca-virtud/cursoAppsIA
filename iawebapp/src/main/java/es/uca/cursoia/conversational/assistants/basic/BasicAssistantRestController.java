package es.uca.cursoia.conversational.assistants.basic;

import dev.langchain4j.service.TokenStream;
import es.uca.cursoia.conversational.ConversationalRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * Servicios web de IA
 */
@RestController
public class BasicAssistantRestController {

    private final BasicAssistantService basicAssistantService;

    public BasicAssistantRestController(BasicAssistantService basicAssistantService) {
        super();
        this.basicAssistantService = basicAssistantService;
    }


    /**
     * Este servicio te saluda
     *
     * @return un saludo
     */
    @GetMapping("/api/v1/hello")
    public String chat() {
        return "Hello World";
    }


    /**
     * Permite realizar una petición al asistente virtual
     *
     * @param request Datos de la petición
     * @return Respuesta del asistente
     */
    @PostMapping("/api/v1/assistants/basic")
    public Flux<String> chatcau(@Valid @RequestBody ConversationalRequest request) {
        TokenStream aux = basicAssistantService.chat(request.getChatSessionId(), request.getMessage());
        return getResponse(aux);
    }


    private Flux<String> getResponse(TokenStream streamingAssistant) {

        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        streamingAssistant
                .onNext(sink::tryEmitNext)
                .onComplete(c -> sink.tryEmitComplete())
                .onError(sink::tryEmitError)
                .start();

        return sink.asFlux();

    }


}
