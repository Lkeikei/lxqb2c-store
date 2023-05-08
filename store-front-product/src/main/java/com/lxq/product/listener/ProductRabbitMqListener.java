package com.lxq.product.listener;

import com.lxq.product.service.ProductService;
import com.lxq.to.OrderToProduct;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  18:02
 */
@Component
public class ProductRabbitMqListener {

    @Resource
    private ProductService productService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sub.queue"),
                    exchange = @Exchange("topic.ex"),
                    key = "sub.number"
            )
    )
    public void subNumber(List<OrderToProduct> orderToProducts){
        productService.subNumber(orderToProducts);
    }
}
