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

//password encrypt
const RandomString = function(length){
    return crypto.randomBytes(Math.ceil(length/2))
    .toString('hex') //convert to hexa
    .slice(0, length); //returning number
};

const sha512 = function(password,salt){
    const hash = crypto.createHmac('sha512',salt); //using sha512
    hash.update(password);
    const value=hash.digest('hex');
    return {
        salt:salt,
        passwordHash:value
    };
};

function saltHashPassword(userPassword){
    const salt = RandomString(16); //generate 16 random string
    const passwordData = sha512(userPassword, salt);
    return passwordData;
}

app.get("/",(req,res,next)=>{
    console.log('password :valdy');
    const encrypt = saltHashPassword('valdy');
    console.log('encrypt: ' +encrypt.passwordHash);
    console.log('salt: ' +encrypt.salt);
});

const PORT = process.env.PORT || 8000
app.listen(PORT, () => {
    console.log("Server is up and listening on " + PORT)
})
