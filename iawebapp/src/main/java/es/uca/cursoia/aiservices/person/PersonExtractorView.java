package es.uca.cursoia.aiservices.person;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;

@PageTitle("Extractor de datos de personas")
@Route(value = "personextractor/:serviceId", layout = MainLayout.class)
public class PersonExtractorView extends VerticalLayout implements BeforeEnterObserver {

    private final PersonExtractorService personExtractorService;
    private FormLayout resultLayout;
    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private DatePicker birthDate = new DatePicker();
    private TextField street = new TextField();
    private TextField city = new TextField();


    public PersonExtractorView(PersonExtractorService personExtractorService) {
        this.personExtractorService = personExtractorService;


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


        // Build a form for showing the result of the Person object
        resultLayout = buildResultLayout();


        Binder<Person> binder = new Binder<>(Person.class);
        binder.bindInstanceFields(this);

        // Add the button click listener
        button.addClickListener(e -> {
            String text = input.getValue();
            Person result = personExtractorService.extractPersonFrom(text);
            binder.setBean(result);
            resultLayout.setVisible(true);

        });


        // Set the layout
        this.add(formLayout);
        this.add(resultLayout);

        this.setJustifyContentMode(JustifyContentMode.CENTER); // Vertical alignment
        this.setSizeFull();
        this.setAlignSelf(Alignment.CENTER, formLayout);
        this.setAlignSelf(Alignment.CENTER, resultLayout);

    }

    private FormLayout buildResultLayout() {
        resultLayout = new FormLayout();
        resultLayout.setWidth("60%");
        resultLayout.setVisible(false);

        firstName.setLabel("Nombre");
        firstName.setReadOnly(true);
        resultLayout.add(firstName);

        lastName.setLabel("Apellidos");
        lastName.setReadOnly(true);
        resultLayout.add(lastName);

        birthDate.setLabel("Fecha de nacimiento");
        birthDate.setReadOnly(true);
        resultLayout.add(birthDate);

        street.setLabel("Dirección");
        street.setReadOnly(false);
        resultLayout.add(street);

        city.setLabel("Ciudad");
        city.setReadOnly(false);
        resultLayout.add(city);


        return resultLayout;
    }


    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        String serviceId = beforeEnterEvent.getRouteParameters().get("serviceId").get();


    }
}