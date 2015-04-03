package de.choong;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import de.choong.pages.AddAnimePage;
import de.choong.pages.AddUserPage;
import de.choong.pages.HomePage;
import de.choong.pages.LoginPage;
import de.choong.pages.ShowAnimePage;
import de.choong.pages.SingleAnimePage;
import de.choong.util.HibernateUtil;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see de.choong.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();

        // Don't show wicket tags in rendered html.
        getMarkupSettings().setStripWicketTags(true);

        // Update schema and load settings.
        HibernateUtil.getSessionFactory();

        addMountPages();
    }

    private void addMountPages() {
        mountPage("/addAnime", AddAnimePage.class);
        mountPage("/addUser", AddUserPage.class);
        mountPage("/login", LoginPage.class);
        mountPage("/animeList", ShowAnimePage.class);
        mountPage("/anime", SingleAnimePage.class);
    }
}
