<template>
  <div id="login" class="text-center">
    <header id="top-box"> </header>
    
    <h1 id="greeting-text">WELCOME TO THE</h1>
    <img id="meal-plan-logo" src="https://cdn.discordapp.com/attachments/738847718750945341/741009920740032512/Final_Capstone_Logo_transparent.png" alt="www.google.com">
    <!-- <h3>Please sign in or register</h3> -->
    <form class="form-signin" @submit.prevent="login">
      <h3 class="sign-in-text">Please sign in</h3>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      /> 
      <br/>
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br/>
      <button type="submit" id="sign-in-button">SIGN IN</button>
      <router-link :to="{ name: 'register' }" id="new-account-link">Need an account?</router-link>
      
    </form>
    <Login />
    <footer id="bottom-box"></footer> 
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#login {
  height: fit-content;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: 900;
  position: relative;
  background-color: lightgrey;
}


.sr-only,
.title,
h2,
.greeting{
  position: relative;
  color:black;  
  font-size: 1rem;
  line-height: 0.5;
  text-align: left;
  justify-content: center;
  /* padding: 25px 25px 25px 25px; */
}

.sign-in-text,
.form-signin{
  position: relative;
  color:black;  
  font-size: 1rem;
  line-height: 0.5;
  text-align: center;
  justify-content: center;
}

#meal-plan-logo{
  max-width: 80%;
  height: 500px;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

#greeting-text{
  text-align: center;
  margin-bottom: 0px;
  font-family:Arial, Helvetica, sans-serif;
}

#new-account-link{
  display: block;
  padding: 10px;
  color: black;
  margin-top: 5px;
  margin-bottom: 30px;
}

#top-box{
  width: 100%;
  background-color: #D90B38;
  height: 50px;
}

#bottom-box{
  width: 100%;
  background-color: #26B762;
  height: 50px;
}

#sign-in-button{
  background-color:#D90B38;
  color: white;
  display: block;
  margin-top: 10px;
  margin-left: auto;
  margin-right: auto;
  padding: 5px;
  }
</style>
