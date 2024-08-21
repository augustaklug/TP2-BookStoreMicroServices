package com.example.BookStorePedidosMicrosservice.repository;

import com.example.BookStorePedidosMicrosservice.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
