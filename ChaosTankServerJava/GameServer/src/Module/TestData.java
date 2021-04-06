package Module;

import java.io.Serializable;

public class TestData implements Serializable {
    public int id;
    public int xInput;
    public int yInput;

    @Override
    public String toString() {
        return String.format("id: %d -> content: (%f, %f)", id, xInput, yInput);
    }
}
