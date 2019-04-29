package codes.pedromanoel.learning.rxjava;

import io.reactivex.Flowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FlowableTest {

    private boolean subscribeWasCalled;
    private boolean itemEmitted;

    @BeforeEach
    void setUp() {
        itemEmitted = false;
        subscribeWasCalled = false;
    }

    @Test
    void items_emitted_only_on_subscription() {
        Flowable<String> flowable = Flowable.fromCallable(() -> {
            itemEmitted = true;
            return "OK";
        });
        assertThat(itemEmitted).as("Item not emitted").isFalse();

        flowable.subscribe();
        assertThat(itemEmitted).as("Item was emitted").isTrue();
    }

    @Test
    void subscription_side_effects_called_on_subscribe() {
        Flowable<Integer> flowableWithOnSubscribe = Flowable
                .just(1)
                .doOnSubscribe(subscription -> subscribeWasCalled = true);
        assertThat(subscribeWasCalled).as("OnSubscribe not called yet").isFalse();

        flowableWithOnSubscribe.subscribe();
        assertThat(subscribeWasCalled).as("OnSubscribe was called").isTrue();
    }
}
