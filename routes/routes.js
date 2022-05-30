const mysql = require('mysql');
const crypto = require('crypto');
const uuid = require('uuid');
const express = require('express');
const router = express.Router()
const app = express();

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

router.get("/a",(req,res,next)=>{
    console.log('password :12334');
    const encrypt = saltHashPassword('valdy');
    console.log('encrypt: ' +encrypt.passwordHash);
    console.log('salt: ' +encrypt.salt);
});

module.exports = router