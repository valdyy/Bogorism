const tf = require('@tensorflow/tfjs');
const place = require('./Place.json')
//const rating const place

function loadData(){
    const place_arr = [];
    for (let i = 0; i < place.length; i++){
        place_arr.push([place[i]['place_id']]);
    }
    return place_arr;
}

async function loadModel(){
    console.log('loading model ...');
    model = await tf.loadLayersModel('https://storage.googleapis.com/bogorism/dataPlaces/model.json')
    console.log('Model Success')
}

const place_arr = tf.tensor(loadData());
const place_len = place.length;
const recommendationResults = [];
async function recommend(userId){
    let user = tf.fill([place_len], Number(userId));
    await loadModel();
    console.log(`Recommending for user: ${userId}`);
    pred_tensor = await model.predict([place_arr, user]).reshape([place_len]);
    pred = pred_tensor.arraySync();
    recommendationResults.splice(0,6)

    for (let i = 0; i<6; i++){
        max = pred_tensor.argMax().arraySync();
        recommendationResults.push(place[max])
        pred.splice(max, 1);
        pred_tensor = tf.tensor(pred);
    }
    return (recommendationResults)
}

module.exports = { recommendationResults, recommend }