<template>
  <div id="register" class="text-center">
    
    
    <form class="form-register" @submit.prevent="register">
      <hr id="hr-top" />
      <h1 class="create-account-title">Create Account</h1>
      <hr id="hr-bottom" />
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <!-- 
      <img id="meal-plan-logo" src="https://cdn.discordapp.com/attachments/738847718750945341/741009920740032512/Final_Capstone_Logo_transparent.png" alt="www.google.com">
      -->
      <div id="grid-form">
        <img id="logo" src="https://cdn.discordapp.com/attachments/738847718750945341/742442282904584192/Final_Capstone_Apple_Logo.png" alt="Home">
        <div id="sign-in-form">
          <label for="username" class="sr-only">Username: </label>
          <input
            type="text"
            id="username"
            class="form-control"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
          /> <br />
          <label for="password" class="sr-only">Password: </label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            required
          /> <br />
          <label for="confirmPassword" class="sr-only">Confirm Password: </label>
          <input
            type="password"
            id="confirmPassword"
            class="form-control"
            placeholder="Confirm Password"
            v-model="user.confirmPassword"
            required
          /> <br />
          <button class="account-button" type="submit">
            Create Account
          </button> <br />
          <p id="or-text">or</p>
          <router-link :to="{ name: 'login' }" id="login-link">Already have an account? Click Here</router-link>
        </div> <!-- End of sign-in-form id -->
      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
#hr-top {
  background-color:#D90B38;
  height: 20px;
  width: 100%;
}

#hr-bottom {
  background-color: #26B762;
  height: 20px;
  width: 100%;
}

.create-account-title {
  text-align: center;
}

#username, #password, #confirmPassword{
  margin: 5px 5px;
}

#grid-form {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "logo create-form";
  height: 80vh;
  background-color: lightgrey;
  margin: 10px 125px 10px 125px;

}

/* #meal-plan-logo {
  height: 300px;
} */

#logo {
  grid-area: logo;
  height: 400px;
  margin: auto auto;
}

#sign-in-form {
  grid-area: create-form;
  text-align: center;
  justify-content: center;
  margin: auto auto;
}

.account-button {
  margin-top: 10px;
  background-color:#D90B38;
  color: white;
  padding: 5px 10px;
}

#login-link {
  color: black;
}
</style>
