
public enum Load {
    NONE, NEVER, ONE, FIVE, PERMA;

    public static Load getLoad(String str) throws NullPointerException {
        Load[] loads = Load.values();
        for (Load load : loads) {
            if (load.toString().equals(str)) {
                return load;
            }
        }
        return Load.NONE;
    }
}
