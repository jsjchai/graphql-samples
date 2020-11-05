# graphql-samples
## 接口文档
### 按照book:id查询信息
* 请求url: http://localhost:8080/graphql
* 请求方式：POST
* 请求参数
```
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

