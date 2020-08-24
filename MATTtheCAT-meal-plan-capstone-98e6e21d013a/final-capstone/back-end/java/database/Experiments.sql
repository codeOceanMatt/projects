begin transaction;

DELETE FROM recipe_ingredients
WHERE ingredient_id = (select ingredient_id from ingredients WHERE ingredient_name = 'Sweet Potato')

rollback;