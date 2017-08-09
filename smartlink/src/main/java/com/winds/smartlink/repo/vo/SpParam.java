package com.winds.smartlink.repo.vo;

/**
 * Store procedure param.
 * @author Windreams
 *
 */
public class SpParam {

	int paramIndex;
	int sqlType;
	Object value;

	public SpParam(int paramIndex, int sqlType, Object value) {
		this.paramIndex = paramIndex;

		this.sqlType = sqlType;
		this.value = value;

	}

	public int getParamIndex() {
		return paramIndex;
	}

	public void setParamIndex(int paramIndex) {
		this.paramIndex = paramIndex;
	}

	public int getSqlType() {
		return sqlType;
	}

	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
