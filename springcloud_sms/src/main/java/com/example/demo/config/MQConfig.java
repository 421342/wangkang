package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 *
 * 配置bean
 */
@Configuration
public class MQConfig {

    public static final String DELAY_QUEUE = "DELAY_QUEUE";
    public static final String QUEUE = "queue-mail";
    public static final String TOPIC_KEY1 = "topic.queuemail1";
    public static final String TOPIC_KEY2 = "topic.queuemail2";
    public static final String TOPIC_EXCHANGE = "topicmailExchage";
    


    /**
     * Direct模式 交换机Exchange
     * 发送者先发送到交换机上，然后交换机作为路由再将信息发到队列，
     * */
	/*
	 * @Bean public Queue queue() { return new Queue(QUEUE, true); }
	 */
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE, true);
//    }

    /**
     * Topic模式 交换机Exchange
     * */
    //实际消费队列
    @Bean
    public Queue Queue() {
        return new Queue(QUEUE, true);
    }
    //延迟消费队列
    @Bean
    public Queue DelayQueue() {
    	Map<String, Object> arguments = new HashMap<>();  
        arguments.put("x-dead-letter-exchange", TOPIC_EXCHANGE);  
        arguments.put("x-dead-letter-routing-key", TOPIC_KEY1);  
        arguments.put("x-message-ttl",9000);  
        Queue queue = new Queue(DELAY_QUEUE,true,false,true,arguments);  
        return queue;
    }
  //创建缓冲队列, 声明dlx
	/*
	 * @Bean public Queue cacheQueue() { Map<String, Object> args = new HashMap<>();
	 * //dlx的名称必须与创建exchange的名称相同 args.put("x-dead-letter-exchange","dlxExc");
	 * return QueueBuilder.nonDurable("delayqueue").withArguments(args).build(); }
	 */
  //创建缓冲队列的dead letter exchange (死信路由)
	/*
	 * @Bean public TopicExchange dlxExchange() { return new
	 * TopicExchange("dlxExc"); }
	 */
    @Bean
    public TopicExchange topicExchange(){
    	Map<String, Object> pros = new HashMap<>();
    	 //设置交换机支持延迟消息推送
    	// pros.put("x-delayed-message", "topic");
    	TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE);
    	//topicExchange.setDelayed(true);
        return topicExchange;
    }
    //绑定
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(Queue()).to(topicExchange()).with(TOPIC_KEY1);
    }
	
	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(DelayQueue()).to(topicExchange()).with(TOPIC_KEY2);
	}

	/*
	 * @Bean public Binding topicBinding3() { return
	 * BindingBuilder.bind(cacheQueue()).to(dlxExchange()).with("topicmail.#"); }
	 */
}
