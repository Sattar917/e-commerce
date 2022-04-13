<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h4 class="pt-3">Search results</h4>
      </div>
    </div>
    <div class="row">
        <div v-for="product of products" :key="product.id" class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
          <ProductBox :product="product"> </ProductBox>
        </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import ProductBox from '../../components/Product/ProductBox';
export default {
   data(){
    return {
      products: null,
      result: null
    }
  },
name: 'SearchProduct',
components : {ProductBox},
methods:{
  fetchResult(){
  axios
        .get(`http://localhost:8080/product/search/${this.result}`)
        .then((response) => (this.products = response.data))
        .catch((err) => console.log(err));
  }
},

mounted(){
     this.result = localStorage.getItem('res');
     this.fetchResult()
  }

}
</script>

<style scoped>
h4 {
  font-family: 'Roboto', sans-serif;
  color: #484848;
  font-weight: 700;
}
#add-product {
  float: right;
  font-weight: 500;
}
</style>