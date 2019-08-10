public class Homework6 {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
        Singleton b = Singleton.getInstance();
        assert(s == b);
    }
}
