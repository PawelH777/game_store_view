package org.example.viewgames.infrastructure.config;

import org.example.viewgames.infrastructure.listener.GameQueueListener;
import org.example.viewgames.infrastructure.model.GameMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitmqConfig {

    static final String queueName = "games-queue";

    @Bean
    SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory,
                                             final MessageListenerAdapter listenerAdapter) {
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(final GameQueueListener gameQueueListener) {
        final MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(gameQueueListener, "processGameMessage");
        messageListenerAdapter.setMessageConverter(consumerJackson2MessageConverter());
        return messageListenerAdapter;
    }

    @Bean
    public Jackson2JsonMessageConverter consumerJackson2MessageConverter() {
        final Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        final DefaultJackson2JavaTypeMapper classMapper = new DefaultJackson2JavaTypeMapper();
        classMapper.setIdClassMapping(Map.of("gameMessage", GameMessage.class));
        messageConverter.setClassMapper(classMapper);
        return messageConverter;
    }
}
