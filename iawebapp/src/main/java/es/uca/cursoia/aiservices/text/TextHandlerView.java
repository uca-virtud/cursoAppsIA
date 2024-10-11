package es.uca.cursoia.aiservices.text;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;

@PageTitle("Manejador de textos")
@Route(value = "aiservices/texthandler", layout = MainLayout.class)
public class TextHandlerView extends VerticalLayout {

    private final TextHandlerService textHandlerService;

    public TextHandlerView(TextHandlerService textHandlerService) {
        this.textHandlerService = textHandlerService;

        // Build the form
        FormLayout formLayout = new FormLayout();
        formLayout.setWidth("60%");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        TextArea input = new TextArea();
        input.setLabel("Prompt");
        input.setPlaceholder("Introduce el texto a procesar");
        formLayout.add(input);

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button summaryButton = new Button("Resumir");
        summaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        horizontalLayout.add(summaryButton);

        Button translateButton = new Button("Translate");
        translateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        horizontalLayout.add(translateButton);

        formLayout.add(horizontalLayout);

        TextArea output = new TextArea();
        output.setLabel("Resultado");
        output.setReadOnly(true);
        formLayout.add(output);


        // Add the button click listener
        translateButton.addClickListener(e -> {
            String text = input.getValue();
            String result = textHandlerService.translate(text, "en");
            output.setValue(result);
        });

        // Add the button click listener
        summaryButton.addClickListener(e -> {
            String text = input.getValue();
            String result = textHandlerService.summarize(text, 3);
            output.setValue(result);
        });


        // Set the layout
        this.add(formLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER); // Vertical alignment

        this.setSizeFull();

        this.setAlignSelf(Alignment.CENTER, formLayout);
    }


}