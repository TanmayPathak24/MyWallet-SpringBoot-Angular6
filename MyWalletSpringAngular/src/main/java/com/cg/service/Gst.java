package com.cg.service;

/**
 * Author: Tanmay Pathak
 * */
public interface Gst {
	double PCT_5=0.05;
	double PCT_12=0.12;
	double PCT_18=0.18;
	
	public double calculateTax (double PCT,double amount);
}