package de.choong;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;
import de.choong.model.user.UserRight;
import de.choong.pages.AddAnimePage;
import de.choong.pages.AddUserPage;
import de.choong.pages.AdministrationPage;
import de.choong.pages.EditAnimePage;
import de.choong.pages.HomePage;
import de.choong.pages.LoginPage;
import de.choong.pages.MultiAnimePage;
import de.choong.pages.SingleAnimePage;
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
        createAdminIfNotExisting();

        // TODO create user profile page

        // TODO create user settings page

        addMountPages();
    }

    private void addMountPages() {
        mountPage("/addAnime", AddAnimePage.class);
        mountPage("/addUser", AddUserPage.class);
        mountPage("/editAnime", EditAnimePage.class);
        mountPage("/login", LoginPage.class);
        mountPage("/administration", AdministrationPage.class);
        mountPage("/animeList", MultiAnimePage.class);
        mountPage("/anime", SingleAnimePage.class);
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
                dao.create(admin);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public <T extends Page> void mountPageNoVers(String path, Class<T> pageClass) {
        mount(new NoVersioningMount(path, pageClass));
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new MySession(request);
    }
}
