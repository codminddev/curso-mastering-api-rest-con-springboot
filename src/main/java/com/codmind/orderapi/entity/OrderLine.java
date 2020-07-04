package com.codmind.orderapi.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ORDER_LINES")
public class OrderLine {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="FK_ORDER", nullable = false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="FK_PRODUCT", nullable = false)
	private Product product;
	
	@Column(name="PRICE", nullable = false)
	private Double price;
	
	@Column(name="QUANTITY", nullable = false)
	private Double quantity;
	
	@Column(name="TOTAL", nullable = false)
	private Double total;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLine other = (OrderLine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
