const express = require('express');
const path = require('path');
const app = express();

app.set('port', process.env.PORT || 9000);

app.use(express.static(path.join(__dirname, 'build')));

const server = app.listen(app.get('port'), function() {
    const port = server.address().port;
    console.log('Express is running on port: ' + port);
});
