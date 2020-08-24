<template>
  <div id="show-recipes-page">
    <hr id="hr-top" />
    <h1 id="show-recipe-title">Here are your recipes.</h1>
    <p>Click for more details.</p>
    <hr id="hr-bottom" />
    <router-link
      id="recipe-link"
      v-bind:to="{ name: 'recipe-detail', params: { id: recipe.recipeId } }"
      class="recipe"
      v-for="recipe in this.$store.state.recipes"
      v-bind:key="recipe.recipeId"
      tag="ul"
    >{{recipe.recipeName}}</router-link>
    <!-- <button v-on:click="goBack()">Go Back</button> -->
    <img id="recipe-details-page" src="https://media.discordapp.net/attachments/738847718750945341/743534325362065498/pexels-elsa-salinas-gomez-1112674.jpg?width=769&height=513" alt="salads!">
  </div>
</template>

<script>
import recipeService from "../services/RecipeService";
export default {
  methods: {
    submitRecipe() {
      recipeService.addRecipe(this.recipe);
    },
    submitIngredient() {
      //    this.recipe.ingredients.push(this.ingredient);
      let newIngredient = {
        name: this.ingredient.name,
        quantity: this.ingredient.quantity,
      };
      this.recipe.ingredients.push(newIngredient);
    },
    newIngredient(ingredientName, ingredientQuantity) {
      this.ingredientName = ingredientName;
      this.ingredientQuantity = ingredientQuantity;
    },
    getAllRecipes() {
      recipeService.getRecipes(this.$store.state.user.id).then((response) => {
        this.$store.commit("LIST_RECIPES", response.data);
      });
    },
    addNewIngredientField(spanName) {
      let newSpan = document.createElement("span");
      document.getElementById(spanName).appendChild(newSpan);
      return newSpan;
    },
    goBack() {
      this.$router.push("/user/{id}/recipe");
    },
    seeAddRecipePage() {
      this.$router.push("/user/{id}/showrecipes");
    },
  },
};
</script>

<style>
.recipes {
  margin-top: 50px;
  margin-left: 30px;
}

#hr-top {
  background-color: #d90b38;
  height: 20px;
  width: 80%;
}

#hr-bottom {
  background-color: #26b762;
  height: 20px;
  width: 80%;
}

#recipe-title {
  text-align: center;
}

#show-recipes-page{
  background: #f2f2f2;
  padding: 10px;
  font-size: 18px;
  text-transform: uppercase;
  text-align: center;
  margin-bottom: 20px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

#recipe-link:hover {
  cursor: pointer;
  text-decoration: underline;
}

h1{
  font-size: 36px;
}

p{
  font-size: 24px;
}

#recipe-details-page{
    width: 22%;
}
</style>