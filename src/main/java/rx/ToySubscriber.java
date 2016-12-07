package rx;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Objects;
import java.util.Random;

@Slf4j
public class ToySubscriber implements Subscriber<Integer> {
    private String name;
    private int capacity;
    private int count;
    private Random random;
    private Subscription subscription;

    public ToySubscriber(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void onSubscribe(Subscription s) {
        log.info("{} - {}.onSubscribe({})", name, Thread.currentThread().getName(), s);
        if (Objects.isNull(s)) {
            throw new NullPointerException();
        }
        subscription = s;
        setCountRandomly();
        subscription.request(count);
    }

    @Override
    public void onNext(Integer integer) {
        log.info("{} - {}.onNext({})", name, Thread.currentThread().getName(), integer);
        if (Objects.isNull(integer)) {
            throw new NullPointerException();
        }
        if (--count == 0) {
            setCountRandomly();
            subscription.request(count);
        }
    }

    @Override
    public void onError(Throwable t) {
        log.info("{} - {}.onError({})", name, Thread.currentThread().getName(), t);
        if (Objects.isNull(t)) {
            throw new NullPointerException();
        }
    }

    @Override
    public void onComplete() {
        log.info("{} - {}.onComplete()", name, Thread.currentThread().getName());
    }

    private void setCountRandomly() {
        count = random.nextInt(capacity)+1;
    }
}
