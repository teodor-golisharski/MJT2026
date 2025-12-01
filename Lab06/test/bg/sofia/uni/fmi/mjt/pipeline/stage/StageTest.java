package bg.sofia.uni.fmi.mjt.pipeline.stage;

import bg.sofia.uni.fmi.mjt.pipeline.step.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StageTest {
    private Stage<String, Integer> stage;

    @BeforeEach
    void setUp() {
        // Старт със реална стъпка
        stage = Stage.start(new Step<String, Integer>() {
            @Override
            public Integer process(String input) {
                return input.length();
            }
        });
    }

    @Test
    void testStartWithNullArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> Stage.start(null),
                "Stage should throw when starting with null");
    }

    @Test
    void testAddStepWithNullArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> stage.addStep(null),
                "Stage should throw when adding null step");
    }

    @Test
    void testExecuteProcessesStepsSequentially() {
        Stage<String, String> fullStage = stage.addStep(new Step<Integer, String>() {
            @Override
            public String process(Integer input) {
                return "Length is " + input;
            }
        });

        String result = fullStage.execute("hello");
        assertEquals("Length is 5", result);
    }
}