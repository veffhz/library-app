# library-app

Simple console application on Spring Boot, Spring Shell and MongoDB. Usage:

#### Author commands
        add-author: Add author "firstName", "middleName", "lastName".
        author: Show author by id.
        authors: Show all authors.
        authors-last-name: Show authors by lastName.
        delete-author: Show author on id.

#### Book commands
        add-book: Add book "authorId", "genreId", "bookName", "publishDate", "language", "publishingHouse", "city", "isbn".
        book: Show book on id.
        books: Show all books.
        books-like-name: Show books like book name.
        books-name: Show books by bookName.
        delete-book: Show author on id.

#### Genre commands
        add-genre: Add genre "genre_name".
        genre-name: Show genres by genreName.
        delete-genre: Show genre on id.
        genre: Show genre on id.
        genres: Show all authors.

#### Other commands
        info: Show all info.
