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
        generators.addItem(new SideNavItem("Textos", "textgenerator/1", LineAwesomeIcon.KEYBOARD.create()));
        generators.addItem(new SideNavItem("Imágenes", "imagegenerator/1", LineAwesomeIcon.IMAGE.create()));
        generators.addItem(new SideNavItem("Multimodal", "multi/1", LineAwesomeIcon.IMAGES.create()));
        nav.addItem(generators);

        SideNavItem aiServices = new SideNavItem("Servicios IA");
        aiServices.setPrefixComponent(LineAwesomeIcon.BRAIN_SOLID.create());
        aiServices.addItem(new SideNavItem("Manejador de textos", "texthandler/1", LineAwesomeIcon.PEN_ALT_SOLID.create()));
        aiServices.addItem(new SideNavItem("Analizador sentimientos", "sentimentanalyzer/1", LineAwesomeIcon.SMILE.create()));
        aiServices.addItem(new SideNavItem("Extractor datos", "personextractor/1", LineAwesomeIcon.USER.create()));
        nav.addItem(aiServices);

        SideNavItem chats = new SideNavItem("Chats");
        chats.setPrefixComponent(LineAwesomeIcon.COMMENTS.create());
        chats.addItem(new SideNavItem("Simple", "chat/simple", LineAwesomeIcon.COMMENT_ALT.create()));
        chats.addItem(new SideNavItem("Con memoria", "chat/memory", LineAwesomeIcon.COMMENT.create()));
        nav.addItem(chats);

        SideNavItem assistants = new SideNavItem("Asistentes");
        assistants.setPrefixComponent(LineAwesomeIcon.UNIVERSAL_ACCESS_SOLID.create());
        assistants.addItem(new SideNavItem("Básico", "assistant/basic", LineAwesomeIcon.BOOK_SOLID.create()));
        //assistants.addItem(new SideNavItem("RAG fácil", "assistant/easyrag", LineAwesomeIcon.BOOK_MEDICAL_SOLID.create()));
        //assistants.addItem(new SideNavItem("RAG ingénuo", "assistant/naiverag", LineAwesomeIcon.BOOK_OPEN_SOLID.create()));
        //assistants.addItem(new SideNavItem("RAG avanzado", "assistant/advancedrag", LineAwesomeIcon.BOOK_READER_SOLID.create()));
        nav.addItem(assistants);

        SideNavItem copilots = new SideNavItem("Copilotos");
        copilots.setPrefixComponent(LineAwesomeIcon.ROBOT_SOLID.create());
        copilots.addItem(new SideNavItem("Básico", "copilots/copilot", LineAwesomeIcon.ROBOT_SOLID.create()));
        nav.addItem(copilots);

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
