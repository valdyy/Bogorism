# Bogorism
## Bogorism is an app that recommends tourist spots in the area.
==
this project using express framework to create RESTFul API easily
Used Modules :
- "mysql" to connect with MySQL 
- "body-parser" to parse parameter
- "crypto" to encrypt password
- "uuid" to create unique id

## Endpoint
### http://35.212.162.240:8000

Register
- URL : /register
- Method : POST
- Response : 
{<br>
  "error": false,<br>
  "message": "User Created"<br>
 }<br>

Login
- URL : /login
- Method : POST
- Response :
{<br>
    "error": false,<br>
    "message": "success",<br>
    "loginResult": {<br>
        "userId": "5e27251d-254a-489d-bd61-1ce17b85e32d",<br>
        "name": "cekname",<br>
        "token": "4c991ce9020cd8bf156ae160305bae97c079c91e938bc836d5df764cee288194161705c888476bdb5cf39ed59db3a47d2ee4fe7ec8c829050f14d3aade5110a9"<br>
    }<br>
}<br>

Get Info User by name
- URL : /users/{name}
- Method : GET
- Response :
{<br>
    "error": false,<br>
    "message": "success",<br>
    "loginResult": {<br>
        "userId": "e1e9742f-8e60-4675-8c67-5bc4d4de70fd",<br>
        "email": "valdyramadhan@gmail.com",<br>
        "name": "valdy ramadhan",<br>
        "token": "92524a8899bd6f0b5c83a34c28af705bb33179817358146c4ae691af43f87079292752e05cf29c2bdab524c353dbdb18ba8ecc98696f0b29902ec93990bb41ef"<br>
        }<br>
}<br>