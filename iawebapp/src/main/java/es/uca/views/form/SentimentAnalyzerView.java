package es.uca.views.form;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.views.MainLayout;

@PageTitle("Analizador de sentimientos")
@Route(value = "sentiment", layout = MainLayout.class)
public class SentimentAnalyzerView extends Composite<VerticalLayout> {

    public SentimentAnalyzerView() {
        FormLayout formLayout = new FormLayout();
        formLayout.setWidth("35%");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        TextArea textArea = new TextArea();
        textArea.setLabel("Texto a procesar");
        textArea.setPlaceholder("Introduce el texto a procesar");
        textArea.setHeight("200px");
        formLayout.add(textArea);

        Button button = new Button("Analizar sentimiento");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        formLayout.add(button);

        TextField textField = new TextField();
        textField.setLabel("Resultado");
        textField.setReadOnly(true);
        formLayout.add(textField);

        this.getContent().setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.END);
        this.getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.END);
        this.getContent().setAlignItems(FlexComponent.Alignment.START);
        this.getContent().setWidthFull();
        this.getContent().setHeightFull();

        this.getContent().add(formLayout);

    }
}