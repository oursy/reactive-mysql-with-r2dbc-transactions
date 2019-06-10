package com.example.mysqlr2;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

//    private final OrderRepository orderRepository;

    private final OrderService orderService;

    @GetMapping(value = "/orders/{id}")
    public Mono<Order> findOne(@PathVariable Integer id) {
        return orderService.findById(id).switchIfEmpty(
                Mono.error(IllegalArgumentException::new)
        );
    }


    @GetMapping(value = "/orders")
    public Flux<Order> findAll() {
        return orderService.findAll();
    }

    @PostMapping(value = "/orders")
    public Mono<Order> save(@Valid @RequestBody Order order) {
        return orderService.save(order);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handlerException(IllegalArgumentException ex) {
        log.error("handler Exception  ", ex);
        return ResponseEntity.badRequest().body("Bad Request");
    }
}
