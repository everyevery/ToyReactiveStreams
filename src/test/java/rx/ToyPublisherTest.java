package rx;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.tck.PublisherVerification;
import org.reactivestreams.tck.TestEnvironment;

public class ToyPublisherTest extends PublisherVerification<Integer> {

    public static final long DEFAULT_TIMEOUT_MILLIS = 100L;
    public static final long PUBLISHER_REFERENCE_CLEANUP_TIMEOUT_MILLIS = 500L;

    public ToyPublisherTest() {
        super(new TestEnvironment(DEFAULT_TIMEOUT_MILLIS, true), PUBLISHER_REFERENCE_CLEANUP_TIMEOUT_MILLIS);
    }

    @Override
    public Publisher<Integer> createPublisher(long elements) {
        return new ToyPublisher((int)elements);
    }

    @Override
    public Publisher<Integer> createFailedPublisher() {
        return new ToyPublisher(-1);
//        return new ToyPublisher() {
//            @Override
//            public void subscribe(Subscriber<? super Integer> s) {
//                s.onError(new RuntimeException("Can't subscribe subscriber: " + s + ", because of reasons."));
//            }
//        };
    }

    // ADDITIONAL CONFIGURATION
    @Override
    public long maxElementsFromPublisher() {
        return Integer.MAX_VALUE;
    }

    @Override
    public long boundedDepthOfOnNextAndRequestRecursion() {
        return 1;
    }
}
