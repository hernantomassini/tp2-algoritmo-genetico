package com.IA.TP2;

import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.IGeneConstraintChecker;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;

public class Journey implements Gene {
	Status GoBoat;
	Status ReturnBoat;
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getUniqueID() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUniqueIDTemplate(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setUniqueIDTemplate(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void applyMutation(int arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getAllele() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getApplicationData() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double getEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getPersistentRepresentation() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isCompareApplicationData() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Gene newGene() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setAllele(Object arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setApplicationData(Object arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setCompareApplicationData(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setConstraintChecker(IGeneConstraintChecker arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setEnergy(double arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setToRandomValue(RandomGenerator arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setValueFromPersistentRepresentation(String arg0)
			throws UnsupportedOperationException, UnsupportedRepresentationException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
