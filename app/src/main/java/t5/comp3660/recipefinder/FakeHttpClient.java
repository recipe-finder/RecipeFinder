package t5.comp3660.recipefinder;

import android.os.AsyncTask;


public class FakeHttpClient extends AsyncTask<String, Void, String> {
    private OnTaskComplete listener;
    private int respId;
    public FakeHttpClient(OnTaskComplete listener, int respId) {
        this.listener = listener;
        this.respId = respId;
    }

    @Override
    protected String doInBackground(String... params){
        String json1 = "[{\"id\":718847,\"title\":\"Creamy Chicken and Wild Rice Soup\",\"image\":\"https://spoonacular.com/recipeImages/718847-312x231.jpg\",\"imageType\":\"jpg\",\"usedIngredientCount\":5,\"missedIngredientCount\":0,\"likes\":15819},{\"id\":444128,\"title\":\"Chicken on Rainbow Rice\",\"image\":\"https://spoonacular.com/recipeImages/444128-312x231.jpg\",\"imageType\":\"jpg\",\"usedIngredientCount\":5,\"missedIngredientCount\":0,\"likes\":5},{\"id\":39714,\"title\":\"Down-Home Chicken and Onions\",\"image\":\"https://spoonacular.com/recipeImages/39714-312x231.jpg\",\"imageType\":\"jpg\",\"usedIngredientCount\":5,\"missedIngredientCount\":0,\"likes\":0},{\"id\":202901,\"title\":\"Simplest Roast Chicken Ever\",\"image\":\"https://spoonacular.com/recipeImages/202901-312x231.jpg\",\"imageType\":\"jpg\",\"usedIngredientCount\":5,\"missedIngredientCount\":0,\"likes\":0},{\"id\":583617,\"title\":\"Lemon and White Wine Chicken\",\"image\":\"https://spoonacular.com/recipeImages/583617-312x231.jpg\",\"imageType\":\"jpg\",\"usedIngredientCount\":4,\"missedIngredientCount\":0,\"likes\":1038},{\"id\":54991,\"title\":\"Coffee-rubbed Roasted Chicken\",\"image\":\"https://spoonacular.com/recipeImages/54991-312x231.jpg\",\"imageType\":\"jpg\",\"usedIngredientCount\":4,\"missedIngredientCount\":0,\"likes\":184},{\"id\":578846,\"title\":\"Buffalo Chicken Wings\",\"image\":\"https://spoonacular.com/recipeImages/578846-312x231.jpg\",\"imageType\":\"jpg\",\"usedIngredientCount\":4,\"missedIngredientCount\":0,\"likes\":78},{\"id\":549653,\"title\":\"Mediterranean Vegetable Chip Crusted Chicken\",\"image\":\"https://spoonacular.com/recipeImages/549653-312x231.jpg\",\"imageType\":\"jpg\",\"usedIngredientCount\":4,\"missedIngredientCount\":0,\"likes\":73},{\"id\":540777,\"title\":\"Teriyaki Chicken\",\"image\":\"https://spoonacular.com/recipeImages/540777-312x231.jpg\",\"imageType\":\"jpg\",\"usedIngredientCount\":4,\"missedIngredientCount\":0,\"likes\":11},{\"id\":574725,\"title\":\"Whole Roast Chicken with Fenugreek\",\"image\":\"https://spoonacular.com/recipeImages/574725-312x231.jpg\",\"imageType\":\"jpg\",\"usedIngredientCount\":4,\"missedIngredientCount\":0,\"likes\":5}]";
        String json2 = "[{\"name\":\"\",\"steps\":[{\"number\":1,\"step\":\"Beat the egg in a bowl, add a pinch of salt and the flour and pour in the extra virgin olive oil and the beer: if needed, add a tablespoon of water to make a smooth but not too liquid batter. It is supposed to cover the apples, not to slide off!Peel the apples, core them paying attention not to break them and cut the apples into horizontal slices, 1 cm thick.\",\"ingredients\":[{\"id\":1034053,\"name\":\"extra virgin olive oil\",\"image\":\"olive-oil.jpg\"},{\"id\":9003,\"name\":\"apple\",\"image\":\"apple.jpg\"},{\"id\":20081,\"name\":\"all purpose flour\",\"image\":\"flour.png\"},{\"id\":14003,\"name\":\"beer\",\"image\":\"beer.jpg\"},{\"id\":2047,\"name\":\"salt\",\"image\":\"salt.jpg\"},{\"id\":1123,\"name\":\"egg\",\"image\":\"egg.jpg\"}],\"equipment\":[{\"id\":404783,\"name\":\"bowl\",\"image\":\"bowl.jpg\"}]},{\"number\":2,\"step\":\"Heat the olive oil in a large frying pan. The right moment to fry the apples is when the oil starts to smoke, as grandma says. Dip the apple slices into the batter and deep fry them until cooked through and golden on both sides.\",\"ingredients\":[{\"id\":4053,\"name\":\"olive oil\",\"image\":\"olive-oil.jpg\"},{\"id\":9003,\"name\":\"apple\",\"image\":\"apple.jpg\"}],\"equipment\":[{\"id\":404645,\"name\":\"frying pan\",\"image\":\"pan.png\"}]},{\"number\":3,\"step\":\"Transfer the apples into a plate lined with a paper towel. Sprinkle the fritters with icing sugar and serve them warm.\",\"ingredients\":[{\"id\":9003,\"name\":\"apple\",\"image\":\"apple.jpg\"}],\"equipment\":[{\"id\":405895,\"name\":\"paper towels\",\"image\":\"paper-towels.jpg\"}]}]}]";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.respId == 1 ? json1 : json2;
    }
    protected void onPostExecute(String result){
        listener.onTaskCompleteRequest(result);
    }
}
