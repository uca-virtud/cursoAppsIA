package es.uca.cursoia.multimodal.imagedescriptor;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;

import java.io.File;

@PageTitle("Descriptor de imágenes")
@Route(value = "multimodals/imagedescriptor", layout = MainLayout.class)
public class ImageDescriptorView extends VerticalLayout {

    private final ImageDescriptorService imageDescriptorService;


    public ImageDescriptorView(ImageDescriptorService imageDescriptorService) {
        this.imageDescriptorService = imageDescriptorService;

        // Build the form
        FormLayout formLayout = new FormLayout();
        formLayout.setWidth("60%");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        FileBuffer buffer = new FileBuffer();
        Upload imageUpload = new Upload(buffer);
        imageUpload.setDropAllowed(true);
        imageUpload.setAcceptedFileTypes("image/png");
        formLayout.add(imageUpload);

        Button button = new Button("Generar");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        formLayout.add(button);

        TextArea output = new TextArea();
        output.setLabel("Resultado");
        output.setReadOnly(true);
        formLayout.add(output);

        // Add the button click listener
        button.addClickListener(e -> {
            File uploadedFile = buffer.getFileData().getFile();
            String result = imageDescriptorService.generate(uploadedFile);
            output.setValue(result);
        });

        // Set the layout
        this.add(formLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER); // Vertical alignment

        this.setSizeFull();

        this.setAlignSelf(Alignment.CENTER, formLayout);
    }


}