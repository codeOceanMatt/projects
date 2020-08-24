<template>
  <div class="recipes">
    <hr id="hr-top" />
    <h1 id="recipe-title">Recipes</h1>
    <h3 id="like-to-do">What would you like to do?</h3>
    <hr id="hr-bottom" />
    <div class="recipe-button">
    <button v-on:click="showAddRecipeForm = !showAddRecipeForm, seeAddRecipePage()" id="add-recipes-tab">Add Recipe</button>
    <add-recipe-component v-show="showAddRecipeForm" />
    <button v-on:click="showMyRecipes = !showMyRecipes, seeShowMyRecipesPage(), getAllRecipes()" id="see-recipe-tab">See Your Recipes</button>
    <show-recipes-component v-show="showMyRecipes" />
    </div>
    <img id="ham-sandwich" src="https://media.discordapp.net/attachments/738847718750945341/743536351017828412/PIXNIO-347649-3328x2219.jpeg?width=769&height=513" alt="club sandwich">
    <!-- <button v-on:click="goBack()" class="home-button">Return to Home</button> -->
  </div>
</template>

<script>
import AddRecipeComponent from "@/components/AddRecipeComponent";
import ShowRecipesComponent from "@/components/ShowRecipesComponent"
import recipeService from "../services/RecipeService";

export default {
  components: {
    AddRecipeComponent,
    ShowRecipesComponent
  },
  name: "recipe",
  data() {
    return {
      recipe: {
        username: this.$store.state.user.username,
        recipeId: "",
        recipeName: "",
        ingredients: [],
        recipeInstructions: "",
      },
      ingredient: {
        name: "",
        quantity: "",
      },
      showAddRecipeForm: false,
      showGetRecipesList: false,
      showMyRecpes: false
    };
  },
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
      this.$router.push("/");
    },
    seeAddRecipePage() {
      this.$router.push("/addrecipe/{id}/add-recipe");
    },
    seeShowMyRecipesPage(){
        this.$router.push("/user/{id}/showrecipes")
    }
  },
};
</script>

<style>

#hr-top {
  background-color: #d90b38;
  height: 20px;
  width: 100%;
}

#hr-bottom {
  background-color: #26b762;
  height: 20px;
  width: 100%;
}

#recipe-title,
.home-button {
  text-align: center;
}

#like-to-do {
    font-size: 24px;
    text-align: center
}

.recipe-button{
  display: grid;
  grid-template-columns: 1fr, 1fr;
  grid-template-areas: "add-recipes see-recipe";
  grid-gap: 100px;
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 40px;
}

#add-recipes-tab {
  grid-area: add-recipes;
  font-size: 2rem;
  border: 3px solid darkgrey;
  background-color: lightgrey;
  margin-top: 15px;
  padding: 20px;
  padding-top: 15px;
  padding-left: 40px;
  height: 120px;
  text-align: center;
  border-radius: 5px;
}
#see-recipe-tab {
  grid-area: see-recipe;
  font-size: 2rem;
  border: 3px solid darkgrey;
  background-color: lightgrey;
  margin-top: 15px;
  padding: 20px;
  padding-top: 15px;
  padding-left: 15px;
  height: 120px;
  text-align: center;
  border-radius: 5px;
}

#ham-sandwich{
    margin-top: 50px;
    width: 25%;
    margin-left: 37.5%;
    border: 3px solid darkgrey;
}
</style>