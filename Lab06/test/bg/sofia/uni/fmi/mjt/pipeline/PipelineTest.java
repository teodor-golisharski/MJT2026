package bg.sofia.uni.fmi.mjt.pipeline;

import bg.sofia.uni.fmi.mjt.pipeline.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PipelineTest {

    @Test
    void testStartCreatesPipelineWithOneStage() {
        Stage<String, Integer> s = mock(Stage.class);
        Pipeline<String, Integer> p = Pipeline.start(s);

        assertNotNull(p);
    }

    @Test
    void testStartThrowsWhenStageIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Pipeline.start(null));
    }

    @Test
    void testExecuteRunsInitialStage() {
        Stage<String, Integer> stage = mock(Stage.class);
        when(stage.execute("abc")).thenReturn(123);

        Pipeline<String, Integer> p = Pipeline.start(stage);

        int result = p.execute("abc");

        assertEquals(123, result);
        verify(stage).execute("abc");
    }

    @Test
    void testExecuteUsesCacheForSameInput() {
        Stage<String, Integer> stage = mock(Stage.class);
        when(stage.execute("hello")).thenReturn(5);

        Pipeline<String, Integer> p = Pipeline.start(stage);

        p.execute("hello");
        p.execute("hello"); // should not call stage again

        verify(stage, times(1)).execute("hello");
    }
}