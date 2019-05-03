package usergui.db.dao.services;

import java.util.List;

import usergui.db.dao.impl.JavaScriptFunctionDaoImpl;
import usergui.model.JavaScriptFunction;



public class JavaScriptFunctionService {

	private final JavaScriptFunctionDaoImpl dao = new JavaScriptFunctionDaoImpl();

	public void create(JavaScriptFunction function) {
		dao.create(function);
	}

	public List<JavaScriptFunction> getAll() {
		return dao.getAll();
	}

	public JavaScriptFunction getFunctionByName(String name) {
		return dao.getFunctionByName(name);
	}

	public void update(JavaScriptFunction function, String oldName) {
		dao.updateByName(function, oldName);
	}

	public void deleteByName(String name) {
		dao.deleteByName(name);
	}
}