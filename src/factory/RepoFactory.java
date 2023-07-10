package factory;

import repository.PersonRepo;
import repository.PersonRepoImpl;

public class RepoFactory {
    private final static PersonRepo INSTANCE = new PersonRepoImpl(PersonListProvider.getPersonList());

    public static PersonRepo getInstance(){
        return INSTANCE;
    }
}
