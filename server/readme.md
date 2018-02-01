### api

---  

1) 刮刮卡

`url`: `http://192.168.3.5:8080/game/scratch-card`

`params`:

```json
{
  "wx_id": "zhangsan"
}
```
`return`:
```json
{
    "curUserGroup": 1,
    "lastList": [
        {
            "money": 9,
            "nickname": "test_user_9",
            "id": 9
        },
        {
            "money": 8,
            "nickname": "test_user_8",
            "id": 8
        }
    ],
    "partnerStatus": false,
    "todayList": [
        {
            "money": 0,
            "nickname": "test_user_0",
            "id": 0
        },
        {
            "money": 1,
            "nickname": "test_user_1",
            "id": 1
        },
        {
            "money": 2,
            "nickname": "test_user_2",
            "id": 2
        },
        {
            "money": 3,
            "nickname": "test_user_3",
            "id": 3
        },
        {
            "money": 4,
            "nickname": "test_user_4",
            "id": 4
        },
        {
            "money": 5,
            "nickname": "test_user_5",
            "id": 5
        },
        {
            "money": 6,
            "nickname": "test_user_6",
            "id": 6
        },
        {
            "money": 7,
            "nickname": "test_user_7",
            "id": 7
        },
        {
            "money": 8,
            "nickname": "test_user_8",
            "id": 8
        },
        {
            "money": 9,
            "nickname": "test_user_9",
            "id": 9
        }
    ],
    "topList": [
        {
            "money": 9,
            "nickname": "test_user_9",
            "id": 9
        },
        {
            "money": 8,
            "nickname": "test_user_8",
            "id": 8
        }
    ],
    "totalNum": 45,
    "totalTop": [
        {
            "money": 9,
            "nickname": "test_user_9",
            "id": 9
        },
        {
            "money": 8,
            "nickname": "test_user_8",
            "id": 8
        },
        {
            "money": 7,
            "nickname": "test_user_7",
            "id": 7
        }
    ]
}
```



