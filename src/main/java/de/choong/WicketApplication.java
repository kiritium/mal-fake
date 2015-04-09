package de.choong;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;
import de.choong.model.user.UserRight;
import de.choong.pages.HomePage;
import de.choong.pages.admin.AdministrationPage;
import de.choong.pages.admin.EditSlideshowPage;
import de.choong.pages.anime.AddAnimePage;
import de.choong.pages.anime.EditAnimePage;
import de.choong.pages.anime.MultiAnimePage;
import de.choong.pages.anime.SingleAnimePage;
import de.choong.pages.user.AddUserPage;
import de.choong.pages.user.LoginPage;
import de.choong.pages.user.UserProfilePage;
import de.choong.pages.user.UserSettingsPage;
import de.choong.util.HibernateUtil;
import de.choong.util.SpringUtil;
import de.choong.util.UserUtil;

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

        // Create admin user
        // Username: admin
        // Password: admin
        createAdminIfNotExisting();

        // TODO Homepage
        // TODO Profile
        // TODO admin slideshow
        // TODO Genres multiselect, Hibernate one to many

        addMountPages();
    }

    private void addMountPages() {
        mountPage("/addAnime", AddAnimePage.class);
        mountPage("/register", AddUserPage.class);
        mountPage("/editAnime", EditAnimePage.class);
        mountPage("/login", LoginPage.class);
        mountPage("/administration", AdministrationPage.class);
        mountPage("/animeList", MultiAnimePage.class);
        mountPage("/anime", SingleAnimePage.class);
        mountPage("/settings", UserSettingsPage.class);
        mountPage("/profile", UserProfilePage.class);
        mountPage("/editSlideshow", EditSlideshowPage.class);
    }

    private void createAdminIfNotExisting() {
        IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");
        try {
            if (dao.readByName("admin") == null) {
                UserDO admin = new UserDO();
                String salt = UserUtil.generateSalt();
                admin.setUsername("admin");
                admin.setPassword(UserUtil.hash("admin", salt));
                admin.setSalt(salt);
                admin.setUserRight(UserRight.ADMIN);
                admin.setEmail("admin@admin.com");
                dao.create(admin);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new MySession(request);
    }
}
