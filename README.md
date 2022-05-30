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
<p> {
  "error": false,
  "message": "User Created"
 } <p>

Login
- URL : /login
- Method : POST
- Response :
<p> {
    "error": false,
    "message": "success",
    "loginResult": {
        "userId": "5e27251d-254a-489d-bd61-1ce17b85e32d",
        "name": "cekname",
        "token": "4c991ce9020cd8bf156ae160305bae97c079c91e938bc836d5df764cee288194161705c888476bdb5cf39ed59db3a47d2ee4fe7ec8c829050f14d3aade5110a9"
    }
} <p>