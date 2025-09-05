package com.diesgut.saga.order.doman.order_history.imp;

import com.diesgut.saga.commons.types.OrderStatus;
import com.diesgut.saga.order.doman.order_history.OrderHistoryEntity;
import com.diesgut.saga.order.doman.order_history.OrderHistoryRepository;
import com.diesgut.saga.order.doman.order_history.OrderHistoryService;
import com.diesgut.saga.order.doman.order_history.dto.OrderHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public void add(UUID orderId, OrderStatus orderStatus) {
        OrderHistoryEntity entity = new OrderHistoryEntity();
        entity.setOrderId(orderId);
        entity.setStatus(orderStatus);
        entity.setCreatedAt(new Timestamp(new Date().getTime()));
        orderHistoryRepository.save(entity);
    }

    @Override
    public List<OrderHistory> findByOrderId(UUID orderId) {
        var entities = orderHistoryRepository.findByOrderId(orderId);
        return entities.stream().map(entity -> {
            OrderHistory orderHistory = new OrderHistory();
            BeanUtils.copyProperties(entity, orderHistory);
            return orderHistory;
        }).toList();
    }
}
