package Module;

import java.io.Serializable;

public class TestData implements Serializable {
    public int id;
    public String str;

    @Override
    public String toString() {
        return String.format("id: %d -> content: %s", id, str);
    }
}
