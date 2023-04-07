package com.bhavika.ContactDirectory.dao;

import java.util.List;
import com.bhavika.ContactDirectory.entity.State;

public interface StateDAO {
	
	public List<State> getStates();

	public State getState(int stateId);

	public int addState(State state);

	public int updateState(State state);

	public int deleteState(int stateId);

}
