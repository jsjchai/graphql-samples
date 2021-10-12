# graphql-samples

## 背景

> GraphQL是由 Facebook在2012年创立的一门开源查询语言(2012年仍处于Facebook内部开发阶段，直到2015年才公开发布)。在它开源之前，Facebook 就已经在内部移动端应用中使用过。为什么选用移动应用？GraphQL 作为通用的 REST 架构的替代方案而被开发出来，它允许客户端只请求其需要的数据——不多也不少，一切在客户端的主导下。

## GraphQL

> GraphQL是一种查询语言，用于从服务器检索数据。它以某种方式替代了REST，SOAP或gRPC

## RESTful架构

### 过度索取

> 在一个 RESTful 架构下，因为后端开发人员定义在各个 URL 的资源上返回的数据，而不是前端开发人员来提出数据需求，使得按需获取数据会非常困难。经常前端需要请求一个资源中所有的信息，即便只需要其中的一部分数据。这个问题被称之为过度获取（overfetching)

## 结构
### 查询和变更类型
```graphql
schema {
  query: Query
  mutation: Mutation
}
```
### 标量类型
* Int：有符号 32 位整数
* Float：有符号双精度浮点值
* String：UTF‐8 字符序列
* Boolean：true 或者 false
* ID：ID标量类型表示一个唯一标识符
### 自定义标量类型的方式
```graphql
# 自定义一个 Date 类型
scalar Date
```
### 枚举类型
```graphql
enum Episode {
  NEWHOPE
  EMPIRE
  JEDI
}
```

### 列表和非空
```graphql
type Character {
    name: String!
    # 非空字符串的数组
    myField: [String!]
}
```

### 接口
```graphql
interface Character {
    id: ID!
    name: String!
    friends: [Character]
    appearsIn: [Episode]!
}

type Human implements Character {
    id: ID!
    name: String!
    friends: [Character]
    appearsIn: [Episode]!
    starships: [Starship]
    totalCredits: Int
}
```

### 输入类型
```graphql
# 传递复杂对象,传递一整个对象作为新建对象
input ReviewInput {
  stars: Int!
  commentary: String
}
```

### 创建GraphQL Java服务主要步骤

1. 定义 GraphQL Schema
2. 确定如何获取查询的实际数据

## 接口文档

### 按照book:id查询信息

* 请求url: http://localhost:8080/graphql
* 请求方式：POST
* 请求参数

```graphql
# 根据ID查询
{
    bookById(id:"book-1"){
        id
        author{
            firstName
        }
    }
}
```

```graphql
#查询所有的作者
{
    findAllBooks{
        id
    }
}
```

```graphql
 # 添加书籍
 mutation{
    addBook(id:"book-100",name:"test",pageCount:10000){
        id
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
* [why-graphql-advantages-disadvantages-alternatives](https://www.robinwieruch.de/why-graphql-advantages-disadvantages-alternatives)
* [GraphQL入门](https://graphql.cn/learn/)
