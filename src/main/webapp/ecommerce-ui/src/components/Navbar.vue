<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <!--    Logo-->
    <router-link class="navbar-brand" :to="{ name: 'Home' }">
      <img id="logo" src="../assets/icon.png" />
    </router-link>

    <!--    Burger Button-->
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <!--      Search Bar-->
      <form class="form-inline ml-auto mr-auto">
        <div class="input-group">
          <input
            aria-label="Username"
            aria-describedby="basic-addon1"
            size="100"
            type="text"
            class="form-control"
            placeholder="Search products"
            v-model="result"
          />
          <button @click="search" :disabled="isDisabled()" id="search-button-navbar">
            <span class="input-group-text" id="search-button-navbar">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-search"
                viewBox="0 0 16 16"
              >
                <path
                  d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
                />
              </svg>
            </span>
          </button>
          
        </div>
      </form>

      <!-- DropDowns-->
      <ul class="navbar-nav ">
        <li class="nav-item dropdown">
          <a
            class="nav-link text-light dropdown-toggle"
            href="#"
            id="navbarDropdown"
            role="button"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            Browse
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <router-link class="dropdown-item" :to="{ name: 'Home' }"
              >Home</router-link
            >
            <router-link class="dropdown-item" :to="{ name: 'Product' }"
              >Product</router-link
            >
            <router-link class="dropdown-item" :to="{ name: 'Category' }"
              >Category</router-link
            >
          </div>
        </li>

        <li class="nav-item dropdown">
          <a
            class="nav-link text-light dropdown-toggle"
            href="#"
            id="navbarDropdown"
            role="button"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            Accounts
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <router-link
              class="dropdown-item"
              v-if="!user"
              :to="{ name: 'Signin' }"
              >Wishlist</router-link
            >
            <router-link class="dropdown-item" v-else :to="{ name: 'Wishlist' }"
              >Wishlist</router-link
            >
            <router-link class="dropdown-item" v-if="role == true" :to="{ name: 'Admin' }"
              >Admin</router-link
            >
            <router-link
              class="dropdown-item"
              v-if="!user"
              :to="{ name: 'Signin' }"
              >Log In</router-link
            >
            <router-link
              class="dropdown-item"
              v-if="!user"
              :to="{ name: 'Signup' }"
              >Sign Up</router-link
            >
            <a class="dropdown-item" v-if="user" href="#" @click="signout"
              >Sign Out</a
            >
          </div>
        </li>

        <li class="nav-item">
          <router-link class="nav-link text-light" :to="{ name: 'Order' }"
            >Orders</router-link
          >
        </li>
        <li class="nav-item">
          <div id="cart">
            <span id="nav-cart-count">{{ cartCount }}</span>
            <router-link class="text-light" :to="{ name: 'Cart' }"
              ><i class="fa fa-shopping-cart" style="font-size:36px"></i
            ></router-link>
          </div>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import axios from 'axios';

export default {
  name: "Navbar",
  props: ["cartCount","onLogo","onSearch"],
  data() {
    return {
      user: null,
      result: '',
      role: null,
    };
  },
  methods: {
    signout() {
      this.$store.dispatch('auth/logout');
      this.$emit("resetCartCount");
      this.$router.push({ name: "Home" });
      swal({
        text: "Logged you out. Visit Again",
        icon: "success",
        closeOnClickOutside: false,
      });
    },
  
    logo(){
      this.onLogo({
        result: this.result
      })
     
  },
    isDisabled(){
      if (this.result.length === 0) {
        return true;
      }
      if (this.result.startsWith(" ")){
        return true;
      }
      return false;
    
    },
    search() {
      this.onSearch({
        result: this.result
      })
      
    },
  
    getRole(){
      if(localStorage.getItem("user") == null){
        return false;
      }
      var temp = JSON.parse(localStorage.getItem("user"));
      var local = JSON.stringify(temp.roles);
      if(local == '["ROLE_ADMIN"]'){
        return true;
      }
        return false;
    }
  },

  mounted() {
    this.user = localStorage.getItem("user");

    this.role = this.getRole();
  },
};
</script>

<style scoped>
#logo {
  height: 50px;
  width: 90px;
  margin-left: 20px;
  margin-right: 20px;
}
.input-group{
  margin-right: 20px;
}
.nav-link {
  color: rgba(255, 255, 255);
}
.navbar-nav{
  margin-top: 10px;
}
#search-button-navbar {
  background-color: #f3f709;
  border-color: none;
  border-top-right-radius: 2px;
  border-bottom-right-radius: 2px;
  border: #f3f709;
  
}

#nav-cart-count {
  background-color: red;
  color: white;
  border-radius: 50%;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 15px;
  height: 15px;
  font-size: 15px;
  margin-left: 10px;
}
#cart {
  position: relative;
}
</style>