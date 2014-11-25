package com.androidexample.mvc;

import java.util.ArrayList;
import java.util.HashMap;

import com.androidexample.mvc.R.color;
import com.androidexample.mvc.R.layout;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter.LengthFilter;
import android.text.Layout.Alignment;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;

public class FirstScreen extends Activity {
	public static ArrayList<ModelProducts> productList = new ArrayList<ModelProducts>();
	
	// List view
    private ListView lv;
     
    // Listview Adapter
    ArrayAdapter<String> adapter;
     
    // Search EditText
    EditText inputSearch;
    public String search="";
     
     
    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstscreen);

		final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
		final Button secondBtn = (Button) findViewById(R.id.second);

		// Get Global Controller Class object (see application tag in
		// AndroidManifest.xml)
		final Controller aController = (Controller) getApplicationContext();

		/****************** Create Dummy Products Data ***********/

		ModelProducts productObject = null;
			//String [] names = {"unga","milk","sugar"};
		
		for (int i = 1; i <= 1000; i++) {
			int price = 10 + i;
			// Create product model class object
			productObject = new ModelProducts("Product " + i,   "Description "
					+ i, price);

			// store product object to arraylist in controller
			aController.setProducts(productObject);

		}

		/****************** Products Data Creation End ***********/

		/******* Create view elements dynamically and show on activity ******/

		// Product arraylist size
		int ProductsSize = aController.getProductsArraylistSize();

		// create the layout params that will be used to define how your
		// button will be displayed
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		// Listview Data
        String products[] = {"Unga", "Tuzo", "Mumias", "rice", "soap",
                                "Bread", "ilara",
                                "sony", "salt", "biscuits", "kiringet"};
         
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        
        
        for(int i =0; i<productList.size(); i++){
        	
        }
        String listArray [] = new String[productList.size()];
         
        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list, R.id.product_name, listArray);
        lv.setAdapter(adapter);  
        
        inputSearch.addTextChangedListener(new TextWatcher() {
            
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
            	//search=cs.toString();
//            	for ( ModelProducts s : productList) {  
//            		ModelProducts s;
//					String nameText =s.getProductName().toString();
//            		   if (!nameText) { // hardcoded, only to illustrate my logic
//            			   productList.remove(s);
//            		   }
//            		} 
            	//clear list 2...list2.clear();
            	for(ModelProducts d : productList){
                    if(d.getProductName() != null && d.getProductName().contains(search));
                       //add that item to list 2
                    adapter.notifyDataSetChanged();
                }
            	
                FirstScreen.this.adapter.getFilter().filter(cs);   
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub                          
            }

			
        });
		/******** Dynamically create view elements - Start **********/

		
		productList.add(new ModelProducts("Unga", "maize flour", 120));
		productList.add(new ModelProducts("Tuzo", "maize flour", 120));
		productList.add( new ModelProducts("jam", "maize flour", 120));
		productList.add(new ModelProducts("rice", "Dawati", 170));
		productList.add(new ModelProducts("soap", "Geisha", 120));
		productList.add( new ModelProducts("Bread", "supa loaf", 50));
		productList.add(new ModelProducts("kiwi", "maize flour", 120));
		productList.add(new ModelProducts("ilara", "maize flour", 120));
		productList.add( new ModelProducts("sony", "maize flour", 120));
		productList.add(new ModelProducts("sugar", "Dawati", 170));
		productList.add(new ModelProducts("geisha", "Geisha", 120));
		productList.add(new ModelProducts("biscuits", "supa loaf", 50));
		
		for (int j = 0; j < productList.size(); j++) {
			// Get probuct data from product data arraylist
			ModelProducts product = productList.get(j);
			
			String pName = product.getProductName();
			int pPrice = product.getProductPrice();

			// Create LinearLayout to view elemnts
			LinearLayout ll = new LinearLayout(this);
			ll.setOrientation(LinearLayout.HORIZONTAL);

			TextView productNameTextView = new TextView(this);
			productNameTextView.setText(" " + pName + " ");

			// Add textView to LinearLayout
			ll.addView(productNameTextView);
			
			
							


			TextView price = new TextView(this);
			price.setText("  Ksh" + pPrice + "     ");

			// Add textView to LinearLayout
			ll.addView(price);

			final Button btn = new Button(this);
			btn.setId(j + 1);
			btn.setText("Add To Cart");
			btn.setBackgroundResource(R.drawable.buttonstyle);
			btn.setRight(50);
			LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lp.setMargins(15, 5, 15, 5);
			btn.setLayoutParams(lp);
			btn.setTextColor(color.white);

			final Button btndelete = new Button(this);
			btndelete.setId(j + 1);
			btndelete.setText("Delete");
			btndelete.setBackgroundResource(R.drawable.buttonstyle);
			btn.setRight(50);
			LinearLayout.LayoutParams lp1= new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lp1.setMargins(15, 0, 15, 5);
			
			btn.setLayoutParams(lp1);

			// set the layoutParams on the button
			btndelete.setLayoutParams(params);

			
			final int index = j;

			// Create click listener for dynamically created button
			btn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					// Clicked button index
					Log.i("TAG", "index :" + index);

					// Get product instance for index
					ModelProducts tempProductObject = aController
							.getProducts(index);

					// Check Product already exist in Cart or Not
					if (!aController.getCart().checkProductInCart(
							tempProductObject)) {
						btn.setText("Added");

						// Product not Exist in cart so add product to
						// Cart product arraylist
						aController.getCart().setProducts(tempProductObject);

						Toast.makeText(
								getApplicationContext(),
								"Now Cart size: "
										+ aController.getCart().getCartSize(),
								Toast.LENGTH_SHORT).show();
					} else {
						// Cart product arraylist contains Product
						Toast.makeText(
								getApplicationContext(),
								"Product " + (index + 1)
										+ " already added in cart.",
								Toast. LENGTH_SHORT).show();
					}
				}
			});
			btndelete.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();


					// Clicked button index
					Log.i("TAG", "index :" + index);
					// Get product instance for index
					ModelProducts tempProductObject = aController
							.getProducts(index);  
			         
			      aController.getCart().deleteProducts(tempProductObject);
			      if (!aController.getCart().checkProductInCart(
							tempProductObject)) {
			    	  btn.setText("Add To cart");
			      }
			      

				}
			});
			
			// Add button to LinearLayout
						ll.addView(btn);
						
			// Add button to LinearLayout
			ll.addView(btndelete);

			

			// Add LinearLayout to XML layout
			lm.addView(ll);
		}

		/******** Dynamically create view elements - End **********/

		secondBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(getBaseContext(), SecondScreen.class);
				startActivity(i);
			}
		});
	}
}
