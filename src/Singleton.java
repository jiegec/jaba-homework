class Singleton extends BaseSingleton {
    private static Singleton INSTANCE;

    public static Singleton getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        synchronized (Singleton.class) {
            if (INSTANCE == null) {
                INSTANCE = new Singleton();
            }
        }
        return INSTANCE;
    }

    private Singleton() {
        // do nothing
    }
}
