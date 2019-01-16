const { VueLoaderPlugin } = require('vue-loader');

module.exports = {
    entry: './ui/views/entry-client.js',
    output: {
        path: __dirname + '/ui/public/js',
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader'
                }
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            }
        ]
    },
    plugins: [
        new VueLoaderPlugin()
    ]
}