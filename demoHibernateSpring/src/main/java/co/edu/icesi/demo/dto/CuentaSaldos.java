package co.edu.icesi.demo.dto;

import java.math.BigDecimal;

public class CuentaSaldos {

	private BigDecimal max, min, sum;
	private Double avg;
	private Long count;
	

	
	public CuentaSaldos() {
		// TODO Auto-generated constructor stub
	}

	
	public CuentaSaldos(BigDecimal max, BigDecimal min, BigDecimal sum, Double avg, Long count) {
		super();
		this.max = max;
		this.min = min;
		this.sum = sum;
		this.avg = avg;
		this.count = count;
	}





	public BigDecimal getMax() {
		return max;
	}
	public void setMax(BigDecimal max) {
		this.max = max;
	}
	public BigDecimal getMin() {
		return min;
	}
	public void setMin(BigDecimal min) {
		this.min = min;
	}
	public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	public Double getAvg() {
		return avg;
	}
	public void setAvg(Double avg) {
		this.avg = avg;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
}
