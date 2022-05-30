const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const router =  require('./routes/routes');
//Acc json params and acc URL encoded params
app.use(bodyParser.json()); 
app.use(bodyParser.urlencoded({extended: true}));
app.use(router);

app.get("/",(req,res,next)=>{
    res.send("Server is Running")
});

const PORT = process.env.PORT || 8000
app.listen(PORT, () => {
    console.log("Server is up and listening on " + PORT)
})
