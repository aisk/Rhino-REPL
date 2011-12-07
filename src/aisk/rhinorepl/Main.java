package aisk.rhinorepl;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class Main extends Activity {
	
    private RhinoVm vm;
	private TextView console;
	private EditText entry;
	Button evalButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	setContentView(R.layout.main);
    	vm = new RhinoVm();
    	
    	console = (TextView)findViewById(R.id.console);
    	entry = (EditText)findViewById(R.id.entry);
    	evalButton = (Button)findViewById(R.id.evalButton);
        evalButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				processEntry();
			}
		});

    }
    
    public void processEntry() {
    	String code = entry.getText().toString();
    	
    	if (! code.trim().equals("")) {
    		String resp = vm.eval(code);
    		console.append("\n> " + code + "\n" + resp);
    		entry.setText("");
    	}
    }
    
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	vm.exit();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);    	
    	menu.add("Reset");

    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	// I am pretty sure item.getTile().equal(x) is not the proper way
    	// to do this, but there have been worse sins committed.    	
    	if (item.getTitle().equals("Reset")) {
    		vm.exit();
    		vm = new RhinoVm();
    		console.setText("");
    		entry.setText("");
    	}
    	
    	return true;
    }
}