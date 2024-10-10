package es.uca.cursoia.generators.multimodal;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;

import java.io.File;

@PageTitle("Generador de texto multimodal")
@Route(value = "multi/:serviceId", layout = MainLayout.class)
public class MultimodalGeneratorView extends VerticalLayout implements BeforeEnterObserver {

    private final MultimodalGeneratorService multimodalGeneratorService;


    public MultimodalGeneratorView(MultimodalGeneratorService multimodalGeneratorService) {
        this.multimodalGeneratorService = multimodalGeneratorService;

        // Build the form
        FormLayout formLayout = new FormLayout();
        formLayout.setWidth("60%");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        TextArea input = new TextArea();
        input.setLabel("Prompt");
        input.setPlaceholder("Introduce el texto a procesar");
        input.setValue("Describe esta imagen");
        formLayout.add(input);

        FileBuffer buffer = new FileBuffer();
        Upload imageUpload = new Upload(buffer);
        imageUpload.setDropAllowed(true);
        imageUpload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif");
        formLayout.add(imageUpload);

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


            File uploadedFile = buffer.getFileData().getFile();

            String result = multimodalGeneratorService.generate(text, uploadedFile);
            output.setValue(result);


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