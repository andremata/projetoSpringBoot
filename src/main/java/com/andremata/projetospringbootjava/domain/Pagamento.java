package com.andremata.projetospringbootjava.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.andremata.projetospringbootjava.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity

//Essa anotação é usada para fazer a junção da super classe com as classes herdadas
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
	//Abstract usado para não poder fazer instancia dessa classe, fazer instancia das classes herdadas
	
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer estado;

    @JsonBackReference
    @JoinColumn(name = "PEDIDO_ID")
    @OneToOne
    @MapsId
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getId();
		this.pedido = pedido;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstadoPagamento() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstadoPagamento(EstadoPagamento estado) {
        this.estado = estado.getId();
    }

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
        Pagamento other = (Pagamento) obj;
        if (id == null) {
                if (other.id != null)
                        return false;
        } else if (!id.equals(other.id))
                return false;
        return true;
    }
}
