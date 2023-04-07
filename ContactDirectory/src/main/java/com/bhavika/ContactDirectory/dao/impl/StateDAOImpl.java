package com.bhavika.ContactDirectory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bhavika.ContactDirectory.DbConnection;
import com.bhavika.ContactDirectory.dao.StateDAO;
import com.bhavika.ContactDirectory.entity.Contact;
import com.bhavika.ContactDirectory.entity.State;


@Component
public class StateDAOImpl implements StateDAO{

	public StateDAOImpl(){
	}
	
	DbConnection DbCon = new DbConnection();
	Connection conn = DbCon.getConn();
	
	@Override
	public List<State> getStates() {

		List<State> theStates = new ArrayList<>();
		try {
			String query = "SELECT * FROM state";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				State state = null;
				state = new State();
				state.setStateName(rs.getString("stateName"));
				state.setStateId(rs.getInt("stateId"));
				theStates.add(state);
				System.out.println(state);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return theStates;
	}

	@Override
	public State getState(int stateId) {

		State state = null;
		try {
			String query = "SELECT * FROM state where stateId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, stateId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				state = new State();
				state.setStateName(rs.getString("stateName"));
				state.setStateId(rs.getInt("stateId"));
				return state;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return state;
		
	}

	@Override
	public int addState(State state) {
		try {
			String query = "INSERT INTO state (stateName) VALUES (?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, state.getStateName());
			int rs = stmt.executeUpdate();
			System.out.println(rs);
			return rs;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public int updateState(State state) {
		try {
			String query = "UPDATE state SET stateName = ? WHERE stateId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, state.getStateName());
			stmt.setInt(2, state.getStateId());
			int rs = stmt.executeUpdate();
			System.out.println(rs);
			return rs;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public int deleteState(int stateId) {
		try {
			String query = "DELETE FROM state WHERE stateId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, stateId);
			int rs = stmt.executeUpdate();
			return rs;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	

}
