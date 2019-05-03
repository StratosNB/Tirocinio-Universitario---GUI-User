package usergui.db.dao;

import java.util.List;

import usergui.model.JavaScriptFunction;


public interface JavaScriptFunctionDao {

	public void create(JavaScriptFunction function);

	public List<JavaScriptFunction> getAll();

	public JavaScriptFunction getFunctionByName(String name);

	public void updateByName(JavaScriptFunction function, String oldName);

	public void deleteByName(String name);
}
