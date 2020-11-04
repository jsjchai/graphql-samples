package com.github.jsjchai.graphql.init;

import graphql.schema.DataFetchingEnvironment;

public interface DataFetcher<T> {

    T get( DataFetchingEnvironment dataFetchingEnvironment) throws Exception;
}
