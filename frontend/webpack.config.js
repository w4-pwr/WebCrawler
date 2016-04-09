//var StaticSiteGeneratorPlugin = require('static-site-generator-webpack-plugin');
//var HtmlWebpackPlugin = require('html-webpack-plugin');
module.exports = {
  entry: './index.js',

  output: {
    filename: './dist/bundle.js',
    publicPath: ''
  },

  /*plugins: [
    //new StaticSiteGeneratorPlugin('./dist/bundle.js', ['./dist'], '/')
     new HtmlWebpackPlugin()
  ],*/

  module: {
    loaders: [
      { test: /\.js$/, exclude: /node_modules/, loader: 'babel-loader?presets[]=es2015&presets[]=react' }
    ]
  }
}
