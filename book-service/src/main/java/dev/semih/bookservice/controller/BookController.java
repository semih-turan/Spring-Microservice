package dev.semih.bookservice.controller;

import dev.semih.bookservice.client.CustomerClient;
import dev.semih.bookservice.model.Book;
import dev.semih.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    public CustomerClient customerClient;

    @PostMapping
    public Book add(@RequestBody Book book) {
        return customerRepository.addBook(book);
    }

    @GetMapping("/{id}")
    public Book findById(@RequestParam("id") Long id) {
        return customerRepository.findById(id);
    }

    @GetMapping
    public List<Book> findAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/with-customers")
    public List<Book> findAllWithCustomer(){
        List<Book> bookList = bookRepository.findAll();
        bookList.forEach(customer -> customer.setCustomerList(customerClient.findByCustomerId(customer.getId())));
        return bookList;
    }

}
