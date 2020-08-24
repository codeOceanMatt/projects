<template>
  <div id="app">
    <div id="nav">
        <div id ="home">
          <router-link v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''">
            <img id="home-logo" src="https://cdn.discordapp.com/attachments/738847718750945341/742442282904584192/Final_Capstone_Apple_Logo.png" alt="Home">
          </router-link>&nbsp;&nbsp;
        </div>
        <div id="links">
          <router-link v-bind:to="{ name: 'recipe' }" v-if="$store.state.token != ''" id="recipes-link">Recipes</router-link>&nbsp;&nbsp;
          <router-link v-bind:to="{ name: 'meal-plan' }" v-if="$store.state.token != ''" id="meal-plan-link">Meal Plan</router-link>&nbsp;&nbsp;
          <router-link v-bind:to="{ name: 'grocery-list' }" v-if="$store.state.token != ''" id="grocery-link">Grocery List</router-link>&nbsp;&nbsp;
        </div>
        <div id="logout">
          <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" id="logout-link">Logout</router-link>
        </div>
    </div>
    <router-view />
  </div>
</template>

<script>

export default {
}
</script>


<style scoped>

#app {
  font-family: Arial, Helvetica, sans-serif;
  font-weight: 900;
}

#nav {
  /* position: absolute; */
  padding-top: 5px;
  padding-bottom: 5px;
  background-color: lightgrey;
  display:grid;
  grid-template-columns: 70px 1fr 1fr;
  grid-template-areas: "home links logout";
  align-items: center;
  width: 100%;
  /* margin: 5px; */
}

#links {
  grid-area: links;
  align-items: center;
  text-align: left;
}

#logout {
  grid-area: logout;
  text-align: right;
  justify-content: center;
  margin-right: 40px;
}
#recipes-link, #meal-plan-link, #grocery-link {
  color: black;
  text-decoration: none;

}
#logout-link {
  color: black;
  text-decoration: none;
}
#home {
  grid-area: home;
}
#home-logo {
  height: 50px;
  padding: 5px;
  margin-left: auto;
  margin-right: auto;
}

button {
  color: #D90B38;
  transition: all 0.3s;
  position: relative;
  padding: 10px 10px 10px 10px;
  cursor: pointer
}

button::before {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
    opacity: 0;
    transition: all 0.3s;
    border-top-width: 1px;
    border-bottom-width: 1px;
    border-top-style: solid;
    border-bottom-style: solid;
    border-top-color: rgba(255,255,255,0.5);
    border-bottom-color: rgba(255,255,255,0.5);
    transform: scale(0.1, 1);
}

button:hover{
    letter-spacing: 2px;
}
button:hover::before {
    opacity: 1; 
    transform: scale(1, 1); 
}
button::after {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
    transition: all 0.3s;
    background-color: rgba(255,255,255,0.1);
}
button:hover::after {
    opacity: 0; 
    transform: scale(0.1, 1);
}

</style>