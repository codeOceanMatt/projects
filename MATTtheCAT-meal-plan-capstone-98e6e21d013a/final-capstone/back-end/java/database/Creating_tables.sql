begin transaction;
drop table if exists recipes cascade;
drop table if exists ingredients cascade;
drop table if exists meal_category cascade;
drop table if exists recipe_ingredients cascade;
drop table if exists user_recipes cascade;
drop table if exists meal_plan cascade;
drop table if exists meal_plan_recipe cascade;

CREATE TABLE recipes (
        recipe_id serial,
        recipe_name varchar(64) NOT NULL,
        instructions text,
        CONSTRAINT pk_recipe_recipe_id PRIMARY KEY (recipe_id)
 );
 

CREATE TABLE ingredients (
        ingredient_id serial,
        ingredient_name varchar(64) NOT NULL,
        CONSTRAINT pk_ingredient_ingredient_id PRIMARY KEY (ingredient_id)
);

CREATE TABLE recipe_ingredients (
        id serial,
        ingredient_id integer,
        recipe_id integer,
        ingredient_quantity varchar(64),
        CONSTRAINT pk_recipe_ingredients_id PRIMARY KEY (id)
);

CREATE TABLE user_recipes (
        id serial,
        user_id integer,
        recipe_id integer,
        CONSTRAINT pk_user_recipes_id PRIMARY KEY (id)
);

CREATE TABLE meal_plan_recipe (
        id serial,
        recipe_id integer,
        meal_plan_id integer,
        meal_category varchar(64),
        day_of_week varchar(64),
        CONSTRAINT pk_meal_plan_recipe_id PRIMARY KEY (id)
);

CREATE TABLE meal_plan (
        plan_id serial,
        user_id integer,
        plan_name varchar(64),
        CONSTRAINT pk_meal_plan_plan_id PRIMARY KEY (plan_id)
);
        
        
ALTER TABLE recipe_ingredients ADD FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id);       
ALTER TABLE recipe_ingredients ADD FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id);
ALTER TABLE user_recipes ADD FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE user_recipes ADD FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id);

ALTER TABLE meal_plan_recipe ADD FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id);
ALTER TABLE meal_plan_recipe ADD FOREIGN KEY (meal_plan_id) REFERENCES meal_plan(plan_id);
ALTER TABLE meal_plan ADD FOREIGN KEY (user_id) REFERENCES users(user_id);

Grant all on table recipes to public;
Grant all on table ingredients to public;
Grant all on table recipe_ingredients to public;
Grant all on table user_recipes to public;
Grant all on table users to public;
Grant all on table meal_plan_recipe to public;
Grant all on table meal_plan to public;

Grant all on sequence ingredients_ingredient_id_seq to public;
Grant all on sequence recipe_ingredients_id_seq to public;
Grant all on sequence recipes_recipe_id_seq to public;
Grant all on sequence seq_user_id to public;
Grant all on sequence user_recipes_id_seq to public;
Grant all on sequence meal_plan_recipe_id_seq to public;
Grant all on sequence meal_plan_plan_id_seq to public;


commit;