# Bogorism
## Bogorism is an app that recommends tourist spots in the area.
==
this project using express framework to create RESTFul API easily
Used Modules :
- "mysql" to connect with MySQL 
- "body-parser" to parse parameter
- "crypto" to encrypt password
- "uuid" to create unique id
- "@tensorflow/tfjs" for the Machine Learning Model Implementations to API

### Currently used services are :
- Google Compute Engine
- Google Cloud SQL
- Google Cloud Storage 

## Endpoint
### http://35.212.162.240:8000

###### Register
- URL : /register
- Method : POST
- body req : name, email, password
- Response : <br>
{<br>
&nbsp;"error": false,<br>
&nbsp;"message": "User Created"<br>
 }<br>

###### Login
- URL : /login
- Method : POST
- Body Req : email, password
- Response : <br>
{<br>
&nbsp;"error": false,<br>
&nbsp;"message": "success",<br>
&nbsp;"loginResult": {<br>
&nbsp;&nbsp;"userId": "e1e9742f-8e60-4675-8c67-5bc4d4de70fd",<br>
&nbsp;&nbsp;"name": "valdy ramadhan",<br>
&nbsp;&nbsp;"token": ""92524a8899bd6f0b5c83a34c28af705bb33179817358146c4ae691af43f87079292752e05cf29c2bdab524c353dbdb18ba8ecc98696f0b29902ec93990bb41ef""<br>
&nbsp;}<br>
}<br>

###### Get Info User by name
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

###### Get all places
- URL : /places
- Method : GET
- Response : <br>
{<br>
&nbsp;"error": false,<br>
&nbsp;"message": "All Places Data",<br>
&nbsp;"placeResult": [ <br>
&nbsp;&nbsp;{<br>
&nbsp;&nbsp;&nbsp;"place_id": 1,<br>
&nbsp;&nbsp;&nbsp;"place_name": "Curug Cikuluwung",<br>
&nbsp;&nbsp;&nbsp;"description": "A tourist spot that serves 2 waterfalls located in Suka Asih Village Waterfall",<br>
&nbsp;&nbsp;&nbsp;"category": "Natural",<br>
&nbsp;&nbsp;&nbsp;"city": "Bogor",<br>
&nbsp;&nbsp;&nbsp;"price": 20000,<br>
&nbsp;&nbsp;&nbsp;"rating": 4.4,<br>
&nbsp;&nbsp;&nbsp;"lat": -6.642084,<br>
&nbsp;&nbsp;&nbsp;"long": 106.643266<br>
&nbsp;&nbsp;},<br>
&nbsp;&nbsp;...<br>
&nbsp;]<br>
}<br>

###### Get places by category with query params "category"
- URL : /places
- req query = ?category
- Method : GET
- Response : <br>
{<br>
&nbsp;"error": false,<br>
&nbsp;"message": "Places by Category {category}",<br>
&nbsp;"placeResult": [<br>
&nbsp;&nbsp;{<br>
&nbsp;&nbsp;&nbsp;"place_id": 1,<br>
&nbsp;&nbsp;&nbsp;"place_name": "Curug Cikuluwung",<br>
&nbsp;&nbsp;&nbsp;"description": "A tourist spot that serves 2 waterfalls located in Suka Asih Village Waterfall",<br>
&nbsp;&nbsp;&nbsp;*"category": "Natural"*,<br>
&nbsp;&nbsp;&nbsp;"city": "Bogor",<br>
&nbsp;&nbsp;&nbsp;"price": 20000,<br>
&nbsp;&nbsp;&nbsp;"rating": 4.4,<br>
&nbsp;&nbsp;&nbsp;"lat": -6.642084,<br>
&nbsp;&nbsp;&nbsp;"long": 106.643266<br>
&nbsp;&nbsp;},<br>
&nbsp;&nbsp;...<br>
&nbsp;]<br>
}<br>

###### Search place by name
- URL : /search
- req query = ?search
- Method : GET
- Response : <br>
{<br>
&nbsp;"error": false,<br>
&nbsp;"founded": 31,<br>
&nbsp;"placeResult": [<br>
&nbsp;&nbsp;{<br>
&nbsp;&nbsp;&nbsp;"place_id": 1,<br>
&nbsp;&nbsp;&nbsp;"place_name": "Curug Cikuluwung",<br>
&nbsp;&nbsp;&nbsp;"description": "A tourist spot that serves 2 waterfalls located in Suka Asih Village Waterfall",<br>
&nbsp;&nbsp;&nbsp;*"category": "Natural"*,<br>
&nbsp;&nbsp;&nbsp;"city": "Bogor",<br>
&nbsp;&nbsp;&nbsp;"price": 20000,<br>
&nbsp;&nbsp;&nbsp;"rating": 4.4,<br>
&nbsp;&nbsp;&nbsp;"lat": -6.642084,<br>
&nbsp;&nbsp;&nbsp;"long": 106.643266<br>
&nbsp;&nbsp;},<br>
&nbsp;&nbsp;...<br>
&nbsp;]<br>
}

###### Detail place by place name
- URL : /places/{place_name}
- Method : GET
- Response : <br>
{<br>
&nbsp;"error": false,<br>
&nbsp;"message": "Detail for curug cikuluwung",<br>
&nbsp;"detail": [<br>
&nbsp;&nbsp;{<br>
&nbsp;&nbsp;&nbsp;"place_id": 1,<br>
&nbsp;&nbsp;&nbsp;"place_name": "Curug Cikuluwung",<br>
&nbsp;&nbsp;&nbsp;"description": "A tourist spot that serves 2 waterfalls located in Suka Asih Village Waterfall",<br>
&nbsp;&nbsp;&nbsp;"category": "Natural",<br>
&nbsp;&nbsp;&nbsp;"city": "Bogor",<br>
&nbsp;&nbsp;&nbsp;"price": 20000,<br>
&nbsp;&nbsp;&nbsp;"rating": 4.4,<br>
&nbsp;&nbsp;&nbsp;"lat": -6.642084,<br>
&nbsp;&nbsp;&nbsp;"long": 106.643266,<br>
&nbsp;&nbsp;&nbsp;"image_a": "https://storage.googleapis.com/bogorism/imagesPlaces/1-A.jpg",<br>
&nbsp;&nbsp;&nbsp;"image_b": "https://storage.googleapis.com/bogorism/imagesPlaces/1-B.jpg\r"<br>
&nbsp;&nbsp;}<br>
&nbsp;]<br>
}

###### Get Place Recommendations by User ID
- URL : /recommend/{id}
- Method : GET
- Response : 
{<br>
&nbsp;"recommendationResults": [<br>
&nbsp;&nbsp;{<br>
&nbsp;&nbsp;&nbsp;"place_id": 80,<br>
&nbsp;&nbsp;&nbsp;"place_name": "Jumpa Bogor (JUMBO)",<br>
&nbsp;&nbsp;&nbsp;"description": "This place is one of the great souvenir centers in the city of Bogor.",<br>
&nbsp;&nbsp;&nbsp;"category": "Shopping",<br>
&nbsp;&nbsp;&nbsp;"city": "Bogor",<br>
&nbsp;&nbsp;&nbsp;"price": 0,<br>
&nbsp;&nbsp;&nbsp;"rating": 4.7,<br>
&nbsp;&nbsp;&nbsp;"lat": -6.561603,<br>
&nbsp;&nbsp;&nbsp;"long": 106.794426,<br>
&nbsp;&nbsp;&nbsp;"image_a": "https://storage.googleapis.com/bogorism/imagesPlaces/80-A.jpg",<br>
&nbsp;&nbsp;&nbsp;"image_b": "https://storage.googleapis.com/bogorism/imagesPlaces/80-B.jpg"<br>
&nbsp;&nbsp;},<br>
&nbsp;&nbsp;...<br>
&nbsp;]<br>
}