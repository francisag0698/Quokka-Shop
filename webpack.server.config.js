var webpack = require('webpack')
var merge = require('webpack-merge')
var baseWebpackConfig = require('./webpack.config')
var webpackConfig = merge(baseWebpackConfig, {
    target: 'node',
    entry: {
        app: './ui/views/entry-client.js'
    },
    devtool: false,
    output: {
        path: __dirname + '/ui/public/js',
        filename: 'server.bundle.js',
        libraryTarget: 'commonjs2'
    },
    externals: Object.keys(require('./package.json').dependencies),
    plugins: [
        new webpack.DefinePlugin({
            'process.env': 'production'
        })
    ],
    optimization: {
        minimize: false
    }
})
module.exports = webpackConfig