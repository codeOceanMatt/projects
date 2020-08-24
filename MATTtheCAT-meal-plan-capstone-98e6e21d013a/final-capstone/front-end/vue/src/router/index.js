import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Recipe from '../views/Recipe.vue'
import RecipeDetail from '../views/RecipeDetail.vue'
import MealPlan from '../views/MealPlan.vue'
import MyMealPlans from '../views/MyMealPlans.vue'
import MealPlanCalendar from '../views/MealPlanCalendar.vue'
import GroceryList from '../views/GroceryList.vue'
import AddRecipe from '../views/AddRecipe.vue'
import ShowRecipes from '../views/ShowRecipes.vue'
import MealPlanDetails from '../views/MealPlanDetails.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/user/{id}/recipe",
      name: "recipe",
      component: Recipe,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/user/{id}/recipe/{id}",
      name: "recipe-detail",
      component: RecipeDetail,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/mealplan",
      name: "meal-plan",
      component: MealPlan,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/mealplan/{id}",
      name: "meal-plan-detail",
      component: MyMealPlans,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/mealplan/{id}/calendar",
      name: "meal-plan-calendar",
      component: MealPlanCalendar,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/grocerylist",
      name: "grocery-list",
      component: GroceryList,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/addrecipe/{id}/add-recipe",
      name: "add-recipe",
      component: AddRecipe,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:"/user/{id}/showrecipes",
      name: "show-recipes",
      component: ShowRecipes,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "mealplan/{id}/recipedetails",
      name: "meal-plan-recipe-details",
      component: MealPlanDetails,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
