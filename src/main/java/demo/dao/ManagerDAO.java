package demo.dao;

/**
 * Created by _dani on 05.07.2016.
 */
public class ManagerDAO {

    private final SessionHolder sessionHolder;
    private OfficeDao officeDao;

    ManagerDAO(SessionHolder sessionHolder) {
        this.sessionHolder = sessionHolder;
    }

    public OfficeDao getOfficeDao() {
        if (officeDao == null) {
            return officeDao = new OfficeDao(sessionHolder);
        }
        return officeDao;
    }
}
