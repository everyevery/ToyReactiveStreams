package rx;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Scanner;

@Slf4j
public class ToyApp {
    public static void main(String[] args) {
        Publisher publisher = new ToyPublisher(10);
        Subscriber subscriber = new ToySubscriber(3);
        publisher.subscribe(subscriber);
        Scanner scanner = new Scanner(System.in);
        log.info("INPUT ENTER TO FINISH");
        scanner.nextLine();
        log.info("END");
    }
}
