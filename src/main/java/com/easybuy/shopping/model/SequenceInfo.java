package com.easybuy.shopping.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sequence_info database table.
 * 
 */
@Entity
@Table(name="sequence_info")
@NamedQuery(name="SequenceInfo.findAll", query="SELECT s FROM SequenceInfo s")
public class SequenceInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String name;

	@Column(name="current_value")
	private int currentValue;

	private int step;

	public SequenceInfo() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentValue() {
		return this.currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	public int getStep() {
		return this.step;
	}

	public void setStep(int step) {
		this.step = step;
	}

}