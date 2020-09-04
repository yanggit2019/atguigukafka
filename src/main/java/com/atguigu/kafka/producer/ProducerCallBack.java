package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerCallBack {
    public static void main(String[] args) {
        //3.创建一个Properties
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.1.51:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        //1.创建一个生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        //2.send方法
        for (int i=0;i<1000;i++){
            producer.send(new ProducerRecord<String,String>("first",i+"","message_"+i),(metadata,exception)->{
                if (exception == null){
                    System.out.println("success");
                }else {
                    exception.printStackTrace();
                }
            });
        }
        //4.关闭生产者
        producer.close();
    }
}
