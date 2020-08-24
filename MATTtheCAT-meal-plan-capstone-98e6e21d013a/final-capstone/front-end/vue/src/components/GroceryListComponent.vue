<template>
  <div class="todo-list">
    <hr id="hr-top" />
    <h1 id="grocery-list-title">My Grocery List</h1>
    <hr id="hr-bottom" />
    <!-- <input class="filter" type="text" v-model="filterText" placeholder="Filter Groceries" /> -->
    <ul>
      <li
        v-for="todo in filteredTodos"
        v-bind:key="todo.name"
        v-bind:class="{ finished: todo.done }"
      >
        <input type="checkbox" v-model="todo.done" />
        {{ todo.name }}
        <span class="delete" v-on:click="deleteTodo(todo)">x</span>
      </li>
    </ul>
    <form v-on:submit.prevent="createTodo">
      <input type="text" v-model="newItem" placeholder="Add new item" />
      <button type="submit" class="btn save">Save</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      filterText: "",
      newItem: "",
      todos: [
        {
          name: "Diet Mtn Dew",
          done: false
        },
        {
          name: "Bacon",
          done: false
        },
        {
          name: "Eggs",
          done: false
        },
        {
          name: "Bread",
          done: false
        },
        {
          name: "Milk",
          done: false
        }
      ]
    };
  },
  methods: {
    deleteTodo(todoToDelete) {
      this.todos = this.todos.filter((todo) => {
        return todo !== todoToDelete;
      });
    },
    createTodo() {
      this.todos.push({
        name: this.newItem,
        done: false
      });
      this.newItem = "";
    }
  },
  computed: {
    filteredTodos() {
      return this.todos.filter((todo) => {
        return todo.name.includes(this.filterText);
      });
    }
  }
};
</script>

<style scoped>

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

#grocery-list-title{
  text-align: center
}

.todo-list {
  width: 100%;
  background: #fff;
  margin: 50px auto;
  border-radius: 10px;
}
h1 {
  /* color: #D90B38; */
  padding: 10px;
  font-size: 24px;
  text-transform: uppercase;
  text-align: center;
  margin-bottom: 20px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}
ul {
  list-style-type: none;
  margin: 0px;
  padding: 0px;
}
li {
  font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px;
}
li:last-child {
  border: 0px;
}
.finished {
  background-color: grey;
}
input[type="text"] {
  font-size: 1em;
  display: block;
  padding: 8px;
}
.delete {
  color: #d90b38;
  cursor: pointer;
}
.filter {
  margin-bottom: 20px !important;
  margin: auto;
  width: 90%;
}

form {
  display: flex;
  padding: 20px;
  margin-right: 1200px
}
form input {
  width: 80%;
  margin-right: 5px !important;
}
form .btn.save {
  font-size: 1em;
}
</style>
