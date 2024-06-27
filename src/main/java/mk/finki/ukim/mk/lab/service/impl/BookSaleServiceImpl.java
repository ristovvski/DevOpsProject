package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.BookSale;
import mk.finki.ukim.mk.lab.repository.jpa.JpaBookSaleRepository;
import mk.finki.ukim.mk.lab.service.BookSaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookSaleServiceImpl implements BookSaleService {
    JpaBookSaleRepository jpaBookSaleRepository;

    public BookSaleServiceImpl(JpaBookSaleRepository jpaBookSaleRepository) {
        this.jpaBookSaleRepository = jpaBookSaleRepository;
    }


    @Override
    public List<BookSale> findAllSales() {
        return jpaBookSaleRepository.findAll();
    }

    @Override
    public List<BookSale> findByBookId(Long id) {
        return jpaBookSaleRepository.findAllByBook_Id(id);
    }

//    @Override
////    public List<BookSale> findByBookStore(Long id) {
////        return jpaBookSaleRepository.findAllByBook_BookStore(id);
////    }
}
