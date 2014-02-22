package ast.type;

import java.util.ArrayList;
import java.util.List;

public class TypeArray implements Type {

	private Type type;
	private Integer size;

	@SuppressWarnings("unused")
	private TypeArray() {
	}

	public TypeArray(Type type, Integer size) {
		super();
		this.type = type;
		this.size = size;
	}

	@Override
	public String toString() {
		if (!(type instanceof TypeArray))
			return type + "[" + size + "]";
		else {
			Type basic = getBasicType();
			StringBuilder sb = new StringBuilder(basic.toString());
			sb.append("[").append(size).append("]");
			List<Integer> sizes = getChildArraysSize();
			for (Integer size : sizes)
				sb.append("[").append(size).append("]");
			return sb.toString();
			
		}
	}

	private Type getBasicType() {
		Type basic = this;
		while (basic instanceof TypeArray) 
			basic = ((TypeArray)basic).getType();
		return basic;
	}
	
	private List<Integer> getChildArraysSize() {
		List<Integer> ret = new ArrayList<Integer> ();
		Type basic = this.getType();
		while (basic instanceof TypeArray) {
			ret.add(((TypeArray)basic).getSize());
			basic = ((TypeArray)basic).getType();
		}
		return ret;
	}
	
	

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	

}
