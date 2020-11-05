# graphql-samples

## GraphQL 
> GraphQL是一种查询语言，用于从服务器检索数据。它以某种方式替代了REST，SOAP或gRPC

### 创建GraphQL Java服务主要步骤
1. 定义 GraphQL Schema
2. 确定如何获取查询的实际数据

## 接口文档
### 按照book:id查询信息
* 请求url: http://localhost:8080/graphql
* 请求方式：POST
* 请求参数
```graphql
{
  bookById(id:"book-1"){
        id
        author{
            firstName
        }
    }
}
```
* 返回结果
```
{
    "data": {
        "bookById": {
            "id": "book-1",
            "author": {
                "firstName": "Joanne"
            }
        }
    }
}
```

## 参考文档
* [Getting started with GraphQL Java and Spring Boot](https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/)
* [Introduction to GraphQL](https://graphql.github.io/learn/)
