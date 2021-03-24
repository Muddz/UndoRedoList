import org.jetbrains.annotations.Nullable;

public class Action {
    public String key;
    public Object value;

    public Action(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Action &&
                ((Action) obj).key.equals(key) &&
                ((Action) obj).value.equals(value);
    }
}
