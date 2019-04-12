package ru.otus.libraryapp.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CommentTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveAndGet() {
        Comment comment = new Comment( "Best", new Date(), "russian");
        Book bookFromDb = entityManager.find(Book.class, 5L);
        comment.setBook(bookFromDb);
        Long id = entityManager.persistAndGetId(comment, Long.class);
        Comment commentFromDb = entityManager.find(Comment.class, id);
        assertEquals(comment, commentFromDb);
    }

}