package rx;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ToySubscription implements Subscription {
    private final static long MAX_SLEEP_DURATION = 1000L;
    private Subscriber<? super Integer> subscriber;
    private int remained;
    private int error;
    private Random random;
    private boolean active = true;

    public ToySubscription(Subscriber<? super Integer> subscriber, int remained, int error) {
        this.subscriber = subscriber;
        this.remained = remained;
        this.error = error;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void request(long n) {
        if (!active) {
            return;
        }

        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            long n2 = n;
//            while (remained > 0) {
                while (n2 > 0 && remained > 0) {
                    log.info("{} - Event {} !!!", Thread.currentThread().getName(), remained);
                    if (remained == error) {
                        subscriber.onError(new RuntimeException("Error!"));
                    } else {
                        subscriber.onNext(remained--);
                        try {
                            long duration = random.nextLong();
                            if (duration < 0)
                                duration = -duration;
                            duration = (duration+1)%MAX_SLEEP_DURATION;
                            log.info("{} - sleep({})", Thread.currentThread().getName(), duration);
                            Thread.sleep(duration);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    n2--;
                }
//            }
            if (remained==0) {
                subscriber.onComplete();
            }
        });

//        try {
//            log.info("attempt to shutdown executorService");
//            executorService.shutdown();
//        executorService.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            log.warn("tasks interrupted", e);
//        }
//        finally {
//            if (!executorService.isTerminated()) {
//                log.warn("cancel non-finished tasks");
//            }
//            executorService.shutdownNow();
//            log.info("shutdown finished");
//        }
    }

    @Override
    public void cancel() {
        if (!active) {
            return;
        }
        active = false;

    }
}
