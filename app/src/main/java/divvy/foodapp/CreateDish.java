package divvy.foodapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class CreateDish extends AppCompatActivity {


    private ArrayList<String> ingredients = new ArrayList();
    private CreateDish act;
    ListView ingredientsList;
    private static final int IMAGE_GALLERY_REQUEST = 220;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dish);
        ingredients.add("None");
        act = this;
        ListAdapter taskAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, ingredients) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                //view.setBackgroundColor(Color.parseColor("#000000"));
                textView.setTextColor(Color.parseColor("#FFFFFF"));

                return view;
            }
        };
        ingredientsList = (ListView) findViewById(R.id.lstIngredients);
        ingredientsList.setAdapter(taskAdapter);

        ingredientsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                if (!(ingredients.get(0).equals("None"))) {
                    AlertDialog.Builder descAlert = new AlertDialog.Builder(act);
                    descAlert.setCancelable(true);


                    descAlert.setTitle("Remove");
                    descAlert.setMessage("Do you want to remove " + ingredients.get(position) + " from the ingredients list?");
                    AlertDialog.Builder alertDialogbuilder = descAlert.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (position == 0 & ingredients.size() == 1) {
                                ingredients.clear();
                                ingredients.add("None");
                            }else {
                                ingredients.remove(position);
                            }
                            updateList();
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //This just closes the dialog box... No need to do anything here.
                        }
                    });
                    AlertDialog alertDialog = descAlert.create();
                    alertDialog.setCanceledOnTouchOutside(true);
                    alertDialog.show();
                }
            }
        });
    }

    public void cameraImg(View v) {

    }

    @Override
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        return super.checkCallingOrSelfUriPermission(uri, modeFlags);
    }

    public void galleryImg(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);
        photoPickerIntent.setDataAndType(data,"image/*");
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }


    public void addIng(View v) {
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add Ingredient")
                .setMessage("Name of ingredient:")
                .setView(taskEditText)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String toAdd = taskEditText.getText().toString();
                        if (ingredients.get(0).equals("None")) {
                            ingredients.clear();
                        }
                            ingredients.add(toAdd);

                        updateList();
                    }
                })
                .setNegativeButton("CANCEL", null)
                .create();
        dialog.show();
    }


    public void updateList() {
        runOnUiThread(new Runnable() {
            public void run() {
                ingredientsList.invalidateViews();
            }
        });
    }
}
