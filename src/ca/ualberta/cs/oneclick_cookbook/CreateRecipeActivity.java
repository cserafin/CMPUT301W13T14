package ca.ualberta.cs.oneclick_cookbook;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Class that acts as the controller for the create recipe screen If the current
 * recipe variable in GlobalApplication is set, does not create a new recipe,
 * rather edits the current one. Is responsible for unsetting the current recipe
 * for future instances to work correctly.
 * 
 * @author Kenneth Armstrong
 * 
 */
public class CreateRecipeActivity extends Activity {

	private int position = -1;
	private int imagePos = 0;
	private Toast toast = null;
	private Uri imageFileUri;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_recipe);
		Bundle extras = getIntent().getExtras();
		// Set the position if we are editing
		if (extras != null) {
			position = extras.getInt("position");
		}
	}

	// Called when the app resumes
	public void onResume() {
		super.onResume();
		GlobalApplication app = (GlobalApplication) getApplication();
		Recipe recipe = app.getCurrentRecipe();
		setInfo(recipe); // Set the info they entered before leaving
		setImage(recipe);
	}

	/**
	 * Overides the back key action. Kills the current activity, as normal, but
	 * also sets the current recipe to null so that future recipes work
	 * properly.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		super.onKeyDown(keyCode, event);
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			GlobalApplication app = (GlobalApplication) getApplication();
			app.setCurrentRecipe(null);
			finish();
			return true;
		}
		return false;
	}

	/**
	 * Function that takes the info of the recipe being edited and puts in into
	 * the text fields. This is used to restore any info the user may have had
	 * prior to leaving the activity.
	 * 
	 * @param recipe
	 *            The recipe to set the info for
	 */
	public void setInfo(Recipe recipe) {
		EditText name = (EditText) findViewById(R.id.createEnterName);
		EditText steps = (EditText) findViewById(R.id.createEnterSteps);

		name.setText(recipe.getName());
		steps.setText(recipe.getSteps());
	}

	/**
	 * Function that sets the image for the recipe.
	 * 
	 * @param recipe
	 *            The recipe that it currently being viewed
	 */
	
	public void setImage(Recipe recipe) {
		// If the recipe has images, show the first one
		if (recipe.getNumImages() > imagePos) {
			ImageButton button = (ImageButton) findViewById(R.id.iCreateImage);
			Drawable d = new BitmapDrawable(getResources(),
					Bitmap.createScaledBitmap(recipe.getImage(imagePos), 300,
							250, true));
			button.setImageDrawable(d);
		} else {
			// If the recipe doesn't have images, show the alert_frame
			ImageButton button = (ImageButton) findViewById(R.id.iCreateImage);
			button.setImageDrawable(getResources().getDrawable(
					android.R.drawable.alert_light_frame));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_recipe, menu);
		return true;
	}

	/**
	 * Function that handles the clicks from a user. Calls the appropriate
	 * function depending on the buton the user clicked.
	 * 
	 * @param v
	 *            The view of the button that was clicked
	 */
	public void clickHandler(View v) {
		switch (v.getId()) {
		case R.id.bCreateDone:
			onDone();
			break;
		case R.id.bCreateDelete:
			onDelete();
			break;
		case R.id.bCreateAddIngredient:
			onAddIngredients();
			break;
		case R.id.bCreateAddPhoto:
			onAddPhoto();
			break;
		case R.id.bCreateRemovePhoto:
			onRemovePhoto();
			break;
		case R.id.iCreateImage:
			onNextPhoto();
			break;
		}
	}

	/**
	 * Function that is called when the user clicks on the add ingredients
	 * button.
	 */
	public void onAddIngredients() {
		GlobalApplication app = (GlobalApplication) getApplication();
		saveInfo(); // Do this to save any text they may have entered
		Intent intent = new Intent(app, AddIngredientsActivity.class);
		startActivity(intent);
	}

	/**
	 * Function that is called when the user clicks on the done button of the
	 * recipe. Exits the create recipe activity.
	 */
	public void onDone() {
		EditText name;
		EditText steps;

		// Get the info the user entered
		name = (EditText) findViewById(R.id.createEnterName);
		steps = (EditText) findViewById(R.id.createEnterSteps);
		String namestring = name.getText().toString();
		String stepstring = steps.getText().toString();
		GlobalApplication app = (GlobalApplication) getApplication();

		Recipe r = app.getCurrentRecipe();
		r.changeName(namestring);
		r.changeSteps(stepstring);

		// If they entered invalid info
		if (r.isValidInfo() != Constants.GOOD) {
			showMessage("Invalid info entered");
			return;
		}

		NetworkHandler nh = new NetworkHandler();

		// Attempt posting to ES
		try {
			nh.postToES(r);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// If it's a new recipe, add it to the user recipe list
		if (position == -1) {
			app.getCurrentUser().addRecipe(r.getID());
		}

		// Set null so future recipes start fresh
		app.setCurrentRecipe(null);
		finish();
		return;
	}

	/**
	 * Function that is called when the user clicks on the delete button of the
	 * recipe.
	 */
	public void onDelete() {
		// If the user is not editing, just delete
		if (position == -1) {
			GlobalApplication app = (GlobalApplication) getApplication();
			app.setCurrentRecipe(null);
			finish();
			return;
		}
		// Builds the alert dialog box
		AlertDialog.Builder prompt = new AlertDialog.Builder(this);
		prompt.setTitle("Delete All");
		prompt.setMessage("Are you sure you want to delete this recipe? It "
				+ "will be gone... forever.");

		prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {

			// User has changed their mind
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			// User does want to delete this recipe
			@Override
			public void onClick(DialogInterface dialog, int which) {
				GlobalApplication app = (GlobalApplication) getApplication();
				NetworkHandler nh = new NetworkHandler();

				try {
					nh.deleteRecipe(app.getCurrentRecipe().getID());
				} catch (IOException e) {
					e.printStackTrace();
				}

				app.getCurrentUser().getUserRecipes().remove(position);

				// Set null so future recipes start fresh
				app.setCurrentRecipe(null);
				finish();
			}
		});
		prompt.show();
		return;
	}

	/**
	 * Function that displays a message to the user in the current activity
	 * 
	 * @param message
	 *            The message to display
	 */
	public void showMessage(String message) {
		// If there is a toast already, get rid of it
		if (toast != null) {
			toast.cancel();
		}
		toast = Toast.makeText(getApplicationContext(), message,
				Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * Function that is called when the user clicks on the add photo button.
	 * Derived from the CameraDemo on eClass.
	 */
	public void onAddPhoto() {
		saveInfo(); // Do this to save and info the user may have entered
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		// Get the path to the folder
		String folder = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/oneclick_cookbook";

		// Make the folder if it doesn;t already exist
		File folderF = new File(folder);
		if (!folderF.exists()) {
			folderF.mkdir();
		}

		// Get the path to the image
		String imageFilePath = folder + "/"
				+ String.valueOf(System.currentTimeMillis()) + "jpg";
		File imageFile = new File(imageFilePath);
		imageFileUri = Uri.fromFile(imageFile);

		// Start the camera activity
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

		return;
	}

	/**
	 * Function that captures the return value from the camera. Also handles
	 * image conversion, etc.
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// If it's the camera returning
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				GlobalApplication app = (GlobalApplication) getApplication();
				Recipe r = app.getCurrentRecipe();
				ImageButton button = (ImageButton) findViewById(R.id.iCreateImage);

				// Convert the JPEG to bitmap
				r.addImage(BitmapFactory.decodeFile(imageFileUri.getPath()));
				// Go to the last photo
				imagePos = r.getNumImages() - 1;
				
				// Set the image
				Drawable d = new BitmapDrawable(getResources(),
						Bitmap.createScaledBitmap(r.getImage(imagePos), 300,
								250, true));
				button.setImageDrawable(d);
				
				// Delete the old JPEG
				File f = new File(imageFileUri.getPath());
				f.delete();
			} else if (resultCode == RESULT_CANCELED) {
			} else {
			}
		}
	}

	/**
	 * Function that is called when the user clicks on the remove photo button.
	 */
	public void onRemovePhoto() {
		GlobalApplication app = (GlobalApplication) getApplication();
		Recipe r = app.getCurrentRecipe();
		r.deleteImage(imagePos);

		// make sure we don't go off the end when deleting
		if (imagePos >= r.getNumImages()) {
			if (imagePos > 0) {
				imagePos--;
			}
		}

		// If there are images to show, show one
		if (!(r.getNumImages() == 0)) {
			ImageButton button = (ImageButton) findViewById(R.id.iCreateImage);
			Drawable d = new BitmapDrawable(getResources(),
					Bitmap.createScaledBitmap(r.getImage(imagePos), 300, 250,
							true));
			button.setImageDrawable(d);
		} else {
			// If no images, display alert frame
			ImageButton button = (ImageButton) findViewById(R.id.iCreateImage);
			button.setImageDrawable(getResources().getDrawable(
					android.R.drawable.alert_light_frame));
		}
		return;
	}

	/**
	 * Function that is called when the user clicks on the image button.
	 * Cycles through the photo's in the recipe.
	 */
	public void onNextPhoto() {
		GlobalApplication app = (GlobalApplication) getApplication();
		Recipe r = app.getCurrentRecipe();
		
		// Go to the next image position
		imagePos++;
		
		// If we are at the end, go back to start
		if (imagePos >= r.getNumImages()) {
			imagePos = 0;
		}
		// Otherwise, display image
		if (!(imagePos == r.getNumImages())) {
			ImageButton button = (ImageButton) findViewById(R.id.iCreateImage);
			Drawable d = new BitmapDrawable(getResources(),
					Bitmap.createScaledBitmap(r.getImage(imagePos), 300, 250,
							true));
			button.setImageDrawable(d);
		}

	}

	/**
	 * Function that saves the users text that they have entered.
	 */
	public void saveInfo() {
		EditText name = (EditText) findViewById(R.id.createEnterName);
		EditText steps = (EditText) findViewById(R.id.createEnterSteps);
		GlobalApplication app = (GlobalApplication) getApplication();

		// Do this to save any user text they may have
		app.getCurrentRecipe().changeName(name.getText().toString());
		app.getCurrentRecipe().changeSteps(steps.getText().toString());
	}
}
