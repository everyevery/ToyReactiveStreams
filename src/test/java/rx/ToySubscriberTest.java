package rx;

import org.reactivestreams.Subscriber;
import org.reactivestreams.tck.SubscriberBlackboxVerification;
import org.reactivestreams.tck.TestEnvironment;

public class ToySubscriberTest extends SubscriberBlackboxVerification<Integer> {

    public static final long DEFAULT_TIMEOUT_MILLIS = 300L;

    public ToySubscriberTest() {
        super(new TestEnvironment(DEFAULT_TIMEOUT_MILLIS));
    }

    @Override
    public Integer createElement(int element) {
        return element;
    }

    @Override
    public Subscriber<Integer> createSubscriber() {
        return new ToySubscriber();
    }
}
