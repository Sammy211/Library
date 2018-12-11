package com.inai.oorpo.library.Library.repository;

import com.inai.oorpo.library.Library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{
}
