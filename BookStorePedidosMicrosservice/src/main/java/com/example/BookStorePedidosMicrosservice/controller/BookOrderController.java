package com.example.BookStorePedidosMicrosservice.controller;

import com.example.BookStorePedidosMicrosservice.dto.BookOrderDTO;
import com.example.BookStorePedidosMicrosservice.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class BookOrderController {

    @Autowired
    private BookOrderService bookOrderService;

    @GetMapping
    public List<BookOrderDTO> getAllOrders() {
        return bookOrderService.getAllBookOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookOrderDTO> getBookOrderById(@PathVariable Long id) {
        return bookOrderService.getBookOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BookOrderDTO createBookOrder(@RequestBody BookOrderDTO bookOrderDTO) {
        return bookOrderService.saveOrder(bookOrderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookOrder(@PathVariable Long id) {
        bookOrderService.deleteBookOrder(id);
        return ResponseEntity.noContent().build();
    }
}
