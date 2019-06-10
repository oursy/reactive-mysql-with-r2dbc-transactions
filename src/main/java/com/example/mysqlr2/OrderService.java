package com.example.mysqlr2;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderService {

    private final DatabaseClient databaseClient;

    @Transactional
    public Mono<Order> save(Order order) {
        return databaseClient.insert()
                .into(Order.class)
                .using(order)
                .map(new BiFunction<Row, RowMetadata, Order>() {
                    @Override
                    public Order apply(Row row, RowMetadata rowMetadata) {
                        return new Order(row.get("LAST_INSERT_ID", Integer.class), order.getName(), order.getCode());
                    }
                }).first();
    }

    public Flux<Order> findAll() {
        return databaseClient.select()
                .from(Order.class)
                .fetch().all();
    }

    public Mono<Order> findById(Integer id) {
        return databaseClient.execute()
                .sql("SELECT *FROM `order`  WHERE  id=:id")
                .bind("id", id)
                .as(Order.class)
                .fetch()
                .one();

    }
}
