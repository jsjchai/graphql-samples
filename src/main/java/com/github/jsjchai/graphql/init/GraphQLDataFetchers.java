package com.github.jsjchai.graphql.init;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author chaicj
 */
@Component
public class GraphQLDataFetchers {


    public DataFetcher<Map<String, String>> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return BOOKS
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher<Map<String, String>> getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return AUTHORS
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher<List<Map<String, String>>> findAllAuthorsDataFetcher() {
        return dataFetchingEnvironment -> AUTHORS;
    }

    public DataFetcher<List<Map<String, String>>> findAllBooks() {
        return dataFetchingEnvironment -> BOOKS;
    }

    public DataFetcher<Map<String, String>> addBook() {
        BOOKS.add(ImmutableMap.of("id","uuuu"));
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String id = book.get("id");
            return AUTHORS
                    .stream()
                    .filter(e -> e.get("id").equals(id))
                    .findFirst()
                    .orElse(null);
        };
    }


    private static final List<Map<String, String>> BOOKS = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );

    private static final List<Map<String, String>> AUTHORS = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );
}
