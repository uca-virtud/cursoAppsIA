package es.uca.cursoia.aiservices.sentiment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;

@PageTitle("Analizador de sentimientos")
@Route(value = "sentimentanalyzer/:serviceId", layout = MainLayout.class)
public class SentimentAnalyzerView extends VerticalLayout implements BeforeEnterObserver {

    private final SentimentAnalyzerService sentimentAnalyzerService;

    public SentimentAnalyzerView(SentimentAnalyzerService sentimentAnalyzerService) {
        this.sentimentAnalyzerService = sentimentAnalyzerService;

        // Build the form
        FormLayout formLayout = new FormLayout();
        formLayout.setWidth("60%");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        TextArea input = new TextArea();
        input.setLabel("Prompt");
        input.setPlaceholder("Introduce el texto a procesar");
        formLayout.add(input);

        Button button = new Button("Analizar");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        formLayout.add(button);

        // Add the button click listener
        button.addClickListener(e -> {
            String text = input.getValue();
            Sentiment result = sentimentAnalyzerService.analyzeSentimentOf(text);

            Notification notification;

            switch (result) {
                case POSITIVE:
                    notification = Notification.show("Sentimiento positivo");
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    break;
                case NEUTRAL:
                    notification = Notification.show("Sentimiento neutral");
                    notification.addThemeVariants(NotificationVariant.LUMO_WARNING);
                    notification.setPosition(Notification.Position.MIDDLE);
                    break;
                case NEGATIVE:
                    notification = Notification.show("Sentimiento negativo");
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    break;
            }


        });

        // Set the layout
        this.add(formLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER); // Vertical alignment

        this.setSizeFull();

        this.setAlignSelf(Alignment.CENTER, formLayout);
    }


    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        String serviceId = beforeEnterEvent.getRouteParameters().get("serviceId").get();


    }
}