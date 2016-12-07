package rx;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Objects;
import java.util.Random;

@Slf4j
public class ToySubscriber implements Subscriber<Integer> {
    private int capacity;
    private int count;
    private Random random;
    private Subscription subscription;

    public ToySubscriber(int capacity) {
        this.capacity = capacity;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void onSubscribe(Subscription s) {
        if (Objects.isNull(s)) {
            throw new NullPointerException();
        }
        subscription = s;
        count = random.nextInt(capacity)+1;
        subscription.request(count);
    }

    @Override
    public void onNext(Integer integer) {
        if (Objects.isNull(integer)) {
            throw new NullPointerException();
        }
        log.info("{} - onNext({})", Thread.currentThread().getName(), integer);
        count--;
        if (count == 0) {
            count = random.nextInt(capacity)+1;
            subscription.request(count);
        }
    }

    @Override
    public void onError(Throwable t) {
        if (Objects.isNull(t)) {
            throw new NullPointerException();
        }
        log.info("{} - onError({})", Thread.currentThread().getName(), t);

    }

    @Override
    public void onComplete() {
        log.info("{} - onComplete()", Thread.currentThread().getName());

    }
}
