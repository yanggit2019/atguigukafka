package com.atguigu.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

public class CustomConsumer {
    public static void main(String[] args) {
        //3.设置属性
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"nn1.hadoop:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"1205");
        
        
        //1.创建一个消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        //4.指定消费的topic
        consumer.subscribe(Arrays.asList("first"));
        //2.调用poll
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record:records
                 ) {
                System.out.println("topic="+record.topic()+" offset=" + record.offset()+" value="+record.value());
            }
            //5.提交offset
            consumer.commitSync();//同步提交
//            consumer.commitAsync();//异步提交
        }
        
    }
}
