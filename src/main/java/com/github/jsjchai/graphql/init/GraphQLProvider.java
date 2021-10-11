package com.github.jsjchai.graphql.init;


import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author chaicj
 */
@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    /**
     * 构建GraphQLSchema
     *
     * @param sdl schema
     * @return GraphQLSchema
     */
    private GraphQLSchema buildSchema(String sdl) {
        var typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    /**
     * 构建
     *
     * @return RuntimeWiring
     */
    private RuntimeWiring buildWiring() {

        Map<String, DataFetcher> dataFetchersMap = Maps.newHashMap();
        dataFetchersMap.put("bookById", graphQLDataFetchers.getBookByIdDataFetcher());
        dataFetchersMap.put("findAllBooks", graphQLDataFetchers.findAllBooks());
        dataFetchersMap.put("findAllAuthors", graphQLDataFetchers.findAllAuthorsDataFetcher());

        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetchers(dataFetchersMap))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("addBook",e->graphQLDataFetchers.addBook()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
                .build();
    }
}
