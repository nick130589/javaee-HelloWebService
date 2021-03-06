package br.com.bancocbss.parcele.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = Cliente._TABLE_NAME)
@XmlRootElement
public class Cliente implements Serializable{

    static final String _TABLE_NAME = "TB_CLIENTE";

	@Transient
	private static final long serialVersionUID = 3060121255979066591L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private String cpf;

	private String email;

	public Cliente() {
	}

	public Cliente(Long id, String nome, String cpf, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public Cliente(String nome, String cpf, String email) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

    public Cliente(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass()))
            return false;
        Cliente o = (Cliente) obj;
        return (this.getNome().equals(o.getNome()) && this.getCpf().equals(o.getCpf()) && this.getEmail().equals(o.getEmail()));
    }

}
