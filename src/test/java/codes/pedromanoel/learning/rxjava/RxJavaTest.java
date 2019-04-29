package codes.pedromanoel.learning.rxjava;

import io.reactivex.Flowable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RxJavaTest {

    @Test
    void name() {
        String result = Flowable.just("Hello World").take(1).blockingFirst();

        assertThat(result).isEqualTo("Hello World");
    }
}
