package wang.mh.tools.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import wang.mh.ServerConfig;

import java.util.Collections;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;

public class BasicDemo {

    private static final String topic = "quickstart";

    public static void main(String[] args) throws Exception{
        new Thread(() -> {
            try {
                produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        consume();
    }

    private static void produce() throws Exception{
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ServerConfig.ALI_SERVER + ":9092");
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        try (KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p)) {
            while (true) {
                String msg = "Hello," + new Random().nextInt(100);
                ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, msg);
                Future<RecordMetadata> future = kafkaProducer.send(record);
                RecordMetadata recordMetadata = future.get();
                System.out.println(recordMetadata.toString());
                System.out.println("消息发送成功:" + msg);
                Thread.sleep(500);
            }
        }
    }

    public static void consume() throws Exception{
        Properties p = new Properties();
        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ServerConfig.ALI_SERVER + ":9092");
        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.GROUP_ID_CONFIG, "duanjt_test");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(p);
        kafkaConsumer.subscribe(Collections.singletonList(topic));// 订阅消息
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("topic:%s,offset:%d,消息:%s", //
                        record.topic(), record.offset(), record.value()));
            }
        }

    }
}


