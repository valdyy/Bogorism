# Bogorism
## Bogorism is an app that recommends tourist spots in the area.
==
this project using express framework to create RESTFul API easily
Used Modules :
- "mysql" to connect with MySQL 
- "body-parser" to parse parameter
- "crypto" to encrypt password
- "uuid" to create unique id

### Currently used services are :
- Google Compute Engine
- Google Cloud SQL
- Google Cloud Storage 

## Endpoint
### http://35.212.162.240:8000

Register
- URL : /register
- Method : POST
- Response : <br>
{<br>
&nbsp;"error": false,<br>
&nbsp;"message": "User Created"<br>
 }<br>

Login
- URL : /login
- Method : POST
- Response : <br>
{<br>
&nbsp;"error": false,<br>
&nbsp;"message": "success",<br>
&nbsp;"loginResult": {<br>
&nbsp;&nbsp;"userId": "5e27251d-254a-489d-bd61-1ce17b85e32d",<br>
&nbsp;&nbsp;"name": "cekname",<br>
&nbsp;&nbsp;"token": "4c991ce9020cd8bf156ae160305bae97c079c91e938bc836d5df764cee288194161705c888476bdb5cf39ed59db3a47d2ee4fe7ec8c829050f14d3aade5110a9"<br>
&nbsp;}<br>
}<br>

Get Info User by name
- URL : /users/{name}
- Method : GET
- Response :<br>
{<br>
 &nbsp;"error": false,<br>
&nbsp;"message": "success",<br>
&nbsp;"loginResult": {<br>
&nbsp;&nbsp;"userId": "e1e9742f-8e60-4675-8c67-5bc4d4de70fd",<br>
&nbsp;&nbsp;"email": "valdyramadhan@gmail.com",<br>
&nbsp;&nbsp;"name": "valdy ramadhan",<br>
&nbsp;&nbsp;"token": "92524a8899bd6f0b5c83a34c28af705bb33179817358146c4ae691af43f87079292752e05cf29c2bdab524c353dbdb18ba8ecc98696f0b29902ec93990bb41ef"<br>
&nbsp;&nbsp;}<br>
}<br>

Get all places
- URL : /places
- Method : GET
- Response : <br>
[<br>
&nbsp;{<br>
&nbsp;&nbsp;"place_id": 1,<br>
&nbsp;&nbsp;"place_name": "Curug Cikuluwung",<br>
&nbsp;&nbsp;"description": "A tourist spot that serves 2 waterfalls located in Suka Asih Village Waterfall",<br>
&nbsp;&nbsp;"category": "Natural",<br>
&nbsp;&nbsp;"city": "Bogor",<br>
&nbsp;&nbsp;"price": 20000,<br>
&nbsp;&nbsp;"rating": 4.4,<br>
&nbsp;&nbsp;"lat": -6.642084,<br>
&nbsp;&nbsp;"long": 106.643266<br>
&nbsp;},<br>
&nbsp;...<br>
]<br>

Get places by category with query params "category"
- URL : /places
- Method : GET
- Response : <br>
[<br>
&nbsp;{<br>
&nbsp;&nbsp;"place_id": 1,<br>
&nbsp;&nbsp;"place_name": "Curug Cikuluwung",<br>
&nbsp;&nbsp;"description": "A tourist spot that serves 2 waterfalls located in Suka Asih Village Waterfall",<br>
&nbsp;&nbsp;*"category": "Natural"*,<br>
&nbsp;&nbsp;"city": "Bogor",<br>
&nbsp;&nbsp;"price": 20000,<br>
&nbsp;&nbsp;"rating": 4.4,<br>
&nbsp;&nbsp;"lat": -6.642084,<br>
&nbsp;&nbsp;"long": 106.643266<br>
&nbsp;},<br>
&nbsp;...<br>
]<br>

Search place by name with query params "search"
- URL : /search
- Method : GET
- Response : <br>
[<br>
&nbsp;{<br>
&nbsp;&nbsp;"place_id": 1,<br>
&nbsp;&nbsp;"place_name": "Curug Cikuluwung",<br>
&nbsp;&nbsp;"description": "A tourist spot that serves 2 waterfalls located in Suka Asih Village Waterfall",<br>
&nbsp;&nbsp;*"category": "Natural"*,<br>
&nbsp;&nbsp;"city": "Bogor",<br>
&nbsp;&nbsp;"price": 20000,<br>
&nbsp;&nbsp;"rating": 4.4,<br>
&nbsp;&nbsp;"lat": -6.642084,<br>
&nbsp;&nbsp;"long": 106.643266<br>
&nbsp;},<br>
&nbsp;...<br>
]<br>

Search place by name with body "searchPlace"
- URL : /search
- Method : GET
- Response : <br>
[<br>
&nbsp;{<br>
&nbsp;&nbsp;"place_id": 1,<br>
&nbsp;&nbsp;"place_name": "Curug Cikuluwung",<br>
&nbsp;&nbsp;"description": "A tourist spot that serves 2 waterfalls located in Suka Asih Village Waterfall",<br>
&nbsp;&nbsp;*"category": "Natural"*,<br>
&nbsp;&nbsp;"city": "Bogor",<br>
&nbsp;&nbsp;"price": 20000,<br>
&nbsp;&nbsp;"rating": 4.4,<br>
&nbsp;&nbsp;"lat": -6.642084,<br>
&nbsp;&nbsp;"long": 106.643266<br>
&nbsp;},<br>
&nbsp;...<br>
]<br>