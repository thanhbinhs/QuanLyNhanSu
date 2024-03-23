package phenikaa.person;

import java.util.Random;

public final class generateID {
    private int randomID;

    private generateID() {
        do {
            randomID = generateRandomNumber();
        } while (employeeList.employees.stream().anyMatch(x -> x.getEid() == randomID));
    }

    private static int generateRandomNumber() {
        long seed = System.currentTimeMillis();
        return new Random(seed).nextInt(Integer.MAX_VALUE) + 1;
    }

    private int getID() {
        return randomID;
    }

    public static int creRandomID() {
        return new generateID().getID();
    }
}
