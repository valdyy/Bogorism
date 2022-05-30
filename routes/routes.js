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

router.post('/register/',(req,res,next)=>{
    const post_data = req.body;

    const uid = uuid.v4();
    const plain_password = post_data.password;
    const hash_data = saltHashPassword(plain_password);
    const password = hash_data.passwordHash;
    const salt = hash_data.salt;

    const name = post_data.name;
    const email = post_data.email;

    con.query('SELECT * FROM user where email=?', [email], function(err,result,fields){
        con.on('error', function(err){
            console.log('[MySQL ERROR]', err);
        });
        if(result && result.length){
            return res.status(400).json({
                error: true,
                message: 'User Already Exist!'
            });
        }else{
            con.query('SELECT * FROM user where name=?', [name], function(err,result,fields){
                con.on('error', function(err){
                    console.log('[MySQL ERROR]', err)
                });
                if (result && result.length){
                    return res.status(400).json({
                        error: true,
                        message: `User's Name Already Exist!`
                    });
                }else{
                    con.query('INSERT INTO `user`(`unique_id`, `name`, `email`, `password`, `salt`, `created_at`, `updated_at`) VALUES (?,?,?,?,?,NOW(),NOW())',[uid,name,email,password,salt],function(err,result,fields){
                        con.on('error', function(err){
                            console.log('[MySQL ERROR]', err);
                            res.json('Register error: ', err);
                        });
                        return res.status(201).json({
                            error: false,
                            message: 'User Created'
                        });
                    })
                }
            })
        }
    });
});

module.exports = router