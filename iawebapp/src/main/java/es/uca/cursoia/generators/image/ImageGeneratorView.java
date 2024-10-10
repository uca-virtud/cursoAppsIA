package es.uca.cursoia.generators.image;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;

@PageTitle("Generador de imágenes")
@Route(value = "imagegenerator/:serviceId", layout = MainLayout.class)
public class ImageGeneratorView extends VerticalLayout implements BeforeEnterObserver {

    private final ImageGeneratorService imageGeneratorService;

    public ImageGeneratorView(ImageGeneratorService imageGeneratorService) {
        this.imageGeneratorService = imageGeneratorService;

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

        // Add the button click listener
        button.addClickListener(e -> {
            String text = input.getValue();
            dev.langchain4j.data.image.Image response = imageGeneratorService.generate(text);

            Dialog dialog = new Dialog();
            dialog.add(new Image(response.url().toString(), "Imágen generada por el prompt: " + text));
            dialog.open();


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