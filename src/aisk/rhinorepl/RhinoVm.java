package aisk.rhinorepl;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.EcmaError;

public class RhinoVm {
	Context cx = null;
	Scriptable scope = null;
	
	RhinoVm() {
		cx = Context.enter();
    	cx.setOptimizationLevel(-1);
    	scope = cx.initStandardObjects();
    	ScriptableObject.putConstProperty(scope, "XXX", Context.javaToJS(this, scope));
	}
	
	public String eval(String code) {
		Object result = null;
		String ret;
		try {
			result = cx.evaluateString(scope, code, "<RhinoShell>", 1, null);
			ret = Context.toString(result);
		} catch (EvaluatorException e) {
			ret = e.getMessage();
		} catch (EcmaError e) {
			ret = e.getMessage();
		}
    	return ret;
	}
	
	public void exit() {
		Context.exit();
	}

}
