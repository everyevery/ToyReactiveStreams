package rx;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Scanner;

@Slf4j
public class ToyApp {
    public static void main(String[] args) {
        Publisher publisher = new ToyPublisher(30);
        Subscriber subscriber = new ToySubscriber("FAST", 5);
        Subscriber subscriber2 = new ToySubscriber("SLOW", 1);
        publisher.subscribe(subscriber);
        publisher.subscribe(subscriber2);

        Publisher publisher2 = new ToyPublisher(30, -1, 20);
        Subscriber subscriber3 = new ToySubscriber("POOR", 3);
        publisher2.subscribe(subscriber3);

        Publisher publisher3 = new ToyPublisher(30, 10, -1);
        Subscriber subscriber4 = new ToySubscriber("UNLUCKY", 3);
        publisher3.subscribe(subscriber4);

        Scanner scanner = new Scanner(System.in);
        log.info("INPUT ENTER TO FINISH");
        scanner.nextLine();
        log.info("END");
    }
}
