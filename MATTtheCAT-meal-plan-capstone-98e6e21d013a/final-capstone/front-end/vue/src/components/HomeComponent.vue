<template>
  <div class="home">
    <hr id="hr-top" />
    <img id="meal-plan-logo" src="https://cdn.discordapp.com/attachments/738847718750945341/741009920740032512/Final_Capstone_Logo_transparent.png" alt="www.google.com">
    <!-- <h3>Please sign in or register</h3> -->
    <!-- <h1>Home</h1> -->
    <h2 id="welcome-text">Welcome back {{$store.state.user.username}}</h2>
    <!-- <h3>The user ID for {{$store.state.user.username}} is {{$store.state.user.id}}</h3>    This works but I just commented it out for looks -Jeff -->

    <hr id="hr-bottom" />

    <h2 id="like-to-do">What would you like to do today?</h2>
      <div id="nav-links">
        <div class="option-container" id="recipes-tab">
            <router-link v-bind:to="{ name: 'recipe' }" class="page-link">Recipes</router-link>
            <div class="nav-tables">
              <ul>Here you can:</ul>
                  <li>Add Recipes</li>
                  <li>View Your Recipes</li>
                  <li>Modify a Recipe</li>
            </div>
        </div>
        <br>
        <div class="option-container" id="meal-plan-tab">
            <router-link v-bind:to="{ name: 'meal-plan' }" class="page-link">Meal Plan</router-link>
            <div class="nav-tables">
              <ul>Here you can:</ul>
                  <li>Add Meal Plans</li>
                  <li>View Your Meal Plans</li>
                  <li>Modify a Meal Plan</li>
            </div>
        </div>
        <br>
        <div class="option-container" id="grocery-list-tab">
            <router-link v-bind:to="{ name: 'grocery-list' }" v-if="$store.state.token != ''" class="page-link">Grocery List</router-link>
            <div class="nav-tables">
              <ul>Here you can:</ul>
                <li>Create a grocery list</li>
            </div>
        </div>
      </div>
    <!-- <form>
     <div id="dynamicInput">
          Entry 1<br><input type="text" name="myInputs[]">
     </div>
     <input type="button" value="Add another text input" onClick="addInput('dynamicInput');">
    </form> -->
  </div>
</template>

<script>
export default {

methods:{
addInput(divName){
    let newdiv = document.createElement('div');
    document.getElementById(divName).appendChild(newdiv);
  }
}
}
</script>

<style scoped>
.option-container {
  width: 100%;
  max-width: 1100px;
  margin: 0 auto;
}

#hr-top {
  background-color:#D90B38;
  height: 20px;
  width: 80%;
}

#hr-bottom {
  background-color: #26B762;
  height: 20px;
  width: 80%;
}

.home {
  background-color: white;
  margin-top: 50px;
}

#meal-plan-logo {
  height: 300px;
  display:block;
  margin-left: auto;
  margin-right: auto;
}

#welcome-text {
  text-align: center;
}

#like-to-do {
  text-align: center;
}

#nav-links {
  display: grid;
  grid-template-columns: 1fr, 1fr, 1fr;
  grid-template-areas: "recipes meal-plan groceries";
  grid-gap: 100px;
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 40px;
}

#recipes-tab {
  grid-area: recipes;
}
#meal-plan-tab {
  grid-area: meal-plan;
}
#grocery-list-tab {
  grid-area: groceries;
}
.page-link {
  background-color:#D90B38;
  color: white;
  padding: 5px 150px 5px 20px;
  justify-content: center;
  display: block;
}

.nav-tables {
  background-color: lightgrey;
  margin-top: 15px;
  padding: 20px;
  padding-top: 15px;
  padding-left: 40px;
  height: 120px;
  text-align: left;
  border-radius: 5px  
}
</style>