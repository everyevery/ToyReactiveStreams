package rx;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Getter
public class ToyPublisher implements Publisher<Integer> {

    private int remained;
    private int error;
    private ConcurrentLinkedQueue<Integer> queue;

    public ToyPublisher(int size) {
        this(size, -1);
    }

    public ToyPublisher(int size, int error) {
        this.remained = size;
        this.error = error;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> s) {
        log.info("{} - subscribe({})", Thread.currentThread().getName(), s);
        Subscription subscription = new ToySubscription(s, remained, error);
        s.onSubscribe(subscription);

    }
}
