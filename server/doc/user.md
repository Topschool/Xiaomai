### 入伙   

1 通过`code`向服务器获取uid `POST`    
`uri`: `/user/get_uid`    
`params`: 
```json
{
  "code": "code"
}
```
`return`:    
```json
{
  "data":"test_uid"
}
```
> `test_uid`为uid    


2 提交`uid`，`username`，`invitationCode`，`area`注册 `POST` 
`uri`: `/user/sign_up`      
`params`: 
```json
{
  "uid": "test_uid",
  "username":"test_username",
  "invitationCode":"test_invitationCode",
  "area":0
}
```
> `area`: 0->北京  1->上海  2->南京  3->无锡    
`return`:    
```json
{
  "data":{
     "uid": "test_uid",
     "username":"test_username"
  }
}
```
