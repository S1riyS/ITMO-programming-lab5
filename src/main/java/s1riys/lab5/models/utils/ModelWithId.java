package s1riys.lab5.models.utils;

public abstract class ModelWithId {
    protected long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private static int currentId = 1;

    public ModelWithId() {
        this.id = generateId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private static int generateId() {
        return currentId++;
    }
}
