package it.angeloromano.db;

import org.springframework.data.jpa.repository.JpaRepository;

import it.angeloromano.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
