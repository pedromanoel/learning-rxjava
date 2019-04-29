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
    void assembly_time_does_not_emit() {
        givenFlowableThatEmitsAnItem();

        assertThat(itemEmitted)
                .as("Item not emitted before subscribe")
                .isFalse();
    }

    @Test
    void items_emitted_only_on_subscription() {
        givenFlowableThatEmitsAnItem().subscribe();

        assertThat(itemEmitted)
                .as("Item emitted after subscribe")
                .isTrue();
    }

    @Test
    void side_effects_not_called_on_assembly() {
        givenFlowableWithSideEffectOnSubscribe();

        assertThat(subscribeWasCalled)
                .as("OnSubscribe not called before subscribe")
                .isFalse();
    }

    @Test
    void subscription_side_effects_called_on_subscribe() {
        givenFlowableWithSideEffectOnSubscribe()
                .subscribe();

        assertThat(subscribeWasCalled)
                .as("OnSubscribe called after subscription")
                .isTrue();
    }

    private Flowable<String> givenFlowableThatEmitsAnItem() {
        return Flowable.fromCallable(() -> {
            itemEmitted = true;
            return "OK";
        });
    }

    private Flowable<Integer> givenFlowableWithSideEffectOnSubscribe() {
        return Flowable
                .just(1)
                .doOnSubscribe(subscription -> subscribeWasCalled = true);
    }
}
