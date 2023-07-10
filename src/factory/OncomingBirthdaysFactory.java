package factory;

import service.OncomingBirthdays;

public class OncomingBirthdaysFactory {
    private static final OncomingBirthdays INSTANCE = new OncomingBirthdays();

    public static OncomingBirthdays getInstance(){
        return INSTANCE;
    }
}
