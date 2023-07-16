package factory;

import service.PersonService;
import service.PersonServiceImpl;

public class ServiceFactory {

    private final static PersonService INSTANCE = new PersonServiceImpl();

    public static PersonService getInstance(){
        return INSTANCE;
    }
}
