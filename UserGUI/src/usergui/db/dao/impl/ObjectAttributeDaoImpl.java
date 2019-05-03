package usergui.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usergui.db.ConnectToDatabase;
import usergui.db.dao.AttributeDao;
import usergui.model.Attribute;

public class ObjectAttributeDaoImpl implements AttributeDao {

	private final Connection conn = ConnectToDatabase.createConnection();
	private final String SQL_CREATE_ATTRIBUTE = "INSERT INTO objects_attributes (name, type) VALUES (?,?)";
	private final String SQL_GET_ALL_ATTRIBUTES = "SELECT * FROM objects_attributes ORDER BY id";
	private final String SQL_UPDATE_ATTRIBUTE = "UPDATE objects_attributes SET name=?, type=? WHERE name=?";
	private final String SQL_DELETE_ATTRIBUTE = "DELETE FROM objects_attributes WHERE name=?";

	@Override
	public void createAttribute(Attribute attribute) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE_ATTRIBUTE, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, attribute.getName());
			pstmt.setString(2, attribute.getType());
			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					attribute.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void createAttributes(List<Attribute> attributes) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Attribute> getAttributesById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attribute> getAllAttributes() {
		List<Attribute> allAttributes = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_ATTRIBUTES);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Attribute attribute = new Attribute();
				attribute.setId(rs.getInt(1));
				attribute.setName(rs.getString(2).trim());
				attribute.setType(rs.getString(4).trim());
				allAttributes.add(attribute);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return allAttributes;
	}

	@Override
	public void updateAttribute(Attribute attribute, String oldName) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_ATTRIBUTE)) {
			pstmt.setString(1, attribute.getName());
			pstmt.setString(2, attribute.getType());
			pstmt.setString(3, oldName);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updateAttributesValuesById(List<Attribute> attributes, String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAttribute(String attributeName) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_ATTRIBUTE)) {
			pstmt.setString(1, attributeName);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public HashMap<String, String> getAllAttributesAndTypes() {
		HashMap<String, String> map = new HashMap<String, String>();

		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_ATTRIBUTES);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {

				switch (rs.getString(4).trim()) {

				case "String":
					map.put(rs.getString(2).trim(), String.class.getSimpleName());
					break;

				case "Integer":
					map.put(rs.getString(2).trim(), Integer.class.getSimpleName());
					break;

				case "Double":
					map.put(rs.getString(2).trim(), Double.class.getSimpleName());
					break;

				case "Boolean":
					map.put(rs.getString(2).trim(), Boolean.class.getSimpleName());
					break;

				case "Date":
					map.put(rs.getString(2).trim(), String.class.getSimpleName());
					break;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return map;
	}
}