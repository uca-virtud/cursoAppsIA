package es.uca.cursoia;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other cursoia.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Span appName = new Span("Curso Apps enriquecidas con IA");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Home", "", LineAwesomeIcon.FILE.create()));


        SideNavItem generators = new SideNavItem("Generadores");
        generators.setPrefixComponent(LineAwesomeIcon.TOOLBOX_SOLID.create());
        // generators.addItem(new SideNavItem("Textos", "generators/text", LineAwesomeIcon.KEYBOARD.create()));
        // generators.addItem(new SideNavItem("Imágenes", "generators/image", LineAwesomeIcon.IMAGE.create()));
        nav.addItem(generators);


        SideNavItem aiServices = new SideNavItem("Servicios IA");
        aiServices.setPrefixComponent(LineAwesomeIcon.BRAIN_SOLID.create());
        // aiServices.addItem(new SideNavItem("Manejador de textos", "aiservices/texthandler", LineAwesomeIcon.PEN_ALT_SOLID.create()));
        // aiServices.addItem(new SideNavItem("Analizador sentimientos", "aiservices/sentimentanalyzer", LineAwesomeIcon.SMILE.create()));
        //  aiServices.addItem(new SideNavItem("Extractor datos", "aiservices/personextractor", LineAwesomeIcon.USER.create()));
        nav.addItem(aiServices);


        SideNavItem multimodals = new SideNavItem("Multimodales");
        multimodals.setPrefixComponent(LineAwesomeIcon.UNIVERSAL_ACCESS_SOLID.create());
        // multimodals.addItem(new SideNavItem("Descriptor de imágenes", "multimodals/imagedescriptor", LineAwesomeIcon.IMAGES.create()));
        nav.addItem(multimodals);


        SideNavItem chats = new SideNavItem("Chatbots");
        chats.setPrefixComponent(LineAwesomeIcon.COMMENTS.create());
        // chats.addItem(new SideNavItem("Simple", "chats/simple", LineAwesomeIcon.COMMENT_ALT.create()));
        // chats.addItem(new SideNavItem("Con memoria", "chats/memory", LineAwesomeIcon.COMMENT.create()));
        nav.addItem(chats);

        SideNavItem assistants = new SideNavItem("Asistentes");
        assistants.setPrefixComponent(LineAwesomeIcon.UNIVERSAL_ACCESS_SOLID.create());
        //   assistants.addItem(new SideNavItem("Básico", "assistants/basic", LineAwesomeIcon.BOOK_SOLID.create()));
        // assistants.addItem(new SideNavItem("Más por venir..."));
        nav.addItem(assistants);

        SideNavItem copilots = new SideNavItem("Copilotos");
        copilots.setPrefixComponent(LineAwesomeIcon.CAR_SOLID.create());
        //  copilots.addItem(new SideNavItem("Próximamente..."));
        nav.addItem(copilots);

        SideNavItem agents = new SideNavItem("Agentes");
        agents.setPrefixComponent(LineAwesomeIcon.ROBOT_SOLID.create());
        // agents.addItem(new SideNavItem("Próximamente..."));
        nav.addItem(agents);

        SideNavItem ws = new SideNavItem("Servicios web", "ws", LineAwesomeIcon.SATELLITE_SOLID.create());
        //   nav.addItem(ws);


        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
