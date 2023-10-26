package Entities.Misc;

import java.util.HashMap;
import java.util.Map;

public class IDGenerator {
    private static final Map<Class<?>, Long> typeCounters = new HashMap<>();

    public static synchronized long generateID(Class<?> objectType) {
        long nextID = typeCounters.getOrDefault(objectType, 0L) + 1;
        typeCounters.put(objectType, nextID);
        return nextID;
    }

    public static void resetCounters() {
        typeCounters.clear(); // Clear all counters
    }
}
