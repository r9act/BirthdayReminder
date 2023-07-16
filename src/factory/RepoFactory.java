package factory;

import repository.PersonRepo;
import repository.PersonRepoImpl;

import java.io.IOException;

public class RepoFactory {

    private static final  PersonRepo INSTANCE;
    static {
        try {
            INSTANCE = new PersonRepoImpl(ProvideExcelFile.getPersonList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static PersonRepo getInstance(){
        return INSTANCE;
    }
}
