package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.BookSale;

import java.util.List;

public interface BookSaleService {
    List<BookSale> findAllSales();
    List<BookSale> findByBookId(Long id);
//    List<BookSale> findByBookStore(Long id);
}
