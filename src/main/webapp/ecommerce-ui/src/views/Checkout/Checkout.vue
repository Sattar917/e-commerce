<template>
  <div class="div_class">
    <h3>You will be redirected to payment page</h3>

    <div class="alert alert-primary" role="alert">
      While making payment use card number 4242 4242 4242 4242 and enter random
      cvv(3 digit)
    </div>

    <button
      class="checkout_button"
      id="proceed-to-checkout"
      @click="goToCheckout()"
    >
      Make payment
    </button>
  </div>
  
</template>

<script>


export default {
  data() {
    return {
      stripeAPIToken: 
      'pk_test_51KVKfUEglvhjigaIeOvt7ev667wjkRwUMkJBqWwDEtYG0c3agYgM3QlzHulEDrQ4Zbru4SQDAVokFWgsNGXWbps600F5seLuGD',
      stripe: '',
      user: null,
      sessionId: null,
      checkoutBodyArray: [],
    };
  },
  name: 'Checkout',
  props: ['baseURL'],
  methods: {
    /*
      Configures Stripe by setting up the elements and
      creating the card element.
    */
    configureStripe() {},
    getAllItems() {
      axios.get(`${this.baseURL}cart/?username=${this.user.username}`).then(
        (response) => {
          if (response.status == 200) {
            let products = response.data;
            let len = Object.keys(products.cartItems).length;
            for (let i = 0; i < len; i++)
              this.checkoutBodyArray.push({
                imageUrl: products.cartItems[i].product.imageURL,
                productName: products.cartItems[i].product.name,
                quantity: products.cartItems[i].quantity,
                price: products.cartItems[i].product.price,
                productId: products.cartItems[i].product.id,
                userId: products.cartItems[i].userId,
              });
          }
        },
        (err) => {
          console.log(err);
        }
      );
    },
    goToCheckout() {
      axios
        .post(
          this.baseURL + 'order/create-checkout-session',
          this.checkoutBodyArray
        )
        .then((response) => {
          localStorage.setItem('sessionId', response.data.sessionId);
          return response.data;
          
        })
        .then((session) => {
          return this.stripe.redirectToCheckout({
            sessionId: session.sessionId,
          });
        });
    },
  },
  mounted() {
    // get the token
    this.user = JSON.parse(localStorage.getItem('user'));
    // get all the cart items
    this.stripe = Stripe(this.stripeAPIToken);
    this.getAllItems();
  },
};
</script>

<style>
.alert {
  width: 50%;
}
.div_class {
  margin-top: 5%;
  margin-left: 30%;
}
.checkout_button {
  background-color: #5d3dec;
  border: none;
  color: white;
  margin-left: 15%;
  padding: 15px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 15px;
  font-weight: bold;
  border-radius: 15px;
}
.checkout_button:focus {
  outline: none;
  box-shadow: none;
}
.checkout_button:disabled {
  background-color: #9b86f7;
  border: none;
  color: white;
  margin-left: 15%;
  padding: 15px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 15px;
  font-weight: bold;
  border-radius: 15px;
  cursor: not-allowed;
}
</style>

<!-- <template>
  <div class="div_class">
    <h3>You will be redirected to payment page</h3>
    <div class="alert alert-primary">
      While making payment use card number 4242 4242 4242 4242 and enter random
      date and cvv (3 digit)
    </div>

    <button class="btn btn-primary" @click="goToCheckout">Make Payment</button>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      stripeAPIToken: '<stripe token>',
      stripe: '',
      token: null,
      checkoutBodyArray: [],
    };
  },
  name: 'Checkout',
  props: ['baseURL'],
  methods: {
    getAllItems() {
      axios
        .get(`${this.baseURL}cart/?token=${this.token}`)
        .then((response) => {
          if (response.status == 200) {
            let products = response.data;
            for (let i = 0; i < products.cartItems.length; i++) {
              this.checkoutBodyArray.push({
                price: products.cartItems[i].product.price,
                quantity: products.cartItems[i].quantity,
                productName: products.cartItems[i].product.name,
                productId: products.cartItems[i].product.id,
              });
            }
          }
        })
        .catch((err) => console.log(err));
    },
    goToCheckout() {
      console.log('checkoutBodyArray', this.checkoutBodyArray);
      axios
        .post(
          `${this.baseURL}order/create-checkout-session`,
          this.checkoutBodyArray
        )
        .then((response) => {
          localStorage.setItem('sessionId', response.data.sessionId);
          console.log('session', response.data);
          this.stripe.redirectToCheckout({
            sessionId: response.data.sessionId,
          });
        })
        .catch((err) => console.log(err));
    },
  },
  mounted() {
    this.token = localStorage.getItem('token');
    this.stripe = window.Stripe(this.stripeAPIToken);
    this.getAllItems();
  },
};
</script>
<style scoped>
.alert {
  width: 50%;
}
.div_class {
  margin-top: 5%;
  margin-left: 30%;
}
.checkout_button {
  background-color: #5d3dec;
  margin: 10px;
}
</style> -->