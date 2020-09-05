package com.atguigu.kafka.producer;

import com.atguigu.kafka.interceptor.CounterInterceptor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.omg.PortableInterceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomProducer {
    public static void main(String[] args) {
        //3.创建一个Properties
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"nn1.hadoop:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        List<String> interceptors = new ArrayList<>();
        interceptors.add("com.atguigu.kafka.interceptor.CounterInterceptor");
        interceptors.add("com.atguigu.kafka.interceptor.TimeInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        
        //1.创建一个生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        //2.send方法
        for (int i=0;i<1000;i++){
            producer.send(new ProducerRecord<String,String>("first",i+"","message_"+i));
        }
        //4.关闭生产者
        producer.flush();
        producer.close();
  }
}
