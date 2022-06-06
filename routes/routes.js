const mysql = require('mysql');
const crypto = require('crypto');
const uuid = require('uuid');
const express = require('express');
const model = require('./model');
const recommendationResults = require('./model');
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

function checkHashPassword(userPassword,salt){
    const passwordData = sha512(userPassword,salt);
    return passwordData;
}

router.post('/register/',(req,res)=>{
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

router.post('/login/', (req,res)=>{
    const post_data = req.body;

    const user_password = post_data.password;
    const email = post_data.email;

    con.query('SELECT * FROM user where email=?', [email], function(err,result,fields){
        con.on('error', function(err){
            console.log('[MySQL ERROR]', err);
        });
        if(result && result.length){
            const id = result[0].unique_id;
            const name = result[0].name;
            const salt = result[0].salt;
            const password = result[0].password;
            const hash_password = checkHashPassword(user_password,salt).passwordHash;
            if (password == hash_password){
                return res.status(200).json({
                    error: false,
                    message: 'success',
                    loginResult: {
                        userId: `${id}`,
                        name: `${name}`,
                        token: `${password}`
                    }
                });
            }else{
                return res.status(400).json({
                    error: true,
                    message: 'Wrong Password!'
                });
            }
        }else{
            return res.status(200).json({
                error: true,
                message: 'User Does Not Exist!'
            });
        }
    });
});

router.get('/users/:name', (req,res)=>{
    con.query('SELECT * FROM user WHERE name=?',[req.params.name], function(err,result,fields){
        con.on('error', function(err){
            console.log('[MySQL ERROR]', err);
        });
        if(result && result.length){
            const id = result[0].unique_id;
            const name = result[0].name;
            const email = result[0].email;
            const salt = result[0].salt;
            const password = result[0].password;
                return res.status(200).json({
                        userId: `${id}`,
                        email: `${email}`,
                        name: `${name}`,
                        token: `${password}`
                });
        }else{
            return res.status(404).json({
                error: true,
                message: 'Data User Not Found!'
            });
        }
    });
});

router.get('/places', (req,res)=>{
    const category = req.query.category;
    if (!category){
        con.query('SELECT * FROM place', function(err,result,fields){
            con.on('error', function(err){
                console.log('[MySQL ERROR]', err);
            });
            if(!err){
                return res.status(200).json({
                    error: false,
                    message: 'All Places Data',
                    placeResult: result,
                });
            }else{
                console.log(err);
            }
        });
    }else{
        con.query(`SELECT * FROM place WHERE category= '${category}'`, function(err,result,fields){
            con.on('error', function(err){
                console.log('[MySQL ERROR]', err);
            });
            if(!err){
                return res.status(200).json({
                    error: false,
                    message: `Places by Category ${category}`,
                    placeResult: result,
                });
            }else{
                console.log(err);
            }
        });
    }
});

router.get("/search", (req, res) => {
    const search = req.query.search;
    if(search !== undefined){
        if (search !== ""){
            con.query(`SELECT * FROM place WHERE place_name LIKE '%${search}%'`, (err, result, field) => {
                if (result == ""){
                    return res.status(404).json({
                        error: true,
                        message: 'No places found by this search key'
                    });
                }else{
                    if(!err) {
                        return res.status(200).json({
                            error: false,
                            founded: result.length,
                            placeResult: result,
                        });
                    } else {
                        res.status(404).json({message: err.sqlMessage});
                    }
                }
                })
            }else{
                return res.status(404).json({
                    error: true,
                    message: 'Please enter a search key!'
                });
            }
        }else{
            return res.status(404).json({
                error: true,
                message: `Please enter a search key 'search' in 'PARAMS'!`
            });
        }
})

router.get('/places/:place_name', (req,res)=>{
    con.query('SELECT * FROM place WHERE place_name=?',[req.params.place_name], function(err,result,fields){
        con.on('error', function(err){
            console.log('[MySQL ERROR]', err);
        });
        const place_name = req.params.place_name
        if(result && result.length){
            return res.status(200).json({
                error: false,
                message: `Detail for ${place_name}`,
                detail: result,
            })
        }else{
            return res.status(404).json({
                error: true,
                message: 'Data Place Not Found!'
            });
        }
    });
});

router.get('/recommend/:userId', (req, res) => {
    const userId = req.params.userId;
    model.recommend(userId)
    setTimeout(() => {
        return res.status(200).json(recommendationResults);
    }, 250);
  });

module.exports = router