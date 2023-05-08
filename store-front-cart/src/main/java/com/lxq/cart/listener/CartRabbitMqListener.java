package com.lxq.cart.listener;

import com.lxq.cart.service.CartService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  17:57
 */
@Component
public class CartRabbitMqListener {

    @Resource
    private CartService cartService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "clear.queue"),
                    exchange = @Exchange(value = "topic.ex"),
                    key = "clear.cart"
            )
    )
    public void clear(List<Integer> cartIds){
        cartService.clearIds(cartIds);
    }
}
