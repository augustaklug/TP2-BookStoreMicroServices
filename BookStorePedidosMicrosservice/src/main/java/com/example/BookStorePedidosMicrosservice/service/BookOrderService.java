package com.example.BookStorePedidosMicrosservice.service;

import com.example.BookStorePedidosMicrosservice.dto.BookDTO;
import com.example.BookStorePedidosMicrosservice.dto.BookOrderDTO;
import com.example.BookStorePedidosMicrosservice.dto.CustomerDTO;
import com.example.BookStorePedidosMicrosservice.model.BookOrder;
import com.example.BookStorePedidosMicrosservice.repository.BookOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookOrderService {

    @Autowired
    private BookOrderRepository bookOrderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    public List<BookOrderDTO> getAllBookOrders() {
        return bookOrderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<BookOrderDTO> getBookOrderById(Long id) {
        return bookOrderRepository.findById(id)
                .map(this::convertToDTO);
    }

    public BookOrderDTO saveOrder(BookOrderDTO bookOrderDTO) {
        // Verifica se o cliente existe
        CustomerDTO customer = customerService.getCustomerById(bookOrderDTO.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        // Verifica se o livro existe e obtém o preço
        BookDTO book = bookService.getBookById(bookOrderDTO.getBookId());
        if (book == null) {
            throw new RuntimeException("Livro não encontrado");
        }

        // Calcula o preço total
        bookOrderDTO.setTotalPrice(book.getPrice() * bookOrderDTO.getQuantity());

        // Converte DTO para entidade
        BookOrder bookOrder = convertToEntity(bookOrderDTO);

        // Salva o pedido
        BookOrder savedOrder = bookOrderRepository.save(bookOrder);

        // Converte a entidade salva de volta para DTO
        return convertToDTO(savedOrder);
    }

    public void deleteBookOrder(Long id) {
        bookOrderRepository.deleteById(id);
    }

    private BookOrder convertToEntity(BookOrderDTO dto) {
        BookOrder entity = new BookOrder();
        entity.setId(dto.getId());
        entity.setBookId(dto.getBookId());
        entity.setCustomerId(dto.getCustomerId());
        entity.setBookTitle(dto.getBookTitle());
        entity.setQuantity(dto.getQuantity());
        entity.setTotalPrice(dto.getTotalPrice());
        return entity;
    }

    private BookOrderDTO convertToDTO(BookOrder entity) {
        BookOrderDTO dto = new BookOrderDTO();
        dto.setId(entity.getId());
        dto.setBookId(entity.getBookId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setBookTitle(entity.getBookTitle());
        dto.setQuantity(entity.getQuantity());
        dto.setTotalPrice(entity.getTotalPrice());
        return dto;
    }
}
