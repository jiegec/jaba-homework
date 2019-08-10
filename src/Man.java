import java.lang.Integer;

class Man extends BasePerson implements Person
{
    private final String name;
    private final String description;
    protected Integer count;

    Man(final String name, final String description) {
        this.name = name;
        this.description = description;
        this.count = 0;
    }

    protected void move() {
        System.out.println("I'm moving...");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int changeSomething() {
        count = count - 1;
        return count;
    }
}

class SuperMan extends Man
{
    @Override
    protected void move() {
        System.out.println("I am flying...");
    }

    void fly() {
        System.out.println("Fly! SuperMan!");
    }

    @Override
    public int changeSomething() {
        count = count + 1;
        return count;
    }


    SuperMan(final String name, final String description) {
        super(name, description);
    }

    SuperMan() {
        this("superMan", "I can fly.");
    }
}
