package transaction_service.transaction_service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import transaction_service.transaction_service.event.PaymentInitiatedEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private static final String TOPIC = "payment-initiated";

    private final KafkaTemplate<String, PaymentInitiatedEvent> kafkaTemplate;

    public void publishPaymentInitiated(PaymentInitiatedEvent event) {
        log.info("Publishing PaymentInitiatedEvent to topic '{}': {}", TOPIC, event);
        kafkaTemplate.send(TOPIC, event.getTransactionId().toString(), event);
    }
}
