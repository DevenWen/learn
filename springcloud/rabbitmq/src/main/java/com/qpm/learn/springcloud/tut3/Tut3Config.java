package com.qpm.learn.springcloud.tut3;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author kangqiang.w
 * @Date 2020-04-18
 **/
@Profile({"tut3", "pub-sub", "public-subscribe"})
@Configuration
public class Tut3Config {

    /**
     * 定义一个交换机
     *
     * @return Exchange对象
     */
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange(("tut.fanout"));
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        /**
         * 定义一个队列1
         * @return 队列对象
         */
        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        /**
         * 定义一个队列2
         * @return 队列对象
         */
        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        /**
         * 将队列和交换器绑定，提交到交换器的数据会被转发给队列
         * @param fanout
         * @param autoDeleteQueue1
         * @return Binding 对象
         */
        @Bean
        public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }

        /**
         * 将队列和交换器绑定，提交到交换器的数据会被转发给队列
         * @param fanout
         * @param autoDeleteQueue2
         * @return Binding 对象
         */
        @Bean
        public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }

        @Bean
        public Tut3Receiver receiver() {
            return new Tut3Receiver();
        }
    }


    @Profile("sender")
    @Bean
    public Tut3Sender sender() {
        return new Tut3Sender();
    }

}
