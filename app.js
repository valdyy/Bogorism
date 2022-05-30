const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser');
const crypto = require('crypto');
const uuid = require('uuid');
const app = express();

//Acc json params and acc URL encoded params
app.use(bodyParser.json()); 
app.use(bodyParser.urlencoded({extended: true}));

//Connect to DataBase
const con = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'users'
});

const PORT = process.env.PORT || 8000
app.listen(PORT, () => {
    console.log("Server is up and listening on " + PORT)
})
