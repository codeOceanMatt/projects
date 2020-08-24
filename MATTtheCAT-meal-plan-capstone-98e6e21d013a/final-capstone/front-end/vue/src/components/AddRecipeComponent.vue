<template>
  <div>
    <hr id="hr-top" />
    <h1 id="add-recipe-title">Add a Recipe</h1>
    <hr id="hr-bottom" />
    <div class="form">
    <form class="recipeForm">
      <div class="field">
        <label class="recipe-name" for="recipe-name">Recipe Name</label>&nbsp;&nbsp;
        <input class="recipe-name" type="text" v-model="recipe.recipeName" />
      </div>
      <div id="ingredients-field" class="field">
        <span class="ingredients-fields">
          <label for="ingredient">Ingredient</label>&nbsp;&nbsp;
          <input type="text" v-model="ingredient.name" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label for="ingredient-quantity">Quantity</label>&nbsp;&nbsp;
          <input type="text" v-model="ingredient.quantity" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <button v-on:click.prevent="submitIngredient">Add Ingredient</button>
        </span>
      </div>
      <div class="field">
        <label class="recipe-instructions" for="recipe-instructions">Instructions</label>
        <textarea type="text" v-model="recipe.recipeInstructions" />
      </div>
      <button class="btn-submit-recipe" v-on:click="submitRecipe">Submit Recipe</button>
    </form>
    </div>
    <br />
    <button class="go-back-button" v-on:click="goBack()">Go Back</button>
  </div>
</template>

<script>
import recipeService from "../services/RecipeService";
export default {
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
      this.ingredient.name = "";
      this.ingredient.quantity = "";
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
      this.$router.push("/addrecipe/{id}/add-recipe");
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

#add-recipe-title {
  background: #f2f2f2;
  padding: 10px;
  font-size: 24px;
  text-transform: uppercase;
  text-align: center;
  margin-bottom: 20px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

.recipeForm {
  width: 450px;
  background: #fff;
  margin: 50px auto;
  font-family: "Roboto Condensed", sans-serif;
  border-radius: 10px;
  font-weight: bold;
}

.field {
  font-size: 1em;
  display: block;
  padding: 8px;
  width: 800px;
  height: 10px;
  margin-bottom: 100px;
}

.ingredients-fields {
  font-size: 1em;
  padding: 8px;
  width: 800px;
  height: 10px;
  margin-right: auto;
  margin-bottom: 20px;
}

label {
  font-size: x-large;
}

textarea {
  width: 800px;
  height: 75px
}

.recipe-instructions {
  margin-top: 200px;
}

.go-back-button{
    margin:0 auto;
    display:block;
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

#recipe-img{
    width: 20%;
    height: auto;
}
</style>