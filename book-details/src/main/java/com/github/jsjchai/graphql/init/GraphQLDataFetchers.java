package com.github.jsjchai.graphql.init;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author chaicj
 */
@Component
@Slf4j
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

    public DataFetcher<List<Map<String, String>>> findAllBooksDataFetcher() {
        return dataFetchingEnvironment -> BOOKS;
    }

    public DataFetcher<Map<String, String>> addBookDataFetcher() {
        log.info("add book");
        return dataFetchingEnvironment -> {
            Map<String, Object> args = dataFetchingEnvironment.getArguments();
            String bookId = dataFetchingEnvironment.getArgument("id");
            BOOKS.add(ImmutableMap.of(
                    "id", bookId,
                    "name", dataFetchingEnvironment.getArgument("name"),
                    "pageCount", "" + dataFetchingEnvironment.getArgument("pageCount")
            ));
            return BOOKS
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }


    private static final List<Map<String, String>> BOOKS = Lists.newArrayList(
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
