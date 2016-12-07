package rx;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


@Slf4j
@Getter
public class ToyPublisher implements Publisher<Integer> {

    private int remained;
    private int error;
    private int complete;

    public ToyPublisher() {
        this(1, -1, -1);
    }

    public ToyPublisher(int size) {
        this(size, -1, -1);
    }

    public ToyPublisher(int size, int error, int complete) {
        this.remained = size > 0 ? size : 1;
        this.error = error;
        this.complete = complete;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> s) {
        log.info("{} - subscribe({})", Thread.currentThread().getName(), s);
        Subscription subscription = new ToySubscription(s, remained, error, complete);
        s.onSubscribe(subscription);
    }
}
