package com.woniuxy.qiantai.mqconsumer;

import com.woniuxy.dal.entity.Order;
import com.woniuxy.servicelayer.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
    @Component
    public class OrderCancellationConsumer {
        @Autowired
        private OrderService orderService;

        private static final String DL_QUEUE_NAME = "order_dl_queue";

        @RabbitListener(queues = DL_QUEUE_NAME)
        public void processOrderCancellation(Long orderId) {
            // 根据订单ID执行订单取消逻辑
            // 检查订单是否已支付，如果未支付，则执行订单取消操作

            // 示例代码：
            Order order = orderService.getById(orderId);
            if (order.getState() == 1) {
                System.out.println("订单支付超时，即将被取消...");
                order.setState(5); // 设置订单状态为已取消
                orderService.updateById(order);
            }
        }
    }

