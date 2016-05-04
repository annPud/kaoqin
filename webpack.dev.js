var webpack = require('webpack');
var path = require('path');

module.exports = {
    entry: [
        'webpack-hot-middleware/client?reload=true',
        './src/main/js/index.js'
    ],
    output: {
        path: path.join(__dirname, 'src/main/webapp/static'),
        filename: 'index.js',
        publicPath: '/build/'
    },
    resolve: {
        extentions: ['', 'js','jsx']
    },
    module: {
        loaders: [{
            test: /\.(js|jsx)$/,
            loader: 'babel-loader',
            query: {
                presets: ['es2015']
            },
            exclude: /node_modules/
        }]
    },
    plugins: [
        new webpack.optimize.OccurenceOrderPlugin(),
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoErrorsPlugin()
    ]
};
