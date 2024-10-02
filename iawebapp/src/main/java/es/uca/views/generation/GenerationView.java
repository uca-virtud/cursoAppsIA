package es.uca.views.generation;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.views.MainLayout;

@PageTitle("Generador de texto")
@Route(value = "generation/:serviceId", layout = MainLayout.class)
public class GenerationView extends VerticalLayout implements BeforeEnterObserver {

    private final GenerationService generationService;

    public GenerationView(GenerationService generationService) {
        this.generationService = generationService;

        // Build the form
        FormLayout formLayout = new FormLayout();
        formLayout.setWidth("60%");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        TextArea input = new TextArea();
        input.setLabel("Prompt");
        input.setPlaceholder("Introduce el texto a procesar");
        formLayout.add(input);

        Button button = new Button("Generar");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        formLayout.add(button);

        TextArea output = new TextArea();
        output.setLabel("Resultado");
        input.setHeight("200px");
        output.setReadOnly(true);
        formLayout.add(output);

        // Add the button click listener
        button.addClickListener(e -> {
            String text = input.getValue();
            String result = generationService.generateText(text);
            output.setValue(result);
        });

        // Set the layout
        this.add(formLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER); // Vertical alignment
        //this.setDefaultHorizontalComponentAlignment(Alignment.END); // Horizontal alignment
        //this.setHorizontalComponentAlignment(Alignment.END);
        //this.setAlignItems(FlexComponent.Alignment.CENTER);
        this.setSizeFull();

        this.setAlignSelf(Alignment.CENTER, formLayout);

    }


    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        String serviceId = beforeEnterEvent.getRouteParameters().get("serviceId").get();


    }
}