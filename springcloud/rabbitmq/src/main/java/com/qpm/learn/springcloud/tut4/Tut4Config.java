package com.qpm.learn.springcloud.tut4;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 设置路由，生产者往一个队列中发送的时间是带有方向的，只有对这个路由感兴趣的消费者才会消费到.
 *
 * 官网的例子：
 * 1、定义两个队列
 * 2、定义一个 DirectExchange 交换机
 * 3、为队列和交换机的绑定增加一个路由key
 * 4、向交换机发送数据时，带上路由key，路由会指定发送到指定队列中。
 */
@Profile({"tut4", "routing"})
@Configuration
public class Tut4Config {

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("tut.direct");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Queue autoDeleteQueue1(){
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(direct)
                    .with("orange");
        }

        @Bean
        public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(direct)
                    .with("black");
        }

        @Bean
        public Binding binding2a(DirectExchange direct, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(direct)
                    .with("green");
        }

        @Bean
        public Binding binding2b(DirectExchange direct, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(direct)
                    .with("black");
        }

        @Bean
        public Tut4Receiver receiver() {
            return new Tut4Receiver();
        }

    }


    @Profile("sender")
    @Bean
    public Tut4Sender sender() {
        return new Tut4Sender();
    }


}
