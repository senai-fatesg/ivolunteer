package br.com.ambientinformatica.ivolunteer.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ambientinformatica.util.Entidade;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends Entidade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "pessoa_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "pessoa_seq", sequenceName = "pessoa_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String nomePessoa;
	private String rg;
	private String orgaoExpeditor;
	private String cpf;
	private String nascionalidade;
	private String descricao;
	private String naturalidade;
	private String profissao;
	private String certidaoNascimento;
	private String cadastroCompleto;

	@Enumerated(EnumType.STRING)
	private EnumEstadoCivil enumEstadoCivil;

	@Enumerated(EnumType.STRING)
	private EnumSexo enumSexo;

	@Enumerated(EnumType.STRING)
	private EnumTipoPessoa enumTipoPessoa;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;

	// data de vencimento criada devido o documento de cadastro de candidato ter
	// validade de 6 meses
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExpedicao;
	
	@Enumerated(EnumType.STRING)
	private EnumPrioridade enumPrioridade;

	private BigDecimal valorInicial = BigDecimal.ZERO;
	private BigDecimal valorFinal = BigDecimal.ZERO;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_id")
	private List<Endereco> listaEndereco = new ArrayList<Endereco>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_id")
	private List<Telefone> listaTelefone = new ArrayList<Telefone>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_id")
	private List<Responsavel> listaResponsavel = new ArrayList<Responsavel>();

	public Integer getIdade() {
		if (this.dataNascimento == null) {
			return 0;
		} else {
			return CalcularIdade(this.dataNascimento.toString());
		}
	}

	/**
	 * calcula a idade do candidato recebe a data de nascimento como tipo string
	 * e retorna a idade como tipo Integer
	 * **/
	private Integer CalcularIdade(String dataNascimento) {
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

		Date dataNascInput = null;
		try {

			dataNascInput = dataFormatada.parse(dataNascimento);

		} catch (Exception e) {
		}

		Calendar dateOfBirth = new GregorianCalendar();

		dateOfBirth.setTime(dataNascInput);

		// Cria um objeto calendar com a data atual

		Calendar today = Calendar.getInstance();

		// Obtendo a idade baseado no ano

		Integer idade = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, idade);

		if (today.before(dateOfBirth)) {
			idade--;
		}
		return idade;
	}

	public void addTelefone(Telefone telefone) {
		if (!this.listaTelefone.contains(telefone)) {
			this.listaTelefone.add(telefone);
		}
	}

	public void addEndereco(Endereco endereco) {
		if (!listaEndereco.contains(endereco)) {
			listaEndereco.add(endereco);
		}
	}

	public void addResponsavel(Responsavel responsavel) {
		if (!listaResponsavel.contains(responsavel)) {
			listaResponsavel.add(responsavel);
		}
	}

	public void removerEndereco(Endereco endereco) {
		if (listaEndereco.contains(endereco)) {
			this.listaEndereco.remove(endereco);
		}
	}

	public void removerTelefone(Telefone telefone) {
		if (listaTelefone.contains(telefone)) {
			this.listaTelefone.remove(telefone);
		}
	}

	public void removerResponsavel(Responsavel responsavel) {
		if (listaResponsavel.contains(responsavel)) {
			this.listaResponsavel.remove(responsavel);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpeditor() {
		return orgaoExpeditor;
	}

	public void setOrgaoExpeditor(String orgaoExpeditor) {
		this.orgaoExpeditor = orgaoExpeditor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public EnumPrioridade getEnumPrioridade() {
		return enumPrioridade;
	}

	public void setEnumPrioridade(EnumPrioridade enumPrioridade) {
		this.enumPrioridade = enumPrioridade;
	}

	public String getNascionalidade() {
		return nascionalidade;
	}

	public void setNascionalidade(String nascionalidade) {
		this.nascionalidade = nascionalidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public EnumSexo getEnumSexo() {
		return enumSexo;
	}

	public void setEnumSexo(EnumSexo enumSexo) {
		this.enumSexo = enumSexo;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public List<Endereco> getListaEndereco() {
		return listaEndereco;
	}

	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public String getCadastroCompleto() {
		return cadastroCompleto;
	}

	public void setCadastroCompleto(String cadastroCompleto) {
		this.cadastroCompleto = cadastroCompleto;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCertidaoNascimento() {
		return certidaoNascimento;
	}

	public void setCertidaoNascimento(String certidaoNascimento) {
		this.certidaoNascimento = certidaoNascimento;
	}

	public EnumEstadoCivil getEnumEstadoCivil() {
		return enumEstadoCivil;
	}

	public void setEnumEstadoCivil(EnumEstadoCivil enumEstadoCivil) {
		this.enumEstadoCivil = enumEstadoCivil;
	}
	
	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public EnumTipoPessoa getEnumTipoPessoa() {
		return enumTipoPessoa;
	}

	public void setEnumTipoPessoa(EnumTipoPessoa enumTipoPessoa) {
		this.enumTipoPessoa = enumTipoPessoa;
	}

	public List<Responsavel> getListaResponsavel() {
		return listaResponsavel;
	}

	
}