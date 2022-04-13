<template>
  <Navbar
    :cartCount="cartCount"
    :onSearch="onSearch" 
    @resetCartCount="resetCartCount"
    v-if="!['Signup', 'Signin'].includes($route.name)"
  />
  <div style="min-height: 60vh">
    <router-view
      v-if="products && categories"
      :baseURL="baseURL"
      :products="products"
      :categories="categories"
      @fetchData="fetchData"
    >
    </router-view>
  </div>
  <Footer v-if="!['Signup', 'Signin'].includes($route.name)" />
</template>

<script>
import Navbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';
export default {
  data() {
    return {
      baseURL: "http://localhost:8080/",
      products: null,
      categories: null,
      key: 0,
      user: null,
      cartCount: 0,
    };
  },
  components: { Footer, Navbar },
  methods: {
    async fetchData() {
      // fetch products
      await axios
        .get(this.baseURL + 'product/')
        .then((res) => (this.products = res.data))
        .catch((err) => console.log(err));
      //fetch categories
      await axios
        .get(this.baseURL + 'category/')
        .then((res) => (this.categories = res.data))
        .catch((err) => console.log(err));
      //fetch cart items
      if (this.user) {
        await axios.get(`${this.baseURL}cart/?username=${this.user.username}`).then(
          (response) => {
            if (response.status == 200) {
              // update cart
              this.cartCount = Object.keys(response.data.cartItems).length;
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    resetCartCount() {
      this.cartCount = 0;
    },
    onLogo(data){
      localStorage.setItem('res', data.result)
      console.log('child comp res:', data)
    },
    onSearch(data){
      localStorage.setItem('res', data.result)
      this.$router.push({name: "SearchProduct"})
    }
  },
  mounted() {
    this.user = JSON.parse(localStorage.getItem('user'));
    this.fetchData();
  },
};
</script>

<style>
html {
  overflow-y: scroll;
}
</style>