### 入伙   

1 通过`code`向服务器获取uid `GET`    
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


2 通过`code`向服务器获取uid `GET` 
`uri`: `/user/sign_up`      
`params`: 
```json
{
  "uid": "test_uid",
  "username":"test_username",
  "invitationCode":"test_invitationCode"
}
```
`return`:    
```json
{
  "data":{
     "uid": "test_uid",
     "username":"test_username"
  }
}
```
