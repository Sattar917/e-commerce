module.exports = {
  parserOptions: {
    parser: "@babel/eslint-parser",
    ecmaVersion: 12,
    sourceType: 'module'
  },
  overrides: [
     {
       files: ['src/views/**/*.vue'],
       rules: {
         'vue/multi-word-component-names': 0,
       },
     },
   ],
   extends: ["plugin:vue/base"]
 }
