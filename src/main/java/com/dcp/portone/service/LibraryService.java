package com.dcp.portone.service;

import com.dcp.portone.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LibraryService {
    public abstract Set<Book> getAllBooks();
    public Set<Book> getBooksByGenre(String arg);
    public Map<String, List<Book>> getBooksByGroup();
}
