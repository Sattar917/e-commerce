module.exports = {
  use: {
      loader: "babel-loader",
      options: {
          plugins: [
              "@babel/plugin-syntax-dynamic-import"
          ]
      }
  }
}